package by.epam.club.entity;

public class User {
    private int id;
    private String login;
    private String email;
    private String date_registration;
    private String role;
    private String block;
    private String deleted;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getDate_registration() {
        return date_registration;
    }

    public void setDate_registration(String date_registration) {
        this.date_registration = date_registration;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getBlock() {
        return block;
    }

    public void setBlock(String block) {
        this.block = block;
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
        if (!(o instanceof User)) return false;

        User user = (User) o;

        if (getId() != user.getId()) return false;
        if (!getLogin().equals(user.getLogin())) return false;
        if (!getEmail().equals(user.getEmail())) return false;
        if (!getDate_registration().equals(user.getDate_registration())) return false;
        if (getRole() != null ? !getRole().equals(user.getRole()) : user.getRole() != null) return false;
        if (getBlock() != null ? !getBlock().equals(user.getBlock()) : user.getBlock() != null) return false;
        return getDeleted() != null ? getDeleted().equals(user.getDeleted()) : user.getDeleted() == null;
    }

    @Override
    public int hashCode() {
        int result = getId();
        result = 31 * result + getLogin().hashCode();
        result = 31 * result + getEmail().hashCode();
        result = 31 * result + getDate_registration().hashCode();
        result = 31 * result + (getRole() != null ? getRole().hashCode() : 0);
        result = 31 * result + (getBlock() != null ? getBlock().hashCode() : 0);
        result = 31 * result + (getDeleted() != null ? getDeleted().hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("User{");
        sb.append("id=").append(id);
        sb.append(", login='").append(login).append('\'');
        sb.append(", email='").append(email).append('\'');
        sb.append(", date_registration='").append(date_registration).append('\'');
        sb.append(", role='").append(role).append('\'');
        sb.append(", block='").append(block).append('\'');
        sb.append(", deleted='").append(deleted).append('\'');
        sb.append('}');
        return sb.toString();
    }
}