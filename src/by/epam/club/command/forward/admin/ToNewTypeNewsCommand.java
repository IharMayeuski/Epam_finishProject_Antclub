package by.epam.club.command.forward.admin;

import by.epam.club.bundlemanager.ConfigurationManager;
import by.epam.club.command.ActionCommand;
import by.epam.club.command.Router;
import by.epam.club.controller.RequestContent;
import by.epam.club.controller.TransmisionType;

import static by.epam.club.entity.Parameter.NEW_TYPE_PAGE_FORVARD;

/**
 * Command send user with role 'admin' to page with form for input
 * @author Maeuski Igor
 * @version 1.0
 * @see by.epam.club.command.ActionCommand
 */


public class ToNewTypeNewsCommand implements ActionCommand {

    /**
     * @param content of the class RequestContent
     * @return new Router that send user on a new page by process Type
     */

    @Override
    public Router execute(RequestContent content) {
        String page = ConfigurationManager.getProperty(NEW_TYPE_PAGE_FORVARD);
        TransmisionType transmitionType = TransmisionType.FORWARD;
        return new Router(page, transmitionType);
    }
}
