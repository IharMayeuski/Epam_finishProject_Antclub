package by.epam.club.command.impl;

import by.epam.club.command.ActionCommand;
import by.epam.club.manager.ConfigurationManager;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class AdminPage implements ActionCommand {
    private static final String ADMIN_PAGE = "path.page.admin.main";

    @Override
    public String execute(HttpServletRequest request) {
        HttpSession session = request.getSession();
        String newLocale = (String) session.getAttribute("local");


        return ConfigurationManager.getProperty(ADMIN_PAGE);
    }
}
