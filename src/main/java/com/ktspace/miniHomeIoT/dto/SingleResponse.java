package com.ktspace.miniHomeIoT.dto;

import lombok.Getter;

@Getter
public class SingleResponse<T> extends CommonResponse {
    T data;
}