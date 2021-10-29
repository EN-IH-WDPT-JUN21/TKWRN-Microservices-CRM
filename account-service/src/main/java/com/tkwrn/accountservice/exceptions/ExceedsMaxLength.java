package com.tkwrn.accountservice.exceptions;

public class ExceedsMaxLength extends Exception{
    public ExceedsMaxLength(String message) {
        super(message);
    }
}
