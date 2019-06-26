package by.epam.club.tools.need_to_delete_other_view;/*
package by.epam.club.command.impl;

import by.epam.club.bundlemanager.ConfigurationManager;
import by.epam.club.command.ActionCommand;
import by.epam.club.controller.RequestContent;
import by.epam.club.controller.*;

public class AdminPage implements ActionCommand {
    private static final String ADMIN_PAGE_FORVARD = "path.page.admin.main";

    @Override
    public Router execute(RequestContent content) {
        String page = ConfigurationManager.getProperty(ADMIN_PAGE_FORVARD);
        TransmisionType transmitionType = TransmisionType.FORWARD;
        //    String newLocale = (String) content.getSessionAttribute("local");


        System.out.println(content.toString()+ "ADMIN_PAGE_FORVARD");
        return new Router(page, transmitionType);
    }
}
*/
