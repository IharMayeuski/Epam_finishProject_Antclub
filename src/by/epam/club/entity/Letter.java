package by.epam.club.entity;

import java.util.List;

public class Letter {
    private int id;
    private int fromUser;
    private int toUser;
    private String name;
    private String text;
    private String blocked;

    private List<Picture> pictures;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getFromUser() {
        return fromUser;
    }

    public void setFromUser(int fromUser) {
        this.fromUser = fromUser;
    }

    public int getToUser() {
        return toUser;
    }

    public void setToUser(int toUser) {
        this.toUser = toUser;
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
        if (!(o instanceof Letter)) return false;

        Letter letter = (Letter) o;

        if (getId() != letter.getId()) return false;
        if (getFromUser() != letter.getFromUser()) return false;
        if (getToUser() != letter.getToUser()) return false;
        if (getName() != null ? !getName().equals(letter.getName()) : letter.getName() != null) return false;
        if (getText() != null ? !getText().equals(letter.getText()) : letter.getText() != null) return false;
        if (getBlocked() != null ? !getBlocked().equals(letter.getBlocked()) : letter.getBlocked() != null)
            return false;
        return getPictures() != null ? getPictures().equals(letter.getPictures()) : letter.getPictures() == null;
    }

    @Override
    public int hashCode() {
        int result = getId();
        result = 31 * result + getFromUser();
        result = 31 * result + getToUser();
        result = 31 * result + (getName() != null ? getName().hashCode() : 0);
        result = 31 * result + (getText() != null ? getText().hashCode() : 0);
        result = 31 * result + (getBlocked() != null ? getBlocked().hashCode() : 0);
        result = 31 * result + (getPictures() != null ? getPictures().hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Letter{");
        sb.append("id=").append(id);
        sb.append(", fromUser=").append(fromUser);
        sb.append(", toUser=").append(toUser);
        sb.append(", name='").append(name).append('\'');
        sb.append(", text='").append(text).append('\'');
        sb.append(", blocked='").append(blocked).append('\'');
        sb.append(", pictures=").append(pictures);
        sb.append('}');
        return sb.toString();
    }
}
