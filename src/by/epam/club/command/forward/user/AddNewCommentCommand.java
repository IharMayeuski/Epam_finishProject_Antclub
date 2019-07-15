package by.epam.club.command.forward.user;

import by.epam.club.bundlemanager.ConfigurationManager;
import by.epam.club.bundlemanager.MessageManager;
import by.epam.club.command.ActionCommand;
import by.epam.club.command.Router;
import by.epam.club.controller.RequestContent;
import by.epam.club.controller.TransmisionType;
import by.epam.club.entity.User;
import by.epam.club.exception.ServiceException;
import by.epam.club.service.comment.CommentService;
import by.epam.club.service.comment.CommentServiceImpl;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import static by.epam.club.entity.Parameter.*;

/**
 * @author Maeuski Igor
 * @version 1.0
 * @see by.epam.club.command.ActionCommand
 */

public class AddNewCommentCommand implements ActionCommand {
    private static final Logger LOGGER = LogManager.getLogger(AddNewCommentCommand.class);

    /**
     * @param content of the class RequestContent
     * @return new Router that send user on a new page by process Type
     */

    @Override
    public Router execute(RequestContent content) {
        String page = ConfigurationManager.getProperty(NEW_COMMENT_PAGE_FORVARD);
        TransmisionType transmitionType = TransmisionType.FORWARD;
        String locale = (String) content.getSessionAttribute(LOCAL_PARAM);
        long articleId = Long.parseLong((String) (content.getSessionAttribute(ARTICLE_ID_PARAM)));
        User user = (User) content.getSessionAttribute(USER_PARAM);
        String text = content.getRequestParameters(TEXT_PARAM, 0);
        CommentService commentService = new CommentServiceImpl();
        try {
            commentService.createOne(text, user.getId(), articleId);
            content.putRequestAttribute(UPDATE_ALL_IS_OK_PARAM, MessageManager.getProperty(UPDATE_ALL_IS_OK_PARAM, locale));
            page = ConfigurationManager.getProperty(DEFAULT_PAGE_FORVARD);
        } catch (ServiceException e) {
            LOGGER.info(SERVICE_EXCEPTION_PARAM, e.getMessage());
            content.putRequestAttribute(ERROR_PARAM, MessageManager.getProperty(e.getMessage(), locale));
        }
        return new Router(page, transmitionType);
    }
}
