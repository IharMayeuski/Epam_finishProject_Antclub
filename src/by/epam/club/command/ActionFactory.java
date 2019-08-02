package by.epam.club.command;

import by.epam.club.bundlemanager.MessageManager;
import by.epam.club.command.forward.global.DefaultPageCommand;
import by.epam.club.controller.RequestContent;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import static by.epam.club.entity.Parameter.*;

/**
 * The class for defining name of the command
 *
 * @author Maeuski Igor
 * @version 1.0
 */

public class ActionFactory {
    private static Logger LOGGER = LogManager.getLogger(ActionFactory.class);

    /**
     * @param content of the class RequestContent
     * @return current it is a command that choose user by button in .jsp
     */

    public static ActionCommand defineCommand(RequestContent content) {
        ActionCommand current;
        String action = content.getRequestParameters(PARAM_NAME_COMMAND, 0);
        String newLocale = (String) content.getSessionAttribute(LOCAL_PARAM);
        if (action == null || action.isEmpty()) {
            current = new DefaultPageCommand();
        } else {
            try {
                CommandEnum currentEnum = CommandEnum.valueOf(action.toUpperCase());
                current = currentEnum.getCurrentCommand();
                if (current == null) {
                    current = new DefaultPageCommand();
                }
            } catch (IllegalArgumentException e) {
                LOGGER.error(ILLEGAL_ACTION_ON_PAGE_MESSAGE, e);
                content.putSessionAttribute(ERROR_PARAM, MessageManager.getProperty(WRONG_ACTION_MESSAGE, newLocale));
                current = new DefaultPageCommand();
            }
        }
        return current;
    }
}
