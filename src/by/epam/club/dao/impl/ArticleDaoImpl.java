package by.epam.club.dao.impl;

import by.epam.club.dao.ArticleDao;
import by.epam.club.dao.DaoGeneral;
import by.epam.club.pool.ConnectionPool;
import by.epam.club.pool.ConnectionProxy;
import by.epam.club.entity.Article;
import by.epam.club.exception.DaoException;
import by.epam.club.tool.CreateDate;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import static by.epam.club.dao.impl.SqlQuery.*;
import static by.epam.club.dao.impl.Status.*;
import static by.epam.club.dao.impl.Status.DELETED;

public class ArticleDaoImpl implements ArticleDao {
    private PreparedStatement st;
    private ResultSet rs;
    private Connection con = null;
    private ConnectionPool connectionPool = null;
    private DaoGeneral daoGeneral = new DaoGeneral();

    @Override
    public boolean create(String name, String text, long userId, int typeNews) throws DaoException {
        Date date = new Date();
        final int defaultValue = 0;
        boolean values = false;

        try {
            connectionPool = ConnectionPool.getInstance();
            con = connectionPool.takeConnection();
            con.setAutoCommit(false);
            st = con.prepareStatement(ARTICLE_INSERT_NEW.getQuery());
            st.setString(1, name);
            st.setString(2, text);
            st.setInt(3, defaultValue);
            st.setInt(4, defaultValue);
            st.setLong(5, date.toInstant().toEpochMilli());
            st.setLong(6, userId);
            st.setInt(7, typeNews);
            st.executeUpdate();
            con.commit();
            values = true;
        } catch (SQLException e) {
            try {
                con.rollback();
            } catch (SQLException message) {
                throw new DaoException(message);
            }
        } finally {
            daoGeneral.close(rs,st);
            connectionPool.returnConnection(con);
        }
        return values;
    }

    @Override
    public Set<Article> takeAllByTypeNews(int typeNews) throws DaoException {
        Set<Article> articles = new HashSet<>();
        try {
            connectionPool = ConnectionPool.getInstance();
            con = connectionPool.takeConnection();
            st = con.prepareStatement(ARTICLE_BY_TYPE_NEWS.getQuery());
            st.setInt(1, typeNews);
            rs = st.executeQuery();
            while (rs.next()) {
                Article article = createArticleData(rs);
                articles.add(article);
            }
        } catch (SQLException e) {
            throw new DaoException(e.getMessage());
        }finally {
            daoGeneral.close(rs,st);
            connectionPool.returnConnection(con);
        }
        return articles;
    }

    @Override
    public Set<Article> takeAllByTypeNewsNotBannedNotDeleted(int typeNews) throws DaoException {

        Set<Article> articles = new HashSet<>();
        try {
            connectionPool = ConnectionPool.getInstance();
            con = connectionPool.takeConnection();
            st = con.prepareStatement(ARTICLE_ALL_BY_TYPE_NEWS_NOTBANNED_NOTDELETED.getQuery());
            st.setInt(1, typeNews);
            rs = st.executeQuery();
            while (rs.next()) {
                Article article = createArticleData(rs);
                articles.add(article);
            }
        } catch (SQLException e) {
            throw new DaoException(e.getMessage());
        }
        finally {
            daoGeneral.close(rs,st);
            connectionPool.returnConnection(con);
        }
        return articles;
    }

    @Override
    public boolean update(String name, String text, long articleId, int typeNews) throws DaoException {
        boolean value = false;
        try {
            connectionPool = ConnectionPool.getInstance();
            con = connectionPool.takeConnection();
            con.setAutoCommit(false);
            st = con.prepareStatement(ARTICLE_UPDATE_DATA.getQuery());

            st.setString(1, name);
            st.setString(2, text);
            st.setInt(3, typeNews);
            st.setLong(4, articleId);
            st.executeUpdate();
            con.commit();
            value = true;
        } catch (SQLException e) {
            try {
                con.rollback();
            } catch (SQLException e1) {
                throw new DaoException(e1);
            }
        } finally {
            daoGeneral.close(rs,st);
            connectionPool.returnConnection(con);
        }
        return value;
    }

    @Override
    public Article check(int articleId) throws DaoException {
        Article article = null;

        try {
            connectionPool = ConnectionPool.getInstance();
            con = connectionPool.takeConnection();
            st = con.prepareStatement(ARTICLE_CHECK.getQuery());
            st.setInt(1, articleId);

            rs = st.executeQuery();
            if (rs.next()) {
                article = createArticleData(rs);
            }
        } catch (SQLException e) {
            throw new DaoException(e);
        } finally {
            daoGeneral.close(rs,st);
            connectionPool.returnConnection(con);
        }
        return article;
    }

    private Article createArticleData(ResultSet rs) throws DaoException {
        Article article = new Article();
        try {
            Date moment = new Date(rs.getBigDecimal(6).longValue());
            CreateDate createDate = new CreateDate();
            String date = createDate.takeDate(moment);

            article.setId(rs.getInt(1));
            article.setTitle(rs.getString(2));
            article.setText(rs.getString(3));
            article.setPositiveRating(rs.getInt(4));
            article.setNegativeRating(rs.getInt(5));
            article.setDate_registration(date);

            if (rs.getInt(7) == 0) {
                article.setBanned(UNBANNED.getStatus());
            } else {
                article.setBanned(BANNED.getStatus());
            }
            if (rs.getInt(8) == 0) {
                article.setDeleted(UNDELETED.getStatus());
            } else {
                article.setDeleted(DELETED.getStatus());
            }
            article.setUserId(rs.getInt(9));
            article.setTypeNewsId(rs.getInt(10));
        } catch (SQLException e) {
            throw new DaoException(e);
        }
        return article;
    }
}