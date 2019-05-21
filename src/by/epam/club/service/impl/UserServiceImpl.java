package by.epam.club.service.impl;

import by.epam.club.dao.DaoProvider;
import by.epam.club.dao.UserDao;
import by.epam.club.entity.User;
import by.epam.club.exception.DaoException;
import by.epam.club.exception.ServiceException;
import by.epam.club.service.UserService;
import by.epam.club.service.validation.CredentialValidator;

public class UserServiceImpl implements UserService {
    DaoProvider daoProvider = DaoProvider.getInstance();
    UserDao userDAO = daoProvider.getUserDAO();

    @Override
    public User checkUser(String login, String password) throws ServiceException {

        if (!CredentialValidator.isCorrect(login, password)) {
            throw new ServiceException("Un correct login or password" + login + ", " + password);
        }
        try {
            return userDAO.check(login, password);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public boolean createUserMaster(String login, String email, String password1, String password2) throws ServiceException {
        if (!password1.equals(password2))
            throw new ServiceException("Please to check your password");
        if (login == null || email == null || password1.isEmpty())
            throw new ServiceException("Login or email or password is empty. Please to check");
        try {
            return userDAO.createUser(login, email, password1);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }

    }
}
