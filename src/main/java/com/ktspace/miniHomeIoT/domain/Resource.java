package com.ktspace.miniHomeIoT.domain;

public class Resource {
    private ResourceGroup resourceGroup;
    private String value;
    public Resource(ResourceGroup resourceGroup, String value){
        this.resourceGroup = resourceGroup;
        this.value = value;
    }
    public String getValue() {
        return value;
    }
}