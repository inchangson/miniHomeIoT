package com.ktspace.miniHomeIoT.dto.response;

import lombok.Builder;
import lombok.Getter;

@Getter
public class ErrorResponse extends GeneralResponse{
    public String message;
    @Builder
    public ErrorResponse(String responseCode, String message){
        super(responseCode);
        this.message = message;
    }

}