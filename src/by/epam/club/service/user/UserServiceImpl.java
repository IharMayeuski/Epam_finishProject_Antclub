package by.epam.club.service.user;

import by.epam.club.dao.TransactionHelper;
import by.epam.club.dao.userdao.UserDao;
import by.epam.club.dao.userdao.UserDaoImpl;
import by.epam.club.entity.Parameter;
import by.epam.club.entity.User;
import by.epam.club.exception.DaoException;
import by.epam.club.exception.ServiceException;
import by.epam.club.tool.PasswordEncryption;
import by.epam.club.tool.CredentialValidator;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.Part;
import java.util.Date;
import java.util.List;

import static by.epam.club.entity.Parameter.*;

public class UserServiceImpl implements UserService {
    private static final Logger LOGGER = LogManager.getLogger(UserServiceImpl.class);

    @Override
    public User checkUser(String login, String password) throws ServiceException {
        UserDao userDao = new UserDaoImpl();
        CredentialValidator credentialValidator = new CredentialValidator();
        PasswordEncryption encryption = new PasswordEncryption();
        Date date = new Date();
        String dateActivity = String.valueOf(date.toInstant().toEpochMilli());

        if (login == null || login.isEmpty() || password == null || password.isEmpty()) {
            throw new ServiceException(USER_EMAIL_EMPTY_MESSAGE);
        } else if (!credentialValidator.isCorrectLoginPassword(login, password)) {
            throw new ServiceException(USER_LOGIN_PASSWORD_CREDENTIAL);
        }
        try {
            String newPass = encryption.create(password);
            return userDao.checkUser(login, newPass, dateActivity);
        } catch (DaoException e) {
            throw new ServiceException(e.getMessage());
        }
    }

    @Override
    public User checkUser(String login) throws ServiceException {
        UserDao userDao = new UserDaoImpl();
        if (login == null || login.isEmpty()) {
            throw new ServiceException(EMPTY_MESSAGE);
        }
        try {
            return userDao.findUserByLogin(login);
        } catch (DaoException e) {
            throw new ServiceException(e.getMessage());
        }
    }

    @Override
    public void markDeleted(String login, String email, String password) throws ServiceException {
        PasswordEncryption encryption = new PasswordEncryption();
        UserDao userDao = new UserDaoImpl();
        if (login == null || login.isEmpty() || email == null || email.isEmpty()) {
            throw new ServiceException(EMPTY_MESSAGE);
        }
        try {
            String newPass = encryption.create(password);
            userDao.markUserDeleted(login, email, newPass);
        } catch (DaoException e) {
            throw new ServiceException(e.getMessage());
        }
    }

