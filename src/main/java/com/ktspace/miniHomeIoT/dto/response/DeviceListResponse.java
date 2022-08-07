package com.ktspace.miniHomeIoT.dto.response;

import com.ktspace.miniHomeIoT.dto.response.GeneralResponse;
import lombok.Getter;

import java.util.List;

@Getter
public class DeviceListResponse<T> extends GeneralResponse {
    ListData data;

    class ListData {
        int deviceCount;
        List<T> deviceList;
    }
}