package com.dbma.thymeleaf;

public class UnauthorizedException extends Exception{
    public UnauthorizedException(String message) {
        super(message);
    }
}
