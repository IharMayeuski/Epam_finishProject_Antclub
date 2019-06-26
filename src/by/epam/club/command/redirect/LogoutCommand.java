package by.epam.club.command.redirect;

import by.epam.club.bundlemanager.ConfigurationManager;
import by.epam.club.command.ActionCommand;
import by.epam.club.command.Router;
import by.epam.club.controller.RequestContent;
import by.epam.club.controller.*;
import by.epam.club.entity.Parameter;

import static by.epam.club.entity.Parameter.INDEX_PAGE;

public class LogoutCommand implements ActionCommand {

    @Override
    public Router execute(RequestContent content) {
        String page = ConfigurationManager.getProperty(INDEX_PAGE);
        TransmisionType transmitionType = TransmisionType.REDIRECT;
        content.invalidateSession();
        return new Router(page, transmitionType);
    }
}