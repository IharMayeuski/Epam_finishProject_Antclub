package by.epam.club.command.impl;

import by.epam.club.command.ActionCommand;
import by.epam.club.command.ConfigurationManager;

import javax.servlet.http.HttpServletRequest;

public class GuestPage implements ActionCommand {
    private static final String GUEST_PAGE = "path.guest.page";

    @Override
    public String execute(HttpServletRequest request) {

        return ConfigurationManager.getProperty(GUEST_PAGE);
    }
}
