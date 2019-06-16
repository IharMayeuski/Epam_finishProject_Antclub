package by.epam.club.dao.impl;

import by.epam.club.dao.CloseStatementResultSet;
import by.epam.club.dao.TypeNewsDao;
import by.epam.club.entity.Parameter;
import by.epam.club.pool.ConnectionPool;
import by.epam.club.entity.TypeNews;
import by.epam.club.exception.DaoException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.Set;

import static by.epam.club.dao.impl.SqlQuery.*;

public class TypeNewsDaoImpl implements TypeNewsDao {
    private ConnectionPool connectionPool = null;
    private Connection connection = null;

    @Override
    public Set<TypeNews> takeTypes() throws DaoException {
        connectionPool = ConnectionPool.getInstance();

        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        CloseStatementResultSet closeStatementResultSet = new CloseStatementResultSet();
        Set<TypeNews> types = new HashSet<>();

        try (Connection con = connectionPool.takeConnection()) {
            preparedStatement = con.prepareStatement(TYPE_FIND_ALL_UNDELETED.getQuery());
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                TypeNews typeData = createTypeData(resultSet);
                types.add(typeData);
            }
        } catch (SQLException e) {
            throw new DaoException("SQL_exception");
        } finally {
            closeStatementResultSet.close(resultSet, preparedStatement);
        }
        return types;
    }

    private TypeNews createTypeData(ResultSet rs) throws DaoException {
        TypeNews typeNews = new TypeNews();
        try {
            typeNews.setId(rs.getInt(1));
            typeNews.setTypeNews(rs.getString(2));
            if (rs.getInt(3) == 0) {
                typeNews.setDeleted(Parameter.UNDELETED_PARAM);
            } else {
                typeNews.setDeleted(Parameter.DELETED_PARAM);
            }
        } catch (SQLException e) {
            throw new DaoException("SQL_exception");
        }
        return typeNews;
    }
}
