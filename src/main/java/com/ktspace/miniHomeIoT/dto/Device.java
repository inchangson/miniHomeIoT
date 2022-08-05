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
public class Device {
    Integer serviceTargetSeq;
    String external;
    Integer deviceSeq;
    String modelTypeCode;
    String modelId;
    String deviceName;
    List<Resource> resource;
}