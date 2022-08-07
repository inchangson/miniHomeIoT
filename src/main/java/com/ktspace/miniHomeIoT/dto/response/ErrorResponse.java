package com.ktspace.miniHomeIoT.dto.response;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ErrorResponse {
    public String responseCode;
    public String errorReason;
}