package com.egomaa.order.exception;

public class NotInStockException extends RuntimeException{

    public NotInStockException(String message) {
        super(message);
    }


}
