package by.epam.club.dao.impl;

public enum Status {
    NOTBANNED("not banned"), NOTDELETED ("not deleted"),
    BANNED("banned"), DELETED("deleted"), ROLE_USER("user"),
    ROLE_ADMIN("admin");

    private String query;

    Status(String query) {
        this.query = query;
    }

    public String getStatus() {
        return query;
    }
}