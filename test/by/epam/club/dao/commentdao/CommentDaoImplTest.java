package by.epam.club.dao.commentdao;

import by.epam.club.dao.articledao.ArticleDaoImpl;
import by.epam.club.dao.articledao.ArticleDaoImplTest;
import by.epam.club.entity.Comment;
import by.epam.club.exception.DaoException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static org.testng.Assert.*;

public class CommentDaoImplTest {
    private static final Logger LOGGER = LogManager.getLogger(CommentDaoImplTest.class);
    private CommentDaoImpl commentDao;
    private Comment comment;
    private final String POSITIVE_TEST = "Positive test is completed successfully";
    private final String POSITIVE = "positive_test";
    private final String NEGATIVE = "negative_test";
    private final String TEXT = "test";

    private final String TEST_TYPE_ID = "1";


    @BeforeClass(groups = {POSITIVE, NEGATIVE})
    public void setUp() {
        commentDao = new CommentDaoImpl();
        comment = new Comment();

    }

    @Test(groups = {POSITIVE})
    public void testCreateOneComment() throws DaoException {
        String scryptDeleteFromType = "DELETE from comment where comment=?";
        commentDao.createOneComment(TEXT,TEST_TYPE_ID, TEST_TYPE_ID);
        commentDao.create(scryptDeleteFromType, TEXT);
        LOGGER.info(POSITIVE_TEST);
    }


}