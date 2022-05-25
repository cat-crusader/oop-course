package com.example.oop.entities;

public class ApplicationEntity {
    private final Long id;
    private final String type;
    private final Long scale;
    private final Long durationInDays;

    private final Boolean isAccepted;

    public ApplicationEntity(Long id, String type, Long scale, Long durationInDays, Boolean isAccepted) {
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
