package com.example.exception;

public class BadRequestException extends Exception{
    BadRequestException(String Message){
        super(Message);
    }
}
