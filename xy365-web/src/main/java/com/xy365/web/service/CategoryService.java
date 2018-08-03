package com.xy365.web.service;

import com.xy365.core.service.XyService;
import org.springframework.data.domain.Pageable;

import java.util.Map;

public interface CategoryService extends XyService {

    Map<String,Object> findAll();

    Map<String,Object> findCategoryByCode(String[] codes);

    Map<String,Object> findCategoryWithType();

}
