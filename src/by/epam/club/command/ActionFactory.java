package by.epam.club.command;

import by.epam.club.bundlemanager.MessageManager;
import by.epam.club.command.forward.DefaultPageCommand;
import by.epam.club.controller.RequestContent;
import by.epam.club.entity.Parameter;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class ActionFactory {
    private static final String WRONG_ACTION_MESSAGE = "message.wrong-action";
    private static Logger LOGGER = LogManager.getLogger(ActionFactory.class);

    public static ActionCommand defineCommand(RequestContent content) {
        ActionCommand current;
        String action = content.getRequestParameters(Parameter.PARAM_NAME_COMMAND, 0);
        String newLocale = (String) content.getSessionAttribute(Parameter.LOCAL_PARAM);

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
                LOGGER.error("Illegal action on the page ", e);
                //todo не работает, еррок не передается на жсп
                content.putRequestAttribute(Parameter.ERROR_PARAM, MessageManager.getProperty(WRONG_ACTION_MESSAGE, newLocale));
                current = new DefaultPageCommand(); // FIXME: 6/7/2019 правильно ли возвращать из кэтча?
                System.out.println(content.toString());//todo удалить строку
            }
        }
        return current;
    }
}
