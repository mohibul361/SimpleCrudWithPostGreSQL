package com.example.SimpleCrudWithPostGreSQL.ExceptionHandler;

public class InvalidEmployeeException extends RuntimeException{

    public InvalidEmployeeException(String message){
        super(message);
    }

}
