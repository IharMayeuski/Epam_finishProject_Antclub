package by.epam.club.dao.impl;

import by.epam.club.dao.ArticleDao;
import by.epam.club.dao.pool.ConnectionPool;
import by.epam.club.dao.pool.ConnectionProxy;
import by.epam.club.entity.Article;
import by.epam.club.entity.Picture;
import by.epam.club.exception.ConnectionPoolException;
import by.epam.club.exception.DaoException;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;

import static by.epam.club.dao.impl.SqlFunction.INSERT_NEW_ARTICLE;

public class ArticleDaoImpl implements ArticleDao {
    private PreparedStatement st;
    private ResultSet rs;
    private ConnectionProxy con = null;
    private ConnectionPool connectionPool = null;

    @Override
    public boolean create(String name, String text, int userId, int typeNews) throws DaoException {
        Date date = new Date();
        final int defaultValue = 0;
        final int bannedBlockDefaultValue = 1;
        boolean values = false;

        try {
            connectionPool = ConnectionPool.getInstance();
            con = connectionPool.takeConnection();
            con.setAutoCommit(false);
            st = con.prepareStatement(INSERT_NEW_ARTICLE.getQuery());
            st.setString(1, name);
            st.setString(2, text);
            st.setInt(3, defaultValue);
            st.setInt(4, defaultValue);
            st.setLong(5, date.toInstant().toEpochMilli());
            st.setInt(6, userId);
            st.setInt(7, typeNews);
            st.setInt(8, bannedBlockDefaultValue);
            st.executeUpdate();
            con.commit();
            values = true;
        } catch (ConnectionPoolException | SQLException e) {
            try {
                con.rollback();
            } catch (SQLException message) {
                throw new DaoException(message);
            }

        } finally {
            if (connectionPool != null) {
                try {
                    connectionPool.returnConnection(con);
                } catch (ConnectionPoolException e) {
                    throw new DaoException(e);//todo уточнить, что делать в файнали со throw
                }
            }
        }
        return values;
    }
}