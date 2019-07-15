package by.epam.club.command.forward.global;

import by.epam.club.bundlemanager.ConfigurationManager;
import by.epam.club.command.ActionCommand;
import by.epam.club.command.Router;
import by.epam.club.controller.RequestContent;
import by.epam.club.controller.*;

import static by.epam.club.entity.Parameter.REGISTRATION_PAGE_FORVARD;

/**
 * Command send guest on the page of registration
 *
 * @author Maeuski Igor
 * @version 1.0
 * @see by.epam.club.command.ActionCommand
 */

public class RegistrationPageCommand implements ActionCommand {

    /**
     * @param content of the class RequestContent
     * @return new Router that send user on a new page by process Type
     */

    @Override
    public Router execute(RequestContent content) {
        String page = ConfigurationManager.getProperty(REGISTRATION_PAGE_FORVARD);
        TransmisionType transmitionType = TransmisionType.FORWARD;
        return new Router(page, transmitionType);
    }
}
