package by.epam.club.command.forward.admin;

import by.epam.club.bundlemanager.ConfigurationManager;
import by.epam.club.bundlemanager.MessageManager;
import by.epam.club.command.ActionCommand;
import by.epam.club.command.Router;
import by.epam.club.controller.RequestContent;
import by.epam.club.controller.TransmisionType;
import by.epam.club.entity.User;
import by.epam.club.exception.ServiceException;
import by.epam.club.service.type.TypeService;
import by.epam.club.service.type.TypeServiceImpl;
import by.epam.club.service.user.UserService;
import by.epam.club.service.user.UserServiceImpl;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

import static by.epam.club.entity.Parameter.*;
import static by.epam.club.entity.Parameter.SERVICE_EXCEPTION_PARAM;

/**
 * Command sends admin to his special Admin panel
 *
 * @author Maeuski Igor
 * @version 1.0
 * @see by.epam.club.command.ActionCommand
 */


public class ToAdminControlCommand implements ActionCommand {
    private static final Logger LOGGER = LogManager.getLogger(ToAdminControlCommand.class);

    /**
     * @param content of the class RequestContent
     * @return new Router that send user on a new page by process Type
     */

    @Override
    public Router execute(RequestContent content) {
        UserService userService = new UserServiceImpl();
        TypeService typeService = new TypeServiceImpl();

        String newLocale = (String) content.getSessionAttribute(LOCAL_PARAM);
        String page = ConfigurationManager.getProperty(ADMIN_VIEW_PAGE_FORVARD);
        TransmisionType transmisionType = TransmisionType.FORWARD;
        try {
            List<User> users = userService.takeAll();
            content.putSessionAttribute(ALL_USERS_PARAM,users);
            content.putSessionAttribute(DELETED_USERS_PARAM , userService.takeDeleted());
            content.putSessionAttribute(BANNED_USERS_PARAM , userService.takeBanned());
            content.putSessionAttribute(ALL_TYPES_PARAM, typeService.takeAll());
        } catch (ServiceException e) {
            content.putRequestAttribute(ERROR_PARAM, MessageManager.getProperty(e.getMessage(), newLocale));
            LOGGER.info(SERVICE_EXCEPTION_PARAM, e.getMessage());
        }

        return new Router(page, transmisionType);
    }
}
