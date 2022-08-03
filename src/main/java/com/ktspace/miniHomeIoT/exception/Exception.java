package com.ktspace.miniHomeIoT.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum Exception {
    INVALID_USER("404", "해당하는 사용자가 없습니다."),
    INAPPROPRIATE_USER("401", "권한이 없습니다."),
    INVALID_DEVICE("405", "해당하는 장치가 없습니다.");

    private final String code;
    private final String message;
}
