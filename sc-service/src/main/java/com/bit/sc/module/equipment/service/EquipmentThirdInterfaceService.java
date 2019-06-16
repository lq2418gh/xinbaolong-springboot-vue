package com.bit.sc.module.equipment.service;

import com.bit.base.vo.BaseVo;
import com.bit.sc.module.equipment.pojo.Equipment;
import com.bit.sc.module.equipment.vo.EquipmentAuthorizeVO;
import com.bit.sc.module.restTemplate.dto.DeviceVO;


/**
 * 设备与第三方接口service
 * @author liuyancheng
 * @create 2018-12-13 13:05
 */
public interface EquipmentThirdInterfaceService {

    /**
     * 4.1 添加设备
     * @param equipment
     * @return
     */
    BaseVo addEquipment(Equipment equipment);

    /**
     * 4.2 更新设备
     * @param equipment
     * @return
     */
    BaseVo updateEquipment(Equipment equipment);

    /**
     * 4.3 移除设备
     * @param id 设备表主键id
     * @return
     */
    BaseVo deleteUpdate(Long id);

    /**
     * 5.1 设置分组权限（添加设备到分组）
     * @param deviceVO
     * @return
     */
    BaseVo bindDevices(DeviceVO deviceVO);

    /**
     * 绑定设备到分组
     * @param equipmentAuthorizeVO
     * @return
     */
    BaseVo bindDevicesToGroup(EquipmentAuthorizeVO equipmentAuthorizeVO);

    /**
     * 移除设备到分组，支持批量
     * @param equipmentAuthorizeVO
     * @return
     */
    BaseVo unBindDevicesToGroup(EquipmentAuthorizeVO equipmentAuthorizeVO);

    /**
     * 设备启用
     * @param equipment
     * @return
     */
    BaseVo blockUp(Equipment equipment);
}
