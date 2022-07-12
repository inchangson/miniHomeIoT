package com.ktspace.miniHomeIoT.domain;

public enum Company {
    GOQUAL("GOQUAL", "HOME_IOT_GOQUAL"),
    BRUNT("BRUNT", "HOME_IOT_BRUNT"),
    SAMSUNG("SAMSUNG", "HOME_IOT_SS_V2"),
    COWAY("COWAY", "HOME_IOT_COWAY"),
    RINNAI("RINNAI", "HOME_IOT_RINNAI"),
    ;

    private String id;
    private String external;

    Company(String id, String external) {
        this.id = id;
        this.external = external;
    }
}
