package com.ktspace.miniHomeIoT.domain;

public enum ResourceGroup {
    CONN_STAT("connection-status", "value", "boolean", "연결"),
    PWR_SWITCH("power-switch", "value", "boolean", "파워"),
    OPEN_BUTTON("open-button", "value", "boolean", "열림"),
    ;
    private final String name;
    private final String code;
    private final String valueType;
    private final String korName;

    ResourceGroup(String name, String code, String valueType, String korName) {
        this.name = name;
        this.code = code;
        this.valueType = valueType;
        this.korName = korName;
    }

    public String getKorName() {
        return korName;
    }
}
