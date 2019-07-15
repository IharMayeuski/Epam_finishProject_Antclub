package by.epam.club.command.forward.admin;

import by.epam.club.bundlemanager.ConfigurationManager;
import by.epam.club.bundlemanager.MessageManager;
import by.epam.club.command.ActionCommand;
import by.epam.club.command.Router;
import by.epam.club.controller.RequestContent;
import by.epam.club.controller.TransmisionType;
import by.epam.club.entity.Parameter;
import by.epam.club.entity.User;
import by.epam.club.exception.ServiceException;
import by.epam.club.service.user.UserService;
import by.epam.club.service.user.UserServiceImpl;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import static by.epam.club.entity.Parameter.*;

/**
 * Command for mark 'User has new role - Admin'
 *
 * @author Maeuski Igor
 * @version 1.0
 * @see by.epam.club.command.ActionCommand
 */


public class MarkAdminCommand implements ActionCommand {
    private static final Logger LOGGER = LogManager.getLogger(MarkAdminCommand.class);

    /**
     * @param content of the class RequestContent
     * @return new Router that send user on a new page by process Type
     */

    @Override
    public Router execute(RequestContent content)  {
        User findUser = (User) content.getSessionAttribute(FIND_USER_PARAM);
        UserService userService = new UserServiceImpl();
        String page = ConfigurationManager.getProperty(DEFAULT_PAGE_FORVARD);
        TransmisionType transmisionType = TransmisionType.FORWARD;
        String locale = (String) content.getSessionAttribute(LOCAL_PARAM);
        try {
            userService.markAdmin(findUser);
            content.putRequestAttribute(UPDATE_ALL_IS_OK_PARAM, MessageManager.getProperty(UPDATE_ALL_IS_OK_PARAM, locale));
        } catch (ServiceException e) {
            LOGGER.info(SERVICE_EXCEPTION_PARAM, e.getMessage());
            content.putRequestAttribute(ERROR_PARAM, MessageManager.getProperty(e.getMessage(), locale));
        }
        return new Router(page, transmisionType);

    }
}