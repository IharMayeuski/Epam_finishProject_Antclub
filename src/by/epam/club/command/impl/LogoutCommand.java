package by.epam.club.command.impl;

import by.epam.club.command.ActionCommand;
import by.epam.club.command.ConfigurationManager;

import javax.servlet.http.HttpServletRequest;

public class LogoutCommand implements ActionCommand {
    private static final String DEFAULT_PAGE = "path.page.default";

    @Override
    public String execute(HttpServletRequest request) {
        String page = ConfigurationManager.getProperty(DEFAULT_PAGE);
        request.getSession().invalidate();
        return page;
    }
}
