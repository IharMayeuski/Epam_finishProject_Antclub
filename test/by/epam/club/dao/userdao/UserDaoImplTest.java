package by.epam.club.dao.userdao;

import by.epam.club.entity.User;
import by.epam.club.exception.DaoException;
import by.epam.club.tool.PasswordEncryption;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.Date;
import java.util.List;

import static by.epam.club.entity.Parameter.*;

public class UserDaoImplTest {
    private static final Logger LOGGER = LogManager.getLogger(UserDaoImplTest.class);
    private UserDaoImpl userDao;
    private User user;
    private final String TEST_LOGIN_VERSION_1 = "test22";
    private final String TEST_LOGIN_VERSION_2 = "test21";
    private final String TEST_EMAIL = "test@test.ru";
    private final String TEST_SENDER_EMAIL = "maevskii@list.ru";


    private final String TEST_PASSWORD = "12345";
    private final String TEST_DATE = "1563057818406";
    private final String NEGATIVE_TEST = "Negative test is completed successfully";
    private final String POSITIVE_TEST = "Positive test is completed successfully";
    private final String POSITIVE = "positive_test";
    private final String NEGATIVE = "negative_test";
    private final String SCRYPT_DELETE_FROM_USER = "DELETE from user where user.login=?";

    @BeforeClass(groups = {POSITIVE, NEGATIVE})
    public void setUp() {
        userDao = new UserDaoImpl();
        user = new User();
    }

    @Test(groups = {POSITIVE})
    public void testCreateUserPositive() throws DaoException {
        PasswordEncryption passwordEncryption = new PasswordEncryption();
        String password = passwordEncryption.create(TEST_PASSWORD);
        Assert.assertTrue(userDao.createUser(TEST_LOGIN_VERSION_2, TEST_EMAIL, password, TEST_DATE));
        userDao.create(SCRYPT_DELETE_FROM_USER, TEST_LOGIN_VERSION_2);
        LOGGER.info(POSITIVE_TEST);
    }

    @Test(groups = {NEGATIVE})
    public void testCreateUserNegative() {
        try {
            userDao.createUser(TEST_LOGIN_VERSION_1, TEST_EMAIL, TEST_PASSWORD, TEST_DATE);
        } catch (DaoException e) {
            LOGGER.info(NEGATIVE_TEST);
        }
    }

    @Test(groups = {POSITIVE})
    public void testCheckUserPositive() throws DaoException {
        String password = new PasswordEncryption().create(TEST_PASSWORD);
        user = userDao.checkUser(TEST_LOGIN_VERSION_1, password, TEST_DATE);
        Assert.assertEquals(user.getLogin(), TEST_LOGIN_VERSION_1);
        LOGGER.info(POSITIVE_TEST);
    }

    @Test(groups = {NEGATIVE})
    public void testCheckUserNegative() throws DaoException {
        String password = new PasswordEncryption().create(TEST_PASSWORD);
        try {
            Assert.assertNull(userDao.checkUser(TEST_LOGIN_VERSION_2, password, TEST_DATE).getLogin());
        } catch (DaoException e) {
            LOGGER.info(NEGATIVE_TEST);
        }
    }

    @Test(groups = {POSITIVE})
    public void testUpdateUserPositive() throws DaoException {
        String dateActivity = String.valueOf(new Date().toInstant().toEpochMilli());
        String password = new PasswordEncryption().create(TEST_PASSWORD);
        user = userDao.checkUser(TEST_LOGIN_VERSION_1, password, dateActivity);
        Assert.assertTrue(userDao.updateUser(user, TEST_LOGIN_VERSION_2, TEST_EMAIL, password));
        LOGGER.info(POSITIVE_TEST);
        userDao.updateUser(user, TEST_LOGIN_VERSION_1, TEST_EMAIL, password);
    }

