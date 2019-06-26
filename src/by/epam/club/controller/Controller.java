package by.epam.club.controller;

import by.epam.club.command.ActionCommand;
import by.epam.club.command.ActionFactory;
import by.epam.club.command.Router;
import by.epam.club.entity.Parameter;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


public class Controller extends HttpServlet {
    private static final Logger LOGGER = LogManager.getLogger(Controller.class);

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response, new RequestContent());
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response, new RequestContent());
    }

    private void processRequest(HttpServletRequest request, HttpServletResponse response, RequestContent content) throws ServletException, IOException {
        try {
            content.extractValues(request);
            ActionCommand command = ActionFactory.defineCommand(content);
            Router router;
            router = command.execute(content);
            content.insertAttributes(request);

            if (router.getTransmisionType().equals(TransmisionType.FORWARD)) {
                RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(router.getPath());
                dispatcher.forward(request, response);
            } else {
                response.sendRedirect(request.getContextPath() + router.getPath());
            }
        }catch (IOException e){
            LOGGER.error(Parameter.CONTROLLER_EXCEPTION_MESSAGE, e);
            e.printStackTrace();
            response.sendRedirect(Parameter.DEFAULT_PAGE_REDIRECT);
        }
    }
}
