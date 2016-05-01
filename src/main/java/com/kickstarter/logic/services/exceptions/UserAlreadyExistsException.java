package com.kickstarter.logic.services.exceptions;

public class UserAlreadyExistsException extends Exception {

    public UserAlreadyExistsException(String userName){
        super(String.format("User with name {0} already exists", userName));
    }
}
