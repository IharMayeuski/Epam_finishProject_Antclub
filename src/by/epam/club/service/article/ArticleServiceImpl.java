package by.epam.club.service.article;

import by.epam.club.dao.TransactionHelper;
import by.epam.club.dao.articledao.ArticleDao;
import by.epam.club.dao.articledao.ArticleDaoImpl;
import by.epam.club.dao.basedao.BaseDao;
import by.epam.club.dao.typenewsdao.TypeNewsDao;
import by.epam.club.dao.typenewsdao.TypeNewsDaoImpl;
import by.epam.club.entity.Article;
import by.epam.club.exception.DaoException;
import by.epam.club.exception.ServiceException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.Part;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import static by.epam.club.dao.SqlQuery.*;
import static by.epam.club.entity.Parameter.*;

/**
 * Class of business logic for Article
 *
 * @author Maeuski Igor
 * @version 1.0
 */
// fixme повторяемый код эксепшенов

public class ArticleServiceImpl extends BaseDao implements ArticleService {
    private static final Logger LOGGER = LogManager.getLogger(ArticleServiceImpl.class);
    /**
     * @param typeNews for taking articles by typeNews id
     * @return {@code List<Article>} all unbanned and not deleted articles
     * @throws ServiceException in the case of mistake if we catch DaoException we will throw ServiceException
     */

    @Override
    public List<Article> takeAllUnbannedUndeletedArticles(int typeNews) throws ServiceException {
        ArticleDao articleDao = new ArticleDaoImpl();
        try {
            return articleDao.takeAllByTypeNewsNotBannedNotDeleted(typeNews);
        } catch (DaoException e) {
            throw new ServiceException(e.getMessage());
        }
    }
    /**
     * @param fileName of file name our article
     * @param title the article's title
     * @param text the article's text
     * @param userId who creates this article
     * @param typeId the id of type news
     * @param part our picture for article
     * @throws ServiceException in the case of mistake if we catch DaoException we will throw ServiceException
     */
    @Override
    public void createNew(String fileName, String title, String text, long userId, int typeId, Part part) throws ServiceException {
        ArticleDaoImpl articleDao = new ArticleDaoImpl();
        Article article;
        try {
            if (articleDao.find(ARTICLE_FIND_ONE_BY_TITLE.getQuery(), ARTICLES_PARAM, title).size() > 0) {
                throw new ServiceException(WE_HAVE_ARTICLE_LIKE_THIS_MESSAGE);
            }
        } catch (DaoException e) {
            throw new ServiceException(e.getMessage());
        }
        TransactionHelper transactionHelper = new TransactionHelper();
        try {
            transactionHelper.beginTransaction(articleDao);
            articleDao.create(title, text, userId, typeId);
            article = (Article) articleDao.find(ARTICLE_FIND_ONE_BY_TITLE.getQuery(), ARTICLES_PARAM, title).get(0);
            String idForBase = Long.toString(article.getId());
            InputStream is;
            try {
                is = part.getInputStream();
            } catch (IOException e) {
                LOGGER.error(UNKNOWN_MISTAKE_MESSAGE);
                throw new ServiceException(UNKNOWN_MISTAKE_MESSAGE);
            }
            articleDao.uploadPic(PICTURE_INSERT_NEW.getQuery(), is, fileName, idForBase);
            transactionHelper.commit();
        } catch (DaoException e) { // FIXME: 7/7/2019 dublicat
            try {
                transactionHelper.rollback();
            } catch (DaoException ee) {
                throw new ServiceException(ee.getMessage());
            }
            throw new ServiceException(e.getMessage());
        } finally {
            try {
                transactionHelper.endTransaction();
            } catch (DaoException e) {
                LOGGER.error(UNKNOWN_MISTAKE_MESSAGE);
            }
        }
    }
    /**
     * @param articleId for delete id
     * @throws ServiceException in the case of mistake if we catch DaoException we will throw ServiceException
     */

    @Override
    public void deleteArticle(String articleId) throws ServiceException {
        ArticleDao articleDao = new ArticleDaoImpl();
        try {
            articleDao.delete(articleId);
        } catch (
                DaoException e) {
            throw new ServiceException(e.getMessage());
        }
    }
    /**
     * @param articleId for what article is our picture
     * @param text the name of picture
     * @param part still one picture for article
     * @throws ServiceException in the case of mistake if we catch DaoException we will throw ServiceException
     */

