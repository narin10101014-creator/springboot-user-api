package com.narin.api.user.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.GenerationType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Column;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;


import java.time.LocalDateTime;

@Entity
@Table(name = "users")
public class User {

    private Long id;
    private String username;
    private String password;
    private String email;
    private String role;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    @Column(name = "username" ,nullable = false, unique = true)
    public String getUsername() {
        return username;
    }
    public void setUsername(String username) {
        this.username = username;
    }
    @Column(name = "password", nullable = false)
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    @Column(name = "email", unique = true)
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    @Column(name = "role")
    public String getRole() {
        return role;
    }
    public void setRole(String role) {
        this.role = role;
    }

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    @PrePersist
    public void onCreate() {
        createdAt = LocalDateTime.now();
    }
    @PreUpdate
    public void onUpdate() {
        updatedAt = LocalDateTime.now();
    }

}
