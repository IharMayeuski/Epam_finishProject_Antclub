package by.epam.club.dao;

import by.epam.club.dao.basedao.BaseDao;
import by.epam.club.exception.DaoException;
import by.epam.club.pool.ConnectionPool;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.SQLException;

import static by.epam.club.entity.Parameter.SQL_TRANSACTION_EXCEPTION_MESSAGE;

/**
 * The special class for making transaction operations
 *
 * @author Maeuski Igor
 * @version 1.0
 */
public class TransactionHelper {
    private static Logger logger = LogManager.getLogger();
    private Connection connection = ConnectionPool.getInstance().takeConnection();
    private BaseDao[] daosArray;

    /**
     * @param daos take difference quantity of BaseDao for transaction
     * @throws DaoException for catching on the service level
     */
    public void beginTransaction(BaseDao... daos) throws DaoException {
        try {
            connection.setAutoCommit(false);
            daosArray = daos;
            for (BaseDao d : daos) {
                d.setConnection(connection);
                d.setInAction(true);
            }
        } catch (SQLException e) {
            logger.error(SQL_TRANSACTION_EXCEPTION_MESSAGE, e);
            throw new DaoException(SQL_TRANSACTION_EXCEPTION_MESSAGE);
        }
    }

    /**
     * @throws DaoException for catching on service level
     */
    public void endTransaction() throws DaoException {
        try {
            connection.setAutoCommit(true);

            ConnectionPool.getInstance().returnConnection(connection);
            for (BaseDao d : daosArray) {
                d.setInAction(false);
            }
        } catch (SQLException e) {
            logger.error(SQL_TRANSACTION_EXCEPTION_MESSAGE, e);

            throw new DaoException(SQL_TRANSACTION_EXCEPTION_MESSAGE);
        }
    }

    /**
     * @throws DaoException for catching on service level
     */
    public void commit() throws DaoException {
        try {
            connection.commit();
        } catch (SQLException e) {
            logger.error(SQL_TRANSACTION_EXCEPTION_MESSAGE, e);
            throw new DaoException(SQL_TRANSACTION_EXCEPTION_MESSAGE);
        }
    }

    /**
     * @throws DaoException for catching on service level
     */
    public void rollback() throws DaoException {
        try {
            connection.rollback();
        } catch (SQLException e) {
            logger.error(SQL_TRANSACTION_EXCEPTION_MESSAGE, e);
            throw new DaoException(SQL_TRANSACTION_EXCEPTION_MESSAGE);
        }
    }
}
