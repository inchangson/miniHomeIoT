package com.ktspace.miniHomeIoT.exception;

import lombok.Getter;

//@Getter
public class DataNotFoundException extends RuntimeException{
    @Getter
    public String reason;
    public DataNotFoundException(String message){
        super();
        this.reason = message;
    }
}