    @Test(groups = {NEGATIVE})
    public void testUpdateUserNegative() throws DaoException {
        Date date = new Date();
        String dateActivity = String.valueOf(date.toInstant().toEpochMilli());
        PasswordEncryption passwordEncryption = new PasswordEncryption();
        String password = passwordEncryption.create(TEST_PASSWORD);
        user = userDao.checkUser(TEST_LOGIN_VERSION_1, password, dateActivity);
        try {
            userDao.updateUser(user, null, TEST_EMAIL, password);
        } catch (DaoException e) {
            LOGGER.info(NEGATIVE_TEST);
        }
    }

    @Test(groups = {POSITIVE})
    public void testCreateIdInUserInfoPositive() throws DaoException {
        String scryptDeleteFromUserInfo = "DELETE from userinfo where user_id=?";
        userDao.createUser(TEST_LOGIN_VERSION_2, TEST_EMAIL, TEST_PASSWORD, TEST_DATE);
        user = userDao.findUserIdForRegistrationTransaction(TEST_LOGIN_VERSION_2);
        userDao.createIdInUserInfo(Long.toString(user.getId()));

        userDao.create(scryptDeleteFromUserInfo, Long.toString(user.getId()));
        userDao.create(SCRYPT_DELETE_FROM_USER, TEST_LOGIN_VERSION_2);
        LOGGER.info(POSITIVE_TEST);
    }

    @Test(groups = {NEGATIVE})
    public void testCreateIdInUserInfoNegative() throws DaoException {
        userDao.createUser(TEST_LOGIN_VERSION_2, TEST_EMAIL, TEST_PASSWORD, TEST_DATE);
        user = userDao.findUserIdForRegistrationTransaction(TEST_LOGIN_VERSION_2);
        try {
            userDao.createIdInUserInfo("");
        } catch (DaoException e) {
            LOGGER.info(NEGATIVE_TEST);
        } finally {
            userDao.create(SCRYPT_DELETE_FROM_USER, TEST_LOGIN_VERSION_2);
        }
    }

    @Test(groups = {POSITIVE})
    public void testMarkUserDeletedPositive() throws DaoException {
        long userId = userDao.findUserIdForRegistrationTransaction(TEST_LOGIN_VERSION_1).getId();
        userDao.markUserDeleted(Long.toString(userId));
        Date date = new Date();
        String dateActivity = String.valueOf(date.toInstant().toEpochMilli());
        PasswordEncryption passwordEncryption = new PasswordEncryption();
        String password = passwordEncryption.create(TEST_PASSWORD);
        user = userDao.checkUser(TEST_LOGIN_VERSION_1, password, dateActivity);
        String actualResult = user.getDeleted();
        Assert.assertEquals(DELETED_PARAM, actualResult);
        LOGGER.info(POSITIVE_TEST);
    }

    @Test(groups = {POSITIVE})
    public void testMarkUserPositive() throws DaoException {
        String dateActivity = String.valueOf(new Date().toInstant().toEpochMilli());
        String password = new PasswordEncryption().create(TEST_PASSWORD);
        user = userDao.checkUser(TEST_LOGIN_VERSION_1, password, dateActivity);
        userDao.markUser(user);
        user = userDao.checkUser(TEST_LOGIN_VERSION_1, password, dateActivity);
        String actualResult = user.getRole();
        Assert.assertEquals(actualResult, USER_PARAM);
        LOGGER.info(POSITIVE_TEST);
    }

    @Test(groups = {POSITIVE})
    public void testMarkAdminPositive() throws DaoException {
        String dateActivity = String.valueOf(new Date().toInstant().toEpochMilli());
        String password = new PasswordEncryption().create(TEST_PASSWORD);
        user = userDao.checkUser(TEST_LOGIN_VERSION_1, password, dateActivity);
        userDao.markAdmin(user);
        user = userDao.checkUser(TEST_LOGIN_VERSION_1, password, dateActivity);
        String actualResult = user.getRole();
        Assert.assertEquals(actualResult, ADMIN_PARAM);
        LOGGER.info(POSITIVE_TEST);
    }

