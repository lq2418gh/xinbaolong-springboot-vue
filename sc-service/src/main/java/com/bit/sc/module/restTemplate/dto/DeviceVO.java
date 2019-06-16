package com.bit.sc.module.restTemplate.dto;

import com.bit.sc.module.restTemplate.vo.ResultDTO;
import lombok.Data;

import java.util.List;

/**
 * @author liuyancheng
 * @create 2018-12-05 14:36
 */
@Data
public class DeviceVO extends ResultDTO {
    /**
     * 设备配置列表
     */
    private List<DeviceConfigDTO> confList;
    /**
     * 设备ID
     */
    private String deviceId;
    /**
     * 控制码
     */
    private Integer ctrlCode;
    private String data;
    /**
     * 设备ID
     */
    private List deviceIdList;
    /**
     * 分组ID
     */
    private List groupIdList;


    private String groupId;


    /**
     *  组织ID
     */
    private String orgId;
}
