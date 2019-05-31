/*
package by.epam.club.tool.need_to_delete_other_view.command1;

import by.epam.club.command.CommandEnum;
import by.epam.club.manager.MessageManager;
import by.epam.club.exception.ControllerException;
import by.epam.club.tool.need_to_delete_other_view.RequestContent;

import static java.util.Locale.ENGLISH;

public class ActionFactory {
    public static final String PARAM_NAME_COMMAND = "command";
    private static final String WRONG_ACTION_MESSAGE = "message.wrong-action";

    public static ActionCommand_new defineCommand(RequestContent content) throws ControllerException {
        ActionCommand_new current;
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

 content.putRequestAttribute(ATTR_NAME_WRONG_ACTION, action)
            + MessageManager.getProperty(WRONG_ACTION_MESSAGE,ENGLISH);
            current = new EmptyCommand();
        }
        return current;
    }
}
*/
