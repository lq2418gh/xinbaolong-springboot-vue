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
import com.bit.sc.module.equipment.pojo.EquipmentType;
import com.bit.sc.module.equipment.vo.EquipmentTypeVO;
import com.bit.sc.module.equipment.service.EquipmentTypeService;
import com.bit.base.vo.BaseVo;

/**
 * EquipmentType的相关请求
 * @author liuyancheng
 */
@RestController
@RequestMapping(value = "/equipmentType")
public class EquipmentTypeController {
	private static final Logger logger = LoggerFactory.getLogger(EquipmentTypeController.class);
	@Autowired
	private EquipmentTypeService equipmentTypeService;
	
    /**
     * 查询所有设备类型
     * @return
     */
    @GetMapping("/listAll")
    public BaseVo listAll(){
        List<EquipmentType> all = equipmentTypeService.findAll();
        BaseVo baseVo = new BaseVo();
        baseVo.setData(all);
        return baseVo;
    }

}
