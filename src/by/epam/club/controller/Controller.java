package by.epam.club.controller;

import by.epam.club.command.ActionCommand;
import by.epam.club.command.ActionFactory;
import by.epam.club.command.Router;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static by.epam.club.entity.Parameter.CONTROLLER_EXCEPTION_MESSAGE;
import static by.epam.club.entity.Parameter.DEFAULT_PAGE_REDIRECT;

/**
 * It is the main servlet of the programm
 *
 * @author Maeuski Igor
 * @version 1.0
 * @see HttpServlet
 */
public class Controller extends HttpServlet {
    private static final Logger LOGGER = LogManager.getLogger(Controller.class);

    /**
     * @param request take HttpServletRequest
     * @param response for sending by get method
     * @throws ServletException this method cant throw this exception
     * @throws IOException this method cant throw IOException
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response, new RequestContent());
    }

    /**
     * @param request take HttpServletRequest
     * @param response for sending by post method
     * @throws ServletException this method cant throw this exception
     * @throws IOException this method cant throw this exception
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response, new RequestContent());
    }

    /**
     * @param request take HttpServletRequest
     * @param response from servlet to outside
     * @param content for sending difference parameters to .jsp
     * @throws ServletException this method cant throw this exception
     * @throws IOException this method cant throw this exception
     */
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
            LOGGER.error(CONTROLLER_EXCEPTION_MESSAGE, e);
            e.printStackTrace();
            response.sendRedirect(DEFAULT_PAGE_REDIRECT);
        }
    }
}
