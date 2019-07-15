package by.epam.club.service.comment;

import by.epam.club.dao.basedao.BaseDao;
import by.epam.club.dao.commentdao.CommentDao;
import by.epam.club.dao.commentdao.CommentDaoImpl;
import by.epam.club.exception.DaoException;
import by.epam.club.exception.ServiceException;

/**
 * Class of business logic for Comment
 *
 * @author Maeuski Igor
 * @version 1.0
 */

public class CommentServiceImpl extends BaseDao implements CommentService {
    /**
     * @param text for creating comment
     * @param userId user's id who makes comment
     * @param articleId article's id for comment
     * @throws ServiceException in the case of mistake if we catch DaoException we will throw ServiceException
     */

    @Override
    public void createOne(String text, long userId, long articleId) throws ServiceException {
        CommentDao commentDao = new CommentDaoImpl();
        try {
            commentDao.createOneComment(text, Long.toString(userId), Long.toString(articleId));
        } catch (DaoException e) {
            throw new ServiceException(e.getMessage());
        }
    }

    /**
     * @param commentId we mark label deleted our comment
     * @throws ServiceException in the case of mistake if we catch DaoException we will throw ServiceException
     */

    @Override
    public void deleteComment(String commentId) throws ServiceException {
        CommentDao commentDao = new CommentDaoImpl();
        try {
            commentDao.deleteComment(commentId);
        } catch (DaoException e) {
            throw new ServiceException(e.getMessage());
        }
    }

    /**
     * @param commentId for checking comment's id
     * @param text for updating
     * @throws ServiceException in the case of mistake if we catch DaoException we will throw ServiceException
     */

    @Override
    public void updateComment(String commentId, String text) throws ServiceException {
        CommentDao commentDao = new CommentDaoImpl();
        try {
            commentDao.updateComment(commentId, text);
        } catch (DaoException e) {
            throw new ServiceException(e.getMessage());
        }
    }
}
