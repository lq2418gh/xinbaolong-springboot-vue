package com.bit.sc.module.restTemplate.dto;


import com.bit.sc.module.restTemplate.vo.BaseFaceVo;
import lombok.Data;


/**
 * @Description:
 * @Author: liyujun
 * @Date: 2018-11-14
 **/
@Data
public class FaceCloudToken {


    /**
     * 返回结果code
     */
    private String accessToken;

    /**
     * 返回结果code
     */
    private String refreshToken;

    /**
     * 认证token的有效时间（秒）
     */
    private  String accessTokenExp;

    /**
     * 刷新token的有效时间（秒）
     */
    private  String refreshTokenExp;




}
