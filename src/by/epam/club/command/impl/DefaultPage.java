package by.epam.club.command.impl;

import by.epam.club.command.ActionCommand;
import by.epam.club.manager.ConfigurationManager;
import by.epam.club.manager.MessageManager;
import by.epam.club.entity.TypeNews;
import by.epam.club.exception.ServiceException;
import by.epam.club.service.ServiceProvider;
import by.epam.club.service.TypeService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Set;


public class DefaultPage implements ActionCommand {
    private static final String DEFAULT_PAGE = "path.page.default";

    public String execute(HttpServletRequest request) {

        ServiceProvider provider = ServiceProvider.getInstance();
        TypeService service = provider.getTypeService();
        HttpSession session = request.getSession();
        String newLocale = (String) session.getAttribute("local");
        try {
            Set<TypeNews> typeNews = service.takeAllUndeleted();
            session.setAttribute("types", typeNews);
        } catch (ServiceException e) {
            request.setAttribute("error", MessageManager.getProperty("message.serviceexception", newLocale));
        }

        return ConfigurationManager.getProperty(DEFAULT_PAGE);
    }
}

