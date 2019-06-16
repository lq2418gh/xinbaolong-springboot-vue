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
import com.bit.sc.module.equipment.pojo.EquipmentStatus;
import com.bit.sc.module.equipment.vo.EquipmentStatusVO;
import com.bit.sc.module.equipment.service.EquipmentStatusService;
import com.bit.base.vo.BaseVo;

/**
 * EquipmentStatus的相关请求
 * @author liuyancheng
 */
@RestController
@RequestMapping(value = "/equipmentStatus")
public class EquipmentStatusController {
	private static final Logger logger = LoggerFactory.getLogger(EquipmentStatusController.class);
	@Autowired
	private EquipmentStatusService equipmentStatusService;
	

    /**
     * 分页查询EquipmentStatus列表
     *
     */
    @PostMapping("/listPage")
    public BaseVo listPage(@RequestBody EquipmentStatusVO equipmentStatusVO) {
    	//分页对象，前台传递的包含查询的参数

        return equipmentStatusService.findByConditionPage(equipmentStatusVO);
    }

    /**
     * 根据主键ID查询EquipmentStatus
     *
     * @param id
     * @return
     */
    @GetMapping("/query/{id}")
    public BaseVo query(@PathVariable(value = "id") Long id) {

        EquipmentStatus equipmentStatus = equipmentStatusService.findById(id);
		BaseVo baseVo = new BaseVo();
		baseVo.setData(equipmentStatus);
		return baseVo;
    }
    
    /**
     * 新增EquipmentStatus
     *
     * @param equipmentStatus EquipmentStatus实体
     * @return
     */
    @PostMapping("/add")
    public BaseVo add(@Valid @RequestBody EquipmentStatus equipmentStatus) {
        equipmentStatusService.add(equipmentStatus);
		BaseVo baseVo = new BaseVo();
        return baseVo;
    }
    /**
     * 修改EquipmentStatus
     *
     * @param equipmentStatus EquipmentStatus实体
     * @return
     */
    @PostMapping("/modify")
    public BaseVo modify(@Valid @RequestBody EquipmentStatus equipmentStatus) {
		equipmentStatusService.update(equipmentStatus);
		BaseVo baseVo = new BaseVo();
        return baseVo;
    }
    
    /**
     * 根据主键ID删除EquipmentStatus
     *
     * @param id
     * @return
     */
    @GetMapping("/delete/{id}")
    public BaseVo delete(@PathVariable(value = "id") Long id) {
        equipmentStatusService.delete(id);
		BaseVo baseVo = new BaseVo();
        return baseVo;
    }

}
