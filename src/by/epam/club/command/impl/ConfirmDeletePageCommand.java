package by.epam.club.command.impl;

import by.epam.club.bundlemanager.ConfigurationManager;
import by.epam.club.command.ActionCommand;
import by.epam.club.controller.RequestContent;
import by.epam.club.controller.Router;
import by.epam.club.controller.TransmisionType;
import by.epam.club.entity.Parameter;

public class ConfirmDeletePageCommand implements ActionCommand {

    @Override
    public Router execute(RequestContent content) {
        String page = ConfigurationManager.getProperty(Parameter.DELETE_ACCOUNT_USER_PAGE);

        TransmisionType transmitionType = TransmisionType.FORWARD;

        return new Router(page, transmitionType);
    }
}
