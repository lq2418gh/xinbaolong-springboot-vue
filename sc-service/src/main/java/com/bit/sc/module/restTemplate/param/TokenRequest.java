package com.bit.sc.module.restTemplate.param;

import lombok.Data;

/**
 * @Description: 请求token
 * @Author: liyujun
 * @Date: 2018-11-14
 **/
@Data
public class TokenRequest {


    /**
     * 客户端ID
     */
    private String loginName;


    /**
     * 客户端密钥
     */
    private String password;

}
