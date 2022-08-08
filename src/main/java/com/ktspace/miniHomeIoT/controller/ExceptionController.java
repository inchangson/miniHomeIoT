package com.ktspace.miniHomeIoT.controller;

import com.ktspace.miniHomeIoT.dto.response.ErrorResponse;
import com.ktspace.miniHomeIoT.dto.response.GeneralResponse;
import com.ktspace.miniHomeIoT.enums.Exception;
import com.ktspace.miniHomeIoT.exception.DataNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ExceptionController {
    private final Logger logger = LoggerFactory.getLogger(this.getClass().getSimpleName());

    public GeneralResponse getErrorResponse(String code, String message) {
        ErrorResponse errorResponse = ErrorResponse.builder()
                .responseCode(code)
                .message(message)
                .build();
        return errorResponse;
    }

    @ExceptionHandler(DataNotFoundException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    private GeneralResponse dataNotFoundException(DataNotFoundException e){
        return getErrorResponse(Exception.INVALID_USER.getCode(), e.getReason());
    }

    @ExceptionHandler(NullPointerException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    private GeneralResponse nullPointerException(NullPointerException e){
        // argument로 null이 오는 경우 illegalArgument를 띄우기도 하지만
        // NPE를 띄우기도 한다는데,
        // 다양한 상황에서 자주 발생하는 예왼데 전역 예외처리로 같이 다뤄도 되는지 걱정.
        return getErrorResponse("404", e.getMessage());
    }
}