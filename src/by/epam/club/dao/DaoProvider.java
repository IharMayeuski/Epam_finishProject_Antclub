package by.epam.club.dao;

import by.epam.club.dao.impl.UserDaoImpl;

public class DaoProvider {
    private static final DaoProvider instance = new DaoProvider();

    private final UserDao userDAO = new UserDaoImpl();

    private DaoProvider() {    }

    public UserDao getUserDAO() {
        return userDAO;
    }

    public static DaoProvider getInstance() {
        return instance;
    }
}