package by.epam.club.entity;

import java.sql.Blob;
import java.util.List;

public class Article {
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Article)) return false;

        Article article = (Article) o;

        if (getId() != article.getId()) return false;
        if (getPositiveRating() != article.getPositiveRating()) return false;
        if (getNegativeRating() != article.getNegativeRating()) return false;
        if (getUserId() != article.getUserId()) return false;
        if (getTypeNewsId() != article.getTypeNewsId()) return false;
        if (getTitle() != null ? !getTitle().equals(article.getTitle()) : article.getTitle() != null) return false;
        if (getText() != null ? !getText().equals(article.getText()) : article.getText() != null) return false;
        if (!getDate_registration().equals(article.getDate_registration())) return false;
        if (getBanned() != null ? !getBanned().equals(article.getBanned()) : article.getBanned() != null) return false;
        return getDeleted() != null ? getDeleted().equals(article.getDeleted()) : article.getDeleted() == null;
    }

    @Override
    public int hashCode() {
        int result = (int) (getId() ^ (getId() >>> 32));
        result = 31 * result + (getTitle() != null ? getTitle().hashCode() : 0);
        result = 31 * result + (getText() != null ? getText().hashCode() : 0);
        result = 31 * result + getPositiveRating();
        result = 31 * result + getNegativeRating();
        result = 31 * result + getDate_registration().hashCode();
        result = 31 * result + (int) (getUserId() ^ (getUserId() >>> 32));
        result = 31 * result + getTypeNewsId();
        result = 31 * result + (getBanned() != null ? getBanned().hashCode() : 0);
        result = 31 * result + (getDeleted() != null ? getDeleted().hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Article{");
        sb.append("id=").append(id);
        sb.append(", title='").append(title).append('\'');
        sb.append(", text='").append(text).append('\'');
        sb.append(", positiveRating=").append(positiveRating);
        sb.append(", negativeRating=").append(negativeRating);
        sb.append(", date_registration='").append(date_registration).append('\'');
        sb.append(", userId=").append(userId);
        sb.append(", typeNewsId=").append(typeNewsId);
        sb.append(", banned='").append(banned).append('\'');
        sb.append(", deleted='").append(deleted).append('\'');
        sb.append('}');
        return sb.toString();
    }
}

