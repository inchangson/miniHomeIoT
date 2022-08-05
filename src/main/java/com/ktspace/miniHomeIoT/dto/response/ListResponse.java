package com.ktspace.miniHomeIoT.dto.response;

import com.ktspace.miniHomeIoT.dto.response.GeneralResponse;
import lombok.Getter;

import java.util.List;
@Getter
public class ListResponse<T> extends GeneralResponse {
    ListData data;
    class ListData{
        int count;
        List<T> list;
    }
}