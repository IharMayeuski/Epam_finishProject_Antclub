package by.epam.club.dao.impl;

import by.epam.club.dao.BaseDao;
import by.epam.club.dao.CloseStatementResultSet;
import by.epam.club.dao.UserDao;
import by.epam.club.entity.Parameter;
import by.epam.club.pool.ConnectionPool;
import by.epam.club.entity.User;
import by.epam.club.exception.DaoException;
import by.epam.club.tool.PasswordEncryption;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.mail.MessagingException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import java.util.TimeZone;

import static by.epam.club.dao.impl.SqlQuery.*;

public class UserDaoImpl extends BaseDao implements UserDao {

    private ConnectionPool connectionPool = null;
    private Connection connection = null;

    private static final Logger LOGGER = LogManager.getLogger(UserDaoImpl.class);


    @Override
    public User checkUser(String log, String pass) throws DaoException {
        connectionPool = ConnectionPool.getInstance();

        PasswordEncryption encryption = new PasswordEncryption();
        CloseStatementResultSet closeStatementResultSet = new CloseStatementResultSet();

        User user;
        String newPass = encryption.create(pass);
        PreparedStatement st = null;
        ResultSet rs = null;


        try (Connection connection = connectionPool.takeConnection()) {
            st = connection.prepareStatement(USER_CHECK_LOGIN_AND_PASSWORD.getQuery());
            st.setString(1, log);
            st.setString(2, newPass);
            rs = st.executeQuery();
            if (rs.next()) {
                user = createUserData(rs);
            } else {
                throw new DaoException("user.login.password.uncorrect");
            }
        } catch (SQLException e) {
            LOGGER.info("SqlException ", e.getMessage());
            throw new DaoException("SQL_exception");
        } finally {
            closeStatementResultSet.close(rs, st);
        }
        return user;
    }

    @Override
    public boolean updateUser(User user, String login, String email, String password) throws DaoException {
        connectionPool = ConnectionPool.getInstance();
        PasswordEncryption encryption = new PasswordEncryption();
        CloseStatementResultSet closeStatementResultSet = new CloseStatementResultSet();

        PreparedStatement st = null;
        ResultSet rs = null;
        boolean value;

        String newPass = encryption.create(password);
        try (Connection connection = connectionPool.takeConnection()) {
            connection.setAutoCommit(false);
            st = connection.prepareStatement(USER_UPDATE_DATA.getQuery());
            st.setString(1, login);
            st.setString(2, email);
            st.setString(3, newPass);
            st.setLong(4, user.getId());
            st.executeUpdate();
            connection.commit();
            value = true;
        } catch (SQLException e) {
            throw new DaoException("SQL_exception");
        } finally {
            closeStatementResultSet.close(rs, st);
        }
        return value;
    }

    @Override
    public boolean createUser(String login, String email, String password) throws DaoException {
        PasswordEncryption encryption = new PasswordEncryption();
        try {
            String newPass = encryption.create(password);
            Date date = new Date();
            String newDate = String.valueOf(date.toInstant().toEpochMilli());
            create(USER_INSERT_NEW.getQuery(), login, email, newPass, newDate);
        } catch (DaoException e) {
            throw new DaoException(e.getMessage());
        }
        return true;
    }


    @Override
    public boolean createIdInUserInfo(long id) throws DaoException {

       /* CloseStatementResultSet closeStatementResultSet = new CloseStatementResultSet();*/
        PreparedStatement st = null;
        ResultSet rs = null;
        boolean value;
        try {
            st = getConnection().prepareStatement(USER_INSERT_NEW_INFO.getQuery());
            st.setLong(1, id);
            st.executeUpdate();
            value = true;
        } catch (SQLException e) {
            LOGGER.info("SqlException ", e);
            throw new DaoException("SQL_exception");
        } finally {
            closeResources(rs,st);
           /* closeStatementResultSet.close(rs, st);*/
        }
        return value;
    }

