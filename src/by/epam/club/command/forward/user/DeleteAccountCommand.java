package by.epam.club.command.forward.user;

import by.epam.club.bundlemanager.ConfigurationManager;
import by.epam.club.bundlemanager.MessageManager;
import by.epam.club.command.ActionCommand;
import by.epam.club.controller.RequestContent;
import by.epam.club.command.Router;
import by.epam.club.controller.TransmisionType;
import by.epam.club.entity.User;
import by.epam.club.exception.ServiceException;
import by.epam.club.service.user.UserService;
import by.epam.club.service.user.UserServiceImpl;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import static by.epam.club.entity.Parameter.*;

/**
 * @author Maeuski Igor
 * @version 1.0
 * @see by.epam.club.command.ActionCommand
 */

public class DeleteAccountCommand implements ActionCommand {
    private static final Logger LOGGER = LogManager.getLogger(DeleteAccountCommand.class);

    /**
     * @param content of the class RequestContent
     * @return new Router that send user on a new page by process Type
     */

    @Override
    public Router execute(RequestContent content) {
        UserService service = new UserServiceImpl();
        String email;
        String password;
        String page = ConfigurationManager.getProperty(DEFAULT_PAGE_FORVARD);
        String locale = (String) content.getSessionAttribute(LOCAL_PARAM);
        TransmisionType transmisionType = TransmisionType.FORWARD;
        try {
            User user = (User) content.getSessionAttribute(USER_PARAM);
            email = content.getRequestParameters(EMAIL_PARAM, 0);
            password = content.getRequestParameters(PASSWORD_PARAM, 0);
            service.markDeleted(user.getLogin(), email, password);
            content.putRequestAttribute(ACCOUNT_DELETED_PARAM, MessageManager.getProperty(ACCOUNT_DELETED_MESSAGE, locale));
            content.putSessionAttribute(ROLE_PARAM, null);
            content.putSessionAttribute(USER_PARAM, null);
            content.putSessionAttribute(TYPES_PARAM, null);
        } catch (ServiceException e) {
            LOGGER.info(SERVICE_EXCEPTION_PARAM, e.getMessage());
            content.putRequestAttribute(ERROR_PARAM, MessageManager.getProperty(WRONG_DATA_MESSAGE, locale));
            page = ConfigurationManager.getProperty(USER_PROFILE_PAGE_FORVARD);
        }
        return new Router(page, transmisionType);
    }
}
