package com.ktspace.miniHomeIoT.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class DeviceDTO {
    Integer serviceTargetSeq;
    String external;
    Integer deviceSeq;
    String modelTypeCode;
    String modelId;
    String deviceName;
    List<ResourceDTO> resource;
}