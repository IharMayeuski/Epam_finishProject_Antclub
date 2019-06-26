package by.epam.club.dao.userdao;

import by.epam.club.dao.basedao.BaseDao;
import by.epam.club.dao.basedao.BaseUploadPic;
import by.epam.club.exception.ServiceException;
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

@SuppressWarnings("unchecked") // FIXME: 6/20/2019 пришлось использовать unchecked проверку, это нормально?
public class UserDaoImpl extends BaseDao implements UserDao {
    private static final Logger LOGGER = LogManager.getLogger(UserDaoImpl.class);

    @Override
    public User checkUser(String log, String pass, String date_activity) throws DaoException {
        List<User> user;
        user = find(USER_CHECK_LOGIN_AND_PASSWORD.getQuery(), USER_PARAM, log, pass);

        if (!user.isEmpty()) {
            create(USER_UPDATE_DATE_INPUT.getQuery(), date_activity, Long.toString(user.get(0).getId()));
            return user.get(0);
        } else {
            throw new DaoException(USER_LOGIN_PASSWORD_UNCORRECT_MESSAGE);
        }
    }

    @Override
    public boolean updateUser(User user, String login, String email, String password) throws DaoException {
        try {
            create(USER_UPDATE_DATA.getQuery(), login, email, password, Long.toString(user.getId()));
            return true;
        } catch (DaoException e) {
            throw new DaoException(e.getMessage());
        }
    }

    @Override
    public boolean createUser(String login, String email, String newPass, String newDate) throws DaoException {
        try {
            create(USER_INSERT_NEW.getQuery(), login, email, newPass, newDate);
        } catch (DaoException e) {
            throw new DaoException(e.getMessage());
        }
        return true;
    }

    @Override
    public boolean createIdInUserInfo(String idForBase) throws DaoException {
        try {
            create(USER_INSERT_NEW_INFO.getQuery(), idForBase);
        } catch (DaoException e) {
            throw new DaoException(e.getMessage());
        }
        return true;
    }

    @Override
    public void markUserDeleted(String login, String email, String password) throws DaoException {
        List<User> user;
        try {
            user = find(USER_CHECK_LOGIN_EMAIL_PASSWORD.getQuery(), USER_LOGIN_PARAM, login, email, password);
            if (!user.isEmpty()) {
                create(USER_MARK_DELETED.getQuery(), login);
            } else {
                throw new DaoException(USER_LOGIN_PASSWORD_UNCORRECT_MESSAGE);
            }
        } catch (DaoException e) {
            throw new DaoException(e.getMessage());
        }
    }

    @Override
    public boolean markUserUndeleted(User user) throws DaoException {
        String id = Long.toString(user.getId());
        try {
            create(USER_MARK_UNDELETED.getQuery(), id);
        } catch (DaoException e) {
            throw new DaoException(e.getMessage());
        }
        return true;
    }

    @Override
    public boolean markUserBanned(User user) throws DaoException {
        String id = Long.toString(user.getId());
        String banned = user.getBanned();
        try {
            if (banned.equals(UNBANNED_PARAM)) {
                create(USER_MARK_BANNED.getQuery(), id);
            }
        } catch (DaoException e) {
            throw new DaoException(e.getMessage());
        }
        return true;
    }

    @Override
    public boolean markUserUnbanned(User user) throws DaoException {
        String id = Long.toString(user.getId());
        String banned = user.getBanned();
        try {
            if (banned.equals(BANNED_PARAM)) {
                create(USER_MARK_UNBANNED.getQuery(), id);
            }
        } catch (DaoException e) {
            throw new DaoException(e.getMessage());
        }
        return true;
    }

    @Override
    public List<User> takeAllUser() throws DaoException {
        List<User> user;
        try {
            user = find(USER_FIND_ALL_USER.getQuery(), USER_PARAM);
            user.sort(new sortUser());
        } catch (DaoException e) {
            throw new DaoException(e.getMessage());
        }
        return user;
    }

