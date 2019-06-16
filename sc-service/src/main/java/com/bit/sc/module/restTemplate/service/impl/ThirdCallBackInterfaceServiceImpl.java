package com.bit.sc.module.restTemplate.service.impl;

import com.bit.base.vo.BaseVo;
import com.bit.sc.module.equipment.dao.EquipmentMapper;
import com.bit.sc.module.equipment.pojo.Equipment;
import com.bit.sc.module.restTemplate.dto.DeviceOnLineParamDTO;
import com.bit.sc.module.restTemplate.dto.DeviceOnlineDTO;
import com.bit.sc.module.restTemplate.service.ThirdCallBackInterfaceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author liuyancheng
 * @create 2018-12-19 16:47
 */
@Service("ThirdCallBackInterfaceService")
public class ThirdCallBackInterfaceServiceImpl implements ThirdCallBackInterfaceService {

    @Autowired
    private EquipmentMapper equipmentMapper;

    @Override
    public BaseVo deviceOnlineMessage(DeviceOnLineParamDTO deviceOnLineParamDTO) {

        //获取入参对象
        List<DeviceOnlineDTO> statusChanged = deviceOnLineParamDTO.getStatusChanged();

        //0：离线；
        //1：上线；
        //2：刷新中；
        if (statusChanged.size() > 0){
            for (DeviceOnlineDTO deviceOnlineDTO : statusChanged) {
                String deviceId = deviceOnlineDTO.getDeviceId();
                String online = deviceOnlineDTO.getOnline();
                Equipment equipment = equipmentMapper.findByDeviceId(deviceId);
                if (equipment != null){
                    //增加判断，如果数据库中的状态与传过来的状态一致，则不更新数据库
                    if(!equipment.getEquipmentOnlineStatus().equals(Integer.valueOf(online))){
                        equipment.setEquipmentOnlineStatus(Integer.valueOf(online));
                        equipmentMapper.update(equipment);
                    }
                }
            }
        }
        BaseVo baseVo = new BaseVo();
        return baseVo;
    }
}
