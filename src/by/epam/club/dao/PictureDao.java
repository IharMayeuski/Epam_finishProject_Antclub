package by.epam.club.dao;

import by.epam.club.entity.Picture;
import by.epam.club.exception.DaoException;

import javax.servlet.http.Part;

public interface PictureDao {
    boolean create (String pictureName, String filePath, int articleId) throws DaoException;
    boolean delete(Picture picture) throws DaoException;
}
