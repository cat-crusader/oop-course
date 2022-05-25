package com.example.oop.dto.response;

public class CurrentDispatcherResponse {
    private final Long id;
    private final String username;

    public CurrentDispatcherResponse(Long id, String username) {
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
