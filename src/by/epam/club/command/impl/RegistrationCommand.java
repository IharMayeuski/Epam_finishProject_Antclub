package by.epam.club.command.impl;

import by.epam.club.command.ConfigurationManager;
import by.epam.club.exception.ServiceException;
import by.epam.club.service.ServiceProviderCommand;
import by.epam.club.service.UserService;

import by.epam.club.command.ActionCommand;
import resource.MessageManager;

import javax.servlet.http.HttpServletRequest;

public class RegistrationCommand implements ActionCommand {
    private static final String PARAMETER_LOGIN = "login";
    private static final String PARAMETER_PASSWORD1 = "password1";
    private static final String PARAMETER_PASSWORD2 = "password2";
    private static final String PARAMETER_EMAIL = "email";

    private static final String DEFAULT_PAGE = "path.page.default";
    private static final String REGISTRATION_PAGE = "path.page.registration";

    @Override
    public String execute(HttpServletRequest request) {
        String page = ConfigurationManager.getProperty(DEFAULT_PAGE);

        String login = request.getParameter(PARAMETER_LOGIN);
        String email = request.getParameter(PARAMETER_EMAIL);
        String password1 = request.getParameter(PARAMETER_PASSWORD1);
        String password2 = request.getParameter(PARAMETER_PASSWORD2);

        ServiceProviderCommand provider = ServiceProviderCommand.getInstance();
        UserService service = provider.getService();

        try {
            if (service.createUserMaster(login, email, password1, password2)) {
                request.setAttribute("registration", MessageManager.getProperty("message.dataok"));
            } else {
                request.setAttribute("error", MessageManager.getProperty("message.wrongdata"));
            }
        } catch (ServiceException e) {
            request.setAttribute("error", e.getMessage());
            page = ConfigurationManager.getProperty(REGISTRATION_PAGE);
        }
        return page;
    }
}
