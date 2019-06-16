package by.epam.club.command.impl;

import by.epam.club.bundlemanager.ConfigurationManager;
import by.epam.club.command.ActionCommand;
import by.epam.club.controller.RequestContent;
import by.epam.club.controller.Router;
import by.epam.club.controller.TransmisionType;
import by.epam.club.entity.Parameter;
import by.epam.club.exception.ServiceException;
import by.epam.club.service.ServiceProvider;
import by.epam.club.service.UserService;

public class NewPasswordCommand implements ActionCommand {

    @Override
    public Router execute(RequestContent content) {
        String locale = (String) content.getSessionAttribute(Parameter.LOCAL_PARAM);
        String page = ConfigurationManager.getProperty(Parameter.DEFAULT_PAGE);
        String email = content.getRequestParameters(Parameter.EMAIL_PARAM, 0);
        System.out.println(email);
        TransmisionType transmisionType = TransmisionType.REDIRECT;

        ServiceProvider provider = ServiceProvider.getInstance();
        UserService userService = provider.getUserService();

        try {///////////// временно
            userService.newPassword(email);
        } catch (ServiceException e) {
            e.printStackTrace();
        }

        return new Router(page,transmisionType);
    }
}
