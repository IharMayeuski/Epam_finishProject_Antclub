package by.epam.club.need_to_delete_other_view;

import by.epam.club.command.ActionCommand;
import by.epam.club.command.ConfigurationManager;

import javax.servlet.http.HttpServletRequest;

public class LoginCommand implements ActionCommand {

    @Override
    public String execute(HttpServletRequest request) {

        String page = ConfigurationManager.getProperty("path.page.login");
        return page;
    }
}