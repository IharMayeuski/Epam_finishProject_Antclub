package by.epam.club.dao.impl;

public enum SqlFunction {
    QUERY_CHECK_USER(new StringBuilder().append("SELECT u.id,u.login,u.email,u.date_registration,r.role,b.block, a.acount FROM user u ").
            append("INNER JOIN role r ON u.role_id= r.role_id ").
            append("INNER JOIN userblock b ON b.block_id=u.userBlock_block_id ").
            append("INNER JOIN deleted_account a ON a.account_id=u.deleted_account_id ").
            append("WHERE u.login=? and u.password=?").toString()),

    MARK_USER_LIKE_DELETED(new StringBuilder().append
            ("UPDATE user SET deleted_account_id=2 WHERE id=?").toString()),

    QUERY_CHECK_USER_LOGIN("SELECT login FROM user WHERE login=?"),

    QUERY_CHECK_USER_EMAIL("SELECT email FROM user WHERE email=?"),

    INSERT_NEW_USER("INSERT INTO user (login, email, password, date_registration, role_id, " +
            "userBlock_block_id, deleted_account_id) VALUES (?,?,?,?,?,?,?)"),

    QUERY_FIND_ALL_USER((new StringBuilder().append("SELECT u.id,u.login,u.email,u.date_registration,r.role,b.block, a.acount FROM user u ").
            append("INNER JOIN role r ON u.role_id= r.role_id ").
            append("INNER JOIN userblock b ON b.block_id=u.userBlock_block_id ").
            append("INNER JOIN deleted_account a ON a.account_id=u.deleted_account_id ").toString())),

    QUERY_FIND_USER_BY_LOGIN(new StringBuilder().append("SELECT u.id,u.login,u.email,u.date_registration,r.role,b.block, a.acount FROM user u ").
            append("INNER JOIN role r ON u.role_id= r.role_id ").
            append("INNER JOIN userblock b ON b.block_id=u.userBlock_block_id ").
            append("INNER JOIN deleted_account a ON a.account_id=u.deleted_account_id ").
            append("WHERE u.login=?").toString()),

    INSERT_NEW_ARTICLE(new StringBuilder().append("INSERT INTO article (name, text, positive_rating, ").
            append("negative_rating, date_article, user_id, type_news_id, banned_block_id) ").
            append("VALUES (?,?,?,?,?,?,?,?)").toString());




    private String query;

    SqlFunction(String query) {
        this.query = query;
    }

    public String getQuery() {
        return query;
    }
}