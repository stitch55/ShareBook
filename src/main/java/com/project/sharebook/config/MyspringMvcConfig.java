package com.project.sharebook.config;

import com.project.sharebook.interceptor.LoginHandlerInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;


@Configuration
public class MyspringMvcConfig extends WebMvcConfigurerAdapter {
    //添加视图映射
@Autowired
LoginHandlerInterceptor loginHandlerInterceptor;


    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        //测试页面
      //  registry.addViewController("/personal").setViewName("personal-center");
        //测试社区
        //测试搜索书本
      //  registry.addViewController("search").setViewName("search");
        //测试更多书籍页面
       // registry.addViewController("lookmore").setViewName("lookmore");

    }

    @Bean
    public LoginHandlerInterceptor getLoginHandlerInterceptor(){
        return new LoginHandlerInterceptor();
    }
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
//
        //spring 4页面在访问的时候也会访问静态资源；*.css  *.js
        //本程序试运行在spring5 ,静态资源会被拦截
        // 但是springBoot 已经做好了静态资源映射，所以在spring boot 中可以不用考虑
        System.out.println("登录拦截");
        registry.addInterceptor(getLoginHandlerInterceptor()).addPathPatterns("/**").excludePathPatterns("/asserts/**","/resource/**","/webjars/**","/zan","/searchBook",
                "/TypeName","/MoreBooks","/pageNumber","/getMoreBook","/getMsgs","/community","/register","/login","/data","/");
      //  super.addInterceptors(registry);
    }

}
