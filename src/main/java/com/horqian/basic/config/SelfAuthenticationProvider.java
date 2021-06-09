package com.horqian.basic.config;

import com.horqian.basic.entity.SysUserTbl;
import com.horqian.basic.service.impl.SysUserTblServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class SelfAuthenticationProvider implements AuthenticationProvider {

    @Autowired
    private SysUserTblServiceImpl sysUserService;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String userName = (String) authentication.getPrincipal(); // 这个获取表单输入中返回的用户名;
        String password = (String) authentication.getCredentials(); // 这个是表单中输入的密码；
        SysUserTbl userInfo = sysUserService.loadUserByUsername(userName);
        BCryptPasswordEncoder bcp = new BCryptPasswordEncoder();
        if (!bcp.matches(password,userInfo.getPassword())) {
            throw new BadCredentialsException("用户名密码不正确，请重新登陆！");
        }
        return new UsernamePasswordAuthenticationToken(userName, password, userInfo.getAuthorities());
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return true;
    }
}
