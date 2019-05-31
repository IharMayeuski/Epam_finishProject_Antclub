package by.epam.club.command.impl;

import by.epam.club.command.ActionCommand;
import by.epam.club.manager.ConfigurationManager;
import by.epam.club.entity.TypeNews;

import javax.servlet.http.HttpServletRequest;
import java.util.Set;

public class LogoutCommand implements ActionCommand {
    private static final String DEFAULT_PAGE = "path.page.default";

    @Override
    public String execute(HttpServletRequest request) {
        String page = ConfigurationManager.getProperty(DEFAULT_PAGE);
        Set<TypeNews> types = (Set<TypeNews>) request.getSession().getAttribute("types");
        request.getSession().invalidate();

        request.setAttribute("types", types);
        return page;
    }
}
