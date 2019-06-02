package by.epam.club.pool;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.*;
import java.util.Enumeration;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import static by.epam.club.pool.DBResourceManager.*;


public class ConnectionPool {
    private static final Logger LOGGER = LogManager.getLogger(ConnectionPool.class);

    private static ConnectionPool instance;
    private static Lock lock = new ReentrantLock();
    private BlockingQueue<ConnectionProxy> pool;
    private static AtomicBoolean isCreatePool = new AtomicBoolean(false);

    private String driverName;
    private String url;
    private String login;
    private String password;
    private int poolSize;

    private ConnectionPool() {
        DBResourceManager dbResourceManager = DBResourceManager.getInstance();
        this.driverName = dbResourceManager.getValue(DB_DRIVER);
        this.url = dbResourceManager.getValue(DB_URL);
        this.login = dbResourceManager.getValue(DB_LOGIN);
        this.password = dbResourceManager.getValue(DB_PASSWORD);
        this.poolSize = Integer.parseInt(dbResourceManager.getValue(DB_POOL_SIZE));
        initialize();
    }

    private void initialize() {
        try {
            Class.forName(driverName);
            pool = new ArrayBlockingQueue<>(poolSize);
            for (int i = 0; i < poolSize; i++) {
                try {
                    ConnectionProxy connectionProxy = new ConnectionProxy(DriverManager.getConnection(url, login, password));
                    pool.put(connectionProxy);
                } catch (InterruptedException e) {
                    //
                    Thread.currentThread().interrupt();
                }
            }
        } catch (SQLException e) {
            //
            throw new RuntimeException(e);

        } catch (ClassNotFoundException e){

            //
            throw new RuntimeException(e);
        }

    }

    public static ConnectionPool getInstance() {
        if (!isCreatePool.get()) {
            try {
                lock.lock();

                if (instance == null) {
                    instance = new ConnectionPool();
                    isCreatePool.set(true);
                }
            } finally {
                lock.unlock();
            }
        }
        return instance;
    }

    public Connection takeConnection() {
        ConnectionProxy connection = null;
        try {
            connection = pool.take();
        } catch (InterruptedException e) {
            //
            Thread.currentThread().interrupt();
        }
        return connection;
    }

    public void returnConnection(Connection connection) {
        if (connection != null) {
            ConnectionProxy toReturn = (ConnectionProxy) connection;
            try {
                if (!connection.isClosed()) {
                    toReturn.setAutoCommit(true);
                    pool.put(toReturn);
                }
            } catch (SQLException e) {
                LOGGER.info("Connection can't close without exception");
            } catch (InterruptedException e) {
                LOGGER.info("Interrupted Exception");
                Thread.currentThread().interrupt();
            }
        }
    }

    public void destroyConnectionPool() {
        ConnectionProxy connection=null;
        int size = pool.size();
        for (int i = 0; i < size; i++) {
            try {
                connection = pool.take();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            if (connection != null) {
                try {
                    connection.realClose();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        deregisterDriver();
        pool.clear();
    }

    private void deregisterDriver() {
        Enumeration<Driver> drivers = DriverManager.getDrivers();
        while (drivers.hasMoreElements()){
            Driver driver = drivers.nextElement();
            try{
                DriverManager.deregisterDriver(driver);
            }catch (SQLException e){
                LOGGER.error("deregister data connections");
            }
        }
    }
}