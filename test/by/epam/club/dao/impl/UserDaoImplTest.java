package by.epam.club.dao.impl;

import by.epam.club.entity.User;
import by.epam.club.exception.DaoException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

//todo before test Please to change user with id_3:  user.deleted_account_id change on 1
public class UserDaoImplTest {
    private static final Logger LOGGER = LogManager.getLogger();
    private UserDaoImpl userDao;
    private User user;

    private String LOGIN = "smirnov";
    private String EMAIL = "smirnov123@gmail.com";
    private String PASSWORD = "12345";
    private int ID_FOR_MARKING_DELETING = 3;

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
    public void testFindByLogin() {
        try {
            Assert.assertNotNull(userDao.findUserByLogin("admin"));
            LOGGER.info("Test take all is completed successfully");
        } catch (DaoException e) {
            System.out.println(e);//todo удалить
            LOGGER.info(e);
        }
    }
}