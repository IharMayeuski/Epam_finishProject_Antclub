package by.epam.club.entity;

import java.util.List;
import java.util.Objects;

public class CommentToArticle extends Entity {
    private long id;
    private String text;
    private String dateRegistration;
    private int positiveRating;
    private int negativeRating;
    private String banned;
    private String deleted;

    private String userLogin;
    private long articleId;
    private long userId;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getDateRegistration() {
        return dateRegistration;
    }

    public void setDateRegistration(String dateRegistration) {
        this.dateRegistration = dateRegistration;
    }

    public int getPositiveRating() {
        return positiveRating;
    }

    public void setPositiveRating(int positiveRating) {
        this.positiveRating = positiveRating;
    }

    public int getNegativeRating() {
        return negativeRating;
    }

    public void setNegativeRating(int negativeRating) {
        this.negativeRating = negativeRating;
    }

    public String getBanned() {
        return banned;
    }

    public void setBanned(String banned) {
        this.banned = banned;
    }

    public String getDeleted() {
        return deleted;
    }

    public void setDeleted(String deleted) {
        this.deleted = deleted;
    }

    public String getUserLogin() {
        return userLogin;
    }

    public void setUserLogin(String userLogin) {
        this.userLogin = userLogin;
    }

    public long getArticleId() {
        return articleId;
    }

    public void setArticleId(long articleId) {
        this.articleId = articleId;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        CommentToArticle comment = (CommentToArticle) o;

        if (id != comment.id) return false;
        if (positiveRating != comment.positiveRating) return false;
        if (negativeRating != comment.negativeRating) return false;
        if (articleId != comment.articleId) return false;
        if (userId != comment.userId) return false;
        if (text != null ? !text.equals(comment.text) : comment.text != null) return false;
        if (!dateRegistration.equals(comment.dateRegistration)) return false;
        if (banned != null ? !banned.equals(comment.banned) : comment.banned != null) return false;
        if (deleted != null ? !deleted.equals(comment.deleted) : comment.deleted != null) return false;
        return userLogin.equals(comment.userLogin);
    }

    @Override
    public int hashCode() {
        int result = (int) (id ^ (id >>> 32));
        result = 31 * result + (text != null ? text.hashCode() : 0);
        result = 31 * result + dateRegistration.hashCode();
        result = 31 * result + positiveRating;
        result = 31 * result + negativeRating;
        result = 31 * result + (banned != null ? banned.hashCode() : 0);
        result = 31 * result + (deleted != null ? deleted.hashCode() : 0);
        result = 31 * result + userLogin.hashCode();
        result = 31 * result + (int) (articleId ^ (articleId >>> 32));
        result = 31 * result + (int) (userId ^ (userId >>> 32));
        return result;
    }

    @Override
    public String toString() {
        return "CommentToArticle{" +
                "id=" + id +
                ", text='" + text + '\'' +
                ", dateRegistration='" + dateRegistration + '\'' +
                ", positiveRating=" + positiveRating +
                ", negativeRating=" + negativeRating +
                ", banned='" + banned + '\'' +
                ", deleted='" + deleted + '\'' +
                ", userLogin='" + userLogin + '\'' +
                ", articleId=" + articleId +
                ", userId=" + userId +
                '}';
    }
}
