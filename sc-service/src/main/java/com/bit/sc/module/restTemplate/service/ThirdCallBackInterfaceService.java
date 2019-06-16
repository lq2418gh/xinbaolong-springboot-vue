package com.bit.sc.module.restTemplate.service;

import com.bit.base.vo.BaseVo;
import com.bit.sc.module.restTemplate.dto.DeviceOnLineParamDTO;

/**
 * @author liuyancheng
 * @create 2018-12-19 16:47
 */
public interface ThirdCallBackInterfaceService {

    /**
     * 设备状态变化通知
     * @param deviceOnLineParamDTO
     * @return
     */
    BaseVo deviceOnlineMessage(DeviceOnLineParamDTO deviceOnLineParamDTO);
}
