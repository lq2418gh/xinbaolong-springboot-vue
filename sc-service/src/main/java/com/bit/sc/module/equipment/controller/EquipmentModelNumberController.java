package com.bit.sc.module.equipment.controller;

import java.io.IOException;
import java.util.List;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import com.bit.base.exception.BusinessException;
import com.bit.sc.module.equipment.pojo.EquipmentModelNumber;
import com.bit.sc.module.equipment.vo.EquipmentModelNumberVO;
import com.bit.sc.module.equipment.service.EquipmentModelNumberService;
import com.bit.base.vo.BaseVo;

/**
 * EquipmentModelNumber的相关请求
 * @author liuyancheng
 */
@RestController
@RequestMapping(value = "/equipmentModelNumber")
public class EquipmentModelNumberController {
	private static final Logger logger = LoggerFactory.getLogger(EquipmentModelNumberController.class);
	@Autowired
	private EquipmentModelNumberService equipmentModelNumberService;
	

    /**
     * 分页查询EquipmentModelNumber列表
     *
     */
    @PostMapping("/listPage")
    public BaseVo listPage(@RequestBody EquipmentModelNumberVO equipmentModelNumberVO) {
    	//分页对象，前台传递的包含查询的参数

        return equipmentModelNumberService.findByConditionPage(equipmentModelNumberVO);
    }

    /**
     * 新增EquipmentModelNumber
     *
     * @param equipmentModelNumber EquipmentModelNumber实体
     * @return
     */
    @PostMapping("/add")
    public BaseVo add(@Valid @RequestBody EquipmentModelNumber equipmentModelNumber) {
        equipmentModelNumberService.add(equipmentModelNumber);
		BaseVo baseVo = new BaseVo();
        return baseVo;
    }
    /**
     * 修改EquipmentModelNumber
     *
     * @param equipmentModelNumber EquipmentModelNumber实体
     * @return
     */
    @PostMapping("/modify")
    public BaseVo modify(@Valid @RequestBody EquipmentModelNumber equipmentModelNumber) {
		equipmentModelNumberService.update(equipmentModelNumber);
		BaseVo baseVo = new BaseVo();
        return baseVo;
    }
    
    /**
     * 根据主键ID删除EquipmentModelNumber
     *
     * @param id
     * @return
     */
    @GetMapping("/delete/{id}")
    public BaseVo delete(@PathVariable(value = "id") Long id) {
        equipmentModelNumberService.delete(id);
		BaseVo baseVo = new BaseVo();
        return baseVo;
    }

    @PostMapping("/findByType")
    public BaseVo findByType(@Valid @RequestBody EquipmentModelNumber equipmentModelNumber){
        return equipmentModelNumberService.findByType(equipmentModelNumber);
    }

    /**
     * 查询所有已启用的设备型号
     * @return
     */
    @PostMapping("/findAll")
    public BaseVo findAllByStatus(){
        return equipmentModelNumberService.findAllByStatus();
    }
}
