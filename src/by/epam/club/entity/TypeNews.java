package by.epam.club.entity;

import java.sql.Blob;
import java.util.Objects;

public class TypeNews extends Entity {
    private int id;
    private String typeNews;
    private String deleted;
    private Blob blob;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTypeNews() {
        return typeNews;
    }

    public void setTypeNews(String typeNews) {
        this.typeNews = typeNews;
    }

    public String getDeleted() {
        return deleted;
    }

    public void setDeleted(String deleted) {
        this.deleted = deleted;
    }

    public Blob getBlob() {
        return blob;
    }

    public void setBlob(Blob blob) {
        this.blob = blob;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        TypeNews typeNews1 = (TypeNews) o;

        if (id != typeNews1.id) return false;
        if (typeNews != null ? !typeNews.equals(typeNews1.typeNews) : typeNews1.typeNews != null) return false;
        if (deleted != null ? !deleted.equals(typeNews1.deleted) : typeNews1.deleted != null) return false;
        return blob != null ? blob.equals(typeNews1.blob) : typeNews1.blob == null;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (typeNews != null ? typeNews.hashCode() : 0);
        result = 31 * result + (deleted != null ? deleted.hashCode() : 0);
        result = 31 * result + (blob != null ? blob.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "TypeNews{" +
                "id=" + id +
                ", typeNews='" + typeNews + '\'' +
                ", deleted='" + deleted + '\'' +
                ", blob=" + blob +
                '}';
    }
}
