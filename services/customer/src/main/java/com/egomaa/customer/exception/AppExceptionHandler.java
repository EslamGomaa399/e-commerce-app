package com.egomaa.customer.exception;

import com.egomaa.customer.dto.EmailAlreadyExistsException;
import com.egomaa.customer.dto.ExceptionResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.ArrayList;
import java.util.List;

@ControllerAdvice
public class AppExceptionHandler {

    @ExceptionHandler(CustomerNotFoundException.class)
    public ResponseEntity<?> handleCustomerNotFoundException(CustomerNotFoundException ex){

        ExceptionResponse exceptionResponse = new ExceptionResponse();
        List<String> errorList = new ArrayList<String>();
        errorList.add(ex.getMessage());

        exceptionResponse.setStatusCode(HttpStatus.NOT_FOUND.value());
        exceptionResponse.setMessage(errorList);

        return new ResponseEntity<>(exceptionResponse,HttpStatus.NOT_FOUND);
    }


    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<?> handleValidationExceptions(MethodArgumentNotValidException ex){

        ExceptionResponse exceptionResponse = new ExceptionResponse();
        List<String> errorList = new ArrayList<String>();

        ex.getBindingResult().getAllErrors()
                .forEach(error -> errorList.add(error.getDefaultMessage()));

        exceptionResponse.setStatusCode(HttpStatus.BAD_REQUEST.value());
        exceptionResponse.setMessage(errorList);

        return new ResponseEntity<>(exceptionResponse, HttpStatus.BAD_REQUEST);
    }


    @ExceptionHandler(EmailAlreadyExistsException.class)
    public ResponseEntity<?> EmailAlreadyExistsException(EmailAlreadyExistsException ex){

        ExceptionResponse exceptionResponse = new ExceptionResponse();
        List<String> errorList = new ArrayList<>();

        errorList.add(ex.getMessage());

        exceptionResponse.setStatusCode(HttpStatus.BAD_REQUEST.value());
        exceptionResponse.setMessage(errorList);

        return new ResponseEntity<>(exceptionResponse, HttpStatus.BAD_REQUEST);
    }

}
