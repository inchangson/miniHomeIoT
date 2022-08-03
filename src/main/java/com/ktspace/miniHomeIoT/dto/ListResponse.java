package com.ktspace.miniHomeIoT.dto;

import lombok.Getter;

import java.util.List;
@Getter
public class ListResponse<T> extends GeneralResponse {
    List<T> dataList;
}