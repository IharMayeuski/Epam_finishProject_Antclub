package by.epam.club.entity;

import java.util.List;

public class CommentToArticle {
    private int id;
    private String name;
    private String text;
    private int positiveRating;
    private int negativeRating;
    private String dateRegistration;
    private int userId;
    private String typeNews;
    private String blocked;

    private List<Picture> pictures;

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

    public String getDateRegistration() {
        return dateRegistration;
    }

    public void setDateRegistration(String dateRegistration) {
        this.dateRegistration = dateRegistration;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof CommentToArticle)) return false;

        CommentToArticle that = (CommentToArticle) o;

        if (getId() != that.getId()) return false;
        if (getPositiveRating() != that.getPositiveRating()) return false;
        if (getNegativeRating() != that.getNegativeRating()) return false;
        if (getUserId() != that.getUserId()) return false;
        if (getName() != null ? !getName().equals(that.getName()) : that.getName() != null) return false;
        if (getText() != null ? !getText().equals(that.getText()) : that.getText() != null) return false;
        if (getDateRegistration() != null ? !getDateRegistration().equals(that.getDateRegistration()) : that.getDateRegistration() != null)
            return false;
        if (getTypeNews() != null ? !getTypeNews().equals(that.getTypeNews()) : that.getTypeNews() != null)
            return false;
        if (getBlocked() != null ? !getBlocked().equals(that.getBlocked()) : that.getBlocked() != null) return false;
        return getPictures() != null ? getPictures().equals(that.getPictures()) : that.getPictures() == null;
    }

    @Override
    public int hashCode() {
        int result = getId();
        result = 31 * result + (getName() != null ? getName().hashCode() : 0);
        result = 31 * result + (getText() != null ? getText().hashCode() : 0);
        result = 31 * result + getPositiveRating();
        result = 31 * result + getNegativeRating();
        result = 31 * result + (getDateRegistration() != null ? getDateRegistration().hashCode() : 0);
        result = 31 * result + getUserId();
        result = 31 * result + (getTypeNews() != null ? getTypeNews().hashCode() : 0);
        result = 31 * result + (getBlocked() != null ? getBlocked().hashCode() : 0);
        result = 31 * result + (getPictures() != null ? getPictures().hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("CommentToArticle{");
        sb.append("id=").append(id);
        sb.append(", name='").append(name).append('\'');
        sb.append(", text='").append(text).append('\'');
        sb.append(", positiveRating=").append(positiveRating);
        sb.append(", negativeRating=").append(negativeRating);
        sb.append(", dateRegistration='").append(dateRegistration).append('\'');
        sb.append(", userId=").append(userId);
        sb.append(", typeNews='").append(typeNews).append('\'');
        sb.append(", blocked='").append(blocked).append('\'');
        sb.append(", pictures=").append(pictures);
        sb.append('}');
        return sb.toString();
    }
}
