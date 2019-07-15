package by.epam.club.command.forward.global;

import by.epam.club.bundlemanager.ConfigurationManager;
import by.epam.club.command.ActionCommand;
import by.epam.club.command.forward.TakeTypeNews;
import by.epam.club.controller.RequestContent;
import by.epam.club.command.Router;
import by.epam.club.controller.TransmisionType;

import static by.epam.club.entity.Parameter.*;

/**
 * This command gives for user who has a role unknown to have role - guest
 *
 * @author Maeuski Igor
 * @version 1.0
 * @see by.epam.club.command.ActionCommand
 */

public class ToBeGuestCommand implements ActionCommand {

    /**
     * @param content of the class RequestContent
     * @return new Router that send user on a new page by process Type
     */

    @Override
    public Router execute(RequestContent content) {
        String page = ConfigurationManager.getProperty(DEFAULT_PAGE_FORVARD);
        TransmisionType transmitionType = TransmisionType.FORWARD;
        TakeTypeNews takeTypeNews = new TakeTypeNews();
        takeTypeNews.typeNews(content);
        content.putSessionAttribute(ROLE_PARAM, GUEST_PARAM);
        return new Router(page, transmitionType);
    }
}


