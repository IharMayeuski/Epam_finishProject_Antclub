package by.epam.club.command.forward.global;

import by.epam.club.bundlemanager.ConfigurationManager;
import by.epam.club.command.ActionCommand;
import by.epam.club.command.Router;
import by.epam.club.command.forward.TakeTypeNews;
import by.epam.club.controller.RequestContent;
import by.epam.club.controller.*;
import by.epam.club.entity.Parameter;

import static by.epam.club.entity.Parameter.*;

/**
 * This command is needed for direct path on the general page
 *
 * @author Maeuski Igor
 * @version 1.0
 * @see by.epam.club.command.ActionCommand
 */

public class DefaultPageCommand implements ActionCommand {

    /**
     * @param content of the class RequestContent
     * @return new Router that send user on a new page by process Type
     */

    @Override
    public Router execute(RequestContent content) {
        String page = ConfigurationManager.getProperty(DEFAULT_PAGE_FORVARD);
        String locale = (String) content.getSessionAttribute(LOCAL_PARAM);
        if (locale==null || locale.isEmpty()) {
            content.putSessionAttribute(LOCAL_PARAM, RUS_PARAM);
        }
        TakeTypeNews takeTypeNews = new TakeTypeNews();
        takeTypeNews.typeNews(content);
        TransmisionType transmisionType = TransmisionType.FORWARD;
        return new Router(page,transmisionType);
    }
}

