package com.ktspace.miniHomeIoT.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum Result {
    RESPONSE_SUCCEED("200", "응답 성공"),
    CONTROL_RESOURCE_SUCCEED("200", "제어 성공"),
    CONTROL_RESOURCE_FAIL("200", "제어 실패"),
    DELETE_RESOURCE_SUCCEED("200", "[deviceSeq : %d] 장치 삭제 성공"),
    DELETE_RESOURCE_FAIL("200", "[deviceSeq : %d] 장치 삭제 실패")
    ;
    private final String code;
    private final String message;
}
