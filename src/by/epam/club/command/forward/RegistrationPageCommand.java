package by.epam.club.command.forward;

import by.epam.club.bundlemanager.ConfigurationManager;
import by.epam.club.command.ActionCommand;
import by.epam.club.controller.RequestContent;
import by.epam.club.controller.*;
import by.epam.club.entity.Parameter;

public class RegistrationPageCommand implements ActionCommand {

    @Override
    public Router execute(RequestContent content) {
        String page = ConfigurationManager.getProperty(Parameter.REGISTRATION_PAGE);
        TransmisionType transmitionType = TransmisionType.FORWARD;

        return new Router(page, transmitionType);

    }
}
