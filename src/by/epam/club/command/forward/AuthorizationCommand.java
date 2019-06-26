package by.epam.club.command.forward;

import by.epam.club.bundlemanager.ConfigurationManager;
import by.epam.club.bundlemanager.MessageManager;
import by.epam.club.command.ActionCommand;
import by.epam.club.command.Router;
import by.epam.club.command.TakeTypeNews;
import by.epam.club.controller.RequestContent;
import by.epam.club.controller.*;
import by.epam.club.entity.User;
import by.epam.club.exception.ServiceException;
import by.epam.club.service.user.UserService;
import by.epam.club.service.user.UserServiceImpl;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import static by.epam.club.entity.Parameter.*;

public class AuthorizationCommand implements ActionCommand {
    private static final Logger LOGGER = LogManager.getLogger(AuthorizationCommand.class);

    @Override
    public Router execute(RequestContent content) {
        String page = ConfigurationManager.getProperty(DEFAULT_PAGE_FORVARD);
        TransmisionType transmitionType = TransmisionType.FORWARD;
        String login = content.getRequestParameters(LOGIN_PARAM, 0);
        String password = content.getRequestParameters(PASSWORD_PARAM, 0);
        String locale = (String) content.getSessionAttribute(LOCAL_PARAM); // FIXME: 6/25/2019
//        TakeTypeNews takeTypeNews = new TakeTypeNews();
        UserService userService = new UserServiceImpl();
        try {
            User user = userService.checkUser(login, password);
            content.putSessionAttribute(USER_PARAM, user);
            if (user.getRole().equals(ADMIN_PARAM) && (!user.getDeleted().equals(DELETED_PARAM))) {
                content.putSessionAttribute(ROLE_PARAM, ADMIN_PARAM);
//                takeTypeNews.typeNews(content);  // FIXME: 6/8/2019 уточнить у ИН правильность обращения к отдельному методу
            } else if (user.getRole().equals(USER_PARAM) && (!user.getDeleted().equals(DELETED_PARAM))) {
                content.putSessionAttribute(ROLE_PARAM, USER_PARAM);
//                takeTypeNews.typeNews(content);
            }
            if (user.getDeleted().equals(DELETED_PARAM)) {
                content.putSessionAttribute(TYPES_PARAM, null);
                content.putRequestAttribute(ERROR_PARAM, MessageManager.getProperty(DELETE_ACCOUNT_MESSAGE, locale));
            }
        } catch (ServiceException e) {
            LOGGER.info(SERVICE_EXCEPTION_PARAM, e.getMessage());
            content.putRequestAttribute(ERROR_PARAM, MessageManager.getProperty(e.getMessage(), locale));
        }
        return new Router(page, transmitionType);
    }
}