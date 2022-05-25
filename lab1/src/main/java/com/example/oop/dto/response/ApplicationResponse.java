package com.example.oop.dto.response;

public class ApplicationResponse {
    private final Long id;
    private final String type;
    private final Long scale;
    private final Long durationInDays;
    private final Boolean isAccepted;

    public ApplicationResponse(Long id, String type, Long scale, Long durationInDays, Boolean isAccepted) {
        this.id = id;
        this.type = type;
        this.scale = scale;
        this.durationInDays = durationInDays;
        this.isAccepted = isAccepted;
    }

    public Long getId() {
        return id;
    }

    public String getType() {
        return type;
    }

    public Long getScale() {
        return scale;
    }

    public Long getDurationInDays() {
        return durationInDays;
    }

    public Boolean getAccepted() {
        return isAccepted;
    }
}
