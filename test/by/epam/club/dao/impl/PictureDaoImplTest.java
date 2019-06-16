package by.epam.club.dao.impl;

import by.epam.club.entity.Parameter;
import by.epam.club.entity.Picture;
import by.epam.club.exception.DaoException;
import by.epam.club.pool.ConnectionPool;
import by.epam.club.tool.FromBlobToPicture;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.io.*;
import java.sql.*;


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
        int ARTICLE_ID = 4;
        String PATH = "C:\\Users\\Maevskiy\\Desktop\\1.png";
        Assert.assertTrue(pictureDao.create(NAME, PATH, ARTICLE_ID));

        ///////////////////////////////todo удалить, использовалось для проверки наличия фото
        ConnectionPool connectionPool = ConnectionPool.getInstance();
        Connection con = connectionPool.takeConnection();
        try {
            PreparedStatement st2 = null;
            st2 = con.prepareStatement("select file from picture");
            ResultSet rs = st2.executeQuery();
            Blob blob = null;
            while (rs.next()) {
                blob = rs.getBlob("file");
            }
            FromBlobToPicture from = new FromBlobToPicture();
            from.create(blob);
            InputStream inputStream = blob.getBinaryStream();
            byte[] buffer = new byte[inputStream.available()];
            inputStream.read(buffer);

            File file = new File("C:\\file1.jpg");
            OutputStream outputStream = new FileOutputStream(file);
            outputStream.write(buffer);
        } catch (SQLException | IOException e) {
            e.printStackTrace();
        }
        //////////////////////
    }

    @Test
    public void testMarkDelete() throws DaoException {
        Assert.assertTrue(pictureDao.markDelete(picture));
    }

    @Test
    public void testMarkBannedUnbanned() throws DaoException {
        picture.setBanned(Parameter.UNBANNED_PARAM);
        Assert.assertTrue(pictureDao.markBannedUnbanned(picture));

        picture.setBanned(Parameter.BANNED_PARAM);
        Assert.assertTrue(pictureDao.markBannedUnbanned(picture));
    }
}