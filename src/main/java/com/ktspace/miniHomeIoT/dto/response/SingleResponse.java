package com.ktspace.miniHomeIoT.dto.response;

import com.ktspace.miniHomeIoT.dto.response.GeneralResponse;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SingleResponse<T> extends GeneralResponse {
    T data;
}