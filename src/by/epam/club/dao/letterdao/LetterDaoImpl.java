package by.epam.club.dao.letterdao;
/**
 * Class of level dao for working with Letter
 *
 * @author Maeuski Igor
 * @version 1.0
 */

import by.epam.club.dao.basedao.BaseDao;
import by.epam.club.entity.Letter;
import by.epam.club.exception.DaoException;

import java.util.Comparator;
import java.util.List;

import static by.epam.club.dao.SqlQuery.*;
import static by.epam.club.entity.Parameter.LETTER_FROM_PARAM;
import static by.epam.club.entity.Parameter.LETTER_TO_PARAM;

@SuppressWarnings("unchecked")
public class LetterDaoImpl extends BaseDao implements LetterDao {
    /**
     *
     * @param userFromId informatino about id user who is sent letter
     * @param userToId information about id user that receives this letter
     * @param date date of sending letter
     * @param title the title of letter
     * @param text text of the letter
     * @throws DaoException for catching it on the logic level in the case of exception
     */
    @Override
    public void sendLetter(String userFromId, String userToId, String date, String title, String text) throws DaoException {
        create(SEND_LETTER.getQuery(), title, text, date, userToId, userFromId);
    }

    /**
     *
     * @param id user's id who sent letter
     * @return {@code List<Letter>} that were sent by user
     * @throws DaoException for catching it on the logic level in the case of exception
     */
    @Override
    public List<Letter> takeLetterUserSent(long id) throws DaoException {
        Comparator<Letter> sort = Comparator.comparingLong(Letter::getId).reversed();
        List<Letter> letters = (List<Letter>) find(SENT_LETTER_BY_USER_TO.getQuery(), LETTER_FROM_PARAM, Long.toString(id));
        letters.sort(sort);
        return letters;
    }

    /**
     *
     * @param id user's id who received letter
     * @return {@code List<Letter>} that were received to user
     * @throws DaoException for catching it on the logic level in the case of exception
     */
    @Override
    public List<Letter> takeLetterUserReceivedFrom(long id) throws DaoException {
        Comparator<Letter> sort = Comparator.comparingLong(Letter::getId).reversed();
        List<Letter> letters = (List<Letter>) find(RECEIVED_LETTER_TO_USER_FROM.getQuery(), LETTER_TO_PARAM, Long.toString(id));
        letters.sort(sort);
        return letters;
    }

    /**
     *
     * @param letterId for mark letter 'is deleted'
     * @throws DaoException for catching it on the logic level in the case of exception
     */
    @Override
    public void deleteLetter(String letterId) throws DaoException {
        create(DELETE_LETTER.getQuery(), letterId);
    }

}
