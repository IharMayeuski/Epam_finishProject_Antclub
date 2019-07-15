package by.epam.club.service.letter;

import by.epam.club.dao.letterdao.LetterDao;
import by.epam.club.dao.letterdao.LetterDaoImpl;
import by.epam.club.entity.Letter;
import by.epam.club.exception.DaoException;
import by.epam.club.exception.ServiceException;

import java.util.Date;
import java.util.List;

import static by.epam.club.entity.Parameter.EMPTY_MESSAGE;

/**
 * Class of business logic for Letter
 *
 * @author Maeuski Igor
 * @version 1.0
 */

public class LetterServiceImpl implements LetterService {
    /**
     * @param userFromId is user's id from letter was sent
     * @param userToId is user's id to letter was sent
     * @param title the title of letter
     * @param text the text of letter
     * @throws ServiceException in the case of mistake if we catch DaoException we will throw ServiceException
     */

    @Override
    public void sendLetter(long userFromId, long userToId, String title, String text) throws ServiceException {
        LetterDao letterDao = new LetterDaoImpl();
        String fromId = Long.toString(userFromId);
        String toId = Long.toString(userToId);
        Date date = new Date();
        String newDate = String.valueOf(date.toInstant().toEpochMilli());
        if ((title==null&&text==null)||(title.isEmpty()&&text.isEmpty())){
            throw new ServiceException(EMPTY_MESSAGE);
        }
        try {
           letterDao.sendLetter(fromId,toId, newDate, title, text);
        } catch (DaoException e) {
            throw new ServiceException(e.getMessage());
        }
    }
    /**
     * @param id for receiving {@code List<Letter>} from data base by id
     * @return {@code List<Letter>} from data base
     * @throws ServiceException in the case of mistake if we catch DaoException we will throw ServiceException
     */

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
    /**
     * @param id for receiving {@code List<Letter>} from data base by id
     * @return {@code List<Letter>} from data base
     * @throws ServiceException in the case of mistake if we catch DaoException we will throw ServiceException
     */

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
    /**
     * @param letterId for mark letter is deleted in data base
     * @throws ServiceException
     */

    @Override
    public void delete(String letterId) throws ServiceException {
        LetterDao letterDao = new LetterDaoImpl();
        try {
            letterDao.deleteLetter(letterId);
        } catch (DaoException e) {
            throw new ServiceException(e.getMessage());
        }
    }
}