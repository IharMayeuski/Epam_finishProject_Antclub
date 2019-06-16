package by.epam.club.dao;

import by.epam.club.entity.TypeNews;
import by.epam.club.exception.DaoException;

import java.util.Set;

public interface TypeNewsDao {
    Set<TypeNews> takeTypes() throws DaoException;
}
