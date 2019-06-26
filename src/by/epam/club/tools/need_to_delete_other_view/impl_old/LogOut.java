package by.epam.club.tools.need_to_delete_other_view.impl_old;/*
package by.epam.club.tool.need_to_delete_other_view.impl_old;

import by.epam.club.exception.ControllerException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class LogOut implements Commander {
    private static final String DEFAULT_PAGE_FORVARD = "/WEB-INF/jsp/default.jsp";

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ControllerException {
        HttpSession session = request.getSession(true);
        session.invalidate();
        RequestDispatcher dispatcher = request.getRequestDispatcher(DEFAULT_PAGE_FORVARD);//todo почему не работает логаут?
        try {
            dispatcher.forward(request, response);
        } catch (ServletException | IOException e) {
            throw new ControllerException(e);
        }
    }
}
*/
