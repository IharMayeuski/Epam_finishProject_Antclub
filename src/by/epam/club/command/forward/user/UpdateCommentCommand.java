package by.epam.club.command.forward.user;

import by.epam.club.bundlemanager.ConfigurationManager;
import by.epam.club.bundlemanager.MessageManager;
import by.epam.club.command.ActionCommand;
import by.epam.club.command.Router;
import by.epam.club.controller.RequestContent;
import by.epam.club.controller.TransmisionType;
import by.epam.club.exception.ServiceException;
import by.epam.club.service.comment.CommentService;
import by.epam.club.service.comment.CommentServiceImpl;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import static by.epam.club.entity.Parameter.*;
import static by.epam.club.entity.Parameter.ERROR_PARAM;

/**
 * @author Maeuski Igor
 * @version 1.0
 * @see by.epam.club.command.ActionCommand
 */

public class UpdateCommentCommand implements ActionCommand {
    private static final Logger LOGGER = LogManager.getLogger(UpdateCommentCommand.class);

    /**
     * @param content of the class RequestContent
     * @return new Router that send user on a new page by process Type
     */

    @Override
    public Router execute(RequestContent content) {
        String commentId = content.getRequestParameters(COMMENT_ID, 0);
        String text = content.getRequestParameters(TEXT_PARAM,0);
        String page = ConfigurationManager.getProperty(DEFAULT_PAGE_FORVARD);
        String locale = (String) content.getSessionAttribute(LOCAL_PARAM);
        TransmisionType transmitionType = TransmisionType.FORWARD;
        CommentService commentService = new CommentServiceImpl();
        try {
            commentService.updateComment(commentId, text);
            content.putRequestAttribute(UPDATE_ALL_IS_OK_PARAM, MessageManager.getProperty(UPDATE_ALL_IS_OK_PARAM, locale));
        } catch (ServiceException e) {
            LOGGER.info(SERVICE_EXCEPTION_PARAM, e.getMessage());
            content.putRequestAttribute(ERROR_PARAM, MessageManager.getProperty(e.getMessage(), locale));
        }
        return new Router(page, transmitionType);
    }
}

