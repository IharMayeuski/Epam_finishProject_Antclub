package by.epam.club.dao.letterdao;

import by.epam.club.entity.Letter;
import by.epam.club.exception.DaoException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.Date;
import java.util.List;

import static by.epam.club.entity.Parameter.DELETED_PARAM;
import static by.epam.club.entity.Parameter.LETTER_FROM_PARAM;

public class LetterDaoImplTest {
    private static final Logger LOGGER = LogManager.getLogger(LetterDaoImplTest.class);
    private LetterDaoImpl letterDao;
    private Letter letter;
    private String newDate;
    private final String NEGATIVE_TEST = "Negative test is completed successfully";
    private final String POSITIVE_TEST = "Positive test is completed successfully";
    private final String POSITIVE = "positive_test";
    private final String NEGATIVE = "negative_test";
    private final String TITLE = "my special title text";
    private final String TEXT = "my text";
    private final long FROM_ID = 1;
    private final long NEGATIVE_ID =-2334;
    private final String TO_ID = "15";


    @BeforeClass(groups = {POSITIVE, NEGATIVE})
    public void setUp() {
        Date date = new Date();
        newDate = String.valueOf(date.toInstant().toEpochMilli());
        letterDao = new LetterDaoImpl();
        letter = new Letter();}

    @Test(groups = {POSITIVE})
    public void testSendLetterPositive() throws DaoException {
        String scrypt = "SELECT letter_id, title, text, letter.banned, letter.deleted, date, user_id, login " +
                "FROM user JOIN letter  on user.id = letter.to_user_id where title=? and text=?";
        String scryptDeleteFromLetter = "DELETE from letter where title='"+TITLE+"' and text='"+TEXT+"'";

        letterDao.sendLetter(Long.toString(FROM_ID),TO_ID,newDate,TITLE,TEXT);
        letter=(Letter) letterDao.find(scrypt,LETTER_FROM_PARAM, TITLE,TEXT).get(0);
        Assert.assertNotNull(letter);
        LOGGER.info(POSITIVE_TEST);
        letterDao.create(scryptDeleteFromLetter);
    }

    @Test(groups = {POSITIVE})
    public void testTakeLetterUserSentPositive() throws DaoException {
        List<Letter> letterList = letterDao.takeLetterUserReceivedFrom(FROM_ID);
        Assert.assertTrue(letterList.size()>0);
        LOGGER.info(POSITIVE_TEST);
    }

    @Test(groups = {NEGATIVE})
    public void testTakeLetterUserSentNegative() throws DaoException {
        List<Letter> letterList = letterDao.takeLetterUserReceivedFrom(NEGATIVE_ID);
        Assert.assertEquals(letterList.size(), 0);
        LOGGER.info(NEGATIVE_TEST);
    }

    @Test(groups = {POSITIVE})
    public void testTakeLetterUserReceivedFromPositive() throws DaoException {
        List<Letter> letterList = letterDao.takeLetterUserSent(FROM_ID);
        Assert.assertTrue(letterList.size()>0);
        LOGGER.info(POSITIVE_TEST);
    }

    @Test(groups = {NEGATIVE})
    public void testTakeLetterUserReceivedFromNegative() throws DaoException {
        List<Letter> letterList = letterDao.takeLetterUserSent(NEGATIVE_ID);
        Assert.assertEquals(letterList.size(), 0);
        LOGGER.info(NEGATIVE_TEST);
    }

    @Test(groups = {POSITIVE})
    public void testDeleteLetter() throws DaoException{
        String scrypt = "SELECT letter_id, title, text, letter.banned, letter.deleted, date, user_id, login " +
                "FROM user JOIN letter  on user.id = letter.to_user_id where title=? and text=?";
        String scryptDeleteFromLetter = "DELETE from letter where title='"+TITLE+"' and text='"+TEXT+"'";
        letterDao.sendLetter(Long.toString(FROM_ID),TO_ID,newDate,TITLE,TEXT);
        letter=(Letter) letterDao.find(scrypt,LETTER_FROM_PARAM, TITLE,TEXT).get(0);
        letterDao.deleteLetter(Long.toString(letter.getId()));
        letter=(Letter) letterDao.find(scrypt,LETTER_FROM_PARAM, TITLE,TEXT).get(0);
        Assert.assertEquals(letter.getDeleted(),DELETED_PARAM);
        letterDao.create(scryptDeleteFromLetter);
    }
}