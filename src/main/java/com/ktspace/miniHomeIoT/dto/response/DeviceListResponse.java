package com.ktspace.miniHomeIoT.dto.response;

import com.ktspace.miniHomeIoT.dto.Device;
import com.ktspace.miniHomeIoT.dto.response.GeneralResponse;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
@Builder
public class DeviceListResponse {
    String responseCode;
    ListData data;

    @Builder
    @Getter
    public static class ListData {
        int deviceCount;
        List<Device> deviceList;
    }
}