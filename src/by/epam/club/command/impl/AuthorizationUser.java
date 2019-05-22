package by.epam.club.command.impl;

import by.epam.club.entity.User;
import by.epam.club.exception.ControllerException;
import by.epam.club.exception.ServiceException;
import by.epam.club.service.ServiceProviderCommand;
import by.epam.club.service.UserService;
import by.epam.club.tool.CreatorFullURL;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class AuthorizationUser implements by.epam.club.command.Commander {
    private static final String PARAMETER_LOGIN = "login";
    private static final String PARAMETER_PASSWORD = "password";

    private static final String USER_PAGE = "/WEB-INF/jsp/user/userMain.jsp";
    private static final String DEFAULT_PAGE = "/WEB-INF/jsp/default.jsp";
    private static final String ADMIN_PAGE = "/WEB-INF/jsp/admin/adminMain.jsp";
    // private static final String ERROR_PAGE = "/WEB-INF/jsp/error.jsp"; todo не понятно, когда исользовать Error Page


    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ControllerException {
        String page = DEFAULT_PAGE;
        String login = request.getParameter(PARAMETER_LOGIN);
        String password = request.getParameter(PARAMETER_PASSWORD);

        ServiceProviderCommand provider = ServiceProviderCommand.getInstance();
        UserService service = provider.getService();
        HttpSession session = request.getSession();
        //String loc = (String) session.getAttribute("local");//todo организовать локаль

        try {
            User user = service.checkUser(login, password);
            session.setAttribute("user", user);
            if (user != null) {
                if (user.getRole().equals("admin")) {
                    page = ADMIN_PAGE;
                } else {
                    page = USER_PAGE;
                }

                if (user.getDeleted().equals("deleted")) {
                    request.setAttribute("error", "this account is deleted");
                    page = DEFAULT_PAGE;
                }

            } else {
                request.setAttribute("error", "login or password error");
            }
            ///////////////////////////////todo разобраться
            String url = CreatorFullURL.create(request);
            request.getSession(true).setAttribute("prev_request", url);
            ////////////////////////////////

        } catch (ServiceException e) {
            request.setAttribute("error", "login or password error");
        } finally {
            try {
                RequestDispatcher dispatcher = request.getRequestDispatcher(page);
                dispatcher.forward(request, response); //todo как быть со throw в файнали?
            } catch (ServletException | IOException e) {
                throw new ControllerException(e);
            }
        }
    }
}

