package com.bit.sc.module.restTemplate.param;

import lombok.Data;

import java.util.List;

/**
 * @Description: 设备新建请求实体
 * @Author: liyujun
 * @Date: 2018-11-14
 **/
@Data
public class DeviceRequest {

    /**
     * 设备序列号
     */
    private String deviceCode;

    /**
     * 设备名
     */
    private String deviceName;

    /**
     * 设备类别
     */
    private Integer deviceType;

    /**
     * 部署场所
     */
    private String position;

    /**
     * 备注
     */
    private String memo ;

    /**
     * 标签名
     */
    private List<String> tagName;

}
