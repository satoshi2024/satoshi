package com.baizhi.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class MvcConfigAjaxAxios  implements WebMvcConfigurer {

    //通过这里面配置: 不需要为每一个访问thymeleaf模板页面单独开发一个controller请求了
    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
    	// ajaxaxios画面
    	registry.addViewController("/manageAjaxAxios").setViewName("ajaxaxios/ajaxaxios");
        
    }
}