    @Override
    public void markUserDeleted(String login, String email, String password) throws DaoException {
        PreparedStatement preparedStatement = null;
        PasswordEncryption encryption = new PasswordEncryption();
        ResultSet resultSet = null;
        CloseStatementResultSet closeStatementResultSet = new CloseStatementResultSet();

        String newPass = encryption.create(password);
        connectionPool = ConnectionPool.getInstance();

        try (Connection connection = connectionPool.takeConnection()) {
            preparedStatement = connection.prepareStatement(USER_CHECK_LOGIN_EMAIL_PASSWORD.getQuery());
            preparedStatement.setString(1, login);
            preparedStatement.setString(2, email);
            preparedStatement.setString(3, newPass);
            resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                connection.setAutoCommit(false);
                preparedStatement = connection.prepareStatement(USER_MARK_DELETED.getQuery());
                preparedStatement.setString(1, login);
                preparedStatement.executeUpdate();
                connection.commit();
            } else {
                throw new DaoException("user.login.password.uncorrect");
            }
        } catch (SQLException e) {
            LOGGER.info("SqlException ", e);
            throw new DaoException("SQL_exception");
        } finally {
            closeStatementResultSet.close(resultSet, preparedStatement);
        }
    }

    @Override
    public boolean markUserUndeleted(User user) throws DaoException {
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        CloseStatementResultSet closeStatementResultSet = new CloseStatementResultSet();

        boolean value;
        connectionPool = ConnectionPool.getInstance();
        try (Connection connection = connectionPool.takeConnection()) {
            connection.setAutoCommit(false);
            preparedStatement = connection.prepareStatement(USER_MARK_UNDELETED.getQuery());
            preparedStatement.setLong(1, user.getId());
            preparedStatement.executeUpdate();
            connection.commit();
            value = true;
        } catch (SQLException e) {
            LOGGER.info("SqlException ", e);
            throw new DaoException("SQL_exception");
        } finally {
            closeStatementResultSet.close(resultSet, preparedStatement);
        }
        return value;
    }

    @Override
    public boolean markUserBannedUnbanned(User user) throws DaoException {
        connectionPool = ConnectionPool.getInstance();
        CloseStatementResultSet closeStatementResultSet = new CloseStatementResultSet();
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        boolean value;

        try (Connection connection = connectionPool.takeConnection()) {
            connection.setAutoCommit(false);
            if (user.getBanned().equals(Parameter.UNBANNED_PARAM)) {
                preparedStatement = connection.prepareStatement(USER_MARK_BANNED.getQuery());
            } else {
                preparedStatement = connection.prepareStatement(USER_MARK_UNBANNED.getQuery());
            }
            preparedStatement.setLong(1, user.getId());
            preparedStatement.executeUpdate();
            connection.commit();
            value = true;
        } catch (SQLException e) {
            LOGGER.info("SqlException ", e);
            throw new DaoException("SQL_exception");
        } finally {
            closeStatementResultSet.close(resultSet, preparedStatement);
        }
        return value;
    }

    @Override
    public Set<User> takeAllUser() throws DaoException {
        PreparedStatement st = null;
        ResultSet rs = null;
        Set<User> users = new HashSet<>();
        connectionPool = ConnectionPool.getInstance();
        CloseStatementResultSet closeStatementResultSet = new CloseStatementResultSet();

        try (Connection connection = connectionPool.takeConnection()) {
            st = connection.prepareStatement(USER_FIND_ALL_USER.getQuery());

            rs = st.executeQuery();
            while (rs.next()) {
                User user = createUserData(rs);
                users.add(user);
            }
        } catch (SQLException e) {
            throw new DaoException("SQL_exception");
        } finally {
            closeStatementResultSet.close(rs, st);
        }
        return users;
    }

    @Override
    public Set<User> takeAllUserUndeleted() throws DaoException {
        PreparedStatement st;
        ResultSet rs;
        Set<User> users = new HashSet<>();
        connectionPool = ConnectionPool.getInstance();
        try (Connection connection = connectionPool.takeConnection()) {
            st = connection.prepareStatement(USER_FIND_ALL_UNDELETED_USER.getQuery());
            rs = st.executeQuery();
            while (rs.next()) {
                User user = createUserData(rs);
                users.add(user);
            }
        } catch (SQLException e) {
            throw new DaoException("SQL_exception");
        }
        return users;
    }

    @Override
    public User findUserByLogin(String login) throws DaoException {
        connectionPool = ConnectionPool.getInstance();
        CloseStatementResultSet closeStatementResultSet = new CloseStatementResultSet();
        PreparedStatement st = null;
        ResultSet rs = null;
        User user;
        try (Connection connection = connectionPool.takeConnection()) {
            st = connection.prepareStatement(USER_FIND_USER_BY_LOGIN.getQuery());
            st.setString(1, login);
            rs = st.executeQuery();
            if (rs.next()) {
                user = createUserData(rs);
            } else {
                LOGGER.info("user is empty");
                throw new DaoException("no_user");
            }
        } catch (SQLException e) {
            throw new DaoException("SQL_exception");
        } finally {
            closeStatementResultSet.close(rs, st);
        }
        return user;
    }

    public User findUserByRegistration(String login) throws DaoException {
//        CloseStatementResultSet closeStatementResultSet = new CloseStatementResultSet();
        PreparedStatement st = null;
        ResultSet rs = null;
        User user;
        try {
            st = getConnection().prepareStatement("select id from user where login=?");
            st.setString(1, login);
            rs = st.executeQuery();
            if (rs.next()) {
                user = createUserId(rs);
            } else {
                LOGGER.info("user is empty");
                throw new DaoException("no_user");
            }
        } catch (SQLException e) {
            throw new DaoException("SQL_exception");
        }

        return user;
    }

    @Override
    public void newPassword(String email) throws DaoException {
        connectionPool = ConnectionPool.getInstance();

        int random = (int) (Math.random() * 1_000_000 + 1_000_000);

        String passwordForUser = Integer.toHexString(random);

        PasswordEncryption encryption = new PasswordEncryption();
        String newPassForDb = encryption.create(passwordForUser);

        Sender sender = new Sender("ant_epam@mail.ru", "ant61206120");
        try {
            sender.send("AntClub. Your new password", "Dear user, your new password is - " + passwordForUser, email);
        } catch (MessagingException e) {
            e.printStackTrace();
        }
        CloseStatementResultSet closeStatementResultSet = new CloseStatementResultSet();
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try (Connection connection = connectionPool.takeConnection()) {
            preparedStatement = connection.prepareStatement(USER_CHECK_EMAIL.getQuery());
            preparedStatement.setString(1, email);
            resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                connection.setAutoCommit(false);
                preparedStatement = connection.prepareStatement(USER_CHANGE_PASSWORD.getQuery());
                preparedStatement.setString(1, newPassForDb);
                preparedStatement.setString(2, email);
                preparedStatement.executeUpdate();
                connection.commit();
            }
        } catch (SQLException e) {
            LOGGER.info("SqlException ", e);
            throw new DaoException("SQL_exception");
        } finally {
            closeStatementResultSet.close(resultSet, preparedStatement);
        }
    }


    private User createUserData(ResultSet rs) throws DaoException {

        User user = new User();
        try {
            Date moment = new Date(rs.getBigDecimal(4).longValue());
            DateFormat dateFormat = SimpleDateFormat.getDateTimeInstance(SimpleDateFormat.MEDIUM, SimpleDateFormat.MEDIUM);
            dateFormat.setTimeZone(TimeZone.getTimeZone("Europe/Minsk"));
            String date = dateFormat.format(moment);

            user.setId(rs.getInt(1));
            user.setLogin(rs.getString(2));
            user.setEmail(rs.getString(3));
            user.setDate_registration(date);
            user.setRole(rs.getString(7));
            user.setFirstname(rs.getString(8));
            user.setFamilyname(rs.getString(9));
            user.setBlob(rs.getBlob(10));

            if (rs.getInt(5) == 0) {
                user.setBanned(Parameter.UNBANNED_PARAM);
            } else {
                user.setBanned(Parameter.BANNED_PARAM);
            }
            if (rs.getInt(6) == 0) {
                user.setDeleted(Parameter.UNDELETED_PARAM);
            } else {
                user.setDeleted(Parameter.DELETED_PARAM);
            }
            System.out.println(user.toString());//todo удалить, стоит для контроля
        } catch (SQLException e) {
            throw new DaoException("SQL_exception");
        }
        return user;
    }

    private User createUserId(ResultSet rs) throws DaoException {

        User user = new User();
        try {
            user.setId(rs.getInt(1));
        } catch (SQLException e) {
            throw new DaoException("SQL_exception");
        }
        return user;

    }


