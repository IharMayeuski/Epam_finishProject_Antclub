package by.epam.club.service.article;

import by.epam.club.entity.Article;
import by.epam.club.exception.DaoException;
import by.epam.club.exception.ServiceException;

import javax.servlet.http.Part;
import java.io.InputStream;
import java.util.List;

/**
 * Interface of business logic for Article
 *
 * @author Maeuski Igor
 * @version 1.0
 */

public interface ArticleService {

    List<Article> takeAllUnbannedUndeletedArticles(int typeNews) throws ServiceException;

    void createNew(String fileName, String title, String text, long userId, int typeId, Part part) throws ServiceException;

    void deleteArticle(String articleId) throws ServiceException;

    void createNewPicture(String articleId, String text, Part part) throws ServiceException;

    void undeleteType(int typeNews) throws ServiceException;

    void deleteType(int typeNews) throws ServiceException;

    void uppPositiveRating(String userId, String newsId, String positiveRating) throws ServiceException;

    void uppNegativeRating(String userId, String newsId, String negativeRating) throws ServiceException;

    void updateArticle(String articleId, String text) throws ServiceException;
}

