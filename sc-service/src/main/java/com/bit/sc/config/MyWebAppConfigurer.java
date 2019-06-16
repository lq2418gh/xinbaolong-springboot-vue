package com.bit.sc.config;

import com.bit.sc.common.Const;
import com.bit.sc.utils.YmlUtil;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * 解决跨域
 *
 * **/
@Configuration
public class MyWebAppConfigurer extends WebMvcConfigurationSupport {




    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**").allowedHeaders("at");
    }


    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler(Const.UPLOAD_PATH+"**").addResourceLocations("file:"+ YmlUtil.getValue("file.localtion"));
        super.addResourceHandlers(registry);
    }
}