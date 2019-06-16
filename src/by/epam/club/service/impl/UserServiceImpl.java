package by.epam.club.service.impl;

import by.epam.club.dao.DaoProvider;
import by.epam.club.dao.TransactionHelper;
import by.epam.club.dao.UserDao;
import by.epam.club.dao.impl.UserDaoImpl;
import by.epam.club.entity.User;
import by.epam.club.exception.DaoException;
import by.epam.club.exception.ServiceException;
import by.epam.club.service.UserService;
import by.epam.club.validation.CredentialValidator;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class UserServiceImpl implements UserService {
    private static final Logger LOGGER = LogManager.getLogger(UserServiceImpl.class);

    private UserDao userDAO;

    public UserServiceImpl() {// FIXME: 6/6/2019 Показать ментору
        DaoProvider daoProvider = DaoProvider.getInstance();
        userDAO = daoProvider.getUserDao();
    }

    @Override
    public User checkUser(String login, String password) throws ServiceException {
        CredentialValidator credentialValidator = new CredentialValidator();

        if (login == null || login.isEmpty() || password == null || password.isEmpty()) {
            throw new ServiceException("user.email.empty");
        } else if (!credentialValidator.isCorrectLoginPassword(login, password)) {
            throw new ServiceException("user.login.password.credential");
        }
        try {


            return userDAO.checkUser(login, password);
        } catch (DaoException e) {
            throw new ServiceException(e.getMessage());
        }
    }

    @Override
    public User checkUser(String login) throws ServiceException {
        CredentialValidator credentialValidator = new CredentialValidator();

        if (login == null || login.isEmpty()) {
            throw new ServiceException("user.login.empty");
        }
        try {
            return userDAO.findUserByLogin(login);
        } catch (DaoException e) {
            throw new ServiceException(e.getMessage());
        }
    }

    @Override
    public void markDeleted(String login, String email, String password) throws ServiceException {
        if (login == null || login.isEmpty() || email == null || email.isEmpty()) {
            throw new ServiceException("user.email.empty");
        }
        try {
            userDAO.markUserDeleted(login, email, password);
        } catch (DaoException e) {
            throw new ServiceException(e.getMessage());
        }
    }

    @Override
    public boolean createUserMaster(String login, String email, String password, String password2) throws ServiceException {
        UserDaoImpl userDaoImpl = new UserDaoImpl();
        CredentialValidator credentialValidator = new CredentialValidator();
        TransactionHelper transactionHelper = new TransactionHelper();
        User user;
        boolean value = false;
        if (!password.equals(password2))
            throw new ServiceException("user.password.uncorrect");
        else if (login == null || login.isEmpty() || email == null || email.isEmpty() || password.isEmpty())
            throw new ServiceException("user.email.empty");
        else if (!credentialValidator.isCorrectLoginPassword(login, password)) {
            throw new ServiceException("user.login.password.uncorrect");
        }

        try {
            userDaoImpl.checkLogin(login);
            userDaoImpl.checkEmail(email);
            try {
                transactionHelper.beginTransaction(userDaoImpl);
                userDaoImpl.createUser(login, email, password);
                user = userDaoImpl.findUserByRegistration(login);
                long id = user.getId();
                userDaoImpl.createIdInUserInfo(id);

                transactionHelper.commit();
                value = true;

            } catch (DaoException e) {
                try {
                    transactionHelper.rollback();
                } catch (DaoException e1) {
                    e.printStackTrace();
                    throw new ServiceException(e1.getMessage());
                }
                throw new ServiceException(e.getMessage());
            } finally {
                try {
                    transactionHelper.endTransaction();
                } catch (DaoException e) {
                    e.printStackTrace();
                    throw new ServiceException(e.getMessage());
                }
            }
        } catch (DaoException e) {
            e.printStackTrace();
        }


        return value; // FIXME: 6/15/2019  может убрать ретурн?
    }


    @Override
    public void newPassword(String email) throws ServiceException {
        CredentialValidator credentialValidator = new CredentialValidator();
        if (email == null || email.isEmpty() || !credentialValidator.isLogicCorrect(email))
            throw new ServiceException("user.email.empty");
        try {
            userDAO.newPassword(email);
        } catch (DaoException e) {
            throw new ServiceException(e.getMessage());
        }
    }
}
