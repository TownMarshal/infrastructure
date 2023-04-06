package com.horqian.basic.config;

import com.horqian.basic.common.CommonCode;
import com.horqian.basic.common.CommonResponse;
import com.horqian.basic.common.CommonResult;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.security.SignatureException;

@RestControllerAdvice
public class PermissionHandler {
    @ExceptionHandler(value = {SignatureException.class})
    @ResponseBody
    public CommonResult authorizationException(SignatureException e) {
        return CommonResponse.makeRsp(CommonCode.FAIL, e.getMessage());
    }
}

