package by.epam.club.entity;

/**
 *Class of Entity for working
 *
 * @author Maeuski Igor
 * @version 1.0
 */

public class Picture extends Entity {
    private long id;
    private String name;
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
        if (o == null || getClass() != o.getClass()) return false;

        Picture picture = (Picture) o;

        if (id != picture.id) return false;
        if (article_id != picture.article_id) return false;
        if (name != null ? !name.equals(picture.name) : picture.name != null) return false;
        if (banned != null ? !banned.equals(picture.banned) : picture.banned != null) return false;
        return deleted != null ? deleted.equals(picture.deleted) : picture.deleted == null;
    }

    @Override
    public int hashCode() {
        int result = (int) (id ^ (id >>> 32));
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (banned != null ? banned.hashCode() : 0);
        result = 31 * result + (deleted != null ? deleted.hashCode() : 0);
        result = 31 * result + (int) (article_id ^ (article_id >>> 32));
        return result;
    }

    @Override
    public String toString() {
        return "Picture{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", banned='" + banned + '\'' +
                ", deleted='" + deleted + '\'' +
                ", article_id=" + article_id +
                '}';
    }
}