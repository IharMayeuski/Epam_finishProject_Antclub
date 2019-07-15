package by.epam.club.dao.typenewsdao;

import by.epam.club.entity.TypeNews;
import by.epam.club.exception.DaoException;

import java.util.List;

/**
 *Interface of level dao for working with TypeNews
 *
 * @author Maeuski Igor
 * @version 1.0
 */
public interface TypeNewsDao {
    List<TypeNews> takeUndeletedTypes() throws DaoException;

    void create(String type) throws DaoException;

    List<TypeNews> takeAllTypes() throws DaoException;

    TypeNews takeOne(int type) throws DaoException;

    void markUndeleted(int typeNews) throws DaoException;

    void markDeleted(int typeNews) throws DaoException;
}
