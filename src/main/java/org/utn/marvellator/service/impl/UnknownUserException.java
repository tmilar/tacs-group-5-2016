package org.utn.marvellator.service.impl;

public class UnknownUserException extends RuntimeException {

    public UnknownUserException(String username){

        super("User with name " + username + "is not logged in");
    }
}
