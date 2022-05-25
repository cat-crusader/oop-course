package com.example.oop.dto.request;

public class ApplicationRequest {
    private String type;
    private Long scale;
    private Long durationInDays;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Long getScale() {
        return scale;
    }

    public void setScale(Long scale) {
        this.scale = scale;
    }

    public Long getDurationInDays() {
        return durationInDays;
    }

    public void setDurationInDays(Long durationInDays) {
        this.durationInDays = durationInDays;
    }
}
