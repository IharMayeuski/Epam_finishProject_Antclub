package by.epam.club.controller;

import by.epam.club.command.ActionCommand;
import by.epam.club.command.ActionFactory;
import by.epam.club.exception.AntCommandException;
import by.epam.club.tool.CreatorFullURL;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public abstract class AntController extends HttpServlet {
    public void processRequest(HttpServletRequest request, HttpServletResponse response, RequestContent content) throws ServletException, IOException {
        String page;
        String encode = "UTF-8";
        request.setCharacterEncoding(encode);
        response.setCharacterEncoding(encode);

        try {
            content.extractValues(request);
            ActionCommand command = ActionFactory.defineCommand(content);
            Router router;
            router = command.execute(content);
            content.insertAttributes(request);
            if (router.getTransmisionType().equals(TransmisionType.FORVARD)) {
                RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(router.getPath());
                dispatcher.forward(request, response);
            } else {
                response.sendRedirect(request.getContextPath() + router.getPath());
            }
        } catch (AntCommandException e) {//todo commandUrlBuilder.TO_ERROR
            CreatorFullURL url = new CreatorFullURL();
            String url_new = url.create(request);
            response.sendRedirect(url_new);
        }
    }
}


