package by.epam.club.dao.userdao;

import by.epam.club.entity.User;
import by.epam.club.exception.DaoException;

import javax.servlet.http.Part;
import java.util.List;

/**
 * Interface of level dao for working with User
 *
 * @author Maeuski Igor
 * @version 1.0
 */
public interface UserDao {

    boolean createUser(String login, String email, String password, String date) throws DaoException;

    void createIdInUserInfo(String id) throws DaoException;

    User checkUser(String login, String password, String date_activity) throws DaoException;

    boolean updateUser(User user, String login, String email, String password) throws DaoException;

    void markUserDeleted(String login, String email, String password) throws DaoException;

    void markUserUndeleted(String user) throws DaoException;

    void markUserBanned(String userId) throws DaoException;

    void markUserUnbanned(String userId) throws DaoException;

    List<User> takeAllUser() throws DaoException;

    List<User> takeAllUserUndeleted() throws DaoException;

    List<User> takeAllUserDeleted() throws DaoException;

    List<User> takeAllUserBanned() throws DaoException;

    User findUserByLogin(String login) throws DaoException;

    User findUserByEmail(String email) throws DaoException;

    void newPassword(String email) throws DaoException;

   void updateUserLoginEmail(User user, String login, String email) throws DaoException;

    void updateUserInfo(User user, String firstname, String familyname) throws DaoException;

    void createUserUploade(String userId, Part part) throws DaoException;

    void markUserDeleted(String userId) throws DaoException;

    void markUser(User findUser) throws DaoException;

    void markAdmin(User findUser) throws DaoException;

}

