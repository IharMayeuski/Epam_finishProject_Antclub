package by.epam.club.dao.impl;

import by.epam.club.entity.Picture;
import by.epam.club.exception.DaoException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import javax.servlet.http.Part;

public class PictureDaoImplTest {
    private static final Logger LOGGER = LogManager.getLogger();
    private PictureDaoImpl pictureDao;

    private final String NAME = "test";
  //  private final Part PART = null;
    private final int ARTICLE_ID = 1;
    private final int TEST_ID = 1;
    private final String PART = "C:\\Users\\Администратор\\Desktop\\рабочий стол\\Thread.jpg";

    @BeforeClass
    public void setUp() {
        pictureDao = new PictureDaoImpl();
    }
    @Test
    public void testCreate() throws DaoException {
        Assert.assertTrue(pictureDao.create(NAME, PART, ARTICLE_ID)); //todo Как создать объект класса Part?
    }

    @Test
    public void testDelete() throws DaoException {
        Picture picture = new Picture();
        picture.setId(TEST_ID);
        Assert.assertTrue(pictureDao.delete(picture));
    }
}