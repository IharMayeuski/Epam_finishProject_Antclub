package by.epam.club.dao.userdao;

import by.epam.club.dao.basedao.BaseDao;
import by.epam.club.dao.basedao.BaseUploadPic;
import by.epam.club.tool.Sender;
import by.epam.club.entity.User;
import by.epam.club.exception.DaoException;
import by.epam.club.tool.PasswordEncryption;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.mail.MessagingException;
import javax.servlet.http.Part;
import java.util.*;

import static by.epam.club.dao.SqlQuery.*;
import static by.epam.club.entity.Parameter.*;

/**
 * Class of level dao for working with User
 *
 * @author Maeuski Igor
 * @version 1.0
 */

@SuppressWarnings("unchecked")
public class UserDaoImpl extends BaseDao implements UserDao {
    private static final Logger LOGGER = LogManager.getLogger(UserDaoImpl.class);
    private static final int NUMBER_PASSWORD_NEW = 1_000_000;

    /**
     *
     * @param login user's login
     * @param password user's password
     * @param date_activity the time of last activity (time is now)
     * @return User to the logic level
     * @throws DaoException for catching it on the logic level in the case of exception
     */
    @Override
    public User checkUser(String login, String password, String date_activity) throws DaoException {
        List<User> user;
        user = find(USER_CHECK_LOGIN_AND_PASSWORD.getQuery(), USER_PARAM, login, password);
        if (!user.isEmpty()) {
            create(USER_UPDATE_DATE_INPUT.getQuery(), date_activity, Long.toString(user.get(0).getId()));
            return user.get(0);
        } else {
            throw new DaoException(USER_LOGIN_PASSWORD_UNCORRECT_MESSAGE);
        }
    }

    /**
     *
     * @param user for getting from this parameter user's id
     * @param login user's login
     * @param email user's email
     * @param password user's password
     * @return true in the case if all is ok
     * @throws DaoException for catching it on the logic level in the case of exception
     */
    @Override
    public boolean updateUser(User user, String login, String email, String password) throws DaoException {
        create(USER_UPDATE_DATA.getQuery(), login, email, password, Long.toString(user.getId()));
        return true;
    }

    /**
     *
     * @param login user's login
     * @param email user's email
     * @param newPass user's password
     * @param newDate user's date of creating user
     * @return true in the case if all is ok and method is finished without any exception
     * @throws DaoException for catching it on the logic level in the case of exception
     */
    @Override
    public boolean createUser(String login, String email, String newPass, String newDate) throws DaoException {
        create(USER_INSERT_NEW.getQuery(), login, email, newPass, newDate);
        return true;
    }

    /**
     *
     * @param idForBase can help to create user's id in the other table - info
     * @throws DaoException for catching it on the logic level in the case of exception
     */
    @Override
    public void createIdInUserInfo(String idForBase) throws DaoException {
        create(USER_INSERT_NEW_INFO.getQuery(), idForBase);
    }

    /**
     *
     * @param login user's login
     * @param email user's email
     * @param password user's password
     * @throws DaoException for catching it on the logic level in the case of exception
     */
    @Override
    public void markUserDeleted(String login, String email, String password) throws DaoException {
        List<User> user;
        user = find(USER_CHECK_LOGIN_EMAIL_PASSWORD.getQuery(), USER_LOGIN_PARAM, login, email, password);
        if (!user.isEmpty()) {
            create(USER_MARK_DELETED.getQuery(), login);
        } else {
            throw new DaoException(USER_LOGIN_PASSWORD_UNCORRECT_MESSAGE);
        }
    }

    /**
     *
     * @param userId it is user's id
     * @throws DaoException for catching it on the logic level in the case of exception
     */
    @Override
    public void markUserDeleted(String userId) throws DaoException {
        create(USER_MARK_DELETED.getQuery(), userId);
    }

    /**
     *
     * @param findUser Object User for getting on next step login
     * @throws DaoException for catching it on the logic level in the case of exception
     */
    @Override
    public void markUser(User findUser) throws DaoException {
        create(USER_UPDATE_ROLE_USER.getQuery(), findUser.getLogin());
    }

    /**
     *
     * @param findUser Object User for getting on next step login
     * @throws DaoException for catching it on the logic level in the case of exception
     */
    @Override
    public void markAdmin(User findUser) throws DaoException {
        create(USER_UPDATE_ROLE_ADMIN.getQuery(), findUser.getLogin());
    }
    /**
     *
     * @param userId it is real user's id
     * @throws DaoException for catching it on the logic level in the case of exception
     */
    @Override
    public void markUserUndeleted(String userId) throws DaoException {
        create(USER_MARK_UNDELETED.getQuery(), userId);
    }
    /**
     *
     * @param userId it is real user's id
     * @throws DaoException for catching it on the logic level in the case of exception
     */
    @Override
    public void markUserBanned(String userId) throws DaoException {
            create(USER_MARK_BANNED.getQuery(), userId);
    }
    /**
     *
     * @param userId it is real user's id
     * @throws DaoException for catching it on the logic level in the case of exception
     */
    @Override
    public void markUserUnbanned(String userId) throws DaoException {
        create(USER_MARK_UNBANNED.getQuery(), userId);
    }

