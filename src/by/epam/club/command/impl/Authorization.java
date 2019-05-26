package by.epam.club.command.impl;

import by.epam.club.command.ActionCommand;
import by.epam.club.command.ConfigurationManager;
import by.epam.club.command.MessageManager;
import by.epam.club.entity.User;
import by.epam.club.exception.ServiceException;
import by.epam.club.service.ServiceProviderCommand;
import by.epam.club.service.UserService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class Authorization implements ActionCommand {
    private static final String PARAMETER_LOGIN = "login";
    private static final String PARAMETER_PASSWORD = "password";

    private static final String ADMIN_PAGE = "path.page.admin.main";
    private static final String DEFAULT_PAGE = "path.page.default";
    private static final String USER_PAGE = "path.page.user.main";
    @Override
    public String execute(HttpServletRequest request) {
        String page = ConfigurationManager.getProperty("path.page.default");

        String login = request.getParameter(PARAMETER_LOGIN);
        String password = request.getParameter(PARAMETER_PASSWORD);

        ServiceProviderCommand provider = ServiceProviderCommand.getInstance();
        UserService service = provider.getService();
        HttpSession session = request.getSession();

        try {
            User user = service.checkUser(login, password);
            if (user != null) {
                session.setAttribute("user", user);

                if (user.getRole().equals("admin")) {
                    page = ConfigurationManager.getProperty(ADMIN_PAGE);
                } else {
                    page = ConfigurationManager.getProperty(USER_PAGE);
                }

                if (user.getDeleted().equals("deleted")) {
                    request.setAttribute("error", MessageManager.getProperty("message.deletedaccount"));
                    page = ConfigurationManager.getProperty(DEFAULT_PAGE);
                }
            } else {
                request.setAttribute("error", MessageManager.getProperty("message.loginerror"));
             }
        } catch (ServiceException e) {
            request.setAttribute("error", MessageManager.getProperty("message.serviceexception"));
        }
        return page;
    }
}
