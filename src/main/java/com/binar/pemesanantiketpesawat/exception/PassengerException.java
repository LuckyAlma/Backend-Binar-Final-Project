package com.binar.pemesanantiketpesawat.exception;

public class PassengerException extends RuntimeException{

    public PassengerException(String message) {
        super(message);
    }

    public PassengerException(String message, Throwable cause) {
        super(message, cause);
    }

    public PassengerException(Throwable cause) {
        super(cause);
    }
}
