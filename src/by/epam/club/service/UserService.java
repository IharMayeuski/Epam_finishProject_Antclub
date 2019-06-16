package by.epam.club.service;


import by.epam.club.entity.Article;
import by.epam.club.entity.User;
import by.epam.club.exception.ServiceException;

import javax.sql.rowset.serial.SerialException;
import java.util.Set;

public interface UserService {
    User checkUser(String login, String password) throws ServiceException;
    User checkUser(String login) throws ServiceException;

    void markDeleted(String login, String email, String password) throws  ServiceException;
    boolean createUserMaster(String login, String email, String password1, String password2) throws ServiceException;

    void newPassword(String email) throws ServiceException;
}
