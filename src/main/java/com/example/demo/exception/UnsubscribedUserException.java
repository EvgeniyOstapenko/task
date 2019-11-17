package com.example.demo.exception;

public class UnsubscribedUserException extends RuntimeException{

    public UnsubscribedUserException(String message) {
        super(message);
    }
}
