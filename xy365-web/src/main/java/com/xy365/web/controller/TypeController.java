package com.xy365.web.controller;

import com.xy365.web.domain.support.SimpleResponse;
import com.xy365.web.service.TypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin("*")
@RequestMapping("/type")
public class TypeController {

    @Autowired
    private TypeService typeService;

    @GetMapping("/list")
    public SimpleResponse findType(@PageableDefault(page = 0,size = 8,sort = {"sort"},direction = Sort.Direction.DESC) Pageable page,String codes){
        return SimpleResponse.success(typeService.findTypeByCategoryCode(codes.split(","),page));
    }
}
