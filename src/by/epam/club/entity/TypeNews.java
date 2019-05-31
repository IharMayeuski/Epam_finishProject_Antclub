package by.epam.club.entity;

import java.util.Objects;

public class TypeNews {
    int id;
    String typeNews;
    String deleted;



    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TypeNews typeNews1 = (TypeNews) o;
        return id == typeNews1.id &&
                Objects.equals(typeNews, typeNews1.typeNews) &&
                Objects.equals(deleted, typeNews1.deleted);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, typeNews, deleted);
    }

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
    @Override
    public String toString() {
        return "TypeNews{" +
                "id=" + id +
                ", typeNews='" + typeNews + '\'' +
                ", deleted='" + deleted + '\'' +
                '}';
    }
}
