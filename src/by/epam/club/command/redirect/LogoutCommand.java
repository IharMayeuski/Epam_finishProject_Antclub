package by.epam.club.command.redirect;

import by.epam.club.bundlemanager.ConfigurationManager;
import by.epam.club.command.ActionCommand;
import by.epam.club.command.Router;
import by.epam.club.controller.RequestContent;
import by.epam.club.controller.*;

import static by.epam.club.entity.Parameter.INDEX_PAGE;

/**
 * The command for forgetting session
 *
 * @author Maeuski Igor
 * @version 1.0
 * @see by.epam.club.command.ActionCommand
 */

public class LogoutCommand implements ActionCommand {

    /**
     * @param content of the class RequestContent
     * @return new Router that send user on a new page by process Type
     */

    @Override
    public Router execute(RequestContent content) {
        String page = ConfigurationManager.getProperty(INDEX_PAGE);
        TransmisionType transmitionType = TransmisionType.REDIRECT;
        content.invalidateSession();
        return new Router(page, transmitionType);
    }
}