package com.example.bookcatalog.infrastructure.exception;

public class IncorrectPasswordException extends RuntimeException{
    public IncorrectPasswordException() {
        super("Incorrect password");
    }
}
