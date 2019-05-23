package by.epam.club.dao.impl;

public enum SqlFunction {
    USER_CHECK_LOGIN_AND_PASSWORD(new StringBuilder().append("SELECT u.id,u.login,u.email,u.date_registration, u.banned, u.deleted, r.role FROM user u ").
            append("INNER JOIN role r ON u.role_role_id=r.role_id ").
            append("WHERE u.login=? and u.password=?").toString()),

    USER_MARK_LIKE_DELETED(new StringBuilder().append
            ("UPDATE user SET deleted=1 WHERE id=?").toString()),

    USER_CHECK_LOGIN("SELECT login FROM user WHERE login=?"),

    USER_CHECK_EMAIL("SELECT email FROM user WHERE email=?"),

    USER_INSERT_NEW("INSERT INTO user (login, email, password, date_registration) VALUES (?,?,?,?)"),

    USER_FIND_ALL_USER((new StringBuilder().append("SELECT u.id,u.login,u.email,u.date_registration, u.banned, u.deleted, r.role FROM user u ").
            append("INNER JOIN role r ON u.role_role_id= r.role_id ").toString())),

    USER_FIND_USER_BY_LOGIN(new StringBuilder().append("SELECT u.id,u.login,u.email,u.date_registration, u.banned, u.deleted, r.role FROM user u ").
            append("INNER JOIN role r ON u.role_role_id= r.role_id ").
            append("WHERE u.login=?").toString()),

    ARTICLE_INSERT_NEW (new StringBuilder().append("INSERT INTO article (title, text, positive_rating, ").
            append("negative_rating, date_article, user_id, type_news_id) ").
            append("VALUES (?,?,?,?,?,?,?)").toString()),

    PICTURE_INSERT_NEW("INSERT INTO picture (file_name, file, article_article_id) VALUES (?,?,?)"),

    PICTURE_DELETE(new StringBuilder().append ("DELETE FROM picture WHERE picture_id=?").toString());

    private String query;

    SqlFunction(String query) {
        this.query = query;
    }

    public String getQuery() {
        return query;
    }
}