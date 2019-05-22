package by.epam.club.controller;

import by.epam.club.command.CommandProvider;
import by.epam.club.command.Commander;
import by.epam.club.exception.ControllerException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.UnsupportedEncodingException;


public class Controller extends HttpServlet {
    private static final String PARAMETER_COMMAND = "command";
    private final CommandProvider provider = CommandProvider.getInstance();

    public Controller() {
        super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)  {
        try {
            processRequest(request,response);
        } catch (ControllerException e) {
            e.printStackTrace(); //todo уточнить правильность
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)  {
        try {
            processRequest(request,response);
        } catch (ControllerException e) {
            e.printStackTrace();//todo уточнить правильность
        }
    }

    private void processRequest(HttpServletRequest request,HttpServletResponse response) throws ControllerException {
        try {
            request.setCharacterEncoding("UTF-8");
        } catch (UnsupportedEncodingException e) {
            throw new ControllerException(e);
        }
        response.setCharacterEncoding("UTF-8");
        String commandName = request.getParameter(PARAMETER_COMMAND);
        Commander command = provider.getCommand(commandName);
        command.execute(request, response);
    }
}