package by.epam.club.dao.letterdao;


import by.epam.club.dao.basedao.BaseDao;
import by.epam.club.entity.Letter;
import by.epam.club.exception.DaoException;

import java.util.List;

import static by.epam.club.dao.SqlQuery.*;
import static by.epam.club.entity.Parameter.LETTER_FROM_PARAM;
import static by.epam.club.entity.Parameter.LETTER_TO_PARAM;

@SuppressWarnings("unchecked")
public class LetterDaoImpl extends BaseDao implements LetterDao {
    @Override
    public void sendLetter(String userFromId, String userToId, String date, String title, String text) throws DaoException {

        try {
            create(SEND_LETTER.getQuery(), title,text, date, userToId,userFromId);
        } catch (DaoException e) {
            throw new DaoException(e.getMessage());
        }
    }

    @Override
    public List<Letter> takeLetterUserSent(long id) throws DaoException {
        return (List<Letter>) find(SENT_LETTER_BY_USER_TO.getQuery(), LETTER_FROM_PARAM, Long.toString(id));
    }

    @Override
    public List<Letter> takeLetterUserReceivedFrom(long id) throws DaoException {
        return (List<Letter>) find(RECEIVED_LETTER_TO_USER_FROM.getQuery(), LETTER_TO_PARAM, Long.toString(id));
    }
}
