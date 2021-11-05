package com.ironhack.accountservice.exceptions;

public class ExceedsMaxLength extends Exception{
    public ExceedsMaxLength(String message) {
        super(message);
    }
}
