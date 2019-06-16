package com.bit.sc.module.restTemplate.vo;

import lombok.Data;

/**
 * @Description:
 * @Author: liyujun
 * @Date: 2018-11-14
 **/
@Data
public class BaseFaceVo {

    /**
     * 返回结果code
     */
    private String code;

    /**
     * 操作结果的文本
     */
    private String message;
}
