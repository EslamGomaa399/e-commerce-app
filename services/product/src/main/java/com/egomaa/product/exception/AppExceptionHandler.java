package com.egomaa.product.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.ArrayList;
import java.util.List;


@ControllerAdvice
public class AppExceptionHandler {

    private ExceptionResponse exceptionResponse = new ExceptionResponse();
    private List<String> errorList = new ArrayList<>();
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<?> handleValidationExceptions(MethodArgumentNotValidException ex){

        ex.getBindingResult().getAllErrors()
                .forEach(error -> errorList.add(error.getDefaultMessage()));

        exceptionResponse.setStatusCode(HttpStatus.BAD_REQUEST.value());
        exceptionResponse.setMessage(errorList);

        return new ResponseEntity<>(exceptionResponse, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<?> EmailAlreadyExistsException(ResourceNotFoundException ex){

        errorList.add(ex.getMessage());

        exceptionResponse.setStatusCode(HttpStatus.BAD_REQUEST.value());
        exceptionResponse.setMessage(errorList);

        return new ResponseEntity<>(exceptionResponse, HttpStatus.BAD_REQUEST);
    }

    //DuplicateElement
    @ExceptionHandler(DuplicateElement.class)
    public ResponseEntity<?> handleDuplicateElement(DuplicateElement ex){

        errorList.add(ex.getMessage());

        exceptionResponse.setStatusCode(HttpStatus.BAD_REQUEST.value());
        exceptionResponse.setMessage(errorList);

        return new ResponseEntity<>(exceptionResponse, HttpStatus.BAD_REQUEST);
    }



}
