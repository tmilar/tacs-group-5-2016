package org.utn.marvellator.service.impl;

public class UserDoesNotExistException extends RuntimeException {

    public UserDoesNotExistException(String invalidUser){

        super("User: " + invalidUser + " does not exist or its password does not match \n");
    }
}
