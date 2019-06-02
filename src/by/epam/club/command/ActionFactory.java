package by.epam.club.command;

import by.epam.club.bundlemanager.MessageManager;
import by.epam.club.command.impl.DefaultPage;
import by.epam.club.controller.RequestContent;

public class ActionFactory {
    public static final String PARAM_NAME_COMMAND = "command";
    private static final String WRONG_ACTION_MESSAGE = "message.wrong-action";

    public static ActionCommand defineCommand(RequestContent content) {
        ActionCommand current = new DefaultPage();
        String action = content.getRequestParameters(PARAM_NAME_COMMAND, 0);
        String newLocale = (String) content.getSessionAttribute("local");

        if (action == null || action.isEmpty()) {
            return current;
        }
        try {
            CommandEnum currentEnum = CommandEnum.valueOf(action.toUpperCase());
            current = currentEnum.getCurrentCommand();
            if (current == null) {
                current = new DefaultPage();
            }

            return current;
        } catch (IllegalArgumentException e) {
            content.putRequestAttribute("error",
                    MessageManager.getProperty("message.wrongaction", newLocale));
        }
        return current;
    }
}
