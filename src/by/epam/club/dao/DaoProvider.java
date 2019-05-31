package by.epam.club.dao;

import by.epam.club.dao.impl.ArticleDaoImpl;
import by.epam.club.dao.impl.PictureDaoImpl;
import by.epam.club.dao.impl.TypeNewsDaoImpl;
import by.epam.club.dao.impl.UserDaoImpl;

public class DaoProvider {
    private static final DaoProvider instance = new DaoProvider();

    private final UserDao userDAO = new UserDaoImpl();
    private final ArticleDao articleDao = new ArticleDaoImpl();
    private final PictureDao pictureDao = new PictureDaoImpl();
    private final TypeNewsDao typeNewsDao = new TypeNewsDaoImpl();

    private DaoProvider() {
    }

    public UserDao getUserDao() {
        return userDAO;
    }

    public ArticleDao getArticleDao() {
        return articleDao;
    }

    public PictureDao getPictureDao() {
        return pictureDao;
    }

    public TypeNewsDao getTypeNewsDao() {
        return typeNewsDao;
    }

    public static DaoProvider getInstance() {
        return instance;
    }
}