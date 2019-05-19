package by.epam.club.controller.command.impl;

import by.epam.club.controller.command.Commander;
import by.epam.club.util.CreatorFullURL;
import by.epam.club.exception.ControllerException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


public class ToDefaultPage implements Commander {
    private static final String DEFAULT_PAGE= "/WEB-INF/jsp/default.jsp";

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ControllerException {

        String url = CreatorFullURL.create(request);
        request.getSession(true).setAttribute("prev_request", url);

        RequestDispatcher dispatcher = request.getRequestDispatcher(DEFAULT_PAGE);
        try {
            dispatcher.forward(request, response);
        } catch (ServletException e) {
            e.printStackTrace();
        } catch (IOException e) {
            throw new ControllerException(e);
        }
    }
}

