package by.epam.club.service.letter;

import by.epam.club.entity.Letter;
import by.epam.club.exception.ServiceException;

import java.util.List;

public interface LetterService {
    void sendLetter(long userFromId, long userToId, String title, String text)throws ServiceException;

    List<Letter> letterUserSent(long id) throws ServiceException;

    List<Letter> letterUserRecieved(long id) throws ServiceException;
}
