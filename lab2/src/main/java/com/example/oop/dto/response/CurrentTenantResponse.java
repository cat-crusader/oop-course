package com.example.oop.dto.response;

public class CurrentTenantResponse {
    private final Long id;
    private final String username;

    public CurrentTenantResponse(Long id, String username) {
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
