package by.epam.club.service.impl;

import by.epam.club.dao.ArticleDao;
import by.epam.club.dao.DaoProvider;
import by.epam.club.dao.TypeNewsDao;
import by.epam.club.entity.TypeNews;
import by.epam.club.exception.DaoException;
import by.epam.club.exception.ServiceException;
import by.epam.club.service.TypeService;

import java.util.Set;

public class TypeServiceImpl implements TypeService {
    DaoProvider daoProvider = DaoProvider.getInstance();
    TypeNewsDao typeNewsDao = daoProvider.getTypeNewsDao();

    @Override
    public Set<TypeNews> takeAllUndeleted() throws ServiceException {
        try {
            return typeNewsDao.takeTypes();
        } catch (DaoException e) {
            throw new ServiceException(e.getMessage());
        }
    }
}
