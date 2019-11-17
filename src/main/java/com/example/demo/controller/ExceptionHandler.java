package com.example.demo.controller;

import com.example.demo.exception.EmailExistException;
import com.example.demo.exception.SubscriptionException;
import com.example.demo.exception.UserNotFoundException;
import com.example.demo.exception.WrongPasswordException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.sql.SQLException;

@ControllerAdvice
@ResponseBody
public class ExceptionHandler {

    @ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR, reason = "This email is already in use!")
    @org.springframework.web.bind.annotation.ExceptionHandler(EmailExistException.class)
    public void catchException(EmailExistException ex) {
        System.out.println(ex.getLocalizedMessage());
    }


    @ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR, reason = "The uploading file option is only for subscribers!")
    @org.springframework.web.bind.annotation.ExceptionHandler(SubscriptionException.class)
    public void catchException(SubscriptionException ex) {
        System.out.println(ex.getLocalizedMessage());
    }

    @ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR, reason = "Database exception")
    @org.springframework.web.bind.annotation.ExceptionHandler(SQLException.class)
    public void catchException(SQLException ex) {
        System.out.println("Database exception \n" + ex.getLocalizedMessage());
    }

    @ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR, reason = "Internal error, see in logs!!!")
    @org.springframework.web.bind.annotation.ExceptionHandler(Exception.class)
    public void catchException(Exception ex) {
        System.out.println("Something wrong:  \n" + ex.getLocalizedMessage());
    }

    @ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR, reason = "Unregistered user!")
    @org.springframework.web.bind.annotation.ExceptionHandler(UserNotFoundException.class)
    public void catchException(UserNotFoundException ex) {
        System.out.println("Database exception \n" + ex.getLocalizedMessage());
    }

    @ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR, reason = "Wrong password!")
    @org.springframework.web.bind.annotation.ExceptionHandler(WrongPasswordException.class)
    public void catchException(WrongPasswordException ex) {
        System.out.println("Something wrong:  \n" + ex.getLocalizedMessage());
    }


}
