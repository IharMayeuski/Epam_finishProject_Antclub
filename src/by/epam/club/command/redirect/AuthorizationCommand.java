package by.epam.club.command.redirect;

import by.epam.club.bundlemanager.MessageManager;
import by.epam.club.command.ActionCommand;
import by.epam.club.command.impl.TakeTypeNews;
import by.epam.club.controller.RequestContent;
import by.epam.club.controller.*;
import by.epam.club.entity.Parameter;
import by.epam.club.entity.User;
import by.epam.club.exception.ServiceException;
import by.epam.club.service.ServiceProvider;
import by.epam.club.service.UserService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


public class AuthorizationCommand implements ActionCommand {
    private static final Logger LOGGER = LogManager.getLogger(AuthorizationCommand.class);

    @Override
    public Router execute(RequestContent content) {
       /* String page = ConfigurationManager.getProperty(Parameter.DEFAULT_PAGE);*/
        String page = "/controller?command=go_to_default_page";

        TransmisionType transmitionType = TransmisionType.REDIRECT;

        String login = content.getRequestParameters(Parameter.LOGIN_PARAM, 0);
        String password = content.getRequestParameters(Parameter.PASSWORD_PARAM, 0);
        String locale = (String) content.getSessionAttribute(Parameter.LOCAL_PARAM);

        ServiceProvider provider = ServiceProvider.getInstance();
        UserService userService = provider.getUserService();
        TakeTypeNews takeTypeNews = new TakeTypeNews();

        takeTypeNews.typeNews(content);// FIXME: 6/8/2019 уточнить у ИН правильность обращения к отдельному методу
        try {
            User user = userService.checkUser(login, password);
            if (user != null) {
                if (user.getRole().equals(Parameter.ADMIN_PARAM) && (!user.getDeleted().equals(Parameter.DELETED_PARAM))) {
                    content.putSessionAttribute(Parameter.ROLE_PARAM, Parameter.ADMIN_PARAM);
                    content.putSessionAttribute(Parameter.USER_PARAM, user);
                } else if (user.getRole().equals(Parameter.USER_PARAM) && (!user.getDeleted().equals(Parameter.DELETED_PARAM))) {
                    content.putSessionAttribute(Parameter.ROLE_PARAM, Parameter.USER_PARAM);
                    content.putSessionAttribute(Parameter.USER_PARAM, user);
                }
                if (user.getDeleted().equals(Parameter.DELETED_PARAM)) {
                    content.putSessionAttribute(Parameter.ERROR_PARAM, MessageManager.getProperty(Parameter.DELETE_ACCOUNT_MESSAGE, locale));
                }
            } else {
                content.putSessionAttribute(Parameter.ERROR_PARAM, MessageManager.getProperty(Parameter.UNKNOWN_MISTAKE_MESSAGE, locale));
            }
        } catch (ServiceException e) {
            LOGGER.info(Parameter.SERVICE_EXCEPTION_PARAM, e.getMessage());
            e.printStackTrace();
           /* content.putRequestAttribute(Parameter.ERROR_PARAM, MessageManager.getProperty(e.getMessage(), locale));*/

            content.putSessionAttribute(Parameter.ERROR_PARAM, MessageManager.getProperty(e.getMessage(), locale));
        }
        return new Router(page, transmitionType);
    }
}