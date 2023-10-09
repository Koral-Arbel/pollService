package com.firstProject.model;

import org.springframework.stereotype.Component;

@Component
public class User {
    private Long id;
    private Long userId;
    private String email;
    private boolean registered;
    public User(){}

    public User(Long id,Long userId, String email, boolean registered) {
        this.id = id;
        this.userId = userId;
        this.email = email;
        this.registered = registered;
    }

    public Long getId() {
        return id;
    }

    public Long getUserId() {
        return userId;
    }

    public String getEmail() {
        return email;
    }

    public boolean isRegistered() {
        return registered;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setRegistered(boolean registered) {
        this.registered = registered;
    }
}
