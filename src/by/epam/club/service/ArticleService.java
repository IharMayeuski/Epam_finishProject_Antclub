package by.epam.club.service;

import by.epam.club.entity.Article;
import by.epam.club.exception.ServiceException;

import java.util.Set;

public interface ArticleService {
    Set<Article> takeAllUnbannedUndeletedArticles(int typeNews) throws ServiceException;
}