    @Test(groups = {POSITIVE})
    public void testMarkUserUndeletedPositive() throws DaoException {
        String dateActivity = String.valueOf(new Date().toInstant().toEpochMilli());
        String password = new PasswordEncryption().create(TEST_PASSWORD);
        user = userDao.checkUser(TEST_LOGIN_VERSION_1, password, dateActivity);
        userDao.markUserUndeleted(Long.toString(user.getId()));
        user = userDao.checkUser(TEST_LOGIN_VERSION_1, password, dateActivity);
        String actualResult = user.getDeleted();
        Assert.assertEquals(actualResult, UNDELETED_PARAM);
        LOGGER.info(POSITIVE_TEST);
    }

    @Test(groups = {POSITIVE})
    public void testMarkUserBannedPositive() throws DaoException {
        String dateActivity = String.valueOf(new Date().toInstant().toEpochMilli());
        String password = new PasswordEncryption().create(TEST_PASSWORD);
        user = userDao.checkUser(TEST_LOGIN_VERSION_1, password, dateActivity);
        userDao.markUserBanned(Long.toString(user.getId()));
        user = userDao.checkUser(TEST_LOGIN_VERSION_1, password, dateActivity);
        String actualResult = user.getBanned();
        Assert.assertEquals(actualResult, BANNED_PARAM);
        LOGGER.info(POSITIVE_TEST);
    }


    @Test(groups = {POSITIVE})
    public void testMarkUserUnbannedPositive() throws DaoException {
        String dateActivity = String.valueOf(new Date().toInstant().toEpochMilli());
        String password = new PasswordEncryption().create(TEST_PASSWORD);
        user = userDao.checkUser(TEST_LOGIN_VERSION_1, password, dateActivity);
        userDao.markUserUnbanned(Long.toString(user.getId()));
        user = userDao.checkUser(TEST_LOGIN_VERSION_1, password, dateActivity);
        String actualResult = user.getBanned();
        Assert.assertEquals(actualResult, UNBANNED_PARAM);
        LOGGER.info(POSITIVE_TEST);
    }

    @Test(groups = {POSITIVE})
    public void testTakeAllUserPositive() throws DaoException {
        List<User> userList = userDao.takeAllUser();
        Assert.assertNotNull(userList);
        LOGGER.info(POSITIVE_TEST + ", totally count of users: " + userList.size());
    }

    @Test(groups = {POSITIVE})
    public void testTakeAllUserUndeletedPositive() throws DaoException {
        List<User> userList = userDao.takeAllUserDeleted();
        for (User user : userList) {
            if (user.getDeleted().equals(UNDELETED_PARAM)) {
                throw new DaoException(ERROR_PARAM);
            }
        }
        LOGGER.info(POSITIVE_TEST + ", totally count of users: " + userList.size());
    }

    @Test(groups = {POSITIVE})
    public void testTakeAllUserDeletedPositive() throws DaoException {
        List<User> userList = userDao.takeAllUserUndeleted();
        for (User user : userList) {
            if (user.getDeleted().equals(DELETED_PARAM)) {
                throw new DaoException(ERROR_PARAM);
            }
        }
        LOGGER.info(POSITIVE_TEST + ", totally count of users: " + userList.size());
    }

    @Test(groups = {POSITIVE})
    public void testTakeAllUserBanned() throws DaoException {
        List<User> userList = userDao.takeAllUserBanned();
        for (User user : userList) {
            if (user.getDeleted().equals(BANNED_PARAM)) {
                throw new DaoException(ERROR_PARAM);
            }
        }
        LOGGER.info(POSITIVE_TEST + ", totally count of users: " + userList.size());
    }

    @Test(groups = {POSITIVE})
    public void testFindUserByLoginPositive() throws DaoException {
        Assert.assertNotNull(userDao.findUserByLogin(TEST_LOGIN_VERSION_1));
        LOGGER.info(POSITIVE_TEST);
    }

