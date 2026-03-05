package com.example.kinoxp.model;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String username;
    private String passwords;
    private Roles roles;

    @ElementCollection(targetClass = Roles.class)
    @Enumerated(EnumType.STRING)
    @CollectionTable(name="users_roles")
    @Column(name="Roles")
    private List<Roles> rolesList;

    public enum Roles {Employee, Admin}

    public User(String username, String passwords, List<Roles> rolesList) {
        this.username = username;
        this.passwords = passwords;
        this.rolesList = rolesList;
    }

    public User() {}

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPasswords() {
        return passwords;
    }

    public void setPasswords(String passwords) {
        this.passwords = passwords;
    }

    public List<Roles> getRolesList() {
        return rolesList;
    }

    public void setRolesList(List<Roles> rolesList) {
        this.rolesList = rolesList;
    }
}
