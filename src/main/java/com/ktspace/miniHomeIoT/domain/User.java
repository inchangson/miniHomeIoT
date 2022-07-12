package com.ktspace.miniHomeIoT.domain;

import java.time.LocalDateTime;
import java.util.List;

public class User {
    private String id;
    private String name;
    private String address;
    private String phoneNum;
    private LocalDateTime createDate;

    private List<Device> devices;
    private List<Service> services;

    //region getter and setter
    public String getSeq() {
        return id;
    }

    public void setSeq(String id) {
        this.id = id;
    }

    public LocalDateTime getCreateDate() {
        return createDate;
    }

    public void setCreateDate(LocalDateTime createDate) {
        this.createDate = createDate;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhoneNum() {
        return phoneNum;
    }

    public void setPhoneNum(String phoneNum) {
        this.phoneNum = phoneNum;
    }
    //endregion
}
