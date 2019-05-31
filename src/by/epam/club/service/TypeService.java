package by.epam.club.service;

import by.epam.club.entity.TypeNews;
import by.epam.club.exception.ServiceException;

import java.util.Set;

public interface TypeService {
    Set<TypeNews> takeAllUndeleted() throws ServiceException;
}
