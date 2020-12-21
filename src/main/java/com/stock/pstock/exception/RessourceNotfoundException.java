package com.stock.pstock.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class RessourceNotfoundException extends Exception{
    public static final Long serialVersionUID = 1L;
    public RessourceNotfoundException(String message){
        super(message);
    }
}
