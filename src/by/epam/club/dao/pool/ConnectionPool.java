package by.epam.club.dao.pool;

import by.epam.club.exception.ConnectionPoolException;

import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import static by.epam.club.dao.pool.DBResourceManager.*;

public class ConnectionPool {
    private static ConnectionPool instance;
    private static Lock lock = new ReentrantLock();
    private BlockingQueue<ConnectionProxy> pool;
    private static AtomicBoolean isCreatePool = new AtomicBoolean(false);

    private String driverName;
    private String url;
    private String login;
    private String password;
    private int poolSize;

    private ConnectionPool() throws ConnectionPoolException {
        DBResourceManager dbResourceManager = DBResourceManager.getInstance();
        this.driverName = dbResourceManager.getValue(DB_DRIVER);
        this.url = dbResourceManager.getValue(DB_URL);
        this.login = dbResourceManager.getValue(DB_LOGIN);
        this.password = dbResourceManager.getValue(DB_PASSWORD);
        this.poolSize = Integer.parseInt(dbResourceManager.getValue(DB_POOL_SIZE));
        initialize();
    }

    private void initialize() throws ConnectionPoolException {
        try {
            Class.forName(driverName);
            pool = new ArrayBlockingQueue<>(poolSize);
            for (int i = 0; i < poolSize; i++)
                pool.offer(new ConnectionProxy(DriverManager.getConnection(url, login, password)));
        } catch (ClassNotFoundException e) {
            throw new ConnectionPoolException("can't find driver", e);
        } catch (SQLException e) {
            throw new ConnectionPoolException("can't connect to your DB", e);
        }
    }

    public static ConnectionPool getInstance() throws ConnectionPoolException {
        if (!isCreatePool.get()) {
            lock.lock();
            try {
                if (instance == null) {
                    instance = new ConnectionPool();
                    isCreatePool.set(true);
                }
            } catch (Exception e) {
                throw new ConnectionPoolException("can't receive instance in ConnectionPool", e);
            } finally {
                lock.unlock();
            }
        }
        return instance;
    }

    public ConnectionProxy takeConnection() throws ConnectionPoolException {
        ConnectionProxy connection;
        try {
            connection = pool.take();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            throw new ConnectionPoolException(e);
        }
        return connection;
    }

    public void returnConnection(ConnectionProxy connection) throws ConnectionPoolException {
        try {
            if (!connection.isClosed()) {
                pool.offer(connection);
            }
        } catch (SQLException e) {
            throw new ConnectionPoolException(e);
        }
    }

}
