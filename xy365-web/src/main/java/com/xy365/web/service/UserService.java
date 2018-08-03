package com.xy365.web.service;
import com.xy365.core.service.XyService;
import com.xy365.web.domain.form.UserForm;
import com.xy365.web.wx.WxSession;

import java.util.Map;

public interface UserService extends XyService {

    Map<String,Object>  registerOrLogin(WxSession wxSession) throws Exception;

}
