package by.epam.club.dao.impl;

import by.epam.club.entity.Parameter;
import by.epam.club.entity.User;
import by.epam.club.exception.DaoException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;


public class UserDaoImplTest {
    private static final Logger LOGGER = LogManager.getLogger(UserDaoImplTest.class);
    private UserDaoImpl userDao;
    private User user;

    @BeforeClass
    public void setUp() {
        userDao = new UserDaoImpl();
        user = new User();
    }

    @Test
    public void testCheckPositive() {
        try {
            Assert.assertEquals(userDao.checkUser("admin", "admin").getLogin(), "admin");
            LOGGER.info("Test checkUser user is completed successfully");
        } catch (DaoException e) {
            LOGGER.info(e);
        }
    }

    @Test
    public void testCheckNegative() {
        try {
            Assert.assertNull(userDao.checkUser("admin", "admin2"));
            LOGGER.info("Test checkUser user negative test is completed successfully");
        } catch (DaoException e) {
            LOGGER.info(e);
        }
    }

    @Test
    public void testCreateUser() throws DaoException {
        String PASSWORD = "12345";
        String EMAIL = "test2@gmail.com";
        String LOGIN = "test2";
        Assert.assertTrue(userDao.createUser(LOGIN, EMAIL, PASSWORD));
    }

/*    @Test
    public void testDeleteUser() {
    //    user.setId(ID_FOR_MARKING_DELETING); todo удалить

        try {
            Assert.assertTrue(userDao.markUserDeleted(ID_FOR_MARKING_DELETING));
            LOGGER.info("Test_Mark_deleted_user is completed");
        } catch (DaoException e) {
            LOGGER.info(e);
        }
    }*/

    @Test
    public void testTakeAllUser() {
        try {
            Assert.assertNotNull(userDao.takeAllUser());
            LOGGER.info("Test take all is completed successfully");

        } catch (DaoException e) {
            LOGGER.info(e);
        }
    }

    @Test
    public void testFindUserByLogin() {
        try {
            Assert.assertNotNull(userDao.findUserByLogin("admin"));
            LOGGER.info("Test take all is completed successfully");
        } catch (DaoException e) {
            LOGGER.info(e);
        }
    }

    @Test
    public void testMarkUserBannedUnbanned() throws DaoException {
        user.setId(2);
        user.setBanned(Parameter.UNBANNED_PARAM);
        Assert.assertTrue(userDao.markUserBannedUnbanned(user));

        user.setBanned(Parameter.BANNED_PARAM);
        Assert.assertTrue(userDao.markUserBannedUnbanned(user));
    }

    @Test
    public void testMarkUserUndeleted() {
        int ID_FOR_MARKING_DELETING = 2;
        user.setId(ID_FOR_MARKING_DELETING);
        try {
            Assert.assertTrue(userDao.markUserUndeleted(user));
            LOGGER.info("Test_Mark_deleted_user is completed");
        } catch (DaoException e) {
            LOGGER.info(e);
        }
    }

    @Test
    public void testTakeAllUserUndeleted() {
        try {
            Assert.assertNotNull(userDao.takeAllUserUndeleted());
            LOGGER.info("Test take all is completed successfully");
        } catch (DaoException e) {
            LOGGER.info(e);
        }
    }

    @Test
    public void testUpdateUser() {
        User user = new User();
        user.setId(2);
        try {
            Assert.assertTrue(userDao.updateUser(user, "test","test@test.ru","test"));
            LOGGER.info("Test take all is completed successfully");
        } catch (DaoException e) {
            LOGGER.info(e);
        }
    }

   /* @Test
    public void testNewPassword() throws DaoException {
       String newPassword =  userDao.newPassword("smirnov");
       LOGGER.info(newPassword + "- new Password");
       Assert.assertNotNull(newPassword);
    }*/
}