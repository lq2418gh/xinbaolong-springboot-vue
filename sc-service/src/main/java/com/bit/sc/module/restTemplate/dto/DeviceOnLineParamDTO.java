package com.bit.sc.module.restTemplate.dto;

import lombok.Data;

import java.util.List;

/**
 * @author liuyancheng
 * @create 2018-12-19 16:44
 */
@Data
public class DeviceOnLineParamDTO {
    private List<DeviceOnlineDTO> statusChanged;
}
