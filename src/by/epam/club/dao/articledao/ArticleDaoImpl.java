package by.epam.club.dao.articledao;

import by.epam.club.dao.basedao.BaseDao;
import by.epam.club.entity.CommentToArticle;
import by.epam.club.entity.Article;
import by.epam.club.exception.DaoException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.*;

import static by.epam.club.dao.SqlQuery.*;
import static by.epam.club.entity.Parameter.*;

@SuppressWarnings("unchecked")
public class ArticleDaoImpl extends BaseDao implements ArticleDao {


    private static final Logger LOGGER = LogManager.getLogger(ArticleDaoImpl.class);

    @Override
    public boolean create(String name, String text, long userId, int typeNews) throws DaoException {
        Date date = new Date();
        final String defaultValue = "0";

        try {
            create(ARTICLE_INSERT_NEW.getQuery(), name, text, defaultValue, defaultValue, Long.toString(date.toInstant().toEpochMilli()), Long.toString(userId), Integer.toString(typeNews));
        } catch (DaoException e) {
            throw new DaoException(e.getMessage());
        }
        return true;
    }

    @Override
    public List<Article> takeAllByTypeNews(int typeNews) throws DaoException {

        List<Article> articles;
        List<CommentToArticle> comments;
        try {
            articles = find(ARTICLE_BY_TYPE_NEWS.getQuery(), ARTICLES_PARAM, Integer.toString(typeNews));
            for (Article article : articles) {
                comments = find(COMMENT_CHECK.getQuery(), COMMENT_PARAM, Long.toString(article.getId()));
                article.setComments(comments);
                article.setCommentQuantity(comments.size());
            }
        } catch (DaoException e) {
            throw new DaoException(e.getMessage());
        }
        return articles;
    }

    @Override
    public List<Article> takeAllByTypeNewsNotBannedNotDeleted(int typeNews) throws DaoException {
        List<Article> articles;
        List<CommentToArticle> comments;
        try {
            articles = find(ARTICLE_ALL_BY_TYPE_NEWS_NOTBANNED_NOTDELETED.getQuery(), ARTICLES_PARAM, Integer.toString(typeNews));
            for (Article article : articles) {
                comments = find(COMMENT_CHECK.getQuery(), COMMENT_PARAM, Long.toString(article.getId()));
                comments.sort(new sortComment().reversed());
                article.setComments(comments);
                article.setCommentQuantity(comments.size());
            }
            articles.sort(new sortArticle().reversed());
        } catch (DaoException e) {
            throw new DaoException(e.getMessage());
        }
        return articles;
    }

    // FIXME: 6/19/2019 перевести на дженерики
    class sortComment implements Comparator<CommentToArticle> {
        public int compare(CommentToArticle a, CommentToArticle b) {
            return Long.compare(a.getId(), b.getId());
        }
    }

    class sortArticle implements Comparator<Article> {
        public int compare(Article a, Article b) {
            return Long.compare(a.getId(), b.getId());
        }
    }

    @Override
    public boolean update(String name, String text, long articleId, int typeNews) throws DaoException {
        return false;
    }

    @Override
    public Article check(int articleId) throws DaoException {
        return null;
    }
}

