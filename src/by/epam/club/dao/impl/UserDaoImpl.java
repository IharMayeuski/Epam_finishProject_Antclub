package by.epam.club.dao.impl;

import by.epam.club.dao.UserDao;
import by.epam.club.dao.pool.ConnectionPool;
import by.epam.club.dao.pool.ConnectionProxy;
import by.epam.club.entity.User;
import by.epam.club.exception.ConnectionPoolException;
import by.epam.club.exception.DaoException;
import by.epam.club.tool.PasswordEncryption;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import java.util.TimeZone;

import static by.epam.club.dao.impl.SqlFunction.*;
import static by.epam.club.dao.impl.Status.*;

public class UserDaoImpl implements UserDao {

    private PasswordEncryption encryption = new PasswordEncryption();
    private PreparedStatement st;
    private ResultSet rs;
    private ConnectionProxy con = null;
    private ConnectionPool connectionPool = null;

    @Override
    public User check(String log, String pass) throws DaoException {
        User user = null;
        String newPass = encryption.create(pass);

        try {
            connectionPool = ConnectionPool.getInstance();
            con = connectionPool.takeConnection();
            st = con.prepareStatement(USER_CHECK_LOGIN_AND_PASSWORD.getQuery());
            st.setString(1, log);
            st.setString(2, newPass);
            rs = st.executeQuery();
            if (rs.next()) {
                user = createUserData(rs);
            }
        } catch (ConnectionPoolException | SQLException e) {
            throw new DaoException(e);
        } finally {//todo уточнить, что делать в файнали со throw
            if (connectionPool != null) {
                try {
                    connectionPool.returnConnection(con);
                } catch (ConnectionPoolException e) {
                    throw new DaoException(e);
                }
            }
        }
        return user;
    }

    @Override
    public boolean createUser(String login, String email, String password) throws DaoException {
        String newPass = encryption.create(password);
        Date date = new Date();
        boolean value = false;
        final int USER = 2;


        try {
            connectionPool = ConnectionPool.getInstance();
            con = connectionPool.takeConnection();
            con.setAutoCommit(false);

            st = con.prepareStatement(USER_CHECK_LOGIN.getQuery());
            st.setString(1, login);

            rs = st.executeQuery();
            if (rs.next())
                throw new DaoException("we have user with this login");

            st = con.prepareStatement(USER_CHECK_EMAIL.getQuery());
            st.setString(1, email);
            rs = st.executeQuery();
            if (rs.next())
                throw new DaoException("we have user with this email");

            st = con.prepareStatement(USER_INSERT_NEW.getQuery());
            st.setString(1, login);
            st.setString(2, email);
            st.setString(3, newPass);
            st.setLong(4, date.toInstant().toEpochMilli());

            st.executeUpdate();
            con.commit();
            value = true;
        } catch (ConnectionPoolException | SQLException e) {
            try {
                con.rollback();
            } catch (SQLException message) {
                throw new DaoException(message);
            }
            throw new DaoException(e);

        } finally {
            if (connectionPool != null) {
                try {
                    connectionPool.returnConnection(con);
                } catch (ConnectionPoolException e) {
                    throw new DaoException(e);//todo уточнить, что делать в файнали со throw
                }
            }
        }
        return value;
    }

    @Override
    public boolean markUserDeleted(User user) throws DaoException {
        boolean value = false;
        try {
            connectionPool = ConnectionPool.getInstance();
            con = connectionPool.takeConnection();
            con.setAutoCommit(false);
            st = con.prepareStatement(USER_MARK_LIKE_DELETED.getQuery());
            st.setInt(1, user.getId());
            st.executeUpdate();
            con.commit();
            value = true;
        } catch (ConnectionPoolException | SQLException e) {
            try {
                con.rollback();
            } catch (SQLException e1) {
                throw new DaoException(e1);
            }
        } finally {//todo уточнить, что делать в файнали со throw
            if (connectionPool != null) {
                try {
                    connectionPool.returnConnection(con);
                } catch (ConnectionPoolException e) {
                    throw new DaoException(e);
                }
            }
        }
        return value;
    }

    @Override
    public Set<User> takeAllUser() throws DaoException {
        Set<User> users = new HashSet<>();
        try {
            connectionPool = ConnectionPool.getInstance();
            con = connectionPool.takeConnection();
            st = con.prepareStatement(USER_FIND_ALL_USER.getQuery());

            rs = st.executeQuery();
            while (rs.next()) {
                User user = createUserData(rs);
                users.add(user);
            }
        } catch (ConnectionPoolException | SQLException e) {
            e.printStackTrace();
        }
        return users;
    }

    @Override
    public User findUserByLogin(String login) throws DaoException {
        User user = null;
        try {
            connectionPool = ConnectionPool.getInstance();
            con = connectionPool.takeConnection();
            st = con.prepareStatement(USER_FIND_USER_BY_LOGIN.getQuery());
            st.setString(1, login);
            rs = st.executeQuery();
            if (rs.next()) {
                user = createUserData(rs);
            } else {
                throw new DaoException("We don't have user with this login: " + login);
            }
        } catch (ConnectionPoolException | SQLException e) {
            throw new DaoException(e);
        } finally {//todo уточнить, что делать в файнали со throw
            if (connectionPool != null) {
                try {
                    connectionPool.returnConnection(con);
                } catch (ConnectionPoolException e) {
                    throw new DaoException(e);
                }
            }
        }
        return user;
    }


    private User createUserData(ResultSet rs) throws DaoException {
        User user = new User();
        try {
            Date moment = new Date(rs.getBigDecimal(4).longValue()); //todo перевод секунд в дату
            DateFormat dateFormat = SimpleDateFormat.getDateTimeInstance(SimpleDateFormat.LONG, SimpleDateFormat.LONG);
            dateFormat.setTimeZone(TimeZone.getTimeZone("Europe/Minsk"));
            String date = dateFormat.format(moment);

            user.setId(rs.getInt(1));
            user.setLogin(rs.getString(2));
            user.setEmail(rs.getString(3));
            user.setDate_registration(date);
            user.setRole(rs.getString(7));

            if (rs.getInt(5) == 0) {
                user.setBanned(NOTBANNED.getStatus());
            } else {
                user.setBanned(BANNED.getStatus());
            }
            if (rs.getInt(6) == 0) {
                user.setDeleted(NOTDELETED.getStatus());
            } else {
                user.setDeleted(DELETED.getStatus());
            }

            System.out.println(user.toString());//todo удалить, стоит для контроля

        } catch (SQLException e) {
            throw new DaoException(e);
        }

        return user;
    }
}