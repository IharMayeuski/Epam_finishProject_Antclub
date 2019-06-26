package by.epam.club.command.forward;

import by.epam.club.bundlemanager.ConfigurationManager;
import by.epam.club.command.ActionCommand;
import by.epam.club.command.Router;
import by.epam.club.controller.RequestContent;
import by.epam.club.controller.TransmisionType;

import static by.epam.club.entity.Parameter.NEW_TYPE_PAGE_FORVARD;

public class ToNewTypeNewsCommand implements ActionCommand {
    @Override
    public Router execute(RequestContent content) {
        String page = ConfigurationManager.getProperty(NEW_TYPE_PAGE_FORVARD);
        TransmisionType transmitionType = TransmisionType.FORWARD;
        return new Router(page, transmitionType);
    }
}
