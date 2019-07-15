package by.epam.club.entity;

import java.sql.Blob;
import java.util.Objects;
/**
 *Class of Entity for working
 *
 * @author Maeuski Igor
 * @version 1.0
 */

public class User extends Entity {
    private long id;
    private String login;
    private String email;
    private String date_registration;
    private String date_activity;

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

    public String getDate_activity() {
        return date_activity;
    }

    public void setDate_activity(String date_activity) {
        this.date_activity = date_activity;
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

        if (id != user.id) return false;
        if (login != null ? !login.equals(user.login) : user.login != null) return false;
        if (email != null ? !email.equals(user.email) : user.email != null) return false;
        if (date_registration != null ? !date_registration.equals(user.date_registration) : user.date_registration != null)
            return false;
        if (date_activity != null ? !date_activity.equals(user.date_activity) : user.date_activity != null)
            return false;
        if (banned != null ? !banned.equals(user.banned) : user.banned != null) return false;
        if (deleted != null ? !deleted.equals(user.deleted) : user.deleted != null) return false;
        if (role != null ? !role.equals(user.role) : user.role != null) return false;
        if (firstname != null ? !firstname.equals(user.firstname) : user.firstname != null) return false;
        if (familyname != null ? !familyname.equals(user.familyname) : user.familyname != null) return false;
        return blob != null ? blob.equals(user.blob) : user.blob == null;
    }

    @Override
    public int hashCode() {
        int result = (int) (id ^ (id >>> 32));
        result = 31 * result + (login != null ? login.hashCode() : 0);
        result = 31 * result + (email != null ? email.hashCode() : 0);
        result = 31 * result + (date_registration != null ? date_registration.hashCode() : 0);
        result = 31 * result + (date_activity != null ? date_activity.hashCode() : 0);
        result = 31 * result + (banned != null ? banned.hashCode() : 0);
        result = 31 * result + (deleted != null ? deleted.hashCode() : 0);
        result = 31 * result + (role != null ? role.hashCode() : 0);
        result = 31 * result + (firstname != null ? firstname.hashCode() : 0);
        result = 31 * result + (familyname != null ? familyname.hashCode() : 0);
        result = 31 * result + (blob != null ? blob.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", login='" + login + '\'' +
                ", email='" + email + '\'' +
                ", date_registration='" + date_registration + '\'' +
                ", date_activity='" + date_activity + '\'' +
                ", banned='" + banned + '\'' +
                ", deleted='" + deleted + '\'' +
                ", role='" + role + '\'' +
                ", firstname='" + firstname + '\'' +
                ", familyname='" + familyname + '\'' +
                ", blob=" + blob +
                '}';
    }
}