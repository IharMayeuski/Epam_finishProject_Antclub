package by.epam.club.dao.impl;

import by.epam.club.dao.articledao.ArticleDaoImpl;
import by.epam.club.exception.DaoException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class ArticleDaoImplTest {
    private static final Logger LOGGER = LogManager.getLogger();
    private ArticleDaoImpl articleDao;

    private final String NAME = "My friend2";
    private final String TEXT = "When I was young. One day my father took me to the forest...";
    private final int USER_ID = 1;
    private final int TYPE_NEWS = 1;

    @BeforeClass
    public void setUp() {
        articleDao = new ArticleDaoImpl();
           }

    @Test
    public void testCreate() {
        try {
            Assert.assertTrue(articleDao.create(NAME, TEXT, USER_ID, TYPE_NEWS));
        } catch (DaoException e) {
            System.out.println(e);
        }
        LOGGER.info("Test create user is completed successfully");
    }



    @Test
    public void testTakeAllByTypeNews() {
        try {
            Assert.assertNotNull(articleDao.takeAllByTypeNews(TYPE_NEWS));//todo перенести переменную

        } catch (DaoException e) {
            System.out.println(e);
        }
        LOGGER.info("Test create user is completed successfully");

    }


    @Test
    public void testTakeAllByTypeNewsNotBannedNotDeleted() throws DaoException {
        Assert.assertNotNull(articleDao.takeAllByTypeNewsNotBannedNotDeleted(TYPE_NEWS));
    }

    @Test
    public void testUpdate() throws DaoException {
        Assert.assertTrue(articleDao.update("1","1", 2,1));
    }

    @Test
    public void testCheck() throws DaoException {
     //   System.out.println(articleDao.checkUser(3));
        Assert.assertNotNull(articleDao.check(3));
    }
}