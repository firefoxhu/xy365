package com.xy365.web.service;

import com.xy365.core.service.XyService;
import org.springframework.data.domain.Pageable;

import java.util.Map;

public interface TypeService extends XyService {

    Map<String,Object> findTypeByCategoryCode(String[] codes, Pageable pageable);

    Map<String,Object> findTypeByCategory(String code);

}
