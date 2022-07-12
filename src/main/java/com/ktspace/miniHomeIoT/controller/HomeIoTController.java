package com.ktspace.miniHomeIoT.controller;

import com.ktspace.miniHomeIoT.domain.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller

public class HomeIoTController {

    @GetMapping("/devices")
    @ResponseBody
    public User getUserInfo(@RequestHeader("serviceSeq") String serviceSeq){
        User user = new User();
        return user;
    }
}
