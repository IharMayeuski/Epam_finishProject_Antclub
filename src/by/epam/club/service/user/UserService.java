package by.epam.club.service.user;

import by.epam.club.entity.User;
import by.epam.club.exception.ServiceException;

import javax.servlet.http.Part;
import java.util.List;

    /**
     * Interface of business logic for User
     *
     * @author Maeuski Igor
     * @version 1.0
     */

public interface UserService {

    User checkUser(String login, String password) throws ServiceException;

    User checkUser(String login) throws ServiceException;

    void markDeleted(String login, String email, String password) throws ServiceException;

    boolean createUserMaster(String login, String email, String password1, String password2) throws ServiceException;

    void newPassword(String email) throws ServiceException;

    void updateUser(User user, String email, String login, String password1, String password2, String firstname, String familyname) throws ServiceException;

    void createUserPic(String userId, Part part) throws ServiceException;

    List<User> takeAll() throws ServiceException;

    List<User> takeDeleted() throws ServiceException;

    List<User> takeBanned() throws ServiceException;

    void blockedUser(String userId) throws ServiceException;

    void unblockedUser(String userId) throws ServiceException;

    void undeletedUser(String userId) throws ServiceException;

    void deletedUser(String id) throws ServiceException;

    void markAdmin(User findUser) throws ServiceException;

    void markUser(User findUser) throws ServiceException;
}