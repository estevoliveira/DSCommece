package com.estevao.DSCommece.services.exceptions;

public class ResourceNotFoundException extends RuntimeException{

    public ResourceNotFoundException(String text) {
        super(text);
    }
}
