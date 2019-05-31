package by.epam.club.command.impl;

import by.epam.club.manager.ConfigurationManager;
import by.epam.club.exception.ServiceException;
import by.epam.club.service.ServiceProvider;
import by.epam.club.service.UserService;

import by.epam.club.command.ActionCommand;
import by.epam.club.manager.MessageManager;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

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

        HttpSession session = request.getSession();
        String newLocale = (String) session.getAttribute("local");

        String login = request.getParameter(PARAMETER_LOGIN);
        String email = request.getParameter(PARAMETER_EMAIL);
        String password1 = request.getParameter(PARAMETER_PASSWORD1);
        String password2 = request.getParameter(PARAMETER_PASSWORD2);

        ServiceProvider provider = ServiceProvider.getInstance();
        UserService service = provider.getUserService();

        try {
            if (service.createUserMaster(login, email, password1, password2)) {
                request.setAttribute("registration", MessageManager.getProperty("message.dataok", newLocale));
            } else {
                request.setAttribute("error", MessageManager.getProperty("message.wrongdata", newLocale));
            }
        } catch (ServiceException e) {
            request.setAttribute("error", MessageManager.getProperty(e.getMessage(),newLocale));
            page = ConfigurationManager.getProperty(REGISTRATION_PAGE);
        }
        return page;
    }
}
