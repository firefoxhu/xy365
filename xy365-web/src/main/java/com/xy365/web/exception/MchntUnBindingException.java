package com.xy365.web.exception;

import com.xy365.web.enums.MchntEnum;
import lombok.Data;

@Data
public class MchntUnBindingException extends  RuntimeException {

    private int code;

    private String message;

    public MchntUnBindingException(String message){
        super(message);

    }

    public MchntUnBindingException(MchntEnum mchntEnum){
        super(mchntEnum.getMsg());
        this.code = mchntEnum.getCode();

    }
}
