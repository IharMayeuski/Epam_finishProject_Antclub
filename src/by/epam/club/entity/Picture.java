package by.epam.club.entity;

import javax.servlet.http.Part;

public class Picture {
    private int id;
    private String name;
    private Part part;
    private int articleId;
    private int commentId;
    private int letterId;
    private int userInfoId;
    private String blocked;

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

    public Part getPart() {
        return part;
    }

    public void setPart(Part part) {
        this.part = part;
    }

    public int getArticleId() {
        return articleId;
    }

    public void setArticleId(int articleId) {
        this.articleId = articleId;
    }

    public int getCommentId() {
        return commentId;
    }

    public void setCommentId(int commentId) {
        this.commentId = commentId;
    }

    public int getLetterId() {
        return letterId;
    }

    public void setLetterId(int letterId) {
        this.letterId = letterId;
    }

    public int getUserInfoId() {
        return userInfoId;
    }

    public void setUserInfoId(int userInfoId) {
        this.userInfoId = userInfoId;
    }

    public String getBlocked() {
        return blocked;
    }

    public void setBlocked(String blocked) {
        this.blocked = blocked;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Picture)) return false;

        Picture picture = (Picture) o;

        if (getId() != picture.getId()) return false;
        if (getArticleId() != picture.getArticleId()) return false;
        if (getCommentId() != picture.getCommentId()) return false;
        if (getLetterId() != picture.getLetterId()) return false;
        if (getUserInfoId() != picture.getUserInfoId()) return false;
        if (getName() != null ? !getName().equals(picture.getName()) : picture.getName() != null) return false;
        if (getPart() != null ? !getPart().equals(picture.getPart()) : picture.getPart() != null) return false;
        return getBlocked() != null ? getBlocked().equals(picture.getBlocked()) : picture.getBlocked() == null;
    }

    @Override
    public int hashCode() {
        int result = getId();
        result = 31 * result + (getName() != null ? getName().hashCode() : 0);
        result = 31 * result + (getPart() != null ? getPart().hashCode() : 0);
        result = 31 * result + getArticleId();
        result = 31 * result + getCommentId();
        result = 31 * result + getLetterId();
        result = 31 * result + getUserInfoId();
        result = 31 * result + (getBlocked() != null ? getBlocked().hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Picture{");
        sb.append("id=").append(id);
        sb.append(", name='").append(name).append('\'');
        sb.append(", part=").append(part);
        sb.append(", articleId=").append(articleId);
        sb.append(", commentId=").append(commentId);
        sb.append(", letterId=").append(letterId);
        sb.append(", userInfoId=").append(userInfoId);
        sb.append(", blocked='").append(blocked).append('\'');
        sb.append('}');
        return sb.toString();
    }
}