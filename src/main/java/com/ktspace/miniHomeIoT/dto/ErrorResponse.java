package com.ktspace.miniHomeIoT.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ErrorResponse extends GeneralResponse{
    public String errorReason;
}