package by.epam.club.command.forward;

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
import static by.epam.club.entity.Parameter.LOCAL_PARAM;

public class SendLetterCommand implements ActionCommand {
    private static final Logger LOGGER = LogManager.getLogger(SendLetterCommand.class);

    @Override
    public Router execute(RequestContent content) {
        String page = ConfigurationManager.getProperty(FIND_USER_PAGE_FORVARD );
        String title = content.getRequestParameters(TITLE_PARAM, 0);
        String text = content.getRequestParameters(TEXT_PARAM, 0);
        User userFrom = (User) content.getSessionAttribute(USER_PARAM);
        long userFromId = userFrom.getId();
        long userToId = Long.parseLong(content.getRequestParameters(FIND_USER_ID_PARAM, 0));
        TransmisionType transmitionType = TransmisionType.FORWARD;
        String locale = (String) content.getSessionAttribute(LOCAL_PARAM);
        LetterService service = new LetterServiceImpl();
        try {
            service.sendLetter(userFromId,userToId, title, text);
            content.putRequestAttribute(UPDATE_ALL_IS_OK_PARAM, MessageManager.getProperty(LETTER_SENT, locale));
        } catch (ServiceException e) {
            LOGGER.info(SERVICE_EXCEPTION_PARAM, e.getMessage());
            content.putRequestAttribute(ERROR_PARAM, MessageManager.getProperty(e.getMessage(), locale));
        }

        return new Router(page, transmitionType);
    }
}
