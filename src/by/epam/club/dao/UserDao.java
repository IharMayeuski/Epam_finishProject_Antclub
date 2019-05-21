package by.epam.club.dao;

import by.epam.club.entity.User;
import by.epam.club.exception.DaoException;

public interface UserDao {
	User check(String login, String password) throws DaoException;
	boolean createUser(String login, String email, String password) throws DaoException;
	boolean deleteUser (User user) throws DaoException;
}
