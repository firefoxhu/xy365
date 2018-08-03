package com.xy365.core.controller;

import com.xy365.core.plugin.SmsSender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@CrossOrigin("*")
@RequestMapping("/code")
public class CodeValidateController {


    @Autowired
    private SmsSender smsSender;

    @GetMapping("/sms")
    public Map<String,Object> send(String phone){
       return   smsSender.send(phone);
    }

}
