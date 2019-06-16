package by.epam.club.dao.impl;

import by.epam.club.dao.ArticleDao;
import by.epam.club.dao.CloseStatementResultSet;
import by.epam.club.entity.CommentToArticle;
import by.epam.club.entity.Parameter;
import by.epam.club.pool.ConnectionPool;
import by.epam.club.entity.Article;
import by.epam.club.exception.DaoException;
import by.epam.club.tool.CreateDate;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import static by.epam.club.dao.impl.SqlQuery.*;

public class ArticleDaoImpl implements ArticleDao {

    private ConnectionPool connectionPool = null;
    private Connection connection = null;
    private static final Logger LOGGER = LogManager.getLogger(UserDaoImpl.class);

    @Override
    public boolean create(String name, String text, long userId, int typeNews) throws DaoException {

        CloseStatementResultSet closeStatementResultSet = new CloseStatementResultSet();
        PreparedStatement st = null;
        ResultSet rs = null;

        Date date = new Date();
        final int defaultValue = 0;
        boolean values;
        connectionPool = ConnectionPool.getInstance();


        try (Connection connection = connectionPool.takeConnection()) {

            connection.setAutoCommit(false);
            st = connection.prepareStatement(ARTICLE_INSERT_NEW.getQuery());
            st.setString(1, name);
            st.setString(2, text);
            st.setInt(3, defaultValue);
            st.setInt(4, defaultValue);
            st.setLong(5, date.toInstant().toEpochMilli());
            st.setLong(6, userId);
            st.setInt(7, typeNews);
            st.executeUpdate();
            connection.commit();
            values = true;
        } catch (SQLException e) {
            try {
                connection.rollback();
            } catch (SQLException e1) {
                LOGGER.error("SqlException on rollback ", e1);
                throw new DaoException("unnone_mistake");
            }
            throw new DaoException("SQL_exception");
        } finally {
            closeStatementResultSet.close(rs, st);
        }
        return values;
    }

    @Override
    public Set<Article> takeAllByTypeNews(int typeNews) throws DaoException {
        CloseStatementResultSet closeStatementResultSet = new CloseStatementResultSet();
        PreparedStatement st = null;
        ResultSet rs = null;
        Set<Article> articles = new HashSet<>();
        try {
            connectionPool = ConnectionPool.getInstance();
            connection = connectionPool.takeConnection();
            st = connection.prepareStatement(ARTICLE_BY_TYPE_NEWS.getQuery());
            st.setInt(1, typeNews);
            rs = st.executeQuery();
            while (rs.next()) {
                Article article = createArticleData(rs);
                articles.add(article);
            }
        } catch (SQLException e) {
            throw new DaoException(e.getMessage());
        } finally {
            closeStatementResultSet.close(rs, st);
        }
        return articles;
    }

    @Override
    public Set<Article> takeAllByTypeNewsNotBannedNotDeleted(int typeNews) throws DaoException {
        CloseStatementResultSet closeStatementResultSet = new CloseStatementResultSet();
        PreparedStatement st = null;
        ResultSet rs = null;
        Set<Article> articles = new HashSet<>();
        try {
            connectionPool = ConnectionPool.getInstance();
            connection = connectionPool.takeConnection();
            st = connection.prepareStatement(ARTICLE_ALL_BY_TYPE_NEWS_NOTBANNED_NOTDELETED.getQuery());
            st.setInt(1, typeNews);
            rs = st.executeQuery();
            while (rs.next()) {
                Article article = createArticleData(rs);
                articles.add(article);
            }
        } catch (SQLException e) {
            throw new DaoException(e.getMessage());
        } finally {
            closeStatementResultSet.close(rs, st);
        }
        return articles;
    }

    @Override
    public boolean update(String name, String text, long articleId, int typeNews) throws DaoException {
        CloseStatementResultSet closeStatementResultSet = new CloseStatementResultSet();
        PreparedStatement st = null;
        ResultSet rs = null;
        boolean value;
        try {
            connectionPool = ConnectionPool.getInstance();
            connection = connectionPool.takeConnection();
            connection.setAutoCommit(false);
            st = connection.prepareStatement(ARTICLE_UPDATE_DATA.getQuery());

            st.setString(1, name);
            st.setString(2, text);
            st.setInt(3, typeNews);
            st.setLong(4, articleId);
            st.executeUpdate();
            connection.commit();
            value = true;
        } catch (SQLException e) {
            try {
                connection.rollback();
            } catch (SQLException e1) {
                LOGGER.error("SqlException on rollback ", e1);
                throw new DaoException("unnone_mistake");
            }
            throw new DaoException("SQL_exception");
        } finally {
            closeStatementResultSet.close(rs, st);
        }
        return value;
    }

