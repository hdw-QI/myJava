package com.study.springboot.config;

import com.study.springboot.interceptor.TokenInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @author 胡代伟
 * @description web配置
 * @date 2024-01-16 15:58
 */
@Configuration
public class WebConfig implements WebMvcConfigurer {

    /**
     * 添加拦截器
     *
     * @param registry
     */
    @Autowired
    private TokenInterceptor tokenInterceptor;
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        //指定拦截器
        registry.addInterceptor(tokenInterceptor)
                //拦截的路径
                .addPathPatterns("/user/getById")
                //放行的路径
                .excludePathPatterns("/emp/getById");
        //多个拦截器配置
        /*registry.addInterceptor(tokenInterceptor)
                .addPathPatterns()
                .excludePathPatterns();*/
    }

    /**
     * 跨域配置
     * @param registry
     */
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        //对那些路径进行跨域
        registry.addMapping("/**")
                //是否允许token
                .allowCredentials(true)
                //允许的请求头
                .allowedHeaders("*")
                //允许的请求方式
                .allowedMethods("*")
                //允许那些ip进行跨域
                .allowedOrigins("*");
    }
}
