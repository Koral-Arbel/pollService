package com.firstProject.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class User {
    private Long userId;
    private String firstName;
    private String lastName;
    private String email;
    private Integer age;
    private String address;
    private boolean registered;
    private LocalDateTime joinDate;

    public User() {
    }

    public User(Long userId, String firstName, String lastName, String email, Integer age, String address, boolean registered, LocalDateTime joinDate) {
        this.userId = userId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.age = age;
        this.address = address;
        this.registered = registered;
        this.joinDate = joinDate;
    }

    public Long getUserId() {
        return userId;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getEmail() {
        return email;
    }

    public Integer getAge() {
        return age;
    }

    public String getAddress() {
        return address;
    }

    public boolean isRegistered() {
        return registered;
    }

    public LocalDateTime getJoinDate() {
        return joinDate;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setRegistered(boolean registered) {
        this.registered = registered;
    }

    public void setJoinDate(LocalDateTime joinDate) {
        this.joinDate = joinDate;
    }
}