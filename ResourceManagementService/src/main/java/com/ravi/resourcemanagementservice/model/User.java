package com.ravi.resourcemanagementservice.model;

import javax.persistence.*;

@Entity
@Table(name = "myuser")
public class User {

    @Id
    @Column(name = "id", unique = true)
//    @GeneratedValue(strategy = GenerationType.AUTO)
//    @GeneratedValue
    private int id;
    private String username;
    private String password;
    private boolean active;
    private String roles; //ROLES

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUserName() {
        return username;
    }

    public void setUserName(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public String getRoles() {
        return roles;
    }

    public void setRoles(String roles) {
        this.roles = roles;
    }
}
