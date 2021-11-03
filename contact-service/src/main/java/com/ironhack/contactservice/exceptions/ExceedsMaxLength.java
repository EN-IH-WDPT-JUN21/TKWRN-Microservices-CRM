package com.ironhack.contactservice.exceptions;

public class ExceedsMaxLength extends Exception{
    public ExceedsMaxLength(String message) {
        super(message);
    }
}
