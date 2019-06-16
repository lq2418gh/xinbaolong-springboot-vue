package com.bit.sc.config;

import com.bit.sc.filter.InterfaceFilter;
import com.bit.sc.filter.TokenFilter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author liuyancheng
 * @create 2018-11-21 13:41
 */
@Configuration
public class FilterConfig {
    /**
     * TokenFilter
     * @return
     */
    @Bean
    public FilterRegistrationBean registrationFilterBean(){
        FilterRegistrationBean filterRegistrationBean=new FilterRegistrationBean();
        filterRegistrationBean.setFilter(new TokenFilter());
        filterRegistrationBean.addUrlPatterns("/*");
        filterRegistrationBean.setOrder(0);
        //是否启用
        filterRegistrationBean.setEnabled(true);
        return filterRegistrationBean;
    }

    /**
     * InterfaceFilter
     * @return
     */
    @Bean
    public FilterRegistrationBean registrationBean(){
        FilterRegistrationBean filterRegistrationBean=new FilterRegistrationBean();
        filterRegistrationBean.setFilter(new InterfaceFilter());
        filterRegistrationBean.addUrlPatterns("/*");
        filterRegistrationBean.setOrder(1);
        //是否启用
        filterRegistrationBean.setEnabled(false);
        return filterRegistrationBean;
    }
}
