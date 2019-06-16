package by.epam.club.command.redirect;

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

public class DeleteAccountCommand implements ActionCommand {
    private static final Logger LOGGER = LogManager.getLogger(AuthorizationCommand.class);

    @Override
    public Router execute(RequestContent content)  {
        String email;
        String password;
   /*     String page = ConfigurationManager.getProperty(Parameter.DEFAULT_PAGE);*/

        String page = "/controller?command=go_to_default_page";
        TransmisionType transmitionType = TransmisionType.REDIRECT;
        String locale = (String) content.getSessionAttribute(Parameter.LOCAL_PARAM);

        ServiceProvider provider = ServiceProvider.getInstance();
        UserService service = provider.getUserService();

        User user = (User) content.getSessionAttribute(Parameter.USER_PARAM);

        email = content.getRequestParameters(Parameter.EMAIL_PARAM, 0);
        password = content.getRequestParameters(Parameter.PASSWORD_PARAM, 0);

        try {
            service.markDeleted(user.getLogin(), email, password);
            content.putRequestAttribute(Parameter.ACCOUNT_DELETED_PARAM, MessageManager.getProperty(Parameter.ACCOUNT_DELETED_MESSAGE, locale));
            content.putSessionAttribute(Parameter.ROLE_PARAM, null);
            content.putSessionAttribute(Parameter.USER_PARAM, null);

        } catch (ServiceException e) {
            content.putRequestAttribute(Parameter.ERROR_PARAM, MessageManager.getProperty(Parameter.WRONG_DATA_MESSAGE, locale));
            page = ConfigurationManager.getProperty(Parameter.DELETE_ACCOUNT_USER_PAGE);
        }

        return new Router(page, transmitionType);
    }
}
