package com.ktspace.miniHomeIoT.domain;

import com.ktspace.miniHomeIoT.domain.enums.Company;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Device {

    private Integer seq;
    private String name;

    //model 관련
    private String modelId;
    private String modelNameKor;
    private String modelTypeCode;

    private Company company;

    private List<Resource> resources;
}
