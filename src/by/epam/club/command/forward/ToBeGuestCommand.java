package by.epam.club.command.forward;

import by.epam.club.bundlemanager.ConfigurationManager;
import by.epam.club.command.ActionCommand;
import by.epam.club.command.TakeTypeNews;
import by.epam.club.controller.RequestContent;
import by.epam.club.command.Router;
import by.epam.club.controller.TransmisionType;

import static by.epam.club.entity.Parameter.*;

public class ToBeGuestCommand implements ActionCommand {

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


