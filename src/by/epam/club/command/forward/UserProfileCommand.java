package by.epam.club.command.forward;

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

import java.util.List;

import static by.epam.club.entity.Parameter.*;

public class UserProfileCommand implements ActionCommand {
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
            e.printStackTrace();
        }


        return new Router(page, transmitionType);
    }
}
