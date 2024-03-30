package com.techbank.cqrs.core.exceptions;

public class AggragateNotFoundException extends RuntimeException{
    public AggragateNotFoundException(String message){
        super(message);
    }
}
