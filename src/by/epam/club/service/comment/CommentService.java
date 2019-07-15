package by.epam.club.service.comment;

import by.epam.club.exception.ServiceException;

/**
 * Interface for business logic for Comment
 *
 * @author Maeuski Igor
 * @version 1.0
 */

public interface CommentService  {

    void createOne(String text, long userId, long articleId) throws ServiceException;

    void deleteComment(String commentId) throws ServiceException;

    void updateComment(String commentId, String text) throws ServiceException;
}
