package by.epam.club.command.forward;

import by.epam.club.bundlemanager.ConfigurationManager;
import by.epam.club.bundlemanager.MessageManager;
import by.epam.club.command.ActionCommand;
import by.epam.club.command.redirect.AuthorizationCommand;
import by.epam.club.controller.RequestContent;
import by.epam.club.controller.Router;
import by.epam.club.controller.TransmisionType;
import by.epam.club.entity.Parameter;
import by.epam.club.entity.User;
import by.epam.club.exception.ServiceException;
import by.epam.club.service.ServiceProvider;
import by.epam.club.service.UserService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class FindUserByLoginCommand implements ActionCommand {
    private static final Logger LOGGER = LogManager.getLogger(AuthorizationCommand.class);

    @Override
    public Router execute(RequestContent content) {
        String locale = (String) content.getSessionAttribute(Parameter.LOCAL_PARAM);
        String page = ConfigurationManager.getProperty(Parameter.DEFAULT_PAGE);

        TransmisionType transmitionType = TransmisionType.FORWARD;
        String searchUser = content.getRequestParameters(Parameter.SEARCH_PARAM, 0);

        ServiceProvider provider = ServiceProvider.getInstance();
        UserService service = provider.getUserService();
        User user = null;
        try {
            user = service.checkUser(searchUser);
        } catch (ServiceException e) {
            LOGGER.info(Parameter.SERVICE_EXCEPTION_PARAM, e.getMessage());
            content.putRequestAttribute(Parameter.ERROR_PARAM, MessageManager.getProperty(e.getMessage(), locale));
        }
        content.putRequestAttribute(Parameter.FIND_USER_PARAM, user);
        System.out.println(user.toString());

        return new Router(page, transmitionType);
    }

}