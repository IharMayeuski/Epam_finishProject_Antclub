package by.epam.club.entity;

import javax.servlet.http.Part;
import java.sql.Blob;

public class Picture {
    private long id;
    private String name;
    private Blob blob;

    private String banned;
    private String deleted;
    private long article_id;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Blob getBlob() {
        return blob;
    }

    public void setBlob(Blob blob) {
        this.blob = blob;
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

    public long getArticle_id() {
        return article_id;
    }

    public void setArticle_id(long article_id) {
        this.article_id = article_id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Picture)) return false;

        Picture picture = (Picture) o;

        if (getId() != picture.getId()) return false;
        if (getArticle_id() != picture.getArticle_id()) return false;
        if (getName() != null ? !getName().equals(picture.getName()) : picture.getName() != null) return false;
        if (!getBlob().equals(picture.getBlob())) return false;
        if (getBanned() != null ? !getBanned().equals(picture.getBanned()) : picture.getBanned() != null) return false;
        return getDeleted() != null ? getDeleted().equals(picture.getDeleted()) : picture.getDeleted() == null;
    }

    @Override
    public int hashCode() {
        int result = (int) (getId() ^ (getId() >>> 32));
        result = 31 * result + (getName() != null ? getName().hashCode() : 0);
        result = 31 * result + getBlob().hashCode();
        result = 31 * result + (getBanned() != null ? getBanned().hashCode() : 0);
        result = 31 * result + (getDeleted() != null ? getDeleted().hashCode() : 0);
        result = 31 * result + (int) (getArticle_id() ^ (getArticle_id() >>> 32));
        return result;
    }
}