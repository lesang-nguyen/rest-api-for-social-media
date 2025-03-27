package com.example.rest.webservices.restfulapiforsocialmedia.entity;

import java.time.LocalDateTime;

public class ErrorDetails {
    private final String message;
    private final String details;
    private final LocalDateTime timestamp;

    public ErrorDetails(LocalDateTime timestamp, String details, String message) {
        super();
        this.message = message;
        this.details = details;
        this.timestamp = timestamp;
    }

    public String getMessage() {
        return message;
    }

    public String getDetails() {
        return details;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }
}
