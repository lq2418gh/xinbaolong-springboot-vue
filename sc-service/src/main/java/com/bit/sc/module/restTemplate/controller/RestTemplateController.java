package com.bit.sc.module.restTemplate.controller;

import com.bit.base.vo.BaseVo;
import com.bit.sc.module.restTemplate.dto.CallBackDTO;
import com.bit.sc.module.restTemplate.dto.DeviceOnLineParamDTO;
import com.bit.sc.module.restTemplate.service.ThirdCallBackInterfaceService;
import com.bit.sc.module.restTemplate.service.ThirdPartyInterfaceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author liuyancheng
 * @create 2018-11-28 19:16
 */
@RestController
@RequestMapping(value = "/restTemplate")
public class RestTemplateController {

    @Autowired
    private ThirdCallBackInterfaceService thirdCallBackInterfaceService;
    @Autowired
    private ThirdPartyInterfaceService thirdPartyInterfaceService;

    /**
     * 创建接口回调
     * @param callBackDTO
     * @return
     */
    @PostMapping("/createCallBack")
    public BaseVo createCallBack(@RequestBody CallBackDTO callBackDTO){
        thirdPartyInterfaceService.createCallBack(callBackDTO);
        BaseVo baseVo = new BaseVo();
        return baseVo;
    }

    /**
     * 设备状态变化通知
     * @param deviceOnLineParamDTO
     * @return
     */
    @PostMapping("/deviceOnlineMessage")
    public BaseVo deviceOnlineMessage(@RequestBody DeviceOnLineParamDTO deviceOnLineParamDTO){
        return thirdCallBackInterfaceService.deviceOnlineMessage(deviceOnLineParamDTO);
    }
}
