package com.example.SimpleCrudWithPostGreSQL.ExceptionHandler;

public class EmployeeNotFoundException extends RuntimeException{

    public EmployeeNotFoundException(String message){
        super(message);
    }
}
