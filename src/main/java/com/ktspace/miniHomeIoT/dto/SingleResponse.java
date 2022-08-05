package com.ktspace.miniHomeIoT.dto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SingleResponse<T> extends GeneralResponse {
    T data;
}