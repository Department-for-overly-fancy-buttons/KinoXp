package com.example.kinoxp.user;

import jakarta.persistence.*;

@Entity
@Table(name = "users")
public class KinoUser {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false, unique = true)
    private String username;
    @Column(nullable = false, name = "\"password\"")
    private String password;
    @Enumerated(EnumType.STRING)
    @Column(name = "\"UserRole\"")
    private Role role;


    public KinoUser(String username, String password, Role role) {
        this.username = username;
        this.password = password;
        this.role = role;
    }

    public KinoUser() {
    }

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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }
}
