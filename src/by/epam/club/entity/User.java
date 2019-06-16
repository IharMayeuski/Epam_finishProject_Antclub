package by.epam.club.entity;



import java.sql.Blob;
import java.util.Objects;

public class User extends Entity  {
    private long id;
    private String login;
    private String email;
    private String date_registration;

    private String banned;
    private String deleted;
    private String role;

    private String firstname;
    private String familyname;
    private Blob blob;

    public long getId() {
        return id;
    }

    public void setId(long id) {
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

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getFamilyname() {
        return familyname;
    }

    public void setFamilyname(String familyname) {
        this.familyname = familyname;
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
        User user = (User) o;
        return id == user.id &&
                Objects.equals(login, user.login) &&
                Objects.equals(email, user.email) &&
                Objects.equals(date_registration, user.date_registration) &&
                Objects.equals(banned, user.banned) &&
                Objects.equals(deleted, user.deleted) &&
                Objects.equals(role, user.role) &&
                Objects.equals(firstname, user.firstname) &&
                Objects.equals(familyname, user.familyname) &&
                Objects.equals(blob, user.blob);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, login, email, date_registration, banned, deleted, role, firstname, familyname, blob);
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", login='" + login + '\'' +
                ", email='" + email + '\'' +
                ", date_registration='" + date_registration + '\'' +
                ", banned='" + banned + '\'' +
                ", deleted='" + deleted + '\'' +
                ", role='" + role + '\'' +
                ", firstname='" + firstname + '\'' +
                ", familyname='" + familyname + '\'' +
                ", blob=" + blob +
                '}';
    }
}