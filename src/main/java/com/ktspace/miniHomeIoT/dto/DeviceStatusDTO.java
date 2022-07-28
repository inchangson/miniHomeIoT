package com.ktspace.miniHomeIoT.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class DeviceStatusDTO {
    Integer serviceTargetSeq;
    String external;
    Integer deviceSeq;
    String modelTypeCode;
    String modelId;
    String deviceName;
    String group;
    String code;
    String value;
}