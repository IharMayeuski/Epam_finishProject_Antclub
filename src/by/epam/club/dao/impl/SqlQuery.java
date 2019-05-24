package by.epam.club.dao.impl;

public enum SqlQuery {
    USER_CHECK_LOGIN_AND_PASSWORD(new StringBuilder().append("SELECT u.id,u.login,u.email,u.date_registration, u.banned, u.deleted, r.role FROM user u ").
            append("INNER JOIN role r ON u.role_role_id=r.role_id WHERE u.login=? and u.password=?").toString()),

    USER_MARK_DELETED(new StringBuilder().append("UPDATE user SET deleted=1 WHERE id=?").toString()),

    USER_MARK_UNDELETED(new StringBuilder().append("UPDATE user SET deleted=0 WHERE id=?").toString()),

    USER_MARK_BANNED(new StringBuilder().append("UPDATE user SET banned=1 where ID=?").toString()),

    USER_MARK_UNBANNED(new StringBuilder().append("UPDATE user SET banned=0 where ID=?").toString()),

    USER_CHECK_LOGIN("SELECT login FROM user WHERE login=?"),

    USER_CHECK_EMAIL("SELECT email FROM user WHERE email=?"),

    USER_INSERT_NEW("INSERT INTO user (login, email, password, date_registration) VALUES (?,?,?,?)"),

    USER_FIND_ALL_USER((new StringBuilder().append("SELECT u.id,u.login,u.email,u.date_registration, u.banned, u.deleted, r.role FROM user u ").
            append("INNER JOIN role r ON u.role_role_id= r.role_id ").toString())),

    USER_FIND_ALL_UNDELETED_USER((new StringBuilder().append("SELECT u.id,u.login,u.email,u.date_registration, u.banned, u.deleted, r.role FROM user u ").
            append("INNER JOIN role r ON u.role_role_id= r.role_id WHERE u.deleted=0 and u.role_role_id=2").toString())),


    USER_FIND_USER_BY_LOGIN(new StringBuilder().append("SELECT u.id,u.login,u.email,u.date_registration, u.banned, u.deleted, r.role FROM user u ").
            append("INNER JOIN role r ON u.role_role_id= r.role_id WHERE u.login=?").toString()),

    ARTICLE_INSERT_NEW (new StringBuilder().append("INSERT INTO article (title, text, positive_rating, ").
            append("negative_rating, date_article, user_id, type_news_id) VALUES (?,?,?,?,?,?,?)").toString()),

    QUERY_FIND_ALL_ARTICLE_BY_TYPE_NEWS((new StringBuilder().append("SELECT article_id, title, text,positive_rating, negative_rating,").
            append("date_article,banned,deleted,user_id,type_news_id FROM article WHERE type_news_id=?").toString())),


    PICTURE_INSERT_NEW("INSERT INTO picture (file_name, file, article_article_id) VALUES (?,?,?)"),

    PICTURE_MARK_DELETE(new StringBuilder().append ("UPDATE picture SET deleted=1 where picture_id=?").toString()),

    PICTURE_MARK_UNBANNED(new StringBuilder().append ("UPDATE picture SET banned=0 where picture_id=?").toString()),

    PICTURE_MARK_BANNED(new StringBuilder().append ("UPDATE picture SET banned=1 where picture_id=?").toString());


    private String query;

    SqlQuery(String query) {
        this.query = query;
    }

    public String getQuery() {
        return query;
    }
}