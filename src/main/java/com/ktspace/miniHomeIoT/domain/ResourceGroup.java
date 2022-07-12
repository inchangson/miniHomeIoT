package com.ktspace.miniHomeIoT.domain;

public enum ResourceGroup {
    CONN_STAT("connection-status", "value", "boolean"),
    PWR_SWITCH("power-switch", "value", "boolean"),
    OPEN_BUTTON("open-button", "value", "boolean"),
    ;
    private final String name;
    private final String code;
    private final String valueType;

    ResourceGroup(String name, String code, String valueType) {
        this.name = name;
        this.code = code;
        this.valueType = valueType;
    }
}
