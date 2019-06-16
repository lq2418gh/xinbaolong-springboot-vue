package com.bit.sc.module.restTemplate.dto;

import lombok.Data;

/**
 * @author liuyancheng
 * @create 2018-12-20 14:30
 */
@Data
public class OpenDoorDTO {

    private String deviceId;

    private RemoteCtrlInfo remoteCtrlInfo;
}
