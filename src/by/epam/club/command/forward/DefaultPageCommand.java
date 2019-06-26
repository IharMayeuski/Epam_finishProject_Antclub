package by.epam.club.command.forward;

import by.epam.club.bundlemanager.ConfigurationManager;
import by.epam.club.command.ActionCommand;
import by.epam.club.command.Router;
import by.epam.club.command.TakeTypeNews;
import by.epam.club.controller.RequestContent;
import by.epam.club.controller.*;
import by.epam.club.entity.Parameter;

import static by.epam.club.entity.Parameter.TYPES_PARAM;

public class DefaultPageCommand implements ActionCommand {

    @Override
    public Router execute(RequestContent content) {
        String page = ConfigurationManager.getProperty(Parameter.DEFAULT_PAGE_FORVARD);

        TakeTypeNews takeTypeNews = new TakeTypeNews();
        takeTypeNews.typeNews(content);


        TransmisionType transmisionType = TransmisionType.FORWARD;
        return new Router(page,transmisionType);
    }
}

