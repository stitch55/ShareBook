package com.project.sharebook.config;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.support.http.StatViewServlet;
import com.alibaba.druid.support.http.WebStatFilter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

//druid的相关配置
@Configuration
public class DruidConfig {
    @ConfigurationProperties(prefix = "spring.datasource")
    @Bean
    public DataSource druidDataSource(){
        return new DruidDataSource();
    }
   //添加druid 的相关管理组件
   @Bean
   public ServletRegistrationBean statViewServlet(){
        //注册Servlet组件
        ServletRegistrationBean bean = new ServletRegistrationBean(new StatViewServlet(),"/druid/*");
        //Serlvet组件相关配置
       Map<String,String> map = new HashMap<String,String>();
       //在StatViewServlet extends ResourceServlet 中找能够配置的信息
       //管理后台账号密码
       map.put("loginUsername","druid");
       map.put("loginPassword","druid");

       return bean;
   }
   @Bean
    public FilterRegistrationBean webStatFilter(){
        //Filter 注册
       FilterRegistrationBean bean = new FilterRegistrationBean();
       bean.setFilter(new WebStatFilter());
       //Filter的县官设置
       return bean;
   }
}
