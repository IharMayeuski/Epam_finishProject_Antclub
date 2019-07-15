package by.epam.club.dao.letterdao;

import by.epam.club.entity.Letter;
import by.epam.club.exception.DaoException;

import java.util.List;
/**
 * Interface of level dao for working with Letter
 *
 * @author Maeuski Igor
 * @version 1.0
 */
public interface LetterDao {
    void sendLetter(String userFromId, String userToId, String date, String title, String text) throws DaoException;

    List<Letter> takeLetterUserSent(long id) throws DaoException;

    List<Letter> takeLetterUserReceivedFrom(long id)throws DaoException;

    void deleteLetter(String letterId) throws DaoException;
}