    @Override
    public List<User> takeAllUserUndeleted() throws DaoException {
        List<User> user;
        try {
            user = find(USER_FIND_ALL_UNDELETED_USER.getQuery(), USER_PARAM);
            user.sort(new sortUser());
        } catch (DaoException e) {
            throw new DaoException(e.getMessage());
        }
        return user;
    }

    @Override
    public List<User> takeAllUserDeleted() throws DaoException {
        List<User> user;
        try {
            user = find(USER_FIND_ALL_DELETED_USER.getQuery(), USER_PARAM);
            user.sort(new sortUser());
        } catch (DaoException e) {
            throw new DaoException(e.getMessage());
        }
        return user;
    }

    @Override
    public List<User> takeAllUserBanned() throws DaoException {
        List<User> user;
        try {
            user = find(USER_FIND_ALL_BANNED_USER.getQuery(), USER_PARAM);
            user.sort(new sortUser());
        } catch (DaoException e) {
            throw new DaoException(e.getMessage());
        }
        return user;
    }

    @Override
    public User findUserByLogin(String login) throws DaoException {
        try {
            return findUser(USER_FIND_USER_BY_LOGIN.getQuery(), USER_PARAM, login);
        } catch (DaoException e) {
            throw new DaoException(e.getMessage());
        }
    }

    @Override
    public User findUserByEmail(String email) throws DaoException {
        try {
            return findUser(USER_FIND_USER_BY_EMAIL.getQuery(), USER_PARAM, email);
        } catch (DaoException e) {
            throw new DaoException(e.getMessage());
        }
    }

    @Override
    public void newPassword(String email) throws DaoException {
        int countForPassword = 1_000_000;
        List<User> users;
        int random = (int) (Math.random() * countForPassword +countForPassword);
        String passwordForUser = Integer.toHexString(random);
        PasswordEncryption encryption = new PasswordEncryption();
        String newPassForDb = encryption.create(passwordForUser);

        try {
            users = find(USER_CHECK_EMAIL.getQuery(), USER_EMAIL_PARAM, email);
            if (!users.isEmpty()) {
                create(USER_CHANGE_PASSWORD.getQuery(), newPassForDb, email);
            } else {
                LOGGER.info("user is empty");
                throw new DaoException(NO_USER_MESSAGE);
            }
        } catch (DaoException e) {
            throw new DaoException(e.getMessage());
        }
        Sender sender = new Sender();
        try {
            sender.send(passwordForUser, email);
        } catch (MessagingException e) {
            LOGGER.error("Something bad with sending message ", e);
            e.printStackTrace();
        }
    }

    @Override
    public boolean updateUserLoginEmail(User user, String login, String email) throws DaoException {
        try {
            create(USER_UPDATE_DATA_EMAIL.getQuery(), login, email, Long.toString(user.getId()));
            return true;
        } catch (DaoException e) {
            throw new DaoException(e.getMessage());
        }
    }

    @Override
    public void updateUserInfo(User user, String firstname, String familyname) throws DaoException {
        try {
            create(USER_UPDATE_INFO.getQuery(), firstname, familyname, Long.toString(user.getId()));
        } catch (DaoException e) {
            throw new DaoException(e.getMessage());
        }
    }

    @Override
    public void createUserUploade(String userId, Part part) throws DaoException {
        BaseUploadPic baseUploadPic = new BaseUploadPic();
        baseUploadPic.uploadPicture(USER_UPDATE_PIC.getQuery(),part,userId);
    }

    private User findUser(String query, String table, String login) throws DaoException {
        List<User> user;
        try {
            user = find(query, table, login);
        } catch (DaoException e) {
            throw new DaoException(e.getMessage());
        }

        if (!user.isEmpty()) {
            return user.get(0);
        } else {
            return null;
        }
    }

    public User findUserIdForRegistrationTransaction(String login) throws DaoException {
        return findUser(USER_FIND_USER_ID.getQuery(), USER_ID_PARAM, login);
    }

    private class sortUser implements Comparator<User> {
        public int compare(User a, User b) {
            return a.getLogin().compareTo(b.getLogin());
        }
    }
}
