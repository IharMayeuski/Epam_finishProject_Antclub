package by.epam.club.dao;

public enum SqlQuery {

    ARTICLE_ALL_BY_TYPE_NEWS_NOTBANNED_NOTDELETED("SELECT article_id, title, text,positive_rating, negative_rating, date_article,article.banned,article.deleted,user_id,type_news_id, user.login FROM article INNER JOIN user ON user_id=user.id WHERE type_news_id=? and article.deleted=0 and article.banned=0"),

    ARTICLE_BY_TYPE_NEWS("SELECT article_id, title, text,positive_rating, negative_rating, date_article,article.banned,article.deleted,user_id,type_news_id, user.login FROM article INNER JOIN user ON user_id=user.id WHERE type_news_id=?"),

    ARTICLE_CHECK("SELECT article_id, title, text, positive_rating, negative_rating, date_article, article.banned, article.deleted, user_id, type_news_id, user.login FROM article INNER JOIN user ON user_id=user.id WHERE article_id=?"),

    ARTICLE_INSERT_NEW("INSERT INTO article (title, text, positive_rating, negative_rating, date_article, user_id, type_news_id) VALUES (?,?,?,?,?,?,?)"),

    ARTICLE_UPDATE_DATA("UPDATE article SET title=?, text=?, type_news_id=? where article_id=?"),

    COMMENT_CHECK("SELECT comment_id, comment, comment_date, positive_reiting, negative_reiting, comment.banned, comment.deleted, user.login, user.id FROM comment INNER JOIN user ON user_id=user.id WHERE article_article_id=?"),

    PICTURE_INSERT_NEW("INSERT INTO picture (file_name, file, article_article_id) VALUES (?,?,?)"),

    PICTURE_MARK_BANNED("UPDATE picture SET banned=1 where picture_id=?"),

    PICTURE_MARK_DELETE("UPDATE picture SET deleted=1 where picture_id=?"),

    PICTURE_MARK_UNBANNED("UPDATE picture SET banned=0 where picture_id=?"),

    PICTURE_SELECT_BLOB("SELECT file from picture where article_article_id=?"),

    TYPE_SELECT_BLOB("SELECT file from type where news_id=?"),

    TYPE_FIND_ALL_UNDELETED("SELECT news_id, type, deleted FROM type WHERE deleted=0"),

    TYPE_FIND_ALL("SELECT news_id, type, deleted FROM type"),

    TYPE_INSERT_NEW("INSERT INTO type (type) VALUE (?)"),

    TYPE_UPDATE_PIC("UPDATE type SET file=? WHERE news_id=?"),

    TYPE_FIND_ONE("SELECT news_id, type, deleted FROM type WHERE type=?"),

    USER_CHECK_EMAIL("SELECT email, deleted FROM user WHERE email=?"),

    USER_CHECK_LOGIN("SELECT login, deleted FROM user WHERE login=?"),

    USER_CHECK_LOGIN_EMAIL_PASSWORD ("SELECT login FROM user WHERE login=? and email=? and password=?"),

    USER_CHECK_LOGIN_AND_PASSWORD("SELECT u.id,u.login,u.email,u.date_registration, u.date_activity, u.banned, u.deleted, r.role, i.firstname, i.familyname, i.file FROM user u INNER JOIN role r ON u.role_role_id=r.role_id INNER JOIN userinfo i ON i.user_id = u.id WHERE u.login=? and u.password=?"),

    USER_FIND_ALL_UNDELETED_USER("SELECT u.id,u.login,u.email,u.date_registration,u.date_activity, u.banned, u.deleted, r.role,i.firstname, i.familyname, i.file FROM user u INNER JOIN role r ON u.role_role_id= r.role_id INNER JOIN userinfo i ON i.user_id = u.id WHERE u.deleted=0 and u.role_role_id=2"),

