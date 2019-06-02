package by.epam.club.command.impl;

import by.epam.club.bundlemanager.ConfigurationManager;
import by.epam.club.bundlemanager.MessageManager;
import by.epam.club.command.ActionCommand;
import by.epam.club.controller.RequestContent;
import by.epam.club.controller.*;
import by.epam.club.entity.TypeNews;
import by.epam.club.exception.AntCommandException;
import by.epam.club.exception.ServiceException;
import by.epam.club.service.ServiceProvider;
import by.epam.club.service.TypeService;

import java.util.Set;


public class DefaultPage implements ActionCommand {
    private static final String DEFAULT_PAGE = "path.page.default";

    @Override
    public Router execute(RequestContent content) throws AntCommandException {
        String page = ConfigurationManager.getProperty(DEFAULT_PAGE);

        TransmisionType transmisionType = TransmisionType.FORVARD;
        String locale = (String) content.getSessionAttribute("local");
        ServiceProvider provider = ServiceProvider.getInstance();
        TypeService service = provider.getTypeService();

        try {
            Set<TypeNews> typeNews = service.takeAllUndeleted();
            content.putSessionAttribute("types", typeNews);
        } catch (ServiceException e) {
            content.putRequestAttribute("error", MessageManager.getProperty("message.serviceexception", locale));
        }
        return new Router(page, transmisionType);
    }

}

