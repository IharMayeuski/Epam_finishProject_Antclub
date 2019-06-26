package by.epam.club.dao.typenewsdao;

import by.epam.club.entity.TypeNews;
import by.epam.club.exception.DaoException;

import java.util.List;
import java.util.Set;

public interface TypeNewsDao {
    List<TypeNews> takeUndeletedTypes() throws DaoException;

    void create(String type) throws DaoException;

    List<TypeNews> takeAllTypes() throws DaoException;
}
