package by.epam.club.command.impl;

import by.epam.club.bundlemanager.ConfigurationManager;
import by.epam.club.command.ActionCommand;
import by.epam.club.controller.RequestContent;
import by.epam.club.controller.*;

public class AdminPage implements ActionCommand {
    private static final String ADMIN_PAGE = "path.page.admin.main";

    @Override
    public Router execute(RequestContent content) {
        String page = ConfigurationManager.getProperty(ADMIN_PAGE);
        TransmisionType transmitionType = TransmisionType.FORVARD;
        //    String newLocale = (String) content.getSessionAttribute("local");

        return new Router(page, transmitionType);
    }
}
