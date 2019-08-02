package by.epam.club.dao.articledao;

import by.epam.club.dao.basedao.BaseDao;
import by.epam.club.dao.basedao.BaseUploadPic;
import by.epam.club.entity.*;
import by.epam.club.exception.DaoException;
import java.io.InputStream;
import java.util.*;

import static by.epam.club.dao.SqlQuery.*;
import static by.epam.club.entity.Parameter.*;

@SuppressWarnings("unchecked")
public class ArticleDaoImpl extends BaseDao implements ArticleDao {

    @Override
    public boolean create(String name, String text, long userId, int typeNews) throws DaoException {
        Date date = new Date();
        try {
            create(ARTICLE_INSERT_NEW.getQuery(), name, text, Long.toString(date.toInstant().toEpochMilli()), Long.toString(userId), Integer.toString(typeNews));
        } catch (DaoException e) {
            throw new DaoException(e.getMessage());
        }
        return true;
    }

    @Override
    public List<Article> takeAllByTypeNews(int typeNews) throws DaoException {
        List<Article> articles;
        List<Comment> comments;
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
        List<Comment> comments;
        List<Picture> pictures;
        try {
            articles = find(ARTICLE_ALL_BY_TYPE_NEWS_NOTBANNED_NOTDELETED.getQuery(), ARTICLES_PARAM, Integer.toString(typeNews));
            for (Article article : articles) {
                comments = find(COMMENT_CHECK.getQuery(), COMMENT_PARAM, Long.toString(article.getId()));
                comments.sort(new sortComment());
                article.setComments(comments);
                article.setCommentQuantity(comments.size());
                pictures = find(PICTURE_CHECK_NOTDELETED_NOTBANNED.getQuery(), PICTURE_PARAM, Long.toString(article.getId()));
                article.setPictures(pictures);
            }
            articles.sort(new sortArticle().reversed());
        } catch (DaoException e) {
            throw new DaoException(e.getMessage());
        }
        return articles;
    }

    @Override
    public void writeDisliker(String userId, String articleId) throws DaoException {
        create(DISLIKER_ADD.getQuery(), userId, articleId);
    }

    @Override
    public void writeLiker(String userId, String articleId) throws DaoException {
        create(LIKER_ADD.getQuery(), userId, articleId);
    }

    @Override
    public void updateArticle(String articleId, String text) throws DaoException {
        create(ARTICLE_TEXT_UPDATE.getQuery(), text, articleId);
    }

    public boolean checkDisliker(String userId, String newsId) throws DaoException {
        List<Dislaker> dislakers = find(DISLIKER_CHECK.getQuery(), DISLAKER_PARAM, userId, newsId);
        return dislakers.size()>0;
    }

    public boolean checkLiker(String userId, String newsId) throws DaoException {
        List<Liker> likers = find(LIKER_CHECK.getQuery(), LIKER_PARAM, userId, newsId);
        return likers.size() > 0;
    }

    class sortComment implements Comparator<Comment> {
        public int compare(Comment a, Comment b) {
            return Long.compare(a.getId(), b.getId());
        }
    }

    class sortArticle implements Comparator<Article> {
        public int compare(Article a, Article b) {
            return Long.compare(a.getId(), b.getId());
        }
    }

    @Override
    public Article findArticles(String title) throws DaoException {
        List<Article> articles;
        articles = find(TYPE_FIND_ONE_BY_TYPE.getQuery(), title);
        return articles.get(0);
    }

    @Override
    public void delete(String articleId) throws DaoException {
        create(ARTICLE_MARK_DELETED.getQuery(), articleId);
    }

    @Override
    public void createNewPic(String articleId, String text, InputStream is) throws DaoException {
        BaseUploadPic baseUploadPic = new BaseUploadPic();
        baseUploadPic.uploadPic(ARTICLE_DOWNLOAD_NEW_PIC.getQuery(), is, text, articleId);
    }

    @Override
    public void uppPositive(String userId, String newsId, String positiveRating) throws DaoException {
        create(ARTICLE_UP_POSITIVE.getQuery(), positiveRating, newsId);
    }

    @Override
    public void uppNegative(String userId, String newsId, String negativeRating) throws DaoException {
        create(ARTICLE_UP_NEGATIVE.getQuery(), negativeRating, newsId);
    }
}

