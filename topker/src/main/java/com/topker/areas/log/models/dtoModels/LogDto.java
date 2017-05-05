package com.topker.areas.log.models.dtoModels;


import java.time.LocalDateTime;

public class LogDto {

    private String message;

    private LocalDateTime date;

    public LogDto(String message) {
        this.message = message;
        this.date = LocalDateTime.now();
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }
}