    USER_FIND_ALL_DELETED_USER("SELECT u.id,u.login,u.email,u.date_registration,u.date_activity, u.banned, u.deleted, r.role,i.firstname, i.familyname, i.file FROM user u INNER JOIN role r ON u.role_role_id= r.role_id INNER JOIN userinfo i ON i.user_id = u.id WHERE u.deleted=1 and u.role_role_id=2"),

    USER_FIND_ALL_BANNED_USER("SELECT u.id,u.login,u.email,u.date_registration,u.date_activity, u.banned, u.deleted, r.role,i.firstname, i.familyname, i.file FROM user u INNER JOIN role r ON u.role_role_id= r.role_id INNER JOIN userinfo i ON i.user_id = u.id WHERE u.banned=1 and u.role_role_id=2"),

    USER_FIND_ALL_USER("SELECT u.id,u.login,u.email,u.date_registration,u.date_activity, u.banned, u.deleted, r.role,i.firstname, i.familyname, i.file FROM user u INNER JOIN role r ON u.role_role_id= r.role_id INNER JOIN userinfo i ON i.user_id = u.id"),

    USER_FIND_USER_BY_LOGIN("SELECT u.id,u.login,u.email,u.date_registration,u.date_activity, u.banned, u.deleted, r.role, i.firstname, i.familyname, i.file FROM user u INNER JOIN role r ON u.role_role_id= r.role_id INNER JOIN userinfo i ON i.user_id = u.id WHERE u.login=?"),

    USER_FIND_USER_BY_EMAIL("SELECT u.id,u.login,u.email,u.date_registration,u.date_activity, u.banned, u.deleted, r.role, i.firstname, i.familyname, i.file FROM user u INNER JOIN role r ON u.role_role_id= r.role_id INNER JOIN userinfo i ON i.user_id = u.id WHERE u.email=?"),

    USER_FIND_USER_ID("SELECT id FROM user WHERE login=?"),

    USER_INSERT_NEW("INSERT INTO user (login, email, password, date_registration) VALUES (?,?,?,?)"),

    USER_INSERT_NEW_INFO("INSERT INTO userinfo (user_id) VALUE (?)"),

    USER_SELECT_BLOB_FROM_INFO("SELECT file from userinfo where user_id=?"),

    USER_MARK_BANNED("UPDATE user SET banned=1 WHERE id=?"),

    USER_MARK_DELETED("UPDATE user SET deleted=1 WHERE login=?"),

    USER_MARK_UNBANNED("UPDATE user SET banned=0 WHERE ID=?"),

    USER_MARK_UNDELETED("UPDATE user SET deleted=0 WHERE id=?"),

    USER_UPDATE_DATE_INPUT("UPDATE user SET date_activity=? WHERE id=?"),

    USER_UPDATE_DATA("UPDATE user SET login=?, email=?, password=? WHERE id=?"),

    USER_UPDATE_DATA_EMAIL("UPDATE user SET login=?, email=? WHERE id=?"),

    USER_CHANGE_PASSWORD("UPDATE user SET password=? WHERE email=?"),

    USER_UPDATE_INFO("UPDATE userinfo SET firstname=?, familyname=? WHERE user_id=?"),

    USER_UPDATE_PIC("UPDATE userinfo SET file=? WHERE user_id=?"),

    SEND_LETTER("INSERT INTO letter (title, text, date, to_user_id, user_id) values(?,?,?,?,?)"),

    SENT_LETTER_BY_USER_TO("SELECT letter_id, title, text, letter.banned, letter.deleted, date, user_id, login FROM user JOIN letter  on user.id = letter.to_user_id WHERE user_id=?"),

    RECEIVED_LETTER_TO_USER_FROM("select letter_id, title, text, letter.banned, letter.deleted, date, user_id, login from user join letter  on user.id = letter.user_id where to_user_id=?"),

    USER_CHANGE_EMAIL("UPDATE user SET email=? WHERE login=?");

    private String query;

    SqlQuery(String query) {
        this.query = query;
    }

    public String getQuery() {
        return query;
    }
}