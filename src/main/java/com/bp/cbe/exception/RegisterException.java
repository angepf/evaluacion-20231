package com.bp.cbe.exception;


import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class RegisterException extends RuntimeException {

    public RegisterException(String message) {
        super( String.format( message ) );

    }

}