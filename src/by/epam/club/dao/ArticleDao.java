package by.epam.club.dao;

import by.epam.club.entity.Article;
import by.epam.club.entity.Picture;
import by.epam.club.exception.DaoException;

import java.util.List;

public interface ArticleDao {
    boolean create (String name, String text, int userId, int typeNews) throws DaoException;
}
