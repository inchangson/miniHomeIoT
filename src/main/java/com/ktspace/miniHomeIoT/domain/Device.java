package com.ktspace.miniHomeIoT.domain;

import java.util.Optional;

public class Device {

    private Integer seq;
    private String name;

    //model 관련
    private String modelId;
    private String modelNameKor;
    private String modelTypeCode;

    private Company company;

    // 상속 이용해서 모델을 받은 디바이스가 리소스를 쓸 수 있게 하는 게 낫나?
    private Optional<Resource> resources;

    // DB 접근에 대한 Abstract function 을 만들까?
    //...

}
