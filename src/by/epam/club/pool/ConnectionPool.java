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

    private static ConnectionPool instance;
    private static Lock lock = new ReentrantLock();
    private static BlockingQueue<ConnectionProxy> poolConnectionProxy;
    private static AtomicBoolean isPoolCreated = new AtomicBoolean(false);

    private static DBResourceManager dbResourceManager = DBResourceManager.getInstance();

    private static final String driverName = dbResourceManager.getValue(DB_DRIVER);
    private static final String url = dbResourceManager.getValue(DB_URL);
    private static final String login = dbResourceManager.getValue(DB_LOGIN);
    private static final String password = dbResourceManager.getValue(DB_PASSWORD);
    private static final int poolSize = Integer.parseInt(dbResourceManager.getValue(DB_POOL_SIZE));

    private static final Logger LOGGER = LogManager.getLogger(ConnectionPool.class);


    private ConnectionPool() {
        initialize();
    }

    public static ConnectionPool getInstance() {
        if (!isPoolCreated.get()) {
            try {
                lock.lock();
                if (instance == null) {
                    instance = new ConnectionPool();
                    isPoolCreated.set(true);
                }
            } finally {
                lock.unlock();
            }
        }
        return instance;
    }

    private void initialize() {
        try {
            Class.forName(driverName);
            poolConnectionProxy = new ArrayBlockingQueue<>(poolSize);
            while (poolConnectionProxy.size() < poolSize) {
                initializeOneConnection();
            }
        } catch (ClassNotFoundException e) {
            LOGGER.error("Can't find class", e);
            throw new RuntimeException(e);
        }
    }

    private void initializeOneConnection() {
        try {
            System.out.println(poolConnectionProxy.toString());
            ConnectionProxy connectionProxy = new ConnectionProxy(DriverManager.getConnection(url, login, password));
            poolConnectionProxy.put(connectionProxy);
        } catch (InterruptedException e) {
            LOGGER.error("Interrupted Exception", e);
            Thread.currentThread().interrupt();
        } catch (SQLException e) {
            LOGGER.error("Connection can't close without exception", e);
            throw new RuntimeException(e);
        }
    }

    private void repairConnectionPool() {
        System.out.println(poolConnectionProxy.size()+" size connection proxy");
       /* while (poolConnectionProxy.size() < poolSize) { fixme не работает починка пула ресурсов...
            initializeOneConnection();
        }*/
    }

    public Connection takeConnection() {
        ConnectionProxy connection = null;
        try {
            connection = poolConnectionProxy.take();
            System.out.println(poolConnectionProxy.size()+ " pool size, "+ connection);
        } catch (InterruptedException e) {
            LOGGER.error("Interrupted Exception", e);
            Thread.currentThread().interrupt();
        }
        return connection;
    }

    public void returnConnection(Connection connection) {
        if (connection != null) {
            ConnectionProxy connectionProxy = (ConnectionProxy) connection;
            try {
                if (!connection.isClosed()) {
                    poolConnectionProxy.put(connectionProxy);
                }
            } catch (SQLException e) {
                LOGGER.error("Connection can't return without exception", e);
            } catch (InterruptedException e) {
                LOGGER.error("Interrupted Exception", e);
                Thread.currentThread().interrupt();
            } finally {
                if (poolConnectionProxy.size() < poolSize) {
                    repairConnectionPool();
                }
                try {
                    if(!connectionProxy.getAutoCommit()) {
                        connectionProxy.setAutoCommit(true);
                    }
                } catch (SQLException e) {
                    LOGGER.error("The problem with autocommit", e);
                }
            }
        }
    }

    public void destroyConnectionPool() {
        ConnectionProxy connection = null;
        int size = poolConnectionProxy.size();
        for (int i = 0; i < size; i++) {
            try {
                connection = poolConnectionProxy.take();
            } catch (InterruptedException e) {
                LOGGER.error("Interrupted Exception", e);
            }
            if (connection != null) {
                try {
                    connection.realClose();
                } catch (SQLException e) {
                    LOGGER.error("Connection can't close without exception", e);
                }
            }
        }
        deregisterDriver();
    }

    private void deregisterDriver() {
        Enumeration<Driver> drivers = DriverManager.getDrivers();
        while (drivers.hasMoreElements()) {
            Driver driver = drivers.nextElement();
            try {
                DriverManager.deregisterDriver(driver);
            } catch (SQLException e) {
                LOGGER.error("deregister data connections is failed", e);
            }
        }
    }
}