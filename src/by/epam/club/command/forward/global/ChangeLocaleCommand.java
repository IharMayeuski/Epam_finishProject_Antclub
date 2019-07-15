package by.epam.club.command.forward.global;

import by.epam.club.bundlemanager.ConfigurationManager;
import by.epam.club.command.ActionCommand;
import by.epam.club.command.Router;
import by.epam.club.controller.RequestContent;
import by.epam.club.controller.*;

import static by.epam.club.entity.Parameter.*;

/**
 * This command is for changing locale (internalisation our application)
 *
 * @author Maeuski Igor
 * @version 1.0
 * @see by.epam.club.command.ActionCommand
 */

public class ChangeLocaleCommand implements ActionCommand {

    /**
     * @param content of the class RequestContent
     * @return new Router that send user on a new page by process Type
     */

    @Override
    public Router execute(RequestContent content) {
        TransmisionType transmitionType = TransmisionType.FORWARD;
        String newLocale = RUS_PARAM;
        int locale = Integer.parseInt(content.getRequestParameters(LOCALE_PARAM, 0));
        // FIXME: 7/3/2019 удалить
       // String pageFromJsp = content.getRequestParameters(PATH_PARAM, 0);
        String pageFromJsp = ConfigurationManager.getProperty(DEFAULT_PAGE_FORVARD);
        if (locale == 1) {
            newLocale = EN_PARAM;
        }
        content.putSessionAttribute(LOCAL_PARAM, newLocale);
        return new Router(pageFromJsp, transmitionType);
    }
}

