package by.epam.club.command.forward.user;

import by.epam.club.bundlemanager.ConfigurationManager;
import by.epam.club.command.ActionCommand;
import by.epam.club.command.Router;
import by.epam.club.controller.RequestContent;
import by.epam.club.controller.TransmisionType;

import static by.epam.club.entity.Parameter.*;

/**
 * @author Maeuski Igor
 * @version 1.0
 * @see by.epam.club.command.ActionCommand
 */

public class ToNewTypeArticleCommand implements ActionCommand {

    /**
     * @param content of the class RequestContent
     * @return new Router that send user on a new page by process Type
     */

    @Override
    public Router execute(RequestContent content) {
        String page = ConfigurationManager.getProperty(NEW_ARTICLE_PAGE_FORVARD);
        int typeId = Integer.parseInt(content.getRequestParameters(TYPE_PARAM, 0));
        content.putSessionAttribute(TYPE_ID, typeId);
        TransmisionType transmitionType = TransmisionType.FORWARD;
        return new Router(page, transmitionType);
    }
}
