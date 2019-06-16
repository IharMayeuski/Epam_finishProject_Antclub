package by.epam.club.dao;

import by.epam.club.entity.Entity;
import by.epam.club.exception.DaoException;
import by.epam.club.pool.ConnectionPool;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class BaseDao <T extends Entity> {
    private static Logger logger = LogManager.getLogger();
    private Connection connection;
    private boolean inAction;

    public void create(String sqlQuery, String... values) throws DaoException {
        if (!inAction) {
            setConnection(ConnectionPool.getInstance().takeConnection());
        }
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = connection.prepareStatement(sqlQuery);
            logger.info("values.length " + values.length);
            for (int i = 0; i < values.length; i++) {
                preparedStatement.setString(i + 1, values[i]);
                logger.info(i + 1 + values[i]);
            }
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new DaoException("SQL exception");
        } finally {
            if (!inAction) {
                closeResources(preparedStatement, connection);
                logger.info(" is it comes to finally close!");
            }
        }
    }

//    public void check(String sqlQuery, String values) throws DaoException {
//        ResultSet rs = null;
//        if (!inAction) {
//            setConnection(ConnectionPool.getInstance().takeConnection());
//        }
//        PreparedStatement preparedStatement = null;
//        try {
//            preparedStatement = connection.prepareStatement(sqlQuery);
//            preparedStatement.setString(1, values);
//
//            rs = preparedStatement.executeQuery();
//            if (rs.next()) {
//                if (rs.getInt(2) == 1) {
//                    throw new DaoException("user.login.deleted");
//                } else {
//                    throw new DaoException("user.login");
//                }
//            }
//
//        } catch (SQLException e) {
//            throw new DaoException(e.getMessage());
//        } finally {
//            if (!inAction) {
//                closeResources(preparedStatement, connection);
//                logger.info(" is it comes to finally close!");
//            }
//        }
//    }


    public void closeResources(PreparedStatement ps, Connection connection) {
        closeResources(ps);
        closeResources(connection);
    }

  /*  public void closeResources(PreparedStatement ps) {
        try {
            if (ps != null) {
                ps.close();
            }
        } catch (SQLException e) {
            logger.error("Exception in closing PreparedStatement", e);
        }
    }*/

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
            logger.error("Exception in closing PreparedStatement", e);
        }
        closeResources(ps);
    }

    public void closeResources(PreparedStatement ps) {
        try {
            if (ps != null) {
                ps.close();
            }
        } catch (SQLException e) {
            logger.error("Exception in closing PreparedStatement", e);
        }
    }

    public void closeResources(Connection connection) {
        if (connection != null) {
            ConnectionPool.getInstance().returnConnection(connection);
        }
    }

    public Connection getConnection() {
        return connection;
    }

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
