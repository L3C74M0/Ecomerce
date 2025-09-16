package com.catalogservice.exception;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ErrorResponse {

    private String message;

    private String statusDetail;

    private int statusCode;

    private LocalDateTime timeStamp;

    private List<ValidationError> validationErrors;

    public ErrorResponse(String message, String statusDetail, int statusCode) {
        this.message = message;
        this.statusCode = statusCode;
        this.timeStamp = LocalDateTime.now();
        this.statusDetail = statusDetail;
        this.validationErrors = new ArrayList<>();
    }

}