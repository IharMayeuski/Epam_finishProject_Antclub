/*
package by.epam.club.tool;


import by.epam.club.exception.DaoException;
import by.epam.club.pool.ConnectionPool;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.SQLException;

public class TransactionHelper {
    private static Logger logger = LogManager.getLogger();
    private Connection connection = ConnectionPool.getInstance().takeConnection();
    private BaseDao[] daosArray;

    public void beginTransaction(BaseDao... daos) throws DaoException {
        try {
            connection.setAutoCommit(false);
            daosArray = daos;
            for (BaseDao d : daos) {
                d.setConnection(connection);
                d.setInAction(true);
            }
        } catch (SQLException e) {
            logger.error("SQL beginTransaction exception ", e);
            throw new DaoException("SQL beginTransaction exception ", e);
        }
    }

    public void endTransaction() throws DaoException {
        try {
            connection.setAutoCommit(true);
            ConnectionPool.getInstance().takeConnection();
            for (BaseDao d : daosArray) {
                d.setInAction(false);
            }
        } catch (SQLException e) {
            logger.error("SQL endTransaction exception ", e);
            throw new DaoException("SQL endTransaction exception ", e);
        }
    }

    public void commit() throws DaoException {
        try {
            connection.commit();
        } catch (SQLException e) {
            logger.error("SQL commit exception ", e);
            throw new DaoException("SQL commit exception ", e);
        }
    }

    public void rollback() throws DaoException {
        try {
            connection.rollback();
        } catch (SQLException e) {
            logger.error("SQL rollback exception ", e);
            throw new DaoException("SQL rollback exception ", e);
        }
    }
}
*/
