package by.epam.club.entity;

import by.epam.club.exception.DaoException;
import by.epam.club.tool.CreateDate;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

import static by.epam.club.entity.Parameter.*;

@SuppressWarnings("unchecked")
public class EntityCreator<T> {
    public T create(String table, ResultSet rs) throws DaoException {//fixme не нравится метод, приходит разных размеров резалтСет
        Entity entity;
        switch (table) {
            case USER_PARAM:
                entity = createUser(rs);
                break;
            case USER_ID_PARAM:
                entity = createUserID(rs);
                break;
            case USER_EMAIL_PARAM:
                entity = createUserEmail(rs);
                break;
            case USER_LOGIN_PARAM:
                entity = createUserLogin(rs);
                break;
            case ARTICLES_PARAM:
                entity = createArticle(rs);
                break;
            case COMMENT_PARAM:
                entity = createComment(rs);
                break;
            case TYPES_PARAM:
                entity = createType(rs);
                break;
            case LETTER_FROM_PARAM:
                entity = createLetterFrom(rs);
                break;
            case LETTER_TO_PARAM:
                entity = createLetterTo(rs);
                break;
            default:
                throw new DaoException(UNKNOWN_ENTITY_TYPE_MESSAGE);
        }
        return (T) entity;
    }

    private Letter createLetterTo(ResultSet rs) throws DaoException {
        Letter letter = createLetter(rs);
        try {
            letter.setFromUser(rs.getString("login"));
        } catch (SQLException e) {
            e.printStackTrace();
            throw new DaoException(e);
        }
        return letter;
    }

    private Letter createLetterFrom(ResultSet rs) throws DaoException {
        Letter letter = createLetter(rs);
        try {
            letter.setToUser(rs.getString("login"));
        } catch (SQLException e) {
            e.printStackTrace();
            throw new DaoException(e);
        }
        return letter;
    }

