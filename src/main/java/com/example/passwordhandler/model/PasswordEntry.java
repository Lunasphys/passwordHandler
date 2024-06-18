package com.example.passwordhandler.model;

import javax.persistence.*;

@Entity
public class PasswordEntry {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String service;
    private String username;
    private String password;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    private void setId(Long id) {
        this.id = id;
    }
    private Long getId() {
        return id;
    }
    private void setService(String service) {
        this.service = service;
    }
    private String getService() {
        return service;
    }

    private void setUsername(String username) {
        this.username = username;
    }
    private String getUsername() {
        return username;
    }

    private void setPassword(String password) {
        this.password = password;
    }
    private String getPassword() {
        return password;
    }
}