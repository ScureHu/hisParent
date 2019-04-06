package com.zcmu.nurse.config;

import com.zcmu.nurse.filter.JwtFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

@Configuration
public class ApplicationConfig extends WebMvcConfigurationSupport {

    @Autowired
    private JwtFilter jwtFilter;

    /**
     * 增加拦截器
     * 除了登录不拦截，其他的都拦截
     * @param registry
     */
    @Override
    protected void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(jwtFilter)
                .addPathPatterns("/**")
                .excludePathPatterns("/**/login");
    }
}
