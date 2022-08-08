package com.ktspace.miniHomeIoT.controller;

import com.ktspace.miniHomeIoT.dto.Device;
import com.ktspace.miniHomeIoT.dto.response.DeviceListResponse;
import com.ktspace.miniHomeIoT.dto.response.SingleResponse;
import com.ktspace.miniHomeIoT.service.HomeIoTService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;

/**
 * HomeIoT의 컨트롤러입니다.
 * @version 1.0
 * @author 손인창
 */
@RestController
    public class HomeIoTController {
        /**
         * 사용자 요청을 처리하는 서비스 객체입니다. {@link HomeIoTService HomeIoTService}
         */
        private final HomeIoTService homeIotService;
        @Autowired
    public HomeIoTController(HomeIoTService homeIotService) {

        this.homeIotService = homeIotService;
    }

    /**
     * 장치 정보 조회는 {@link HomeIoTService#getUserDevices(String) getUserDevices} 를 참조
     * @param userId
     * @return 해당 아이디에 존재하는 모든 장치의 정보(리소스 포함)를 반환
     */
    @RequestMapping(value = "/devices", method = RequestMethod.GET)
    public DeviceListResponse getUserDevices(@RequestHeader("userId") String userId) {
        DeviceListResponse result = homeIotService.getUserDevices(userId);
        return result;
    }

    /**
     * 헤더의 아이디를 통한 사용자 검증과 path parameter 로 넘어오는 장치 seq값을 기준으로
     * 해당하는 장치 정보를 불러옵니다.
     * 장치 정보 조회는 {@link HomeIoTService#readDeviceInfo(String, Integer) readDeviceInfo} 를 참조
     * @param userId
     * @param dvcSeq
     * @return 특정 deviceSequence에 해당하는 장치의 정보(리소스 포함)를 반환
     */
    @RequestMapping(value = "/devices/{deviceSeq}", method = RequestMethod.GET)
    public SingleResponse<Device> readDeviceInfo(@RequestHeader("userId") String userId, @PathVariable(value = "deviceSeq") Integer dvcSeq) {
        SingleResponse<Device> result = homeIotService.readDeviceInfo(userId, dvcSeq);
        return result;
    }

    /**
     * 해당하는 장치의 리소스 값(DB)을 변경하고 로그(이력)를 남깁니다.
     * {@link HomeIoTService#controlResource(String, Integer, String, String) controlResource}를 참조
     * @param userId
     * @param dvcSeq
     * @param map
     * @return 값 수정 성공 여부를 반환합니다.
     */
    @RequestMapping(value = "/devices/{deviceSeq}", method = RequestMethod.PUT)
    public SingleResponse<HashMap<String, String>> controlResource(@RequestHeader("userId") String userId, @PathVariable(value = "deviceSeq") Integer dvcSeq, @RequestBody HashMap<String, Object> map) {
        //장치 리소스 수정
        HashMap<String, String> resourceDTO = (HashMap<String, String>) map.get("resource");
        SingleResponse<HashMap<String, String>> result = homeIotService.controlResource(userId, dvcSeq, resourceDTO.get("group"), resourceDTO.get("value"));
        return result;
    }

    /**
     * 해당하는 장치를 삭제합니다.
     * {@link HomeIoTService#deleteDevice(String, Integer) deleteDevice}를 참조
     * @param userId
     * @param devSeq
     * @return 장치 삭제 성공 여부를 반환합니다.
     */
    @RequestMapping(value = "/devices/{deviceSeq}", method = RequestMethod.DELETE)
    public SingleResponse<?> deleteDevice(@RequestHeader("userId") String userId, @PathVariable(value = "deviceSeq") Integer devSeq) {
        SingleResponse<?> result = homeIotService.deleteDevice(userId, devSeq);
        return result;
    }
}