package com.ktspace.miniHomeIoT.controller;

import com.ktspace.miniHomeIoT.dto.ResponseDTO;
import com.ktspace.miniHomeIoT.service.HomeIoTService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;

@RestController
public class HomeIoTController {
    private final HomeIoTService homeIotService;
    private final String CODE_NA = "000";
    private final String CODE_SUCCEED = "200";

    @Autowired
    public HomeIoTController(HomeIoTService homeIotService) {

        this.homeIotService = homeIotService;
    }

    private String getResultCode(ResponseDTO responseDTO) {
        if (responseDTO.getData() == null) {
            return CODE_NA;
        }
        return CODE_SUCCEED;
    }

    @RequestMapping(value = "/devices", method = RequestMethod.GET)
    public ResponseEntity<?> getUserDevices(@RequestHeader("userId") String userId) {
        //장치 목록 조회 : 관련된 리소스 모두 가져옴
        ResponseDTO responseDTO = new ResponseDTO();
        responseDTO.setData(homeIotService.getUserDevices(userId));
        responseDTO.setResultCode(getResultCode(responseDTO));
        return new ResponseEntity<>(responseDTO, HttpStatus.OK);
    }

    @RequestMapping(value = "/devices/{deviceSeq}", method = RequestMethod.GET)
    public ResponseEntity<?> readDeviceInfo(@RequestHeader("userId") String userId, @PathVariable(value = "deviceSeq") Integer dvcSeq) {
        //장치 리소스 조회
        ResponseDTO responseDTO = new ResponseDTO();
        responseDTO.setData(homeIotService.readDeviceInfo(userId, dvcSeq));
        responseDTO.setResultCode(getResultCode(responseDTO));
        return new ResponseEntity<>(responseDTO, HttpStatus.OK);
    }

    @RequestMapping(value = "/devices/{deviceSeq}", method = RequestMethod.PUT)
    public ResponseEntity<?> controlResource(@RequestHeader("userId") String userId, @PathVariable(value = "deviceSeq") Integer dvcSeq, @RequestBody HashMap<String, Object> map) {
        //장치 리소스 수정
        HashMap<String, String> resourceDTO = (HashMap<String, String>) map.get("resource");
        ResponseDTO responseDTO = new ResponseDTO();
        responseDTO.setData(homeIotService.controlResource(userId, dvcSeq, resourceDTO.get("group"), resourceDTO.get("value")));
        responseDTO.setResultCode(getResultCode(responseDTO));

        return new ResponseEntity<>(responseDTO, HttpStatus.OK);
    }

    @RequestMapping(value = "/devices/{deviceSeq}", method = RequestMethod.DELETE)
    public ResponseEntity<?> deleteDevice(@RequestHeader("userId") String userId, @PathVariable(value = "deviceSeq") Integer devSeq) {
        // 장치 삭제
        ResponseDTO responseDTO = new ResponseDTO();//알아서 날려주려나..?
        responseDTO.setData(homeIotService.deleteDevice(userId, devSeq));
        responseDTO.setResultCode(getResultCode(responseDTO));
        return new ResponseEntity<>(responseDTO, HttpStatus.OK);
    }
}
