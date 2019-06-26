package by.epam.club.service.article;

import by.epam.club.entity.Article;
import by.epam.club.exception.ServiceException;

import java.util.List;

public interface ArticleService {
    List<Article> takeAllUnbannedUndeletedArticles(int typeNews) throws ServiceException;
}

