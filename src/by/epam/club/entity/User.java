package by.epam.club.entity;

public class User {
    private int id;
    private String login;
    private String email;
    private String date_registration;
    private int role;
    private int block;

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

    public int getRole() {
        return role;
    }

    public void setRole(int role) {
        this.role = role;
    }

    public int getBlock() {
        return block;
    }

    public void setBlock(int block) {
        this.block = block;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof User)) return false;

        User user = (User) o;

        if (getId() != user.getId()) return false;
        if (getRole() != user.getRole()) return false;
        if (getBlock() != user.getBlock()) return false;
        if (!getLogin().equals(user.getLogin())) return false;
        if (!getEmail().equals(user.getEmail())) return false;
        return getDate_registration().equals(user.getDate_registration());
    }

    @Override
    public int hashCode() {
        int result = getId();
        result = 31 * result + getLogin().hashCode();
        result = 31 * result + getEmail().hashCode();
        result = 31 * result + getDate_registration().hashCode();
        result = 31 * result + getRole();
        result = 31 * result + getBlock();
        return result;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("User{");
        sb.append("id=").append(id);
        sb.append(", login='").append(login).append('\'');
        sb.append(", email='").append(email).append('\'');
        sb.append(", date_registration='").append(date_registration).append('\'');
        sb.append(", role=").append(role);
        sb.append(", block=").append(block);
        sb.append('}');
        return sb.toString();
    }
}