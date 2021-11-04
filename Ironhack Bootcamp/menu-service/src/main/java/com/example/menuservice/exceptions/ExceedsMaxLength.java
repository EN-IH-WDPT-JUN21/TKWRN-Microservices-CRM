package com.example.menuservice.exceptions;

public class ExceedsMaxLength extends Exception{
    public ExceedsMaxLength(String message) {
        super(message);
    }
}
