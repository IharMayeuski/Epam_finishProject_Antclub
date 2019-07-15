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
 * Command for mark 'User is deleted'
 *
 * @author Maeuski Igor
 * @version 1.0
 * @see by.epam.club.command.ActionCommand
 */


public class DeleteUserCommand implements ActionCommand {
    private static final Logger LOGGER = LogManager.getLogger(DeleteUserCommand.class);

    /**
     * @param content of the class RequestContent
     * @return new Router that send user on a new page by process Type
     */

    @Override
    public Router execute(RequestContent content) {
        String locale = (String) content.getSessionAttribute(Parameter.LOCAL_PARAM);
        TransmisionType transmitionType = TransmisionType.FORWARD;
        UserService userService = new UserServiceImpl();
        String page = ConfigurationManager.getProperty(DEFAULT_PAGE_FORVARD);
        String userId = content.getRequestParameters(USER_ID_PARAM,0);
        if (userId==null){
           User user = (User) content.getSessionAttribute(FIND_USER_PARAM);
           userId = Long.toString(user.getId());
        }

        try {
            userService.deletedUser(userId);
            content.putRequestAttribute(UPDATE_ALL_IS_OK_PARAM, MessageManager.getProperty(UPDATE_ALL_IS_OK_PARAM, locale));

        } catch (ServiceException e) {
            LOGGER.info(Parameter.SERVICE_EXCEPTION_PARAM, e.getMessage());
            content.putRequestAttribute(ERROR_PARAM, MessageManager.getProperty(e.getMessage(), locale));
        }
        return new Router(page, transmitionType);
    }
}