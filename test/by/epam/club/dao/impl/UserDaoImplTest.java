package by.epam.club.dao.impl;

import by.epam.club.entity.User;
import by.epam.club.exception.DaoException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static by.epam.club.dao.impl.Status.BANNED;
import static by.epam.club.dao.impl.Status.UNBANNED;

public class UserDaoImplTest {
    private static final Logger LOGGER = LogManager.getLogger(UserDaoImplTest.class);
    private UserDaoImpl userDao;
    private User user;

    private final String LOGIN = "test2";
    private final String EMAIL = "test2@gmail.com";
    private final String PASSWORD = "12345";

    private final int ID_FOR_MARKING_DELETING = 2;

    @BeforeClass
    public void setUp() {
        userDao = new UserDaoImpl();
        user = new User();

    }

    @Test
    public void testCheckPositive() {
        try {
            Assert.assertEquals(userDao.check("admin", "admin").getLogin(), "admin");
            LOGGER.info("Test check user is completed successfully");
        } catch (DaoException e) {
            System.out.println(e);
            LOGGER.info(e);
        }
    }

    @Test
    public void testCheckNegative() {
        try {
            Assert.assertNull(userDao.check("admin", "admin2"));
            LOGGER.info("Test check user negative test is completed successfully");
        } catch (DaoException e) {
            System.out.println(e);
            LOGGER.info(e);
        }
    }

    @Test
    public void testCreateUser() throws DaoException {
        Assert.assertTrue(userDao.createUser(LOGIN, EMAIL, PASSWORD));

    }

    @Test
    public void testDeleteUser() {
        user.setId(ID_FOR_MARKING_DELETING);

        try {
            Assert.assertTrue(userDao.markUserDeleted(user));
            LOGGER.info("Test_Mark_deleted_user is completed");
        } catch (DaoException e) {
            System.out.println(e);//todo удалить
            LOGGER.info(e);
        }
    }

    @Test
    public void testTakeAllUser() {
        try {
            Assert.assertNotNull(userDao.takeAllUser());
            LOGGER.info("Test take all is completed successfully");
        } catch (DaoException e) {
            System.out.println(e);//todo удалить
            LOGGER.info(e);
        }
    }

    @Test
    public void testFindUserByLogin() {
        try {
            Assert.assertNotNull(userDao.findUserByLogin("admin"));
            LOGGER.info("Test take all is completed successfully");
        } catch (DaoException e) {
            System.out.println(e);//todo удалить
            LOGGER.info(e);
        }
    }

    @Test
    public void testMarkUserBannedUnbanned() throws DaoException {
        user.setId(2);
        user.setBanned(UNBANNED.getStatus());
        Assert.assertTrue(userDao.markUserBannedUnbanned(user));

        user.setBanned(BANNED.getStatus());
        Assert.assertTrue(userDao.markUserBannedUnbanned(user));
    }

    @Test
    public void testMarkUserUndeleted() {
        user.setId(ID_FOR_MARKING_DELETING);
        try {
            Assert.assertTrue(userDao.markUserUndeleted(user));
            LOGGER.info("Test_Mark_deleted_user is completed");
        } catch (DaoException e) {
            System.out.println(e);//todo удалить
            LOGGER.info(e);
        }
    }

    @Test
    public void testTakeAllUserUndeleted() {
        try {
            Assert.assertNotNull(userDao.takeAllUserUndeleted());
            LOGGER.info("Test take all is completed successfully");
        } catch (DaoException e) {
            System.out.println(e);//todo удалить
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
            System.out.println(e);//todo удалить
            LOGGER.info(e);
        }
    }
}