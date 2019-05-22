package by.epam.club.entity;

import java.sql.Blob;
import java.util.List;

public class Article {
    private int id;
    private String name;
    private String text;
    private int positiveRating;
    private int negativeRating;
    private String date_registration;
    private int userId;
    private String typeNews;
    private String blocked;

    private List<Picture> pictures;
    private List<CommentToArticle> comments;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getTypeNews() {
        return typeNews;
    }

    public void setTypeNews(String typeNews) {
        this.typeNews = typeNews;
    }

    public String getBlocked() {
        return blocked;
    }

    public void setBlocked(String blocked) {
        this.blocked = blocked;
    }

    public List<Picture> getPictures() {
        return pictures;
    }

    public void setPictures(List<Picture> pictures) {
        this.pictures = pictures;
    }

    public List<CommentToArticle> getComments() {
        return comments;
    }

    public void setComments(List<CommentToArticle> comments) {
        this.comments = comments;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Article)) return false;

        Article article = (Article) o;

        if (getId() != article.getId()) return false;
        if (getPositiveRating() != article.getPositiveRating()) return false;
        if (getNegativeRating() != article.getNegativeRating()) return false;
        if (getUserId() != article.getUserId()) return false;
        if (getName() != null ? !getName().equals(article.getName()) : article.getName() != null) return false;
        if (getText() != null ? !getText().equals(article.getText()) : article.getText() != null) return false;
        if (getDate_registration() != null ? !getDate_registration().equals(article.getDate_registration()) : article.getDate_registration() != null)
            return false;
        if (getTypeNews() != null ? !getTypeNews().equals(article.getTypeNews()) : article.getTypeNews() != null)
            return false;
        if (getBlocked() != null ? !getBlocked().equals(article.getBlocked()) : article.getBlocked() != null)
            return false;
        if (getPictures() != null ? !getPictures().equals(article.getPictures()) : article.getPictures() != null)
            return false;
        return getComments() != null ? getComments().equals(article.getComments()) : article.getComments() == null;
    }

    @Override
    public int hashCode() {
        int result = getId();
        result = 31 * result + (getName() != null ? getName().hashCode() : 0);
        result = 31 * result + (getText() != null ? getText().hashCode() : 0);
        result = 31 * result + getPositiveRating();
        result = 31 * result + getNegativeRating();
        result = 31 * result + (getDate_registration() != null ? getDate_registration().hashCode() : 0);
        result = 31 * result + getUserId();
        result = 31 * result + (getTypeNews() != null ? getTypeNews().hashCode() : 0);
        result = 31 * result + (getBlocked() != null ? getBlocked().hashCode() : 0);
        result = 31 * result + (getPictures() != null ? getPictures().hashCode() : 0);
        result = 31 * result + (getComments() != null ? getComments().hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Article{");
        sb.append("id=").append(id);
        sb.append(", name='").append(name).append('\'');
        sb.append(", text='").append(text).append('\'');
        sb.append(", positiveRating=").append(positiveRating);
        sb.append(", negativeRating=").append(negativeRating);
        sb.append(", date_registration='").append(date_registration).append('\'');
        sb.append(", userId=").append(userId);
        sb.append(", typeNews='").append(typeNews).append('\'');
        sb.append(", blocked='").append(blocked).append('\'');
        sb.append(", pictures=").append(pictures);
        sb.append(", comments=").append(comments);
        sb.append('}');
        return sb.toString();
    }
}