package by.epam.club.command.impl;

import by.epam.club.command.ActionCommand;
import by.epam.club.manager.ConfigurationManager;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class ChangeLocale implements ActionCommand {
    @Override
    public String execute(HttpServletRequest request) {
        String newLocale = request.getParameter("locale");
        HttpSession session;
        session = request.getSession(false);
        session.setAttribute("local", newLocale);

        return ConfigurationManager.getProperty("path.page.default");

    }

}

