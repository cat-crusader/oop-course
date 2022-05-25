package com.example.oop.entities;

public class DispatcherEntity {
    private final Long id;
    private final String username;

    public DispatcherEntity(Long id, String username) {
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
