package by.epam.club.command.forward;

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

public class FindUserByLoginCommand implements ActionCommand {
    private static final Logger LOGGER = LogManager.getLogger(AuthorizationCommand.class);

    @Override
    public Router execute(RequestContent content) {
        String locale = (String) content.getSessionAttribute(LOCAL_PARAM);
        String page = ConfigurationManager.getProperty(DEFAULT_PAGE_FORVARD);
        TransmisionType transmitionType = TransmisionType.FORWARD;
        String searchUser = content.getRequestParameters(SEARCH_PARAM, 0);

        UserService service = new UserServiceImpl();
        User user = null;
        try {
            user = service.checkUser(searchUser);
            if (user!=null){
                content.putSessionAttribute(FIND_USER_PARAM, user);
                page = ConfigurationManager.getProperty(FIND_USER_PAGE_FORVARD);
            }else {
                content.putRequestAttribute(ERROR_PARAM, MessageManager.getProperty(NO_USER_MESSAGE, locale));
            }
        } catch (ServiceException e) {
            LOGGER.info(SERVICE_EXCEPTION_PARAM, e.getMessage());
            content.putRequestAttribute(ERROR_PARAM, MessageManager.getProperty(e.getMessage(), locale));
        }
         return new Router(page, transmitionType);
    }
}