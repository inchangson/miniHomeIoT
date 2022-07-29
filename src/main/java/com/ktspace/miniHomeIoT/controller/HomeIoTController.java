package com.ktspace.miniHomeIoT.controller;

import com.ktspace.miniHomeIoT.domain.Device;
import com.ktspace.miniHomeIoT.dto.ResponseDTO;
import com.ktspace.miniHomeIoT.service.HomeIoTService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class HomeIoTController {
    private final HomeIoTService homeIotService;
    private final String CODE_NA = "000";
    private final String CODE_SUCCEED = "200";

    @Autowired
    public HomeIoTController(HomeIoTService homeIotService) {

        this.homeIotService = homeIotService;
    }

    private String getResultCode(ResponseDTO responseDTO){
        if (responseDTO.getData() == null) {
            return CODE_NA;
        }
        return CODE_SUCCEED;
    }

    @RequestMapping(value = "findAll", method = RequestMethod.POST)
    public ResponseEntity<?> findAll() {
        ResponseDTO responseDTO = new ResponseDTO();
        responseDTO.setData(homeIotService.findAll());
        responseDTO.setResultCode(getResultCode(responseDTO));
        return new ResponseEntity<>(responseDTO, HttpStatus.OK);
    }

    @RequestMapping(value = "/devices", method = RequestMethod.GET)
    //장치 목록 조회 : 관련된 리소스 모두 가져옴
    public ResponseEntity<?> getUserDevices(@RequestHeader("userId") String userId) {
        ResponseDTO responseDTO = new ResponseDTO();
        responseDTO.setData(homeIotService.getUserDevices(userId));
        responseDTO.setResultCode(getResultCode(responseDTO));
        return new ResponseEntity<>(responseDTO, HttpStatus.OK);
    }

    @RequestMapping(value = "/devices/{deviceSeq}", method = RequestMethod.GET)
//    @ResponseBody
    //장치 리소스 조회
    public ResponseEntity<?> readDeviceInfo(@PathVariable(value = "deviceSeq") int dvcSeq) {//@RequestHeader("serviceSeq") String serviceSeq) {
        ResponseDTO responseDTO = new ResponseDTO();
        responseDTO.setData(homeIotService.readDeviceInfo(dvcSeq));
        responseDTO.setResultCode(getResultCode(responseDTO));
        return new ResponseEntity<>(responseDTO, HttpStatus.OK);
    }

    @RequestMapping(value = "/devices/{deviceSeq}", method = RequestMethod.PUT)
//    @ResponseBody
    //장치 리소스 수정
    public String controlResource(@PathVariable(value = "deviceSeq") int devSeq) {//@RequestHeader("serviceSeq") String serviceSeq) {
        String rscGrp = "test";
        String value = "test";

        return homeIotService.controlResource(devSeq, rscGrp, value);
    }

    @RequestMapping(value = "/devices/{deviceSeq}", method = RequestMethod.DELETE)
    public ResponseEntity<?> deleteDevice(@PathVariable(value = "deviceSeq") int devSeq) {
        ResponseDTO responseDTO = new ResponseDTO();//알아서 날려주려나..?
        responseDTO.setData(homeIotService.deleteDevice(devSeq));
        responseDTO.setResultCode(getResultCode(responseDTO));
        return new ResponseEntity<>(responseDTO, HttpStatus.OK);
    }

    @RequestMapping(value = "/devices/{deviceSeq}", method = RequestMethod.POST)
//    @ResponseBody
    public void createDevice(@PathVariable(value = "deviceSeq") int devSeq) {//@RequestHeader("serviceSeq") String serviceSeq) {

    }
}
