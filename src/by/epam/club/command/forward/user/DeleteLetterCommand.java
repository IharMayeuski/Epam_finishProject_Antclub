package by.epam.club.command.forward.user;

import by.epam.club.bundlemanager.ConfigurationManager;
import by.epam.club.bundlemanager.MessageManager;
import by.epam.club.command.ActionCommand;
import by.epam.club.command.Router;
import by.epam.club.controller.RequestContent;
import by.epam.club.controller.TransmisionType;
import by.epam.club.entity.User;
import by.epam.club.exception.ServiceException;
import by.epam.club.service.letter.LetterService;
import by.epam.club.service.letter.LetterServiceImpl;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import static by.epam.club.entity.Parameter.*;
import static by.epam.club.entity.Parameter.ERROR_PARAM;

/**
 * @author Maeuski Igor
 * @version 1.0
 * @see by.epam.club.command.ActionCommand
 */

public class DeleteLetterCommand implements ActionCommand {
    private static final Logger LOGGER = LogManager.getLogger(DeleteLetterCommand.class);

    /**
     * @param content of the class RequestContent
     * @return new Router that send user on a new page by process Type
     */

    @Override
    public Router execute(RequestContent content) {
        String page = ConfigurationManager.getProperty(DEFAULT_PAGE_FORVARD);
        String letterId = content.getRequestParameters(LETTER_ID_PARAM, 0);
        TransmisionType transmitionType = TransmisionType.FORWARD;
        String locale = (String) content.getSessionAttribute(LOCAL_PARAM);
        LetterService service = new LetterServiceImpl();
        try {
            service.delete(letterId);
            content.putRequestAttribute(UPDATE_ALL_IS_OK_PARAM, MessageManager.getProperty(LETTER_DELETED_FOR_BOTH_SIDE_MESSAGE, locale));
        } catch (ServiceException e) {
            LOGGER.info(SERVICE_EXCEPTION_PARAM, e.getMessage());
            content.putRequestAttribute(ERROR_PARAM, MessageManager.getProperty(e.getMessage(), locale));
        }
        return new Router(page, transmitionType);
    }
}

