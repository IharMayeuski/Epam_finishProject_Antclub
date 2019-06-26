package by.epam.club.service.article;

import by.epam.club.dao.articledao.ArticleDao;
import by.epam.club.dao.articledao.ArticleDaoImpl;
import by.epam.club.entity.Article;
import by.epam.club.exception.DaoException;
import by.epam.club.exception.ServiceException;

import java.util.List;

public class ArticleServiceImpl implements ArticleService {
    @Override
    public List<Article> takeAllUnbannedUndeletedArticles(int typeNews) throws ServiceException {
        ArticleDao articleDao = new ArticleDaoImpl();

        try {
            return articleDao.takeAllByTypeNewsNotBannedNotDeleted(typeNews);
        } catch (DaoException e) {
            throw new ServiceException(e.getMessage());
        }
    }
}
