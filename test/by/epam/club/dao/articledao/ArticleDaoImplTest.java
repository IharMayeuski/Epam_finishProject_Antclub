package by.epam.club.dao.articledao;

import by.epam.club.entity.Article;
import by.epam.club.exception.DaoException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.List;


public class ArticleDaoImplTest {
    private static final Logger LOGGER = LogManager.getLogger(ArticleDaoImplTest.class);
    private ArticleDaoImpl articleDao;
    private final String POSITIVE_TEST = "Positive test is completed successfully";
    private final String POSITIVE = "positive_test";
    private final String NEGATIVE = "negative_test";
    private final int TEST_TYPE_ID = 1;


    @BeforeClass(groups = {POSITIVE, NEGATIVE})
    public void setUp() {
        articleDao = new ArticleDaoImpl();
    }

    @Test(groups = {POSITIVE})
    public void testTakeAllByTypeNews()throws DaoException {
        List<Article> articles = articleDao.takeAllByTypeNews(TEST_TYPE_ID);
        Assert.assertTrue(articles.size()>0);
        LOGGER.info(POSITIVE_TEST);
    }

    @Test(groups = {NEGATIVE})
    public void testTakeAllByTypeNewsNegative()throws DaoException {
        String negativeTest = "Negative test is completed successfully";
        int negativeId = -2334;
        List<Article> articles = articleDao.takeAllByTypeNews(negativeId);
        Assert.assertEquals(articles.size(), 0);
        LOGGER.info(negativeTest);
    }

    @Test(groups = {POSITIVE})
    public void testTakeAllByTypeNewsNotBannedNotDeleted() throws DaoException {
        List<Article> articles = articleDao.takeAllByTypeNews(TEST_TYPE_ID);
        Assert.assertTrue(articles.size()>0);
        LOGGER.info(POSITIVE_TEST);
    }

    @Test(groups = {POSITIVE})
    public void testUpdateArticle() throws DaoException{
        String testArticleId = "40";
        String testNewText = "test_test";
        articleDao.updateArticle(testArticleId, testNewText);
        String testText = "test";
        articleDao.updateArticle(testArticleId, testText);
        LOGGER.info(POSITIVE_TEST);
    }
}