    @Override
    public Article check(int articleId) throws DaoException {
        CloseStatementResultSet closeStatementResultSet = new CloseStatementResultSet();
        PreparedStatement st = null;
        ResultSet rs = null;
        Article article = null;

        try {
            connectionPool = ConnectionPool.getInstance();
            connection = connectionPool.takeConnection();
            st = connection.prepareStatement(ARTICLE_CHECK.getQuery());
            st.setInt(1, articleId);

            rs = st.executeQuery();
            if (rs.next()) {
                article = createArticleData(rs);
            }
        } catch (SQLException e) {
            throw new DaoException(e);
        } finally {
            closeStatementResultSet.close(rs, st);
        }
        return article;
    }

    private Article createArticleData(ResultSet resultSetArticle) throws DaoException {
        Article article = new Article();

        ArrayList<CommentToArticle> comments = new ArrayList<>();
        connectionPool = ConnectionPool.getInstance();
        CloseStatementResultSet closeStatementResultSet = new CloseStatementResultSet();
        PreparedStatement preparedStatement = null;
        ResultSet resultSetComment = null;
        CommentToArticle comment;


        try (Connection connection = connectionPool.takeConnection()) {
            preparedStatement = connection.prepareStatement(COMMENT_CHECK.getQuery());
            preparedStatement.setInt(1, resultSetArticle.getInt(1));

            resultSetComment = preparedStatement.executeQuery();
            while (resultSetComment.next()) {
                comment = createCommentData(resultSetComment);
                comments.add(comment);
            }

        } catch (
                SQLException e) {
            try {
                connection.rollback();
            } catch (SQLException e1) {
                LOGGER.error("SqlException on rollback ", e1);
                throw new DaoException("unnone_mistake");
            }
            LOGGER.info("SqlException ", e);
            throw new DaoException("SQL_exception");
        } finally {
            closeStatementResultSet.close(resultSetComment, preparedStatement);
        }


        try {
            Date moment = new Date(resultSetArticle.getBigDecimal(6).longValue());
            CreateDate createDate = new CreateDate();
            String date = createDate.takeDate(moment);

            article.setId(resultSetArticle.getInt(1));
            article.setTitle(resultSetArticle.getString(2));
            article.setText(resultSetArticle.getString(3));
            article.setPositiveRating(resultSetArticle.getInt(4));
            article.setNegativeRating(resultSetArticle.getInt(5));
            article.setDate_registration(date);

            if (resultSetArticle.getInt(7) == 0) {
                article.setBanned(Parameter.UNBANNED_PARAM);
            } else {
                article.setBanned(Parameter.BANNED_PARAM);
            }
            if (resultSetArticle.getInt(8) == 0) {
                article.setDeleted(Parameter.UNDELETED_PARAM);
            } else {
                article.setDeleted(Parameter.DELETED_PARAM);
            }
            article.setUserId(resultSetArticle.getInt(9));
            article.setTypeNewsId(resultSetArticle.getInt(10));
            article.setUserLogin(resultSetArticle.getString(11));
            article.setComments(comments);
            article.setCommentQuantity(comments.size());
        } catch (SQLException e) {
            e.printStackTrace();
            throw new DaoException(e);
        }
        return article;
    }

    private CommentToArticle createCommentData(ResultSet resultSetComment) throws DaoException {
        CommentToArticle comment = new CommentToArticle();
        try {
            Date moment = new Date(resultSetComment.getBigDecimal(3).longValue());
            CreateDate createDate = new CreateDate();
            String date = createDate.takeDate(moment);

            comment.setId(resultSetComment.getLong(1));
            comment.setText(resultSetComment.getString(2));
            comment.setDateRegistration(date);

            comment.setPositiveRating(resultSetComment.getInt(4));
            comment.setNegativeRating(resultSetComment.getInt(5));


            if (resultSetComment.getInt(6) == 0) {
                comment.setBanned(Parameter.UNBANNED_PARAM);
            } else {
                comment.setBanned(Parameter.BANNED_PARAM);
            }
            if (resultSetComment.getInt(7) == 0) {
                comment.setDeleted(Parameter.UNDELETED_PARAM);
            } else {
                comment.setDeleted(Parameter.DELETED_PARAM);
            }

            comment.setUserLogin(resultSetComment.getString(8));
            comment.setUserId(resultSetComment.getLong(9));
        } catch (SQLException e) {
            e.printStackTrace();
            throw new DaoException(e);
        }
        return comment;
    }
}
