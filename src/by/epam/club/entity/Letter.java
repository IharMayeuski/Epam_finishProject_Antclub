package by.epam.club.entity;
/**
 *Class of Entity for working
 *
 * @author Maeuski Igor
 * @version 1.0
 */
public class Letter extends Entity  {
    private long id;
    private String title;
    private String text;
    private String banned;
    private String deleted;
    private String date;
    private String fromUser;
    private String toUser;

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

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getFromUser() {
        return fromUser;
    }

    public void setFromUser(String fromUser) {
        this.fromUser = fromUser;
    }

    public String getToUser() {
        return toUser;
    }

    public void setToUser(String toUser) {
        this.toUser = toUser;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Letter letter = (Letter) o;

        if (id != letter.id) return false;
        if (title != null ? !title.equals(letter.title) : letter.title != null) return false;
        if (text != null ? !text.equals(letter.text) : letter.text != null) return false;
        if (banned != null ? !banned.equals(letter.banned) : letter.banned != null) return false;
        if (deleted != null ? !deleted.equals(letter.deleted) : letter.deleted != null) return false;
        if (date != null ? !date.equals(letter.date) : letter.date != null) return false;
        if (fromUser != null ? !fromUser.equals(letter.fromUser) : letter.fromUser != null) return false;
        return toUser != null ? toUser.equals(letter.toUser) : letter.toUser == null;
    }

    @Override
    public int hashCode() {
        int result = (int) (id ^ (id >>> 32));
        result = 31 * result + (title != null ? title.hashCode() : 0);
        result = 31 * result + (text != null ? text.hashCode() : 0);
        result = 31 * result + (banned != null ? banned.hashCode() : 0);
        result = 31 * result + (deleted != null ? deleted.hashCode() : 0);
        result = 31 * result + (date != null ? date.hashCode() : 0);
        result = 31 * result + (fromUser != null ? fromUser.hashCode() : 0);
        result = 31 * result + (toUser != null ? toUser.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Letter{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", text='" + text + '\'' +
                ", banned='" + banned + '\'' +
                ", deleted='" + deleted + '\'' +
                ", date='" + date + '\'' +
                ", fromUser='" + fromUser + '\'' +
                ", toUser='" + toUser + '\'' +
                '}';
    }
}