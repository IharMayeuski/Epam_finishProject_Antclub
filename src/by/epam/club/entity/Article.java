package by.epam.club.entity;

import java.util.ArrayList;
import java.util.List;

public class Article extends Entity{
    private long id;
    private String title;
    private String text;
    private int positiveRating;
    private int negativeRating;
    private String date_registration;
    private long userId;
    private int typeNewsId;
    private String banned;
    private String deleted;
    private String userLogin;
    private List<CommentToArticle> comments;
    private int commentQuantity;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
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

    public String getDate_registration() {
        return date_registration;
    }

    public void setDate_registration(String date_registration) {
        this.date_registration = date_registration;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public int getTypeNewsId() {
        return typeNewsId;
    }

    public void setTypeNewsId(int typeNewsId) {
        this.typeNewsId = typeNewsId;
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

    public List<CommentToArticle> getComments() {
        return comments;
    }

    public void setComments(List<CommentToArticle> comments) {
        this.comments = comments;
    }

    public int getCommentQuantity() {
        return commentQuantity;
    }

    public void setCommentQuantity(int commentQuantity) {
        this.commentQuantity = commentQuantity;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Article article = (Article) o;

        if (id != article.id) return false;
        if (positiveRating != article.positiveRating) return false;
        if (negativeRating != article.negativeRating) return false;
        if (userId != article.userId) return false;
        if (typeNewsId != article.typeNewsId) return false;
        if (commentQuantity != article.commentQuantity) return false;
        if (title != null ? !title.equals(article.title) : article.title != null) return false;
        if (text != null ? !text.equals(article.text) : article.text != null) return false;
        if (!date_registration.equals(article.date_registration)) return false;
        if (banned != null ? !banned.equals(article.banned) : article.banned != null) return false;
        if (deleted != null ? !deleted.equals(article.deleted) : article.deleted != null) return false;
        if (userLogin != null ? !userLogin.equals(article.userLogin) : article.userLogin != null) return false;
        return comments != null ? comments.equals(article.comments) : article.comments == null;
    }

    @Override
    public int hashCode() {
        int result = (int) (id ^ (id >>> 32));
        result = 31 * result + (title != null ? title.hashCode() : 0);
        result = 31 * result + (text != null ? text.hashCode() : 0);
        result = 31 * result + positiveRating;
        result = 31 * result + negativeRating;
        result = 31 * result + date_registration.hashCode();
        result = 31 * result + (int) (userId ^ (userId >>> 32));
        result = 31 * result + typeNewsId;
        result = 31 * result + (banned != null ? banned.hashCode() : 0);
        result = 31 * result + (deleted != null ? deleted.hashCode() : 0);
        result = 31 * result + (userLogin != null ? userLogin.hashCode() : 0);
        result = 31 * result + (comments != null ? comments.hashCode() : 0);
        result = 31 * result + commentQuantity;
        return result;
    }

    @Override
    public String toString() {
        return "Article{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", text='" + text + '\'' +
                ", positiveRating=" + positiveRating +
                ", negativeRating=" + negativeRating +
                ", date_registration='" + date_registration + '\'' +
                ", userId=" + userId +
                ", typeNewsId=" + typeNewsId +
                ", banned='" + banned + '\'' +
                ", deleted='" + deleted + '\'' +
                ", userLogin='" + userLogin + '\'' +
                ", comments=" + comments +
                ", commentQuantity=" + commentQuantity +
                '}';
    }
}