package com.egomaa.order.exception;

import lombok.Data;

import java.util.List;

@Data
public class ExceptionResponse {
    private int statusCode;
    private List<String> message;
}
