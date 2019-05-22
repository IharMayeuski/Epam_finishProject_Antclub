package by.epam.club.dao;

import by.epam.club.exception.DaoException;

import javax.servlet.http.Part;

public interface PictureDao {
    boolean create (String name, Part part, String picOwner, int idOwner) throws DaoException;
}
