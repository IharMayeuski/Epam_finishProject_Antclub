package by.epam.club.command.redirect;

import by.epam.club.bundlemanager.ConfigurationManager;
import by.epam.club.command.ActionCommand;
import by.epam.club.controller.RequestContent;
import by.epam.club.controller.*;
import by.epam.club.entity.Parameter;
import by.epam.club.exception.ServiceException;
import by.epam.club.service.ServiceProvider;
import by.epam.club.service.UserService;

import by.epam.club.bundlemanager.MessageManager;

public class RegistrationCommand implements ActionCommand {

    @Override
    public Router execute(RequestContent content) {
        String page = "/controller?command=go_to_registration_page";
        TransmisionType transmitionType = TransmisionType.REDIRECT;
        String newLocale = (String) content.getSessionAttribute(Parameter.LOCAL_PARAM);

        String login = content.getRequestParameters(Parameter.LOGIN_PARAM, 0);
        String email = content.getRequestParameters(Parameter.EMAIL_PARAM, 0);
        String password1 = content.getRequestParameters(Parameter.PASSWORD_PARAM1, 0);
        String password2 = content.getRequestParameters(Parameter.PASSWORD_PARAM2, 0);

        ServiceProvider provider = ServiceProvider.getInstance();
        UserService service = provider.getUserService();
        try {
            if (service.createUserMaster(login, email, password1, password2)) {
                content.putRequestAttribute(Parameter.REGISTRATION_PARAM, MessageManager.getProperty(Parameter.DATA_OK_MESSAGE, newLocale));
                page = "/controller?command=go_to_default_page";
            } else {
                content.putRequestAttribute(Parameter.ERROR_PARAM, MessageManager.getProperty(Parameter.WRONG_DATA_MESSAGE, newLocale));

            }
        } catch (ServiceException e) {
             content.putRequestAttribute(Parameter.ERROR_PARAM, MessageManager.getProperty(e.getMessage(), newLocale));
            e.printStackTrace();

        }
        return new Router(page, transmitionType);
    }
}
