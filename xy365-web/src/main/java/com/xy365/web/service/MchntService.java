package com.xy365.web.service;
import com.xy365.core.service.XyService;
import com.xy365.web.domain.form.MchntForm;
import com.xy365.web.domain.form.ShopForm;
import com.xy365.web.domain.form.ShopInfoForm;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

public interface MchntService  extends XyService {

    Map<String,Object> mchntCome(HttpServletRequest request,MchntForm mchntForm);

    Map<String,Object> mchntExist(String mobile);

    Map<String,Object> findMchntByUserId(HttpServletRequest request);

    Map<String,Object> checkUserBindingMchnt(HttpServletRequest request);
}
