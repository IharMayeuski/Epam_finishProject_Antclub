package by.epam.club.command;


import by.epam.club.command.impl.DefaultPage;
import by.epam.club.manager.MessageManager;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class ActionFactory {
    public static ActionCommand defineCommand (HttpServletRequest request){
        ActionCommand current = new DefaultPage();
        String action = request.getParameter("command");

        HttpSession session = request.getSession();
        String newLocale = (String) session.getAttribute("local");


        if ((action==null)||action.isEmpty()){
            return current;
        }
        try{
            CommandEnum currentEnum = CommandEnum.valueOf(action.toUpperCase());
            current = currentEnum.getCurrentCommand();

        }catch (IllegalArgumentException e){
            request.setAttribute("error",action+ MessageManager.getProperty("message.wrongaction", newLocale));
        }
        return current;
    }
}
