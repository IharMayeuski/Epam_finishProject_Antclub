package by.epam.club.dao.articledao;

import by.epam.club.entity.Article;
import by.epam.club.exception.DaoException;

import java.util.List;
import java.util.Set;

public interface ArticleDao {
    boolean create(String name, String text, long userId, int typeNews) throws DaoException;
    List<Article> takeAllByTypeNews(int typeNews) throws DaoException;
    List<Article> takeAllByTypeNewsNotBannedNotDeleted(int typeNews) throws DaoException;
    boolean update(String name, String text, long articleId, int typeNews) throws DaoException;
    Article check(int articleId) throws DaoException;
}
