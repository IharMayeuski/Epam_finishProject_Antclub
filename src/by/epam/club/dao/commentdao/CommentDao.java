package by.epam.club.dao.commentdao;

import by.epam.club.dao.basedao.BaseDao;
import by.epam.club.exception.DaoException;
/**
 * Interface of level dao for working with Comment
 *
 * @author Maeuski Igor
 * @version 1.0
 */
public interface CommentDao {

    void createOneComment(String text, String toString, String toString1) throws DaoException;

    void deleteComment(String commentId) throws DaoException;

    void updateComment(String commentId, String text) throws DaoException;

}
