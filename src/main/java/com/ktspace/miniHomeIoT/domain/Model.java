package com.ktspace.miniHomeIoT.domain;

import java.util.Optional;

public class Model {
    private String id;
    private String korName;
    private String typeCode;
    private Company company;

    private Optional<Resource> resources;

    //region getter and setter
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getKorName() {
        return korName;
    }

    public void setKorName(String korName) {
        this.korName = korName;
    }

    public String getTypeCode() {
        return typeCode;
    }

    public void setTypeCode(String typeCode) {
        this.typeCode = typeCode;
    }

    public Company getCompany() {
        return company;
    }

    public void setCompany(Company company) {
        this.company = company;
    }
    //endregion
}