package by.epam.club.command.impl;

import by.epam.club.command.ActionCommand;
import by.epam.club.command.ConfigurationManager;

import javax.servlet.http.HttpServletRequest;

public class AdminPage implements ActionCommand {
    private static final String ADMIN_PAGE = "path.page.admin.main";

    @Override
    public String execute(HttpServletRequest request) {
        return ConfigurationManager.getProperty(ADMIN_PAGE);
    }
}
