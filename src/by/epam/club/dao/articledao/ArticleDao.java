package by.epam.club.dao.articledao;

import by.epam.club.entity.Article;
import by.epam.club.exception.DaoException;

import javax.servlet.http.Part;
import java.io.InputStream;
import java.util.List;

public interface ArticleDao {
    boolean create(String name, String text, long userId, int typeNews) throws DaoException;

    List<Article> takeAllByTypeNews(int typeNews) throws DaoException;

    List<Article> takeAllByTypeNewsNotBannedNotDeleted(int typeNews) throws DaoException;

    Article findArticles(String title) throws DaoException;

    void delete(String articleId) throws DaoException;

    void createNewPic(String articleId, String text, InputStream is) throws DaoException;

    void uppPositive(String userId, String newsId, String rating) throws DaoException;

    void uppNegative(String userId, String newsId, String rating) throws DaoException;

    void writeDisliker(String userId, String articleId) throws DaoException;

    void writeLiker(String userId, String articleId) throws DaoException;

    void updateArticle(String articleId, String text)throws DaoException;

}
