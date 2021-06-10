package com.horqian.basic.config;

import com.alibaba.fastjson.JSON;


import com.horqian.basic.common.AjaxResponseBody;
import com.horqian.basic.service.impl.SysUserDetailsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class AjaxAuthenticationSuccessHandler implements AuthenticationSuccessHandler {

    @Autowired
    private SysUserDetailsServiceImpl sysUserService;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Authentication authentication) throws IOException, ServletException {
        AjaxResponseBody responseBody = new AjaxResponseBody();
        String userName = (String) authentication.getPrincipal(); // 这个获取表单输入中返回的用户名;

        responseBody.setStatus(200);
        responseBody.setMsg("登录成功!");
        responseBody.setResult(sysUserService.loadUserByUsername(userName));
        httpServletResponse.setContentType("application/json;charset=utf-8");
        httpServletResponse.getWriter().write(JSON.toJSONString(responseBody));
    }
}

