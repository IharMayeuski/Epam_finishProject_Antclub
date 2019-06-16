package by.epam.club.command.redirect;

import by.epam.club.bundlemanager.ConfigurationManager;
import by.epam.club.command.ActionCommand;
import by.epam.club.controller.RequestContent;
import by.epam.club.controller.*;
import by.epam.club.entity.Parameter;
import by.epam.club.entity.TypeNews;
import by.epam.club.exception.AntCommandException;

import javax.print.DocFlavor;
import java.util.Set;

public class LogoutCommand implements ActionCommand {

    @Override
    public Router execute(RequestContent content) {
        String page = ConfigurationManager.getProperty(Parameter.INDEX_PAGE);
        TransmisionType transmitionType = TransmisionType.REDIRECT;
        content.invalidateSession();
        return new Router(page, transmitionType);
    }
}