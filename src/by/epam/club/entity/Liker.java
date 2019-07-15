package by.epam.club.entity;
/**
 *Class of Entity for working
 *
 * @author Maeuski Igor
 * @version 1.0
 */
public class Liker extends Entity {
    private long id;
    private long userId;
    private long articlesId;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public long getArticlesId() {
        return articlesId;
    }

    public void setArticlesId(long articlesId) {
        this.articlesId = articlesId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Liker liker = (Liker) o;

        if (id != liker.id) return false;
        if (userId != liker.userId) return false;
        return articlesId == liker.articlesId;
    }

    @Override
    public int hashCode() {
        int result = (int) (id ^ (id >>> 32));
        result = 31 * result + (int) (userId ^ (userId >>> 32));
        result = 31 * result + (int) (articlesId ^ (articlesId >>> 32));
        return result;
    }

}