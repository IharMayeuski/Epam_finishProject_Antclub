/*
package by.epam.club.controller;

import by.epam.club.need_to_delete_other_view.command1.*;
import by.epam.club.exception.ControllerException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public abstract class AbstractSharingPicController extends HttpServlet {
    public void processRequest(HttpServletRequest request, HttpServletResponse response, RequestContent content) throws ControllerException {
      //  try {
            content.extractValues(request);
            ActionCommand command = ActionFactory.defineCommand(content);
            Router router;
            router = command.execute(content);
            content.insertAttributes(request);
            if (router.getTransmisionType().equals(TransmisionType.FORVARD)) {
                RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(router.getPath());
                try {
                    dispatcher.forward(request, response);
                } catch (ServletException | IOException e) {
                    e.printStackTrace();
                }

            } else {
                try {
                    response.sendRedirect(request.getContextPath() + router.getPath());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
*/
/* } catch (SharingPicCommandException e) {
            response.sendRedirect(CommandUrlBuilder.TO_ERROR)
                    .setParams(PARAM_NAME_ERROR_MESSAGE, e.getMessage())
                    .getUrl());
        }*//*


    }
}
*/
