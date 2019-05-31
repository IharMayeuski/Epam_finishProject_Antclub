package by.epam.club.service.impl;

import by.epam.club.dao.ArticleDao;
import by.epam.club.dao.DaoProvider;
import by.epam.club.entity.Article;
import by.epam.club.exception.DaoException;
import by.epam.club.exception.ServiceException;
import by.epam.club.service.ArticleService;

import java.util.Set;

public class ArticleServiceImpl implements ArticleService {
    DaoProvider daoProvider = DaoProvider.getInstance();
    ArticleDao articleDao = daoProvider.getArticleDao();

    @Override
    public Set<Article> takeAllUnbannedUndeletedArticles(int typeNews) throws ServiceException {

        try {
            return articleDao.takeAllByTypeNewsNotBannedNotDeleted(typeNews);
        } catch (DaoException e) {
            throw new ServiceException(e.getMessage());
        }
    }
}
