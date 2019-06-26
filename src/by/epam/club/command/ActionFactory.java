package by.epam.club.command;

import by.epam.club.bundlemanager.MessageManager;
import by.epam.club.command.forward.DefaultPageCommand;
import by.epam.club.controller.RequestContent;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import static by.epam.club.entity.Parameter.*;

public class ActionFactory {
    private static Logger LOGGER = LogManager.getLogger(ActionFactory.class);

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
