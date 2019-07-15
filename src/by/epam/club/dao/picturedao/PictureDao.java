package by.epam.club.dao.picturedao;

import by.epam.club.exception.DaoException;
/**
 * Interface of level dao for working with Picture
 *
 * @author Maeuski Igor
 * @version 1.0
 */
public interface PictureDao {

    void delete(String pictureId) throws DaoException;
}
