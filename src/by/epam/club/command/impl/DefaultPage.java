package by.epam.club.command.impl;

import by.epam.club.command.ActionCommand;
import by.epam.club.command.ConfigurationManager;

import javax.servlet.http.HttpServletRequest;


public class DefaultPage implements ActionCommand {
    private static final String DEFAULT_PAGE = "path.page.default";

    public String execute(HttpServletRequest request) {

        return ConfigurationManager.getProperty(DEFAULT_PAGE);
    }
}