@Override
public boolean checkLogin(String login) throws DaoException {
        connectionPool = ConnectionPool.getInstance();
        CloseStatementResultSet closeStatementResultSet = new CloseStatementResultSet();
        boolean value = true;
        PreparedStatement st = null;
        ResultSet rs = null;

        try (Connection connection = connectionPool.takeConnection()) {
            st = connection.prepareStatement(USER_CHECK_LOGIN.getQuery());
            st.setString(1, login);
            rs = st.executeQuery();
            if (rs.next()) {
                if (rs.getInt(2) == 1) {
                    throw new DaoException("user.login.deleted");
                } else {
                    throw new DaoException("user.login");
                }
            }
        } catch (SQLException e) {
            value = false;
            LOGGER.info("SqlException ", e.getMessage());
            throw new DaoException("SQL_exception");
        } finally {
            closeStatementResultSet.close(rs, st);
        }
        return value;
    }




@Override
   public boolean checkEmail(String email) throws DaoException {
        connectionPool = ConnectionPool.getInstance();
        CloseStatementResultSet closeStatementResultSet = new CloseStatementResultSet();

        PreparedStatement st = null;
        ResultSet rs = null;
        boolean value = true;

        try (Connection connection = connectionPool.takeConnection()) {
            st = connection.prepareStatement(USER_CHECK_EMAIL.getQuery());
            st.setString(1, email);
            rs = st.executeQuery();
            if (rs.next()) {
                if (rs.getInt(2) == 1) {
                    throw new DaoException("user.email.deleted");
                } else {
                    throw new DaoException("user.email");
                }
            }
        } catch (SQLException e) {
            value = false;
            LOGGER.info("SqlException ", e.getMessage());
            throw new DaoException("SQL_exception");
        } finally {
            closeStatementResultSet.close(rs, st);
        }
        return value;
    }
}
