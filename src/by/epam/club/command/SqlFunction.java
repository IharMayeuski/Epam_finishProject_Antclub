package by.epam.club.command;

public enum SqlFunction {
    QUERY_CHECK_USER (new StringBuilder().append("SELECT u.id,u.login,u.email,u.date_registration,r.role,b.block, a.acount FROM user u ").
            append("INNER JOIN role r ON u.role_id= r.role_id ").
            append("INNER JOIN userblock b ON b.block_id=u.userBlock_block_id ").
            append("INNER JOIN deleted_account a ON a.account_id=u.deleted_account_id ").
            append("WHERE u.login=? and u.password=?").toString());



    private String query;

    SqlFunction(String query) {
        this.query=query;
    }

    public String getQuery(){
        return query;
    }
}