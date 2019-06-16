package by.epam.club.dao;

import by.epam.club.entity.User;
import by.epam.club.exception.DaoException;

import java.util.Set;

public interface UserDao {

	boolean createUser(String login, String email, String password) throws DaoException;//todo registration
	boolean createIdInUserInfo(long id) throws DaoException;

	User checkUser(String login, String password) throws DaoException;//todo authorization
	boolean updateUser(User user, String login, String email, String password) throws DaoException;
	void markUserDeleted(String login, String email, String password) throws DaoException;//todo markDelete account

	boolean markUserUndeleted(User user) throws DaoException; // todo fix account by Admin

	boolean markUserBannedUnbanned(User user) throws DaoException; // todo banned/unbanned

	Set<User> takeAllUser() throws DaoException;//todo find all users

	Set<User> takeAllUserUndeleted() throws DaoException; // todo find all undeleted users;

	User findUserByLogin(String login) throws DaoException; //todo for admin, find user by login

	void newPassword(String email) throws DaoException;

	boolean checkLogin(String login) throws DaoException;
	boolean checkEmail(String email) throws DaoException;

}
