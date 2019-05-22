package by.epam.club.dao.impl;

import by.epam.club.entity.User;
import by.epam.club.exception.DaoException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class UserDaoImplTest {
    private static final Logger LOGGER = LogManager.getLogger();
    private UserDaoImpl userDao;
    private User user;
    private String LOGIN = "test2";
    private String EMAIL = "test2@test.test";
    private String PASSWORD = "test";
    private int ID_FOR_MARKING_DELETING=3;//todo before test Please to change user.deleted_account_id in position_1

    @BeforeClass
    public void setUp() {
        userDao = new UserDaoImpl();
        user = new User();
    }

    @Test
    public void testCheck() {
    }

    @Test
    public void testCreateUser() {
        try {
            Assert.assertTrue(userDao.createUser(LOGIN,EMAIL,PASSWORD));
            LOGGER.info("Test create user is completed successfully");
        } catch (DaoException e) {
            System.out.println("excep");
            LOGGER.info("Test create user is failed");
        }
    }

    @Test
    public void testDeleteUser() {
        user.setId(ID_FOR_MARKING_DELETING);

        try {
            Assert.assertTrue(userDao.deleteUser(user));
            LOGGER.info("Test_Mark_deleted_user is completed");
        } catch (DaoException e) {
            System.out.println("excep2");

            LOGGER.info("Test_Mark_deleted_user is failed");
        }
    }
}