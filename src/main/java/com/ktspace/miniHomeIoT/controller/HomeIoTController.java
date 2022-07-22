package com.ktspace.miniHomeIoT.controller;

import com.ktspace.miniHomeIoT.domain.Device;
import com.ktspace.miniHomeIoT.domain.Resource;
import com.ktspace.miniHomeIoT.domain.ResourceGroup;
import com.ktspace.miniHomeIoT.repository.HomeIoTRepository;
import com.ktspace.miniHomeIoT.service.HomeIoTService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class HomeIoTController {
    private final HomeIoTService homeIotService;

    @Autowired
    public HomeIoTController(HomeIoTService homeIotService) {
        this.homeIotService = homeIotService;
    }

    @RequestMapping(value = "/devices", method = RequestMethod.GET)
//    @ResponseBody
    //장치 목록 조회 : 관련된 리소스 모두 가져옴
    public List<Device> getUserDevices(@RequestHeader("serviceSeq") String serviceSeq) {
        String userId = "test";
        return homeIotService.getUserDevices(userId);
    }

    @RequestMapping(value = "/devices/{deviceSeq}", method = RequestMethod.GET)
//    @ResponseBody
    //장치 리소스 조회
    public Optional<Device> readDeviceInfo(@PathVariable(value="deviceSeq") int devSeq){//@RequestHeader("serviceSeq") String serviceSeq) {
        return homeIotService.readDeviceInfo(devSeq);
    }

    @RequestMapping(value = "/devices/{deviceSeq}", method = RequestMethod.PUT)
//    @ResponseBody
    //장치 리소스 수정
    public String controlResource(@PathVariable(value="deviceSeq") int devSeq){//@RequestHeader("serviceSeq") String serviceSeq) {
        String rscGrp = "test";
        String value = "test";

        return homeIotService.controlResource(devSeq, rscGrp, value);
    }

    @RequestMapping(value = "/devices/{deviceSeq}", method = RequestMethod.DELETE)
//    @ResponseBody
    public void deleteDevice(@PathVariable(value="deviceSeq") int devSeq){//@RequestHeader("serviceSeq") String serviceSeq) {
        homeIotService.deleteDevice(devSeq);
    }

    @RequestMapping(value = "/devices/{deviceSeq}", method = RequestMethod.POST)
//    @ResponseBody
    public void createDevice(@PathVariable(value="deviceSeq") int devSeq){//@RequestHeader("serviceSeq") String serviceSeq) {

    }
}
