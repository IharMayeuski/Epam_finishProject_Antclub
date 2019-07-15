package by.epam.club.command.forward.user;

import by.epam.club.bundlemanager.ConfigurationManager;
import by.epam.club.command.ActionCommand;
import by.epam.club.command.Router;
import by.epam.club.controller.RequestContent;
import by.epam.club.controller.TransmisionType;
import by.epam.club.entity.Letter;
import by.epam.club.entity.User;
import by.epam.club.exception.ServiceException;
import by.epam.club.service.letter.LetterService;
import by.epam.club.service.letter.LetterServiceImpl;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

import static by.epam.club.entity.Parameter.*;

/**
 * @author Maeuski Igor
 * @version 1.0
 * @see by.epam.club.command.ActionCommand
 */

public class UserProfileCommand implements ActionCommand {
    private static final Logger LOGGER = LogManager.getLogger(UserProfileCommand.class);

    /**
     * @param content of the class RequestContent
     * @return new Router that send user on a new page by process Type
     */

    @Override
    public Router execute(RequestContent content) {
        String page = ConfigurationManager.getProperty(USER_PROFILE_PAGE_FORVARD);
        TransmisionType transmitionType = TransmisionType.FORWARD;
        User user = (User) content.getSessionAttribute(USER_PARAM);
        LetterService letterService = new LetterServiceImpl();
        try {
           List<Letter> lettersSentByUser=  letterService.letterUserSent(user.getId());
            List<Letter> lettersReceivedFromAnybodyToUser =  letterService.letterUserRecieved(user.getId());
            content.putSessionAttribute(LETTER_FROM_PARAM, lettersSentByUser);
            content.putSessionAttribute(LETTER_TO_PARAM, lettersReceivedFromAnybodyToUser);
        } catch (ServiceException e) {
            LOGGER.info(SERVICE_EXCEPTION_PARAM, e.getMessage());
        }
        return new Router(page, transmitionType);
    }
}
