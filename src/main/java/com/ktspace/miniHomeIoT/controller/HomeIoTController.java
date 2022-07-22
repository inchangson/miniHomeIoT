package com.ktspace.miniHomeIoT.controller;

import com.ktspace.miniHomeIoT.domain.User;

import org.springframework.web.bind.annotation.*;

@RestController
public class HomeIoTController {

    @RequestMapping(value = "/devices", method = RequestMethod.GET)
//    @ResponseBody
    public User getUserInfo(@RequestHeader("serviceSeq") String serviceSeq) {
        User user = new User();
        return user;
    }

    @RequestMapping(value = "/devices/{deviceSeq}", method = RequestMethod.GET)
//    @ResponseBody
    public void readDeviceInfo(@PathVariable(value="deviceSeq") int devSeq){//@RequestHeader("serviceSeq") String serviceSeq) {

    }

    @RequestMapping(value = "/devices/{deviceSeq}", method = RequestMethod.PUT)
//    @ResponseBody
    public void controlResource(@PathVariable(value="deviceSeq") int devSeq){//@RequestHeader("serviceSeq") String serviceSeq) {

    }

    @RequestMapping(value = "/devices/{deviceSeq}", method = RequestMethod.DELETE)
//    @ResponseBody
    public void deleteDevice(@PathVariable(value="deviceSeq") int devSeq){//@RequestHeader("serviceSeq") String serviceSeq) {

    }

    @RequestMapping(value = "/devices/{deviceSeq}", method = RequestMethod.POST)
//    @ResponseBody
    public void createDevice(@PathVariable(value="deviceSeq") int devSeq){//@RequestHeader("serviceSeq") String serviceSeq) {

    }
}
