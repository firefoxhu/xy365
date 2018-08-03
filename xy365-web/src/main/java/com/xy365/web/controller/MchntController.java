package com.xy365.web.controller;

import com.xy365.web.domain.form.MchntForm;
import com.xy365.web.domain.form.ShopForm;
import com.xy365.web.domain.form.ShopInfoForm;
import com.xy365.web.domain.support.SimpleResponse;
import com.xy365.web.exception.MchntUnBindingException;
import com.xy365.web.service.MchntService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RestController
@CrossOrigin("*")
@RequestMapping("/mchnt")
public class MchntController {


    @Autowired
    private MchntService mchntService;

    @PostMapping("/comeIn")
    public SimpleResponse mchntCome(HttpServletRequest request, @RequestBody MchntForm mchntForm){
        try {
            return SimpleResponse.success(mchntService.mchntCome(request,mchntForm));
        }catch (Exception e){
            return SimpleResponse.error(e.getMessage());
        }
    }


    @GetMapping("/checkPhone")
    public SimpleResponse checkMchntPhone(String phone){
        try {
            return SimpleResponse.success(mchntService.mchntExist(phone));
        }catch (Exception e){
            return SimpleResponse.error(e.getMessage());
        }
    }

    @GetMapping("/mchntInfo")
    public SimpleResponse findMchntByUserId(HttpServletRequest request){
        try {
            return SimpleResponse.success(mchntService.findMchntByUserId(request));
        }catch (MchntUnBindingException e){
            return SimpleResponse.error(e.getCode(),e.getMessage());
        }catch (Exception e){
            return SimpleResponse.error(e.getMessage());
        }
    }
    @GetMapping("/binding")
    public SimpleResponse binding(HttpServletRequest request){
        try {
            return SimpleResponse.success(mchntService.checkUserBindingMchnt(request));
        }catch (MchntUnBindingException e){
            return SimpleResponse.error(e.getCode(),e.getMessage());
        }catch (Exception e){
            return SimpleResponse.error(e.getMessage());
        }
    }
}
