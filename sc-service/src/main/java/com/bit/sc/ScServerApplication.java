package com.bit.sc;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.transaction.annotation.EnableTransactionManagement;
/**
 * @Description:
 * @Author: liyujun
 * @Date: 2018-10-24
 **/
@EnableScheduling
@SpringBootApplication
@EnableTransactionManagement
@ServletComponentScan//识别filter
@MapperScan("com.bit.sc.module.*.dao")
public class ScServerApplication {


    public static void main(String[] args) {
        SpringApplication.run(ScServerApplication.class, args);
    }

}
