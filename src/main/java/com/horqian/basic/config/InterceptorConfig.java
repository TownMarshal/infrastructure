package com.horqian.basic.config;

import com.horqian.basic.interceptor.AuthenticationInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @author macro
 * @create 2020-11-25-17:39
 **/
@Configuration
public class InterceptorConfig implements WebMvcConfigurer {
    /**
     * 拦截所有请求，通过判断是否有 @LoginRequired 注解 决定是否需要登录
     *
     * @param registry
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(authenticationInterceptor())
                .addPathPatterns("/**").
                //然后添加释放路径
                excludePathPatterns("/login")
                .excludePathPatterns("/swagger-resources/**","configuration/ui","/swagger-ui.html/**","/v2/api-docs")
                .excludePathPatterns("/webjars/**");
    }

    @Bean
    public AuthenticationInterceptor authenticationInterceptor() {
        return new AuthenticationInterceptor();
    }
}
