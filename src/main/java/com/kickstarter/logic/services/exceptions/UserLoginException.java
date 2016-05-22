package com.kickstarter.logic.services.exceptions;

public class UserLoginException extends Exception {

    public UserLoginException(){
        super("Wrong user name or password");
    }
}
