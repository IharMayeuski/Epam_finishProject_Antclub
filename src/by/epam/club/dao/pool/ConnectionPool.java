package by.epam.club.dao.pool;

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
                    pool.put(new ConnectionProxy(DriverManager.getConnection(url, login, password)));
                } catch (InterruptedException e) {
                    //todo logger
                    Thread.currentThread().interrupt();
                }
            }
        } catch (ClassNotFoundException | SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static ConnectionPool getInstance() {
        if (!isCreatePool.get()) {
            lock.lock();
            if (instance == null) {
                instance = new ConnectionPool();
                isCreatePool.set(true);
            }
            lock.unlock();
        }
        return instance;
    }

    public ConnectionProxy takeConnection() {
        ConnectionProxy connection = null;
        try {
            connection = pool.take();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        return connection;
    }

    public void returnConnection(ConnectionProxy connection) {
        try {
            try {
                if (!connection.isClosed()) {
                    pool.put(connection);
                }
            } catch (SQLException e) {
                //todo logger
            }
        } catch (InterruptedException e) {
            //todo logger
            Thread.currentThread().interrupt();
        }
    }

}
