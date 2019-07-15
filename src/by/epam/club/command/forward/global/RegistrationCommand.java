package by.epam.club.command.forward.global;

import by.epam.club.bundlemanager.ConfigurationManager;
import by.epam.club.command.ActionCommand;
import by.epam.club.command.Router;
import by.epam.club.controller.RequestContent;
import by.epam.club.controller.*;
import by.epam.club.exception.ServiceException;
import by.epam.club.service.user.UserService;

import by.epam.club.bundlemanager.MessageManager;
import by.epam.club.service.user.UserServiceImpl;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import static by.epam.club.entity.Parameter.*;

/**
 * Create new User after receiving parameters from form of registration
 *
 * @author Maeuski Igor
 * @version 1.0
 * @see by.epam.club.command.ActionCommand
 */

public class RegistrationCommand implements ActionCommand {
    private static final Logger LOGGER = LogManager.getLogger(RegistrationCommand.class);

    /**
     * @param content of the class RequestContent
     * @return new Router that send user on a new page by process Type
     */

    @Override
    public Router execute(RequestContent content) {
        String page = ConfigurationManager.getProperty(REGISTRATION_PAGE_FORVARD);
        TransmisionType transmitionType = TransmisionType.FORWARD;
        String newLocale = (String) content.getSessionAttribute(LOCAL_PARAM);
        String login = content.getRequestParameters(LOGIN_PARAM, 0);
        String email = content.getRequestParameters(EMAIL_PARAM, 0);
        String password1 = content.getRequestParameters(PASSWORD_PARAM1, 0);
        String password2 = content.getRequestParameters(PASSWORD_PARAM2, 0);
        UserService service = new UserServiceImpl();
        try {
            if (service.createUserMaster(login, email, password1, password2)) {
                content.putRequestAttribute(REGISTRATION_PARAM, MessageManager.getProperty(DATA_OK_MESSAGE, newLocale));
                page = ConfigurationManager.getProperty(DEFAULT_PAGE_REDIRECT);
            } else {
                content.putRequestAttribute(ERROR_PARAM, MessageManager.getProperty(WRONG_DATA_MESSAGE, newLocale));
            }
        } catch (ServiceException e) {
            content.putRequestAttribute(ERROR_PARAM, MessageManager.getProperty(e.getMessage(), newLocale));
            LOGGER.info(SERVICE_EXCEPTION_PARAM, e.getMessage());
        }
        return new Router(page, transmitionType);
    }
}
