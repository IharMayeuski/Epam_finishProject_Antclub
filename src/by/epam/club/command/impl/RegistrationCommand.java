package by.epam.club.command.impl;

import by.epam.club.bundlemanager.ConfigurationManager;
import by.epam.club.command.ActionCommand;
import by.epam.club.controller.RequestContent;
import by.epam.club.controller.*;
import by.epam.club.exception.ServiceException;
import by.epam.club.service.ServiceProvider;
import by.epam.club.service.UserService;

import by.epam.club.bundlemanager.MessageManager;

public class RegistrationCommand implements ActionCommand {
    private static final String PARAMETER_LOGIN = "login";
    private static final String PARAMETER_PASSWORD1 = "password1";
    private static final String PARAMETER_PASSWORD2 = "password2";
    private static final String PARAMETER_EMAIL = "email";

    private static final String DEFAULT_PAGE = "path.page.default";
    private static final String REGISTRATION_PAGE = "path.page.registration";

    @Override
    public Router execute(RequestContent content) {
        String page = ConfigurationManager.getProperty(DEFAULT_PAGE);
        TransmisionType transmitionType = TransmisionType.FORVARD;
        String newLocale = (String) content.getSessionAttribute("local");

        String login = content.getRequestParameters(PARAMETER_LOGIN, 0);
        String email = content.getRequestParameters(PARAMETER_EMAIL, 0);
        String password1 = content.getRequestParameters(PARAMETER_PASSWORD1, 0);
        String password2 = content.getRequestParameters(PARAMETER_PASSWORD2, 0);

        ServiceProvider provider = ServiceProvider.getInstance();
        UserService service = provider.getUserService();

        try {
            if (service.createUserMaster(login, email, password1, password2)) {
                content.putRequestAttribute("registration", MessageManager.getProperty("message.dataok", newLocale));
            } else {
                content.putRequestAttribute("error", MessageManager.getProperty("message.wrongdata", newLocale));
            }
        } catch (ServiceException e) {
            content.putRequestAttribute("error", MessageManager.getProperty(e.getMessage(), newLocale));
            page = ConfigurationManager.getProperty(REGISTRATION_PAGE);
        }

        return new Router(page, transmitionType);
    }
}
