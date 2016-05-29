package org.utn.marvellator.service.impl;

public class CurrentUserIsNotTheCreatorException extends RuntimeException {

    public CurrentUserIsNotTheCreatorException(){
        super("Sorry, this group is not yours :(");
    }
}
