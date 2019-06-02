package by.epam.club.command.impl;

import by.epam.club.bundlemanager.ConfigurationManager;
import by.epam.club.command.ActionCommand;
import by.epam.club.controller.RequestContent;
import by.epam.club.controller.*;

public class ChangeLocaleCommand implements ActionCommand {
    private final String ENG_LOCALE = "en"; //todo спросить, правильно ли размещать здесь
    private final String RUS_LOCALE = "rus";

    @Override
    public Router execute(RequestContent content) {
        String page = ConfigurationManager.getProperty("path.page.default");

        TransmisionType transmitionType = TransmisionType.REDIRECT;
        String newLocale = ENG_LOCALE;
        int locale = Integer.parseInt(content.getRequestParameters("locale", 0));

        if (locale == 2) {
            newLocale = RUS_LOCALE;
        }
        content.putSessionAttribute("local", newLocale);

        return new Router(page, transmitionType);

    }
}

