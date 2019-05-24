package by.epam.club.dao;

import by.epam.club.entity.Article;
import by.epam.club.entity.Picture;
import by.epam.club.exception.DaoException;

import java.util.List;
import java.util.Set;

public interface ArticleDao {
    boolean create (String name, String text, long userId, int typeNews) throws DaoException;
    Set<Article> takeAllByTypeNews (int typeNews) throws DaoException;
    Set<Article> takeAllByTypeNewsNotBannedNotDeleted(int typeNews);
}
