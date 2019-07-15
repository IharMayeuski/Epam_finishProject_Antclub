package by.epam.club.service.picture;

import by.epam.club.exception.ServiceException;

/**
 * Interface of business logic for Picture
 *
 * @author Maeuski Igor
 * @version 1.0
 */

public interface PictureService {

    void deletePicture(String pictureId) throws ServiceException;
}
