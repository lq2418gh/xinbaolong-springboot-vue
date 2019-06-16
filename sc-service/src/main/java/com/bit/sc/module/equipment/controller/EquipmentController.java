package com.bit.sc.module.equipment.controller;

import javax.validation.Valid;

import com.bit.sc.module.equipment.service.EquipmentThirdInterfaceService;
import com.bit.sc.module.equipment.vo.EquipmentAuthorizeVO;
import com.bit.sc.module.restTemplate.dto.DeviceOnLineParamDTO;
import com.bit.sc.module.restTemplate.dto.DeviceVO;
import com.bit.sc.module.restTemplate.service.ThirdCallBackInterfaceService;
import com.sun.xml.internal.rngom.parse.host.Base;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.bit.sc.module.equipment.pojo.Equipment;
import com.bit.sc.module.equipment.vo.EquipmentVO;
import com.bit.sc.module.equipment.service.EquipmentService;
import com.bit.base.vo.BaseVo;

/**
 * Equipment的相关请求
 * @author liuyancheng
 */
@RestController
@RequestMapping(value = "/equipment")
public class EquipmentController {
	private static final Logger logger = LoggerFactory.getLogger(EquipmentController.class);
	@Autowired
	private EquipmentService equipmentService;
	@Autowired
    private EquipmentThirdInterfaceService equipmentThirdInterfaceService;
	@Autowired
    private ThirdCallBackInterfaceService thirdCallBackInterfaceService;
	

    /**
     * 分页查询Equipment列表
     *
     */
    @PostMapping("/listPage")
    public BaseVo listPage(@RequestBody EquipmentVO equipmentVO) {
    	//分页对象，前台传递的包含查询的参数
        return equipmentService.findAllPage(equipmentVO);
    }

    /**
     * 新增Equipment
     *
     * @param equipment Equipment实体
     * @return
     */
    @PostMapping("/add")
    public BaseVo add(@Valid @RequestBody Equipment equipment) {
        return equipmentThirdInterfaceService.addEquipment(equipment);
    }

    /**
     * 修改Equipment
     *
     * @param equipment Equipment实体
     * @return
     */
    @PostMapping("/modify")
    public BaseVo modify(@Valid @RequestBody Equipment equipment) {
        equipmentThirdInterfaceService.updateEquipment(equipment);
		BaseVo baseVo = new BaseVo();
        return baseVo;
    }
    
    /**
     * 根据主键ID删除Equipment
     *
     * @param id
     * @return
     */
    @GetMapping("/delete/{id}")
    public BaseVo delete(@PathVariable(value = "id") Long id) {
        BaseVo baseVo = equipmentThirdInterfaceService.deleteUpdate(id);
        return baseVo;
    }

    /**
     * 设备停用
     * @param equipment
     * @return
     */
    @PostMapping("/blockUpOrStartUp")
    public BaseVo blockUpOrStartUp(@RequestBody Equipment equipment){
        BaseVo baseVo = equipmentThirdInterfaceService.blockUp(equipment);
        return baseVo;
    }

    /**
     * 给设备分组,修改分组，取消分组
     * @param deviceVO
     * @return
     */
    @PostMapping("/bindDevices")
    public BaseVo bindDevices(@RequestBody DeviceVO deviceVO){
        return equipmentThirdInterfaceService.bindDevices(deviceVO);
    }

    /**
     * 设备批量授权分组接口
     * @param equipmentAuthorizeVO
     * @return
     */
    @PostMapping("/bindDevicesToGroup")
    public BaseVo bindDevicesToGroup(@RequestBody EquipmentAuthorizeVO equipmentAuthorizeVO){
        return equipmentThirdInterfaceService.bindDevicesToGroup(equipmentAuthorizeVO);
    }

    /**
     * 设备批量取消授权分组接口
     * @param equipmentAuthorizeVO
     * @return
     */
    @PostMapping("/unBindDevicesToGroup")
    public BaseVo unBindDevicesToGroup(@RequestBody EquipmentAuthorizeVO equipmentAuthorizeVO){
        return equipmentThirdInterfaceService.unBindDevicesToGroup(equipmentAuthorizeVO);
    }

    /**
     * 远程开门接口
     * @param openDoorDTO
     * @return
     */
//    @PostMapping("/openDoor")
//    public BaseVo openDoor(OpenDoorDTO openDoorDTO){
////        return equipmentThirdInterfaceService.openDoor(openDoorDTO);
//        return null;
//    }

    /**
     * 7.5 设备在线状态回调
     * @param deviceOnLineParamDTO
     * @return
     */
    @PostMapping("/deviceOnlineMessage")
    public BaseVo deviceOnlineMessage(@RequestBody DeviceOnLineParamDTO deviceOnLineParamDTO){
        return thirdCallBackInterfaceService.deviceOnlineMessage(deviceOnLineParamDTO);
    }
}
