package by.epam.club.command.impl;

import by.epam.club.bundlemanager.ConfigurationManager;
import by.epam.club.command.ActionCommand;
import by.epam.club.controller.RequestContent;
import by.epam.club.controller.*;
import by.epam.club.entity.TypeNews;
import by.epam.club.exception.AntCommandException;

import java.util.Set;

public class LogoutCommand implements ActionCommand {
    private static final String DEFAULT_PAGE = "path.page.default";

    @Override
    public Router execute(RequestContent content) throws AntCommandException {
        String page = ConfigurationManager.getProperty(DEFAULT_PAGE);
        TransmisionType transmitionType = TransmisionType.REDIRECT;

        Set<TypeNews> types = (Set<TypeNews>) content.getSessionAttribute("types");
        String locale = (String) content.getSessionAttribute("local");

        content.invalidateSession();
        content.putSessionAttribute("types", types);
        content.putSessionAttribute("local", locale);

        return new Router(page, transmitionType);
    }
}
/*
    String page = ConfigurationManager.getProperty("path.page.default");

    TransmisionType transmitionType = TransmisionType.REDIRECT;
    String newLocale = ENG_LOCALE;
    int locale = Integer.parseInt(content.getRequestParameters("locale", 0));

        if (locale == 2) {
                newLocale = RUS_LOCALE;
                }
                content.putSessionAttribute("local", newLocale);

                return new Router(page, transmitionType);

                }*/
