package com.xy365.web.controller;
import com.xy365.web.domain.form.ShopInfoTypeForm;
import com.xy365.web.domain.support.SimpleResponse;
import com.xy365.web.service.ShopInfoTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import javax.servlet.http.HttpServletRequest;
@RestController
@CrossOrigin("*")
@RequestMapping("/shopInfoType")
public class ShopInfoTypeController {


    @Autowired
    private ShopInfoTypeService shopInfoTypeService;


    @PostMapping("/binding")
    public SimpleResponse bindingType(HttpServletRequest request, @RequestBody ShopInfoTypeForm shopInfoTypeForm){
        try {
            return SimpleResponse.success(shopInfoTypeService.bindingType(request, shopInfoTypeForm.getTypeIds()));
        }catch (Exception e){
            return SimpleResponse.error(e.getMessage());
        }
    }



}
