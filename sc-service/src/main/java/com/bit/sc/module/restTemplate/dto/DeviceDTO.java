package com.bit.sc.module.restTemplate.dto;

import com.bit.sc.module.restTemplate.vo.ResultDTO;
import lombok.Data;

import java.util.List;

/**
 * @author liuyancheng
 * @create 2018-12-04 13:54
 * 设备实体类
 */
@Data
public class DeviceDTO extends ResultDTO {
    /**
     * 设备id
     */
    private String deviceId;
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
     * 标签名
     */
    private List tagName;
    /**
     * 备注
     */
    private String memo;
    /**
     * 设备密钥
     */
    private String secretKey;
    /**
     * 消息服务器地址
     */
    private String mqServer;
    /**
     * 组织ID
     */
    private String orgId;
    /**
     * 设备初始绑定认证key 平台唯一  16位
     */
    private String authKey;
    /**
     * 设备ID
     */
    private List<String> deviceIdList;

}