    @Override
    public void createNewPicture(String articleId, String text, Part part) throws ServiceException {
        ArticleDao articleDao = new ArticleDaoImpl();
        try {
            InputStream is = part.getInputStream();
            articleDao.createNewPic(articleId, text, is);
        } catch (
                DaoException e) {
            throw new ServiceException(e.getMessage());
        } catch (IOException e) {
            throw new ServiceException(UNKNOWN_MISTAKE_MESSAGE);
        }
    }
    /**
     * @param typeNews id for mark type of not deleted
     * @throws ServiceException in the case of mistake if we catch DaoException we will throw ServiceException
     */

    @Override
    public void undeleteType(int typeNews) throws ServiceException {
        TypeNewsDao typeDao = new TypeNewsDaoImpl();
        try {
            typeDao.markUndeleted(typeNews);
        } catch (DaoException e) {
            throw new ServiceException(e.getMessage());
        }
    }
    /**
     * @param typeNews id for mark type of deleted
     * @throws ServiceException in the case of mistake if we catch DaoException we will throw ServiceException
     */

    @Override
    public void deleteType(int typeNews) throws ServiceException {
        TypeNewsDao typeDao = new TypeNewsDaoImpl();
        try {
            typeDao.markDeleted(typeNews);
        } catch (DaoException e) {
            throw new ServiceException(e.getMessage());
        }
    }
    /**
     * @param userId it is id of user for upp raiting
     * @param newsId it is id of news for upp
     * @param positiveRating new raiting for our news
     * @throws ServiceException in the case of mistake if we catch DaoException we will throw ServiceException
     */

    @Override
    public void uppPositiveRating(String userId, String newsId, String positiveRating) throws ServiceException {
        ArticleDaoImpl articleDao = new ArticleDaoImpl();
        boolean result;
        try {
            result = articleDao.checkLiker(userId, newsId);
            if (result) {
                throw new ServiceException(WE_HAVE_THIS_LIKER_MESSAGE);
            }
        } catch (DaoException e) {
            LOGGER.error(e.getMessage());
            throw new ServiceException(e.getMessage());
        }
        TransactionHelper transactionHelper = new TransactionHelper();
        try {
            int rating = Integer.parseInt(positiveRating) + 1;
            transactionHelper.beginTransaction(articleDao);
            articleDao.uppPositive(userId, newsId, Integer.toString(rating));
            articleDao.writeLiker(userId, newsId);
            transactionHelper.commit();
        } catch (DaoException e) {
            try {
                transactionHelper.rollback();
            } catch (DaoException e1) {
                throw new ServiceException(e1.getMessage());
            }
            throw new ServiceException(e.getMessage());
        } finally {
            try {
                transactionHelper.endTransaction();
            } catch (DaoException e) {
                LOGGER.error(UNKNOWN_MISTAKE_MESSAGE);
            }
        }
    }
    /**
     * @param userId it is id of user for upp raiting
     * @param newsId it is id of news for upp
     * @param negativeRating new raiting for our news
     * @throws ServiceException in the case of mistake if we catch DaoException we will throw ServiceException
     */

    @Override
    public void uppNegativeRating(String userId, String newsId, String negativeRating) throws ServiceException {
        ArticleDaoImpl articleDao = new ArticleDaoImpl();
        boolean result;
        try {
            result = articleDao.checkDisliker(userId, newsId);
            if (result) {
                throw new ServiceException(WE_HAVE_THIS_LIKER_MESSAGE);
            }
        } catch (DaoException e) {
            LOGGER.error(e.getMessage());
            throw new ServiceException(e.getMessage());
        }
        TransactionHelper transactionHelper = new TransactionHelper();
        try {
            int rating = Integer.parseInt(negativeRating) + 1;
            transactionHelper.beginTransaction(articleDao);
            articleDao.uppNegative(userId, newsId, Integer.toString(rating));
            articleDao.writeDisliker(userId, newsId);
            transactionHelper.commit();
        } catch (DaoException e) {
            try {
                transactionHelper.rollback();
            } catch (DaoException e1) {
                throw new ServiceException(e1.getMessage());
            }
            throw new ServiceException(e.getMessage());
        } finally {
            try {
                transactionHelper.endTransaction();
            } catch (DaoException e) {
                LOGGER.error(UNKNOWN_MISTAKE_MESSAGE);
            }
        }
    }
    /**
     * @param articleId we are updating text of our article
     * @param text it is a text for updating
     * @throws ServiceException in the case of mistake if we catch DaoException we will throw ServiceException
     */

    @Override
    public void updateArticle(String articleId, String text) throws ServiceException {
        ArticleDao articleDao = new ArticleDaoImpl();
        try {
           articleDao.updateArticle(articleId, text);
        } catch (DaoException e) {
            throw new ServiceException(e.getMessage());
        }
    }
}



