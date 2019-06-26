package by.epam.club.dao.userdao;

import by.epam.club.entity.User;
import by.epam.club.exception.DaoException;
import by.epam.club.exception.ServiceException;

import javax.servlet.http.Part;
import java.util.List;
import java.util.Set;

public interface UserDao {

    boolean createUser(String login, String email, String password, String date) throws DaoException;

    boolean createIdInUserInfo(String id) throws DaoException;

    User checkUser(String login, String password, String date_activity) throws DaoException;

    boolean updateUser(User user, String login, String email, String password) throws DaoException;

    void markUserDeleted(String login, String email, String password) throws DaoException;

    boolean markUserUndeleted(User user) throws DaoException;

    boolean markUserBanned(User user) throws DaoException;

    boolean markUserUnbanned(User user) throws DaoException;

    List<User> takeAllUser() throws DaoException;

    List<User> takeAllUserUndeleted() throws DaoException;

    List<User> takeAllUserDeleted() throws DaoException;

    List<User> takeAllUserBanned() throws DaoException;

    User findUserByLogin(String login) throws DaoException;

    User findUserByEmail(String email) throws DaoException;

    void newPassword(String email) throws DaoException;

    boolean updateUserLoginEmail(User user, String login, String email) throws DaoException;

    void updateUserInfo(User user, String firstname, String familyname) throws DaoException;

    void createUserUploade(String userId, Part part) throws DaoException;

}

