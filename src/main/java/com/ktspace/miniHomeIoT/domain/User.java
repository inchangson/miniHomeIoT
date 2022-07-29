package com.ktspace.miniHomeIoT.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class User {
    private String id;
    private String name;
    private String address;
    private String phoneNum;
    private LocalDateTime createDate;

    private List<Device> devices;
    private List<Service> services;
}
