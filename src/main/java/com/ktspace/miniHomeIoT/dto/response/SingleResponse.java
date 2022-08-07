package com.ktspace.miniHomeIoT.dto.response;

import com.ktspace.miniHomeIoT.dto.response.GeneralResponse;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
public class SingleResponse<T> extends GeneralResponse {
    T data;
    @Builder
    public SingleResponse(String responseCode, T data){
        super(responseCode);
        this.data = data;
    }
}