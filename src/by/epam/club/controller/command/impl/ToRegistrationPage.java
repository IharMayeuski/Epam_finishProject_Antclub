package by.epam.club.controller.command.impl;

import by.epam.club.controller.command.Commander;
import by.epam.club.exception.ControllerException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class ToRegistrationPage implements Commander {
    private static final String REGISTRATION_PAGE = "/WEB-INF/jsp/registration.jsp";

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ControllerException {
        RequestDispatcher dispatcher = request.getRequestDispatcher(REGISTRATION_PAGE);//todo почему не работает логаут?

        try {
            dispatcher.forward(request, response);
        } catch (ServletException | IOException e) {
            throw new ControllerException(e);
        }
    }
}