    /**
     *
     * @return {@code List<User>} of our Users
     * @throws DaoException for catching it on the logic level in the case of exception
    */
    @Override
    public List<User> takeAllUser() throws DaoException {
        Comparator<User> comparator = Comparator.comparing(User::getLogin);
        List<User> user;
        user = find(USER_FIND_ALL_USER.getQuery(), USER_PARAM);
        user.sort(comparator);
        return user;
    }
    /**
     *
     * @return {@code List<User>} of our Users
     * @throws DaoException for catching it on the logic level in the case of exception
     */
    @Override
    public List<User> takeAllUserUndeleted() throws DaoException {
        Comparator<User> comparator = Comparator.comparing(User::getLogin);
        List<User> user;
        user = find(USER_FIND_ALL_UNDELETED_USER.getQuery(), USER_PARAM);
        user.sort(comparator);
        return user;
    }
    /**
     *
     * @return {@code List<User>} of our Users
     * @throws DaoException for catching it on the logic level in the case of exception
     */
    @Override
    public List<User> takeAllUserDeleted() throws DaoException {
        Comparator<User> comparator = Comparator.comparing(User::getLogin);
        List<User> user;
        user = find(USER_FIND_ALL_DELETED_USER.getQuery(), USER_PARAM);
        user.sort(comparator);
        return user;
    }
    /**
     *
     * @return {@code List<User>} of our Users
     * @throws DaoException for catching it on the logic level in the case of exception
     */
    @Override
    public List<User> takeAllUserBanned() throws DaoException {
        Comparator<User> comparator = Comparator.comparing(User::getLogin);
        List<User> user;
        user = find(USER_FIND_ALL_BANNED_USER.getQuery(), USER_PARAM);
        user.sort(comparator);
        return user;
    }

    /**
     *
     * @param login it is user's login
     * @return User after searching them in data base
     * @throws DaoException for catching it on the logic level in the case of exception
     */
    @Override
    public User findUserByLogin(String login) throws DaoException {
        return findUser(USER_FIND_USER_BY_LOGIN.getQuery(), USER_PARAM, login);
    }

    /**
     *
     * @param email it is user's email
     * @return User after searching them in data base
     * @throws DaoException for catching it on the logic level in the case of exception
     */
    @Override
    public User findUserByEmail(String email) throws DaoException {
        return findUser(USER_FIND_USER_BY_EMAIL.getQuery(), USER_PARAM, email);
    }

    /**
     *
     * @param email user's email
     * @throws DaoException for catching it on the logic level in the case of exception
     */
    @Override
    public void newPassword(String email) throws DaoException {
        int countForPassword = NUMBER_PASSWORD_NEW;
        List<User> users;
        int random = (int) (Math.random() * countForPassword + countForPassword);
        String passwordForUser = Integer.toHexString(random);
        PasswordEncryption encryption = new PasswordEncryption();
        String newPassForDb = encryption.create(passwordForUser);
        users = find(USER_CHECK_EMAIL.getQuery(), USER_EMAIL_PARAM, email);
        if (!users.isEmpty()) {
            create(USER_CHANGE_PASSWORD.getQuery(), newPassForDb, email);
        } else {
            LOGGER.info(USER_IS_EMPTY_PARAM);
            throw new DaoException(NO_USER_MESSAGE);
        }
        Sender sender = new Sender();
        try {
            sender.send(passwordForUser, email);
        } catch (MessagingException e) {
            LOGGER.error(SOMETHING_BAD_WITH_SEND_MESSAGE, e);
            throw new DaoException(UNKNOWN_MISTAKE_MESSAGE);
        }
    }

    /**
     *
     * @param user  this Object User necessary for getting from them user's id
     * @param login user's login
     * @param email user's email
     * @throws DaoException for catching it on the logic level in the case of exception
     */
    @Override
    public void updateUserLoginEmail(User user, String login, String email) throws DaoException {
        create(USER_UPDATE_DATA_EMAIL.getQuery(), login, email, Long.toString(user.getId()));
    }

    /**
     *
     * @param user this oject necessary for getting from it user's id
     * @param firstname user's first name
     * @param familyname user's family name
     * @throws DaoException for catching it on the logic level in the case of exception
     */
    @Override
    public void updateUserInfo(User user, String firstname, String familyname) throws DaoException {
        create(USER_UPDATE_INFO.getQuery(), firstname, familyname, Long.toString(user.getId()));
    }

    /**
     *
     * @param userId user's id
     * @param part picture for uploading to data base
     * @throws DaoException for catching it on the logic level in the case of exception
     */
    @Override
    public void createUserUploade(String userId, Part part) throws DaoException {
        BaseUploadPic baseUploadPic = new BaseUploadPic();
        baseUploadPic.uploadPicture(USER_UPDATE_PIC.getQuery(), part, userId);
    }

    /**
     *
     * @param query this query is necessary for creating sql script
     * @param table for understanding on the class EntityCreator the method of working
     * @param login user's login
     * @return {@User}
     * @throws DaoException for catching it on the logic level in the case of exception
     */
    private User findUser(String query, String table, String login) throws DaoException {
        List<User> user;
        user = find(query, table, login);
        if (!user.isEmpty()) {
            return user.get(0);
        } else {
            return null;
        }
    }

    /**
     *
     * @param login user's login
     * @return User
     * @throws DaoException for catching it on the logic level in the case of exception
     */
    public User findUserIdForRegistrationTransaction(String login) throws DaoException {
        return findUser(USER_FIND_USER_ID.getQuery(), USER_ID_PARAM, login);
    }
}
