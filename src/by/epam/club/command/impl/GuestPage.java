package by.epam.club.command.impl;

import by.epam.club.bundlemanager.ConfigurationManager;
import by.epam.club.command.ActionCommand;
import by.epam.club.controller.RequestContent;
import by.epam.club.controller.Router;
import by.epam.club.controller.TransmisionType;

public class GuestPage implements ActionCommand {
    private static final String GUEST_PAGE = "path.page.guest.main";


    @Override
    public Router execute(RequestContent content) {
        String page = ConfigurationManager.getProperty(GUEST_PAGE);
        TransmisionType transmitionType = TransmisionType.FORVARD;
        //    String newLocale = (String) content.getSessionAttribute("local");

        return new Router(page, transmitionType);
    }
}


