package com.egomaa.order.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.ArrayList;
import java.util.List;

@ControllerAdvice
public class AppExceptionHandler {

    @ExceptionHandler(NotInStockException.class)
    public ResponseEntity<?> handleCustomerNotFoundException(NotInStockException ex){

        ExceptionResponse exceptionResponse = new ExceptionResponse();
        List<String> errorList = new ArrayList<String>();
        errorList.add(ex.getMessage());

        exceptionResponse.setStatusCode(HttpStatus.BAD_REQUEST.value());
        exceptionResponse.setMessage(errorList);

        return new ResponseEntity<>(exceptionResponse,HttpStatus.BAD_REQUEST);
    }



}
