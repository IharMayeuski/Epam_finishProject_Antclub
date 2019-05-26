package by.epam.club.command.impl;

import by.epam.club.command.ActionCommand;
import by.epam.club.command.ConfigurationManager;

import javax.servlet.http.HttpServletRequest;

public class RegistrationPage implements ActionCommand {

    private static final String REGISTRATION_PAGE = "path.page.registration";

    @Override
    public String execute(HttpServletRequest request) {
        return ConfigurationManager.getProperty(REGISTRATION_PAGE);

    }
}
