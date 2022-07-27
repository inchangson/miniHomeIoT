package com.ktspace.miniHomeIoT.domain;

import com.ktspace.miniHomeIoT.domain.enums.ResourceGroup;

public class Resource {
    private ResourceGroup resourceGroup;
    private String value;

    public Resource(ResourceGroup resourceGroup, String value) {
        this.resourceGroup = resourceGroup;
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}