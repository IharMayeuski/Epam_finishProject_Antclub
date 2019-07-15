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

public class ToUpdateArticleCommand implements ActionCommand {

    /**
     * @param content of the class RequestContent
     * @return new Router that send user on a new page by process Type
     */

    @Override
    public Router execute(RequestContent content) {
        String text = content.getRequestParameters(TEXT_PARAM, 0);
        String articleId = content.getRequestParameters(ARTICLE_PARAM, 0);
        String page = ConfigurationManager.getProperty(UPDATE_ARTICLE_PAGE_FORVARD);
        TransmisionType transmitionType = TransmisionType.FORWARD;
        content.putRequestAttribute(TEXT_PARAM, text);
        content.putRequestAttribute(ARTICLE_ID_PARAM, articleId);
        return new Router(page, transmitionType);
    }
}
