package com.example.oop.entities;

public class TenantEntity {
    private final Long id;
    private final String username;

    public TenantEntity(Long id, String username) {
        this.id = id;
        this.username = username;
    }

    public Long getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }
}
