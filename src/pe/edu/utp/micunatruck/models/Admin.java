package pe.edu.utp.micunatruck.models;

import java.util.Date;

public class Admin {


    private int id;
    private String name;
    private String lastName;
    private String photo;
    private String email;
    private String password;
    private boolean flag_active;
    private Date update_at;
    private Date deleted_at;
    private Date created_at;

    public Admin() {
    }

    public Admin(int id, String name, String lastName, String photo, String email, String password, boolean flag_active, Date update_at, Date deleted_at, Date created_at) {
        this.id = id;
        this.name = name;
        this.lastName = lastName;
        this.photo = photo;
        this.email = email;
        this.password = password;
        this.flag_active = flag_active;
        this.update_at = update_at;
        this.deleted_at = deleted_at;
        this.created_at = created_at;
    }

    public int getId() {
        return id;
    }

    public Admin setId(int id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public Admin setName(String name) {
        this.name = name;
        return this;
    }

    public String getLastName() {
        return lastName;
    }

    public Admin setLastName(String lastName) {
        this.lastName = lastName;
        return this;
    }

    public String getPhoto() {
        return photo;
    }

    public Admin setPhoto(String photo) {
        this.photo = photo;
        return this;
    }

    public String getEmail() {
        return email;
    }

    public Admin setEmail(String email) {
        this.email = email;
        return this;
    }

    public String getPassword() {
        return password;
    }

    public Admin setPassword(String password) {
        this.password = password;
        return this;
    }

    public boolean getFlag_active() {
        return flag_active;
    }

    public Admin setFlag_active(boolean flag_active) {
        this.flag_active = flag_active;
        return this;
    }

    public Date getUpdate_at() {
        return update_at;
    }

    public Admin setUpdate_at(Date update_at) {
        this.update_at = update_at;
        return this;
    }

    public Date getDeleted_at() {
        return deleted_at;
    }

    public Admin setDeleted_at(Date deleted_at) {
        this.deleted_at = deleted_at;
        return this;
    }

    public Date getCreated_at() {
        return created_at;
    }

    public Admin setCreated_at(Date created_at) {
        this.created_at = created_at;
        return this;
    }
}
