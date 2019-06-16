package by.epam.club.command.forward;

import by.epam.club.bundlemanager.ConfigurationManager;
import by.epam.club.command.ActionCommand;
import by.epam.club.command.impl.TakeTypeNews;
import by.epam.club.controller.RequestContent;
import by.epam.club.controller.Router;
import by.epam.club.controller.TransmisionType;
import by.epam.club.entity.Parameter;

public class ToBeGuestCommand implements ActionCommand {

    @Override
    public Router execute(RequestContent content) {
        String page = ConfigurationManager.getProperty(Parameter.DEFAULT_PAGE);
        TransmisionType transmitionType = TransmisionType.FORWARD;
        TakeTypeNews takeTypeNews = new TakeTypeNews();
        takeTypeNews.typeNews(content);
        content.putSessionAttribute(Parameter.ROLE_PARAM, Parameter.GUEST_PARAM);
        return new Router(page, transmitionType);
    }
}


