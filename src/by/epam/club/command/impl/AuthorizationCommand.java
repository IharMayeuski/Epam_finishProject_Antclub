package by.epam.club.command.impl;

import by.epam.club.bundlemanager.ConfigurationManager;
import by.epam.club.bundlemanager.MessageManager;
import by.epam.club.command.ActionCommand;
import by.epam.club.controller.RequestContent;
import by.epam.club.controller.*;
import by.epam.club.entity.User;
import by.epam.club.exception.ServiceException;
import by.epam.club.service.ServiceProvider;
import by.epam.club.service.UserService;

public class AuthorizationCommand implements ActionCommand {
    private static final String PARAMETER_LOGIN = "login";
    private static final String PARAMETER_PASSWORD = "password";

    private static final String ADMIN_PAGE = "path.page.admin.main";
    private static final String DEFAULT_PAGE = "path.page.default";
    private static final String USER_PAGE = "path.page.user.main";


    @Override
    public Router execute(RequestContent content) {
        String page = ConfigurationManager.getProperty(DEFAULT_PAGE);
        TransmisionType transmitionType = TransmisionType.FORVARD;
        String login = content.getRequestParameters(PARAMETER_LOGIN, 0);
        String password = content.getRequestParameters(PARAMETER_PASSWORD, 0);
        String locale = (String) content.getSessionAttribute("local");

        ServiceProvider provider = ServiceProvider.getInstance();
        UserService service = provider.getUserService();

        try {
            User user = service.checkUser(login, password);
            if (user != null) {
                content.putSessionAttribute("user", user);

                if (user.getRole().equals("admin")) {
                    page = ConfigurationManager.getProperty(ADMIN_PAGE);
                } else {
                    page = ConfigurationManager.getProperty(USER_PAGE);
                }

                if (user.getDeleted().equals("deleted")) {
                    content.putSessionAttribute("error", MessageManager.getProperty("message.deletedaccount", locale));
                    page = ConfigurationManager.getProperty(DEFAULT_PAGE);
                }
            } else {
                content.putRequestAttribute("error", MessageManager.getProperty("message.loginerror", locale));
            }
        } catch (ServiceException e) {
            content.putRequestAttribute("error", MessageManager.getProperty("message.serviceexception", locale));
        }
        return new Router(page, transmitionType);
    }
}