    @Test(groups = {NEGATIVE})
    public void testFindUserByLoginNegative() throws DaoException {
        String testBadLogin = "We_don't_have_this_login";
        Assert.assertNull(userDao.findUserByLogin(testBadLogin));
        LOGGER.info(NEGATIVE_TEST);
    }

    @Test(groups = {POSITIVE})
    public void testFindUserByEmailPositive() throws DaoException {
        Assert.assertNotNull(userDao.findUserByEmail(TEST_EMAIL));
        LOGGER.info(POSITIVE_TEST);
    }

    @Test(groups = {NEGATIVE})
    public void testFindUserByEmailNegative() throws DaoException {
        String testBadEmail = "We_don't_have_this_email@tut.by";
        Assert.assertNull(userDao.findUserByEmail(testBadEmail));
        LOGGER.info(NEGATIVE_TEST);
    }

    @Test(groups = {POSITIVE})
    public void testNewPasswordPositive() throws DaoException {
        userDao.newPassword(TEST_SENDER_EMAIL);
        LOGGER.info(POSITIVE_TEST);
    }

    @Test(groups = {NEGATIVE}, expectedExceptions = DaoException.class)
    public void testNewPasswordNegative() throws DaoException {
        userDao.newPassword(TEST_SENDER_EMAIL + "!");
    }

    @Test(groups = {POSITIVE})
    public void testFindUserIdForRegistrationTransactionPositive() throws DaoException {
        user = userDao.findUserIdForRegistrationTransaction(TEST_LOGIN_VERSION_1);
        Assert.assertNotNull(user);
        LOGGER.info(POSITIVE_TEST);
    }

    @Test(groups = {NEGATIVE})
    public void testFindUserIdForRegistrationTransactionNegative() throws DaoException {
        user = userDao.findUserIdForRegistrationTransaction(TEST_LOGIN_VERSION_1 + "!");
        Assert.assertNull(user);
        LOGGER.info(NEGATIVE_TEST);
    }

    @Test(groups = {POSITIVE})
    public void testUpdateUserLoginEmailPositive() throws DaoException {
        String dateActivity = String.valueOf(new Date().toInstant().toEpochMilli());
        String password = new PasswordEncryption().create(TEST_PASSWORD);
        user = userDao.checkUser(TEST_LOGIN_VERSION_1, password, dateActivity);
        userDao.updateUserLoginEmail(user, TEST_LOGIN_VERSION_1 + "!", TEST_EMAIL);
        user = userDao.checkUser(TEST_LOGIN_VERSION_1 + "!", password, dateActivity);
        String actualResult = user.getLogin();
        Assert.assertEquals(actualResult, TEST_LOGIN_VERSION_1 + "!");
        userDao.updateUserLoginEmail(user, TEST_LOGIN_VERSION_1, TEST_EMAIL);
        LOGGER.info(POSITIVE_TEST);
    }

    @Test(groups = {POSITIVE})
    public void testUpdateUserInfo() throws DaoException {
        String dateActivity = String.valueOf(new Date().toInstant().toEpochMilli());
        String password = new PasswordEncryption().create(TEST_PASSWORD);
        user = userDao.checkUser(TEST_LOGIN_VERSION_1, password, dateActivity);
        userDao.updateUserInfo(user, TEST_LOGIN_VERSION_1, TEST_LOGIN_VERSION_1);
        user = userDao.checkUser(TEST_LOGIN_VERSION_1, password, dateActivity);
        String actualResult = user.getFirstname();
        Assert.assertEquals(actualResult, TEST_LOGIN_VERSION_1);
        LOGGER.info(POSITIVE_TEST);
    }

    // FIXME: 7/15/2019 удалить
//    @Test
//    public void testCreateUserUploade() throws DaoException {
//    }
}