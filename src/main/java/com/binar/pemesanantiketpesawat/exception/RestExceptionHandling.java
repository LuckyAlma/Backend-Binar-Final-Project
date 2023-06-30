package com.binar.pemesanantiketpesawat.exception;

import com.binar.pemesanantiketpesawat.request.ErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class RestExceptionHandling {

    @ExceptionHandler
    public ResponseEntity<ErrorResponse> penumpangResponseResponseEntity(PassengerException exception) {
        // create a MovieErrorResponse

        ErrorResponse error = new ErrorResponse();


        error.setStatus(HttpStatus.NO_CONTENT.value());
        error.setMessage(exception.getMessage());
        error.setTimeStamp(System.currentTimeMillis());

        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
    }

}
