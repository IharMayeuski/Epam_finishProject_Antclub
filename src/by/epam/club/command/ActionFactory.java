package by.epam.club.command;


import by.epam.club.command.impl.DefaultPage;

import javax.servlet.http.HttpServletRequest;

public class ActionFactory {
    public ActionCommand defineCommand (HttpServletRequest request){
        ActionCommand current = new DefaultPage();
        String action = request.getParameter("command");
        if ((action==null)||action.isEmpty()){
            return current;
        }
        try{
            CommandEnum currentEnum = CommandEnum.valueOf(action.toUpperCase());
            current = currentEnum.getCurrentCommand();

        }catch (IllegalArgumentException e){
            request.setAttribute("error",action+ MessageManager.getProperty("message.wrongaction"));


        }
        return current;
    }
}
