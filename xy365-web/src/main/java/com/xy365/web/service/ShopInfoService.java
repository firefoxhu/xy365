package com.xy365.web.service;

import com.xy365.core.service.XyService;
import com.xy365.web.domain.form.ShopInfoForm;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

public interface ShopInfoService extends XyService {

    Map<String,Object> viewShop(long shoInfoId);

    Map<String,Object> updateShopInfo(HttpServletRequest request,ShopInfoForm shopInfoForm);

}
