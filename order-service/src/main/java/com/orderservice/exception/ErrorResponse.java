package com.orderservice.exception;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ErrorResponse {

    private String message;

    private String statusDetail;

    private int statusCode;

    private LocalDateTime timeStamp;

    public ErrorResponse(String message, String statusDetail, int statusCode) {
        this.message = message;
        this.statusCode = statusCode;
        this.timeStamp = LocalDateTime.now();
        this.statusDetail = statusDetail;
    }

}