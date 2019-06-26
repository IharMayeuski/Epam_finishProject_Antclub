package by.epam.club.dao.picturedao;

import by.epam.club.entity.Picture;
import by.epam.club.exception.DaoException;

import javax.servlet.http.Part;

public interface PictureDao {
    boolean create(String pictureName, String filePath, long articleId) throws DaoException;
    boolean markDelete(Picture picture) throws DaoException;
    boolean markBannedUnbanned(Picture picture) throws DaoException;

}
