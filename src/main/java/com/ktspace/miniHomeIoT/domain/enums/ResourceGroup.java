package com.ktspace.miniHomeIoT.domain.enums;

public enum ResourceGroup {
    CONN_STAT("connection-status", "value", "boolean", "연결"),
    PWR_SWITCH("power-switch", "value", "boolean", "파워"),
    OPEN_BUTTON("open-button", "value", "boolean", "열림"),
    ERROR("N/A", "N/A", "N/A", "해당 리소스 없음"),
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

    public static ResourceGroup findByName(String name){
        switch (name){
            case "connection-status" : return CONN_STAT;
            case "power-switch"      : return PWR_SWITCH;
            case "open-button"       : return OPEN_BUTTON;
            default: /*throw error*/return ERROR;
        }
    }

    public String getKorName() {
        return korName;
    }
}