    private Letter createLetter(ResultSet resultSetLetter) throws DaoException {
        CreateDate createDate = new CreateDate();
        Letter letter = new Letter();
        try {
            Date moment = new Date(resultSetLetter.getBigDecimal("date").longValue());
            String date = createDate.takeDate(moment);
            letter.setId(resultSetLetter.getInt("letter_id"));
            letter.setTitle(resultSetLetter.getString("title"));
            letter.setText(resultSetLetter.getString("text"));
            letter.setDate(date);
            if (resultSetLetter.getInt("banned") == 0) {
                letter.setBanned(UNBANNED_PARAM);
            } else {
                letter.setBanned(BANNED_PARAM);
            }
            if (resultSetLetter.getInt("deleted") == 0) {
                letter.setDeleted(UNDELETED_PARAM);
            } else {
                letter.setDeleted(Parameter.DELETED_PARAM);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new DaoException(e);
        }
        return letter;
    }

    private TypeNews createType(ResultSet rs) throws DaoException {
        TypeNews typeNews = new TypeNews();
        try {
            typeNews.setId(rs.getInt(1));
            typeNews.setTypeNews(rs.getString(2));
            if (rs.getInt(3) == 0) {
                typeNews.setDeleted(Parameter.UNDELETED_PARAM);
            } else {
                typeNews.setDeleted(Parameter.DELETED_PARAM);
            }
        } catch (SQLException e) {
            throw new DaoException(SQL_EXCEPTION_MESSAGE);
        }
        return typeNews;
    }

    private Article createArticle(ResultSet resultSetArticle) throws DaoException {
        Article article = new Article();
        try {
            Date moment = new Date(resultSetArticle.getBigDecimal(6).longValue());
            CreateDate createDate = new CreateDate();
            String date = createDate.takeDate(moment);

            article.setId(resultSetArticle.getInt(1));
            article.setTitle(resultSetArticle.getString(2));
            article.setText(resultSetArticle.getString(3));
            article.setPositiveRating(resultSetArticle.getInt(4));
            article.setNegativeRating(resultSetArticle.getInt(5));
            article.setDate_registration(date);

            if (resultSetArticle.getInt(7) == 0) {
                article.setBanned(UNBANNED_PARAM);
            } else {
                article.setBanned(BANNED_PARAM);
            }
            if (resultSetArticle.getInt(8) == 0) {
                article.setDeleted(UNDELETED_PARAM);
            } else {
                article.setDeleted(Parameter.DELETED_PARAM);
            }
            article.setUserId(resultSetArticle.getInt(9));
            article.setTypeNewsId(resultSetArticle.getInt(10));
            article.setUserLogin(resultSetArticle.getString(11));
          /*  article.setComments(comments);
            article.setCommentQuantity(comments.size());*/
        } catch (SQLException e) {
            e.printStackTrace();
            throw new DaoException(e);
        }
        return article;
    }

    private CommentToArticle createComment(ResultSet resultSetComment) throws DaoException {
        CommentToArticle comment = new CommentToArticle();
        try {
            Date moment = new Date(resultSetComment.getBigDecimal(3).longValue());
            CreateDate createDate = new CreateDate();
            String date = createDate.takeDate(moment);

            comment.setId(resultSetComment.getLong(1));
            comment.setText(resultSetComment.getString(2));
            comment.setDateRegistration(date);

            comment.setPositiveRating(resultSetComment.getInt(4));
            comment.setNegativeRating(resultSetComment.getInt(5));


            if (resultSetComment.getInt(6) == 0) {
                comment.setBanned(Parameter.UNBANNED_PARAM);
            } else {
                comment.setBanned(Parameter.BANNED_PARAM);
            }
            if (resultSetComment.getInt(7) == 0) {
                comment.setDeleted(Parameter.UNDELETED_PARAM);
            } else {
                comment.setDeleted(Parameter.DELETED_PARAM);
            }

            comment.setUserLogin(resultSetComment.getString(8));
            comment.setUserId(resultSetComment.getLong(9));
        } catch (
                SQLException e) {
            e.printStackTrace();
            throw new DaoException(e);
        }
        return comment;
    }

    private User createUser(ResultSet rs) throws DaoException {
        try {
            User user = new User();
            Date moment = new Date(rs.getBigDecimal(DATE_REGISTRATION_PARAM).longValue());
            DateFormat dateFormat = SimpleDateFormat.getDateTimeInstance(SimpleDateFormat.SHORT, SimpleDateFormat.SHORT);
            dateFormat.setTimeZone(TimeZone.getTimeZone(EUROPE_MINSK_PARAM));
            String date = dateFormat.format(moment);
            String dateActivity;
            try {
                Date momentActivity = new Date(rs.getBigDecimal(DATE_ACTIVITY_PARAM).longValue());
                dateActivity = dateFormat.format(momentActivity);
            } catch (NullPointerException e) {
                dateActivity = date;
            }

            user.setDate_registration(date);
            user.setDate_activity(dateActivity);

            user.setId(rs.getLong(ID_PARAM));
            user.setLogin(rs.getString(Parameter.LOGIN_PARAM));
            user.setEmail(rs.getString(Parameter.EMAIL_PARAM));

            user.setRole(rs.getString(Parameter.ROLE_PARAM));
            user.setFirstname(rs.getString(FIRSTNAME_PARAM));
            user.setFamilyname(rs.getString(FAMILYNAME_PARAM));
            user.setBlob(rs.getBlob(FILE_PARAM));

            if (rs.getInt(BANNED_PARAM) == 0) {
                user.setBanned(UNBANNED_PARAM);
            } else {
                user.setBanned(BANNED_PARAM);
            }
            if (rs.getInt(DELETED_PARAM) == 0) {
                user.setDeleted(UNDELETED_PARAM);
            } else {
                user.setDeleted(DELETED_PARAM);
            }

            return user;
        } catch (SQLException e) {
            e.printStackTrace();
            throw new DaoException(SQL_EXCEPTION_MESSAGE);
        }
    }

    private User createUserEmail(ResultSet rs) throws DaoException {
        try {
            User user = new User();
            user.setEmail(rs.getString(Parameter.EMAIL_PARAM));

            if (rs.getInt(DELETED_PARAM) == 0) {
                user.setDeleted(UNDELETED_PARAM);
            } else {
                user.setDeleted(DELETED_PARAM);
            }
            return user;
        } catch (SQLException e) {
            e.printStackTrace();
            throw new DaoException(SQL_EXCEPTION_MESSAGE);
        }
    }

    private User createUserID(ResultSet rs) throws DaoException {

        User user = new User();
        try {
            user.setId(rs.getLong(ID_PARAM));
            return user;
        } catch (SQLException e) {
            e.printStackTrace();
            throw new DaoException(SQL_EXCEPTION_MESSAGE);
        }
    }

    private User createUserLogin(ResultSet rs) throws DaoException {
        User user = new User();
        try {
            user.setLogin(rs.getString(LOGIN_PARAM));
            return user;
        } catch (SQLException e) {
            e.printStackTrace();
            throw new DaoException(SQL_EXCEPTION_MESSAGE);
        }
    }
}

