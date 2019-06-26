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
    private static Lock LOCK = new ReentrantLock();
    private static BlockingQueue<ConnectionProxy> POOL_CONNECTION_PROXY_AVAILABLE;
    private static BlockingQueue<ConnectionProxy> POOL_CONNECTION_PROXY_USED;
    private static AtomicBoolean IS_POOL_CREATED = new AtomicBoolean(false);

    private static DBResourceManager DB_RESOURCE_MANAGER = DBResourceManager.getInstance();

    private static final String DRIVER_NAME = DB_RESOURCE_MANAGER.getValue(DB_DRIVER);
    private static final String URL = DB_RESOURCE_MANAGER.getValue(DB_URL);
    private static final String LOGIN = DB_RESOURCE_MANAGER.getValue(DB_LOGIN);
    private static final String PASSWORD = DB_RESOURCE_MANAGER.getValue(DB_PASSWORD);
    private static final int POOLSIZE = Integer.parseInt(DB_RESOURCE_MANAGER.getValue(DB_POOL_SIZE));

    private static final Logger LOGGER = LogManager.getLogger(ConnectionPool.class);


    private ConnectionPool() {
        initialize();
    }

    public static ConnectionPool getInstance() {
        if (!IS_POOL_CREATED.get()) {
            try {
                LOCK.lock();
                if (instance == null) {
                    instance = new ConnectionPool();
                    IS_POOL_CREATED.set(true);
                }
            } finally {
                LOCK.unlock();
            }
        }
        return instance;
    }

    private void initialize() {
        try {
            Class.forName(DRIVER_NAME);
            POOL_CONNECTION_PROXY_AVAILABLE = new ArrayBlockingQueue<>(POOLSIZE);
            POOL_CONNECTION_PROXY_USED = new ArrayBlockingQueue<>(POOLSIZE);
            while (POOL_CONNECTION_PROXY_AVAILABLE.size() < POOLSIZE) {
                initializeOneConnection();
            }
        } catch (ClassNotFoundException e) {
            LOGGER.error("Can't find class", e);
            throw new RuntimeException(e);
        }
    }

    private void initializeOneConnection() {
        try {
            System.out.println(POOL_CONNECTION_PROXY_AVAILABLE.toString());
            ConnectionProxy connectionProxy = new ConnectionProxy(DriverManager.getConnection(URL, LOGIN, PASSWORD));
            POOL_CONNECTION_PROXY_AVAILABLE.put(connectionProxy);
        } catch (InterruptedException e) {
            LOGGER.error("Interrupted Exception", e);
            Thread.currentThread().interrupt();
        } catch (SQLException e) {
            LOGGER.error("Connection can't close without exception", e);
            throw new RuntimeException(e);
        }
    }

    private void repairConnectionPool() {
        initializeOneConnection();
        System.out.println("REPAIR CONNECTION");
    }

    public Connection takeConnection() {
        ConnectionProxy connection = null;
        try {
            connection = POOL_CONNECTION_PROXY_AVAILABLE.take();
            POOL_CONNECTION_PROXY_USED.put(connection);

        } catch (InterruptedException e) {
            LOGGER.error("Interrupted Exception", e);
            Thread.currentThread().interrupt();
        }
        return connection;
    }

    public void returnConnection(Connection connection) {
        int allConnections = 0;
        if (connection != null) {
            ConnectionProxy connectionProxy = (ConnectionProxy) connection;
            try {
                if (!connection.isClosed()) {
                    POOL_CONNECTION_PROXY_AVAILABLE.put(connectionProxy);
                    POOL_CONNECTION_PROXY_USED.remove(connectionProxy);
                }
            } catch (SQLException e) {
                LOGGER.error("Connection can't return without exception", e);
            } catch (InterruptedException e) {
                LOGGER.error("Interrupted Exception", e);
                Thread.currentThread().interrupt();
            } finally {
                allConnections = POOL_CONNECTION_PROXY_AVAILABLE.size() + POOL_CONNECTION_PROXY_USED.size();
                if (allConnections < POOLSIZE) {
                    repairConnectionPool();
                }
                try {
                    if (!connectionProxy.getAutoCommit()) {
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
        int size = POOL_CONNECTION_PROXY_AVAILABLE.size();
        for (int i = 0; i < size; i++) {
            try {
                connection = POOL_CONNECTION_PROXY_AVAILABLE.take();
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