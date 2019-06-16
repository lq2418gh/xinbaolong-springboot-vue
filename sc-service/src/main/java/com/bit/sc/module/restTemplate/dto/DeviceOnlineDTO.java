package com.bit.sc.module.restTemplate.dto;

import lombok.Data;

/**
 * @author liuyancheng
 * @create 2018-12-17 13:36
 */
@Data
public class DeviceOnlineDTO {
    /**
     * 设备ID
     */
    private String deviceId;
    /**
     * 在线状态
     * 0：离线；
     * 1：上线；
     * 2：刷新中；
     */
    private String online;
}
