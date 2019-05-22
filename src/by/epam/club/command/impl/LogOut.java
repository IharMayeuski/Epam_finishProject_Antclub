package by.epam.club.command.impl;

import by.epam.club.command.Commander;
import by.epam.club.exception.ControllerException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class LogOut implements Commander {
    private static final String DEFAULT_PAGE = "/WEB-INF/jsp/default.jsp";

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ControllerException {
        HttpSession session = request.getSession(true);
        session.invalidate();
        RequestDispatcher dispatcher = request.getRequestDispatcher(DEFAULT_PAGE);//todo почему не работает логаут?
        try {
            dispatcher.forward(request, response);
        } catch (ServletException | IOException e) {
            throw new ControllerException(e);
        }
    }
}
