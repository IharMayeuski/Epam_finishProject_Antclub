/*
package by.epam.club.tool.need_to_delete_other_view;

import by.epam.club.command.ActionCommand;
import by.epam.club.bundlemanager.ConfigurationManager;
import by.epam.club.bundlemanager.MessageManager;
import by.epam.club.exception.ServiceException;
import by.epam.club.service.ServiceProvider;
import by.epam.club.service.UserService;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;

public class LoginCommand1 implements ActionCommand {
    private static final String PARAMETER_LOGIN = "login";
    private static final String PARAMETER_PASSWORD1 = "password1";
    private static final String PARAMETER_PASSWORD2 = "password2";
    private static final String PARAMETER_EMAIL = "email";

    private static final String DEFAULT_PAGE = "/WEB-INF/jsp/default.jsp";
    private static final String REGISTRATION_PAGE = "/WEB-INF/jsp/registration.jsp";

    @Override
    public String execute(HttpServletRequest request) {
        String page = null;

        String login = request.getParameter(PARAMETER_LOGIN);
        String email = request.getParameter(PARAMETER_EMAIL);
        String password1 = request.getParameter(PARAMETER_PASSWORD1);
        String password2 = request.getParameter(PARAMETER_PASSWORD2);

        ServiceProvider provider = ServiceProvider.getInstance();
        UserService service = provider.getUserService();
        RequestDispatcher dispatcher = null;

        try {
            if (service.createUserMaster(login, email, password1, password2)) {
                request.setAttribute("registration", MessageManager.getProperty("message.loginOK"));
                page = ConfigurationManager.getProperty("path.page");
                dispatcher = request.getRequestDispatcher(DEFAULT_PAGE);
            } else {
                request.setAttribute("error", "Something wrong with your data!");
                dispatcher = request.getRequestDispatcher(REGISTRATION_PAGE);
            }
        } catch (ServiceException e) {
            request.setAttribute("error", e);
            dispatcher = request.getRequestDispatcher(REGISTRATION_PAGE);
        */
/*} finally {
            try {
                dispatcher.forward(request, response);
            } catch (NullPointerException | ServletException | IOException e) {
                throw new ControllerException(e);
            }*//*

        }
        return page;
    }
}

*/