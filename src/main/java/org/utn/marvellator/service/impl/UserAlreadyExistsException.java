package org.utn.marvellator.service.impl;

public class UserAlreadyExistsException extends RuntimeException{
    private final String message;

    public UserAlreadyExistsException(String username) {
        this.message = "User with username: " + username + " already exists!";
    }

    @Override
    public String getMessage() {
        return message;
    }
}
