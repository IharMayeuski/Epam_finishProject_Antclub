package by.epam.club.dao.letterdao;

import by.epam.club.entity.Letter;
import by.epam.club.exception.DaoException;

import java.util.List;

public interface LetterDao {
    void sendLetter(String userFromId, String userToId, String date, String title, String text) throws DaoException;

    List<Letter> takeLetterUserSent(long id) throws DaoException;

    List<Letter> takeLetterUserReceivedFrom(long id)throws DaoException;
}

