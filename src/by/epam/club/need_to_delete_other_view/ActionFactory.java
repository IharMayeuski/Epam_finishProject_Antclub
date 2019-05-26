/*
package by.epam.club.need_to_delete_other_view.command1;

import by.epam.club.command.CommandEnum;
import by.epam.club.exception.ControllerException;

public class ActionFactory {
    public static final String PARAM_NAME_COMMAND = "command";
    private static final String WRONG_ACTION_MESSAGE = "message.wrong-action";

    public static ActionCommand defineCommand(RequestContent content) throws ControllerException {
        ActionCommand current;
        content.getRequestParameters(PARAM_NAME_COMMAND);
        String action = content.getRequestParameters(PARAM_NAME_COMMAND).0);
        if(action==null || action.isEmpty()){
            throw new ControllerException();
        }
        try {
            CommandEnum currentEnum = CommandEnum.valueOf(action.toUpperCase());
            current = currentEnum.getCurrentCommand();
            if (current==null){
               // return new EmptyCommand();
            }
            return current;
        }catch (IllegalArgumentException e){

           */
/* content.putRequestAttribute(ATTR_NAME_WRONG_ACTION, action)
            +MessageManager.getProperty(WRONG_ACTION_MESSAGE,ENGLISH);
            current = new EmptyCommand();*//*

        }
        return current;
    }
}
*/
