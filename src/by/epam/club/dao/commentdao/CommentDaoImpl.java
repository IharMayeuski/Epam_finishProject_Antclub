package by.epam.club.dao.commentdao;

import by.epam.club.dao.basedao.BaseDao;
import by.epam.club.exception.DaoException;

import java.util.Date;

import static by.epam.club.dao.SqlQuery.*;

/**
 * Class of level dao for working with Comment
 *
 * @author Maeuski Igor
 * @version 1.0
 */

public class CommentDaoImpl extends BaseDao implements CommentDao {
    /**
     *
     * @param text for creating your comment
     * @param userId user's id for understanding who wrote comment
     * @param articleId for writing andt tie article and comment for them
     * @throws DaoException for catching it on the logic level in the case of exception
     */
    @Override
    public void createOneComment(String text, String userId, String articleId) throws DaoException {
        Date date = new Date();
        create(COMMENT_INSERT_NEW.getQuery(), text, Long.toString(date.toInstant().toEpochMilli()), userId, articleId);
    }

    /**
     *
     * @param commentId for mark comment like 'is deleted'
     * @throws DaoException for catching it on the logic level in the case of exception
     *      */
    @Override
    public void deleteComment(String commentId) throws DaoException {
        create(COMMENT_MARK_DELETED.getQuery(), commentId);
    }

    /**
     *
     * @param commentId id for updating comment
     * @param text for changing comment on new
     * @throws DaoException for catching it on the logic level in the case of exception
     */
    @Override
    public void updateComment(String commentId, String text) throws DaoException {
        create(COMMENT_MARK_UPDATE.getQuery(), text, commentId);
    }
}
