package by.epam.club.command.forward;

import by.epam.club.bundlemanager.MessageManager;
import by.epam.club.command.forward.global.AuthorizationCommand;
import by.epam.club.controller.RequestContent;
import by.epam.club.entity.TypeNews;
import by.epam.club.exception.ServiceException;
import by.epam.club.service.type.TypeService;
import by.epam.club.service.type.TypeServiceImpl;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

import static by.epam.club.entity.Parameter.*;

/**
 * Class for putting in the session object - TypeNews
 *
 * @author Maeuski Igor
 * @version 1.0
 */

public class TakeTypeNews {
    private static final Logger LOGGER = LogManager.getLogger(AuthorizationCommand.class);

    /**
     * @param content of the class RequestContent
     */

    public void typeNews(RequestContent content) {
        String newLocale = (String) content.getSessionAttribute(LOCAL_PARAM);
        TypeService typeService = new TypeServiceImpl();
        try {
            List<TypeNews> typeNews = typeService.takeAllUndeleted();
            content.putSessionAttribute(TYPES_PARAM, typeNews);
        } catch (ServiceException e) {
            LOGGER.info(SERVICE_EXCEPTION_PARAM, e.getMessage());
            content.putRequestAttribute(ERROR_PARAM, MessageManager.getProperty(UNKNOWN_MISTAKE_MESSAGE, newLocale));
        }
    }
}
