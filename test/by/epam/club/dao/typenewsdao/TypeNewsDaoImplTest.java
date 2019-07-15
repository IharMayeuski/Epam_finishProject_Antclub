package by.epam.club.dao.typenewsdao;

import by.epam.club.entity.TypeNews;
import by.epam.club.exception.DaoException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.List;

import static by.epam.club.entity.Parameter.*;

public class TypeNewsDaoImplTest {
    private static final Logger LOGGER = LogManager.getLogger(TypeNewsDaoImplTest.class);
    private TypeNewsDaoImpl typeNewsDao;
    private TypeNews typeNews;
    private final int TEST_TYPE_POSITIVE = 62;

    private final String NEGATIVE_TEST = "Negative test is completed successfully";
    private final String POSITIVE_TEST = "Positive test is completed successfully";
    private final String POSITIVE = "positive_test";
    private final String NEGATIVE = "negative_test";


    @BeforeClass(groups = {POSITIVE, NEGATIVE})
    public void setUp() {
        typeNewsDao = new TypeNewsDaoImpl();
        typeNews = new TypeNews();
    }

    @Test(groups = {POSITIVE})
    public void testTakeUndeletedTypesPositive() throws DaoException {
        List<TypeNews> listTypeNews = typeNewsDao.takeUndeletedTypes();
        Assert.assertNotNull(listTypeNews);
        LOGGER.info(POSITIVE_TEST + ", count of typeNews: " + listTypeNews.size());
    }

    @Test(groups = {POSITIVE})
    public void testCreate() throws DaoException {
        String testTypeCreateNew = "new_test";
        typeNewsDao.create(testTypeCreateNew);
        typeNews = typeNewsDao.findType(testTypeCreateNew, TYPES_PARAM);
        Assert.assertNotNull(typeNews.getTypeNews());
        LOGGER.info(POSITIVE_TEST);
        String scryptDeleteFromType = "DELETE from type where type=?";
        typeNewsDao.create(scryptDeleteFromType, testTypeCreateNew);
    }

    @Test(groups = {POSITIVE})
    public void testTakeAllTypes() throws DaoException {
        List<TypeNews> listTypeNews = typeNewsDao.takeAllTypes();
        Assert.assertNotNull(listTypeNews);
        LOGGER.info(POSITIVE_TEST + ", count of typeNews: " + listTypeNews.size());
    }

    @Test(groups = {POSITIVE})
    public void testTakeOnePositive() throws DaoException {
        Assert.assertNotNull(typeNewsDao.takeOne(TEST_TYPE_POSITIVE));
        LOGGER.info(POSITIVE_TEST);
    }

    @Test(groups = {NEGATIVE})
    public void testTakeOneNegative() {
        try {
            int testTypeNegative = 55;
            typeNewsDao.takeOne(testTypeNegative);
        } catch (DaoException e) {
            LOGGER.info(NEGATIVE_TEST);
        }
    }

    @Test(groups = {POSITIVE})
    public void testMarkUndeletedPositive() throws DaoException {
        typeNewsDao.markUndeleted(TEST_TYPE_POSITIVE);
        typeNews = typeNewsDao.takeOne(TEST_TYPE_POSITIVE);
        String actualResult = typeNews.getDeleted();
        Assert.assertEquals(actualResult, UNDELETED_PARAM);
        LOGGER.info(POSITIVE_TEST);
    }

    @Test(groups = {POSITIVE}, priority = 1)
    public void testMarkDeletedPositive() throws DaoException {
        typeNewsDao.markDeleted(TEST_TYPE_POSITIVE);
        typeNews = typeNewsDao.takeOne(TEST_TYPE_POSITIVE);
        String actualResult = typeNews.getDeleted();
        Assert.assertEquals(actualResult, DELETED_PARAM);
        LOGGER.info(POSITIVE_TEST);
    }

    @Test(groups = {POSITIVE})
    public void testFindTypePositive() throws DaoException {
        String testTypeTextPositive = "test";
        Assert.assertNotNull(typeNewsDao.findType(testTypeTextPositive, TYPES_PARAM));
        LOGGER.info(POSITIVE_TEST);
    }

    @Test(groups = {NEGATIVE})
    public void testFindTypeNegative() {
        try {
            String testTypeTextNegative = "test22";
            typeNewsDao.findType(testTypeTextNegative, TYPES_PARAM);
        } catch (DaoException e) {
            LOGGER.info(NEGATIVE_TEST);
        }
    }

}