    @Override
    public boolean createUserMaster(String login, String email, String password, String password2) throws ServiceException {
        UserDaoImpl userDaoImpl = new UserDaoImpl();
        CredentialValidator credentialValidator = new CredentialValidator();
        User user;
        boolean value;
        if (!password.equals(password2))
            throw new ServiceException(USER_PASSWORD_UNCORRECT_MESSAGE);
        else if (login == null || login.isEmpty() || email == null || email.isEmpty() || password.isEmpty())
            throw new ServiceException(EMPTY_MESSAGE);
        else if (!credentialValidator.isCorrectLoginPassword(login, password)) {
            throw new ServiceException(USER_LOGIN_PASSWORD_UNCORRECT_MESSAGE);
        }
        checkEmailLogin(login, email);
        TransactionHelper transactionHelper = new TransactionHelper();
        try {
            transactionHelper.beginTransaction(userDaoImpl);
            PasswordEncryption encryption = new PasswordEncryption();
            String newPass = encryption.create(password);
            Date date = new Date();
            String newDate = String.valueOf(date.toInstant().toEpochMilli());
            userDaoImpl.createUser(login, email, newPass, newDate);
            user = userDaoImpl.findUserIdForRegistrationTransaction(login);
            String idForBase = Long.toString(user.getId());
            userDaoImpl.createIdInUserInfo(idForBase);
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
                LOGGER.error(UNKNOWN_MISTAKE_MESSAGE);
            }
        }
        return value; // FIXME: 6/15/2019  может убрать ретурн?
    }

    @Override
    public void updateUser(User user, String email, String login, String password1, String password2, String firstname, String familyname) throws ServiceException {
        CredentialValidator credentialValidator = new CredentialValidator();
        UserDao userDaoCheckLogin = new UserDaoImpl(); // fixme ИН правильно ли создавать несколько дао, если несколько разных запросов в методе
        UserDao userDaoCheckEmail = new UserDaoImpl();
        UserDao userDaoUpdateEmailLogin = new UserDaoImpl();
        UserDao userDaoUpdateInfo = new UserDaoImpl();
        PasswordEncryption encryption = new PasswordEncryption();
        String newPass;
        if(email.isEmpty()&&login.isEmpty()&&firstname.isEmpty()&&
                familyname.isEmpty()&&password1.isEmpty()&&password2.isEmpty()){
            throw new ServiceException(NOTHING_FOR_CHANGING_MESSAGE);
        }

        if (firstname!=null&&!firstname.isEmpty()) {
            if(!credentialValidator.isLogicSize(firstname)) {
                throw new ServiceException(VERY_LONG_PARAMETER_MESSAGE);
            }
        }else {
            firstname=user.getFirstname();
        }

        if (familyname!=null&&!familyname.isEmpty()) {
            if (!credentialValidator.isLogicSize(familyname)) {
                throw new ServiceException(VERY_LONG_PARAMETER_MESSAGE);
            }
        }else {
            familyname = user.getFamilyname();
        }

        if (login == null || user.getLogin().equals(login) || login.isEmpty()) {
            login = user.getLogin();
        } else {
            credentialValidator.isLogicSize(login);
            User checkSuchlogin = null;
            try {
                checkSuchlogin = userDaoCheckLogin.findUserByLogin(login);
            } catch (DaoException ignored) {
            }
            if (checkSuchlogin != null) {
                throw new ServiceException(USER_LOGIN_MESSAGE);
            }
        }

        if (email == null || user.getEmail().equals(email) || email.isEmpty()) {
            email = user.getEmail();
        } else {
            credentialValidator.isLogicSize(email);
            User checkSuchEmail = null;
            try {
                checkSuchEmail = userDaoCheckEmail.findUserByEmail(email);
            } catch (DaoException ignored) {
            }
            if (checkSuchEmail != null) {
                throw new ServiceException(USER_EMAIL_MESSAGE);
            }
        }

        try {
            if ((password1 == null && password2 == null) || (password1.isEmpty() && password2.isEmpty())) {
                userDaoUpdateEmailLogin.updateUserLoginEmail(user, login, email);
                userDaoUpdateInfo.updateUserInfo(user, firstname, familyname);
            } else if (password1 != null && password2 != null && !password1.equals(password2)) {
                throw new ServiceException(USER_PASSWORD_UNCORRECT_MESSAGE);
            } else {
                newPass = encryption.create(password1);
                userDaoUpdateEmailLogin.updateUser(user, login, email, newPass);
                userDaoUpdateInfo.updateUserInfo(user, firstname, familyname);
            }
        } catch (DaoException e) {
            throw new ServiceException(e.getMessage());
        }
    }

    @Override
    public void newPassword(String email) throws ServiceException {
        CredentialValidator credentialValidator = new CredentialValidator();
        UserDao userDao = new UserDaoImpl();

        if (email == null || email.isEmpty() || !credentialValidator.isLogicSize(email))
            throw new ServiceException(USER_EMAIL_EMPTY_MESSAGE);
        try {
            userDao.newPassword(email);
        } catch (DaoException e) {
            throw new ServiceException(e.getMessage());
        }
    }

    @Override
    public void createUserPic(String userId, Part part) throws ServiceException {
        UserDao userDao = new UserDaoImpl();
        if (part != null&&userId!=null) {
            try {
                userDao.createUserUploade(userId,part);
            } catch (DaoException e) {
                throw new ServiceException(e.getMessage());
            }
        }
    }

    @Override
    public List<User> takeAll() throws ServiceException {
        UserDao userDao = new UserDaoImpl();
        try {
            return userDao.takeAllUser();
        } catch (DaoException e) {
            throw new ServiceException(e.getMessage());
        }
    }

    @Override
    public List<User> takeDeleted() throws ServiceException {
        UserDao userDao = new UserDaoImpl();
        try {
            return userDao.takeAllUserDeleted();
        } catch (DaoException e) {
            throw new ServiceException(e.getMessage());
        }
    }

    @Override
    public List<User> takeBanned() throws ServiceException {
        UserDao userDao = new UserDaoImpl();
        try {
            return userDao.takeAllUserBanned();
        } catch (DaoException e) {
            throw new ServiceException(e.getMessage());
        }
    }


    private void checkEmailLogin(String login, String email) throws ServiceException {
        UserDao userDao = new UserDaoImpl();
        User user;
        try {
            user = userDao.findUserByLogin(login);

            if (user != null) {
                if (user.getDeleted().equals(Parameter.DELETED_PARAM)) {
                    throw new ServiceException(USER_LOGIN_DELETED_MESSAGE);
                } else {
                    throw new ServiceException(USER_LOGIN_MESSAGE);
                }
            }
            user = userDao.findUserByEmail(email);
            if (user != null) {
                if (user.getDeleted().equals(Parameter.DELETED_PARAM)) {
                    throw new ServiceException(USER_EMAIL_DELETED_MESSAGE);
                } else {
                    throw new ServiceException(USER_EMAIL_MESSAGE);
                }
            }
        } catch (DaoException e) {
            throw new ServiceException(e.getMessage());
        }
    }
}
