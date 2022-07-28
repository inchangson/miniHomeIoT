package com.ktspace.miniHomeIoT.domain.enums;

public enum ResourceGroup {
    CONN_STAT("connection-status"),
    PWR_SWITCH("power-switch"),
    OPEN_BUTTON("open-button"),
    ERROR("N/A"),
    ;
    private final String name;
    private final String code;
    private final String valueType;
    private final String korName;

    ResourceGroup(String name) {
        switch (name){
            case "connection-status" :
                this.name = name;
                this.code = "value";
                this.valueType = "boolean";
                this.korName = "연결";
                break;
            case "power-switch"      :
                this.name = name;
                this.code = "value";
                this.valueType = "boolean";
                this.korName = "파워";
                break;
            case "open-button"       :
                this.name = name;
                this.code = "value";
                this.valueType = "boolean";
                this.korName = "열림";
                break;
            default:
                this.name = "N/A";
                this.code = "N/A";
                this.valueType = "N/A";
                this.korName = "해당 리소스 없음";
                break;
        }
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
