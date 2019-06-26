package by.epam.club.service.letter;

import by.epam.club.dao.letterdao.LetterDao;
import by.epam.club.dao.letterdao.LetterDaoImpl;
import by.epam.club.entity.Letter;
import by.epam.club.exception.DaoException;
import by.epam.club.exception.ServiceException;

import java.util.Date;
import java.util.List;

import static by.epam.club.entity.Parameter.EMPTY_MESSAGE;

public class LetterServiceImpl implements LetterService {
    @Override
    public void sendLetter(long userFromId, long userToId, String title, String text) throws ServiceException {
        LetterDao letterDao = new LetterDaoImpl();
        String fromId = Long.toString(userFromId);
        String toId = Long.toString(userToId);
        Date date = new Date();
        String newDate = String.valueOf(date.toInstant().toEpochMilli());
        if (title.isEmpty()&&text.isEmpty()){
            throw new ServiceException(EMPTY_MESSAGE);
        }

        try {
           letterDao.sendLetter(fromId,toId, newDate, title, text);
        } catch (DaoException e) {
            throw new ServiceException(e.getMessage());
        }
    }

    @Override
    public List<Letter> letterUserSent(long id) throws ServiceException {
        LetterDao letterDao = new LetterDaoImpl();
        List<Letter> letters = null;
        try {
            letters = letterDao.takeLetterUserSent(id);
        } catch (DaoException e) {
            throw new ServiceException(e.getMessage());
        }

        return letters;
    }

    @Override
    public List<Letter> letterUserRecieved(long id) throws ServiceException {
        LetterDao letterDao = new LetterDaoImpl();
        List<Letter> letters = null;
        try {
            letters = letterDao.takeLetterUserReceivedFrom(id);
        } catch (DaoException e) {
            throw new ServiceException(e.getMessage());
        }

        return letters;
    }
}