package com.xy365.web.service;

import com.xy365.core.service.XyService;
import com.xy365.web.domain.conditon.ShopCondition;
import org.springframework.data.domain.Pageable;

import java.util.Map;

public interface ShopService extends XyService {


    Map<String,Object> findShopByCondition(ShopCondition condition, Pageable pageable);

    Map<String,Object> checkShopNameExist(String shopName);

    Map<String,Object> findShopByShopInfoId(long shopInfoId);


    Map<String,Object> findShopByType(long typeId,Pageable pageable);
}
