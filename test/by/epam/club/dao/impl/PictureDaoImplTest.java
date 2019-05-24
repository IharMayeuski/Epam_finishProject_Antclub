package by.epam.club.dao.impl;

import by.epam.club.entity.Picture;
import by.epam.club.exception.DaoException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static by.epam.club.dao.impl.Status.BANNED;
import static by.epam.club.dao.impl.Status.UNBANNED;

public class PictureDaoImplTest {
    private static final Logger LOGGER = LogManager.getLogger();
    private PictureDaoImpl pictureDao;
    private Picture picture = new Picture();


    @BeforeClass
    public void setUp() {
        pictureDao = new PictureDaoImpl();
        int TEST_ID = 1;
        picture.setId(TEST_ID);

    }

    @Test
    public void testCreate() throws DaoException {
        String NAME = "test";
        int ARTICLE_ID = 1;
        String PATH = "web\\img\\TitlePage.jpg";

        Assert.assertTrue(pictureDao.create(NAME, PATH, ARTICLE_ID));
    }

    @Test
    public void testMarkDelete() throws DaoException {
        Assert.assertTrue(pictureDao.markDelete(picture));
    }

    @Test
    public void testMarkBannedUnbanned() throws DaoException {
        picture.setBanned(UNBANNED.getStatus());
        Assert.assertTrue(pictureDao.markBannedUnbanned(picture));

        picture.setBanned(BANNED.getStatus());
        Assert.assertTrue(pictureDao.markBannedUnbanned(picture));
    }
}