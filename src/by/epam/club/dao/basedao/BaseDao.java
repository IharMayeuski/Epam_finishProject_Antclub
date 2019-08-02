package by.epam.club.dao.basedao;

import by.epam.club.entity.Entity;
import by.epam.club.entity.EntityCreator;
import by.epam.club.exception.DaoException;
import by.epam.club.pool.ConnectionPool;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.InputStream;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import static by.epam.club.entity.Parameter.*;

/**
 * Class for upload picture to data base
 *
 * @author Maeuski Igor
 * @version 1.0
 */
public abstract class BaseDao<T extends Entity> {
    private static Logger LOGGER = LogManager.getLogger(BaseDao.class);
    private Connection connection;
    private boolean inAction;

    /**
     *
     * @param sqlQuery real SQL query script
     * @param values for insert in SQL query by preparedStatement
     * @throws DaoException for catching it on the logic level in the case of exception
     */
    public void create(String sqlQuery, String... values) throws DaoException {
        if (!inAction) {
            setConnection(ConnectionPool.getInstance().takeConnection());
        }
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = connection.prepareStatement(sqlQuery);
            for (int i = 0; i < values.length; i++) {
                preparedStatement.setString(i + 1, values[i]);
            }
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            LOGGER.warn(SQL_EXCEPTION_MESSAGE, e);
            throw new DaoException(SQL_EXCEPTION_MESSAGE);
        } finally {
            if (!inAction) closeResources(preparedStatement, connection);
        }
    }

    /**
     *
     * @param sqlQuery real SQL query script
     * @param is byte code of Picture
     * @param values for insert in SQL query
     * @throws DaoException for catching it on the logic level in the case of exception
     */
    public void uploadPic(String sqlQuery, InputStream is, String... values) throws DaoException {
        if (!inAction) {
            setConnection(ConnectionPool.getInstance().takeConnection());
        }
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = connection.prepareStatement(sqlQuery);
            preparedStatement.setBlob(1, is);
            for (int i = 0; i < values.length; i++) {
                preparedStatement.setString(i + 2, values[i]);
            }
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            LOGGER.warn(SQL_EXCEPTION_MESSAGE, e);
            throw new DaoException(SQL_EXCEPTION_MESSAGE);
        } finally {
            if (!inAction) closeResources(preparedStatement, connection);
        }
    }

    /**
     *
     * @param sqlQuery real SQL query script
     * @param table this table necessary for EntityCreator
     * @param values for insert in SQL query
     * @return {@code List<Entity>}
     * @throws DaoException for catching it on the logic level in the case of exception
     */
    public List<T> find(String sqlQuery, String table, String... values) throws DaoException {
        List<T> entities = new ArrayList<>();
        if (!inAction) {
            connection = ConnectionPool.getInstance().takeConnection();
        }
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            preparedStatement = connection.prepareStatement(sqlQuery);
            for (int i = 0; i < values.length; i++) {
                preparedStatement.setString(i + 1, values[i]);
            }
            resultSet = preparedStatement.executeQuery();
            EntityCreator<T> creator = new EntityCreator<>();
            while (resultSet.next()) {
                T entity = creator.create(table, resultSet);
                entities.add(entity);
            }
        } catch (SQLException e) {
            LOGGER.warn(SQL_EXCEPTION_MESSAGE, e);
            e.printStackTrace();
            throw new DaoException(SQL_EXCEPTION_MESSAGE);
        } finally {
            if (!inAction) {
                closeResources(preparedStatement, connection, resultSet);
            }
        }
        return entities;
    }

    /**
     *
     * @param ps we close preparedStatement
     * @param connection  we close our connetion
     */
    private void closeResources(PreparedStatement ps, Connection connection) {
        closeResources(ps);
        closeResources(connection);
    }

    /**
     *
     * @param ps we close preparedStatement
     * @param connection  we close our connetion
     * @param rs we clode our resultSet
     */
    public void closeResources(PreparedStatement ps, Connection connection, ResultSet rs) {
        closeResources(rs, ps);
        closeResources(connection);
    }

    /**
     *
     * @param rs we clode our resultSet
     * @param ps we close preparedStatement
     */
    public void closeResources(ResultSet rs, Statement ps) {
        try {
            if (rs != null) {
                rs.close();
            }
        } catch (SQLException e) {
            LOGGER.error(EXCEPTION_IN_PREPARED_STATEMENT_MESSAGE, e);
        }
        closeResources(ps);
    }

    /**
     *
     * @param ps we close preparedStatement
     */
    public void closeResources(Statement ps) {
        try {
            if (ps != null) {
                ps.close();
            }
        } catch (SQLException e) {
            LOGGER.error(EXCEPTION_IN_PREPARED_STATEMENT_MESSAGE, e);
        }
    }

    /**
     *
     * @param connection  we close our connetion
     */
    private void closeResources(Connection connection) {
        if (connection != null) {
            ConnectionPool.getInstance().returnConnection(connection);
        }
    }

    public void setConnection(Connection connection) {
        this.connection = connection;
    }

    public void setInAction(boolean inAction) {
        this.inAction = inAction;
    }
}
