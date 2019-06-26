package by.epam.club.dao.basedao;

import by.epam.club.entity.Entity;
import by.epam.club.entity.EntityCreator;
import by.epam.club.exception.DaoException;
import by.epam.club.pool.ConnectionPool;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.Part;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static by.epam.club.entity.Parameter.*;

public abstract class BaseDao<T extends Entity> {
    private static Logger LOGGER = LogManager.getLogger(BaseDao.class);
    private Connection connection;
    private boolean inAction;

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
            throw new DaoException(SQL_EXCEPTION_MESSAGE);
        } finally {
            if (!inAction) closeResources(preparedStatement, connection);
        }
    }

    public void uploadPicInTransaction(String sqlQuery, InputStream is, String id) throws DaoException {
        if (!inAction) {
            setConnection(ConnectionPool.getInstance().takeConnection());
        }
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = connection.prepareStatement(sqlQuery);
            preparedStatement.setBlob(1, is);
            preparedStatement.setString(2, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new DaoException(SQL_EXCEPTION_MESSAGE);
        } finally {
            if (!inAction) closeResources(preparedStatement, connection);
        }
    }

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
            throw new DaoException(SQL_EXCEPTION_MESSAGE);
        } finally {
            if (!inAction) {
                closeResources(preparedStatement, connection, resultSet);
            }
        }
        return entities;
    }

    public void closeResources(PreparedStatement ps, Connection connection) {
        closeResources(ps);
        closeResources(connection);
    }

    public void closeResources(PreparedStatement ps, Connection connection, ResultSet rs) {
        closeResources(rs, ps);
        closeResources(connection);
    }

    public void closeResources(ResultSet rs, PreparedStatement ps) {
        try {
            if (rs != null) {
                rs.close();
            }
        } catch (SQLException e) {
            LOGGER.error(EXCEPTION_IN_PREPARED_STATEMENT_MESSAGE, e);
        }
        closeResources(ps);
    }

    public void closeResources(PreparedStatement ps) {
        try {
            if (ps != null) {
                ps.close();
            }
        } catch (SQLException e) {
            LOGGER.error(EXCEPTION_IN_PREPARED_STATEMENT_MESSAGE, e);
        }
    }

    public void closeResources(Connection connection) {
        if (connection != null) {
            ConnectionPool.getInstance().returnConnection(connection);
        }
    }

   /* public Connection getConnection() {
        return connection;
    }*/

    public void setConnection(Connection connection) {
        this.connection = connection;
    }

    public boolean isInAction() {
        return inAction;
    }

    public void setInAction(boolean inAction) {
        this.inAction = inAction;
    }
}
