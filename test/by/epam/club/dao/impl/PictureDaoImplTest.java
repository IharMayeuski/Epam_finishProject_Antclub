package by.epam.club.dao.impl;

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

    private final String NAME = "name";
    private final Part PART = null;
    private final String OWNER = "comment";
    private final int DEFAULT_BANNED = 1;

    @BeforeClass
    public void setUp() {
        pictureDao = new PictureDaoImpl();
    }
    @Test
    public void testCreate() throws DaoException {
      /*  Assert.assertNull(pictureDao.create(NAME, PART, OWNER, DEFAULT_BANNED));*/ //todo Как создать объект класса Part?

    }
}