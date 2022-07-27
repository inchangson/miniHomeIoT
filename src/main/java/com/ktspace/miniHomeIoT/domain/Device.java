package com.ktspace.miniHomeIoT.domain;

import com.ktspace.miniHomeIoT.domain.enums.Company;

import java.util.List;

public class Device {

    private Integer seq;
    private String name;

    //model 관련
    private String modelId;
    private String modelNameKor;
    private String modelTypeCode;

    private Company company;

    private List<Resource> resources;

    public Device(Integer seq, String name, String modelId, String modelNameKor, String modelTypeCode, Company company) {
        this.seq = seq;
        this.name = name;
        this.modelId = modelId;
        this.modelNameKor = modelNameKor;
        this.modelTypeCode = modelTypeCode;
        this.company = company;
    }

    //region getter and setter
    public Integer getSeq() {
        return seq;
    }

    public void setSeq(Integer seq) {
        this.seq = seq;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getModelId() {
        return modelId;
    }

    public void setModelId(String modelId) {
        this.modelId = modelId;
    }

    public String getModelNameKor() {
        return modelNameKor;
    }

    public void setModelNameKor(String modelNameKor) {
        this.modelNameKor = modelNameKor;
    }

    public String getModelTypeCode() {
        return modelTypeCode;
    }

    public void setModelTypeCode(String modelTypeCode) {
        this.modelTypeCode = modelTypeCode;
    }

    public Company getCompany() {
        return company;
    }

    public void setCompany(Company company) {
        this.company = company;
    }

    public List<Resource> getResources() {
        return resources;
    }

    public void setResources(List<Resource> resources) {
        this.resources = resources;
    }
    //endregion
}
