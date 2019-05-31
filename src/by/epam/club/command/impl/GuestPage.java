package by.epam.club.command.impl;

import by.epam.club.command.ActionCommand;
import by.epam.club.manager.ConfigurationManager;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class GuestPage implements ActionCommand {
    private static final String GUEST_PAGE = "path.page.guest.main";

    @Override
    public String execute(HttpServletRequest request) {
        HttpSession session = request.getSession();
        String newLocale = (String) session.getAttribute("local");


        return ConfigurationManager.getProperty(GUEST_PAGE);
    }
}
