package com.example.spring.exception;

public class ApiException extends Exception {
    public ApiException(String message){
        super(message);
    }

}
