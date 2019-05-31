package by.epam.club.controller;

import by.epam.club.command.ActionCommand;
import by.epam.club.command.ActionFactory;
import by.epam.club.manager.ConfigurationManager;
import by.epam.club.manager.MessageManager;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class Controller extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        System.out.println(request.toString()+", "+ response.toString()+" - get"); //todo удалить
        processRequest(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        System.out.println(request.toString()+", "+ response.toString()+" - post"); //todo удалить
        processRequest(request, response);
    }

    private void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String page;
        String encode = "UTF-8";
        request.setCharacterEncoding(encode);
        response.setCharacterEncoding(encode);

        ActionFactory client = new ActionFactory();
        ActionCommand command = client.defineCommand(request);
        page = command.execute(request);

        HttpSession session = request.getSession();
        String newLocale = (String) session.getAttribute("local");

        if (page != null) {
            RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(page);
            dispatcher.forward(request, response);
        } else {
            page = ConfigurationManager.getProperty("path.page.default");
            request.getSession().setAttribute("nullpage",MessageManager.getProperty("message.nullpage", newLocale));
            response.sendRedirect(request.getContextPath() + page);
        }
    }
}
