package com.xy365.web.wx;

import lombok.Data;

import java.io.Serializable;

@Data
public class WxSession implements Serializable {

    private String session_key;

    private String openId;

    private String expired_session;

}
