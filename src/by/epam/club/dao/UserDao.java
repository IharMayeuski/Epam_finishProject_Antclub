package by.epam.club.dao;

import by.epam.club.entity.User;
import by.epam.club.exception.DaoException;

import java.util.Set;

public interface UserDao {
	User check(String login, String password) throws DaoException;//todo authorization

	boolean createUser(String login, String email, String password) throws DaoException;//todo registration

	boolean markUserDeleted(User user) throws DaoException;//todo markDelete account

	boolean markUserUndeleted (User user) throws DaoException; // todo fix account by Admin

	boolean markUserBannedUnbanned (User user) throws DaoException; // todo banned/unbanned

	Set<User> takeAllUser() throws DaoException;//todo find all users

	Set<User> takeAllUserUndeleted() throws DaoException; // todo find all undeleted users;

	User findUserByLogin(String login) throws DaoException; //todo for admin, find user by login

}
