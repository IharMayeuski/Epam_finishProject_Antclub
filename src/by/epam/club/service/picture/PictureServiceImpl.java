package by.epam.club.service.picture;

import by.epam.club.dao.picturedao.PictureDao;
import by.epam.club.dao.picturedao.PictureDaoImpl;
import by.epam.club.exception.DaoException;
import by.epam.club.exception.ServiceException;

/**
 * Class of business logic for Picture
 *
 * @author Maeuski Igor
 * @version 1.0
 */

public class PictureServiceImpl implements PictureService {
    /**
     * @param pictureId for deleting picture from data base by id
     * @throws ServiceException in the case of mistake if we catch DaoException we will throw ServiceException
     */

    @Override
    public void deletePicture(String pictureId) throws ServiceException {
        PictureDao pictureDao = new PictureDaoImpl();
        try {
           pictureDao.delete(pictureId);
        } catch (DaoException e) {
            throw new ServiceException(e.getMessage());
        }
    }
}
