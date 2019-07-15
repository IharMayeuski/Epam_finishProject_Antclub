package by.epam.club.command.forward.user;

import by.epam.club.bundlemanager.ConfigurationManager;
import by.epam.club.bundlemanager.MessageManager;
import by.epam.club.command.Router;
import by.epam.club.controller.RequestContent;
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

public class AccountUpdateCommand implements by.epam.club.command.ActionCommand {
    private static final Logger LOGGER = LogManager.getLogger(AccountUpdateCommand.class);

    /**
     * @param content of the class RequestContent
     * @return new Router that send user on a new page by process Type
     */

    @Override
    public Router execute(RequestContent content) {
        String email;
        String password1;
        String password2;
        String login;
        String firstname;
        String familyname;
        String page = ConfigurationManager.getProperty(DEFAULT_PAGE_FORVARD);
        TransmisionType transmitionType = TransmisionType.FORWARD;
        String locale = (String) content.getSessionAttribute(LOCAL_PARAM);
        UserService service = new UserServiceImpl();
        User user = (User) content.getSessionAttribute(USER_PARAM);
        email = content.getRequestParameters(EMAIL_PARAM, 0);
        password1 = content.getRequestParameters(PASSWORD_PARAM1, 0);
        password2 = content.getRequestParameters(PASSWORD_PARAM2, 0);
        login = content.getRequestParameters(LOGIN_PARAM, 0);
        firstname = content.getRequestParameters(FIRSTNAME_PARAM, 0);
        familyname = content.getRequestParameters(FAMILYNAME_PARAM, 0);
        try {
            service.updateUser(user, email, login, password1, password2, firstname, familyname);
            content.putSessionAttribute(USER_PARAM, null);
            content.putSessionAttribute(ROLE_PARAM, null);
            content.putRequestAttribute(UPDATE_ALL_IS_OK_PARAM, MessageManager.getProperty(DATA_OK_MESSAGE , locale));
        } catch (ServiceException e) {
            LOGGER.info(SERVICE_EXCEPTION_PARAM, e.getMessage());
            content.putRequestAttribute(ERROR_PARAM, MessageManager.getProperty(e.getMessage(), locale));
            page=ConfigurationManager.getProperty(USER_PROFILE_PAGE_FORVARD);
        }
        return new Router(page, transmitionType);
    }
}
