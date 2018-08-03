package com.xy365.web.controller;

import com.xy365.web.domain.support.SimpleResponse;
import com.xy365.web.service.CategoryService;
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
@RequestMapping("/category")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @GetMapping("/list")
    public SimpleResponse findAll(){
        return SimpleResponse.success(categoryService.findAll());
    }

    @GetMapping("/code")
    public SimpleResponse findCategory(String codes){
        return SimpleResponse.success(categoryService.findCategoryByCode(codes.split(",")));
    }


    @GetMapping("/listWithType")
    public SimpleResponse findCategoryWithType(){
        return SimpleResponse.success(categoryService.findCategoryWithType());
    }


}
