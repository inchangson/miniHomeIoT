package com.ktspace.miniHomeIoT.dto.response;

import com.ktspace.miniHomeIoT.dto.response.GeneralResponse;
import lombok.Getter;

import java.util.List;
@Getter
public class ListResponse<T> extends GeneralResponse {
    List<T> data;
}