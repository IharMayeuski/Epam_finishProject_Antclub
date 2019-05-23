package by.epam.club.service;


import by.epam.club.entity.User;
import by.epam.club.exception.ServiceException;

public interface UserService {
    User checkUser(String login, String password) throws ServiceException;

    boolean createUserMaster(String login, String email, String password1, String password2) throws ServiceException;
}