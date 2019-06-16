package com.bit.sc.module.equipment.service.impl;

import java.util.List;
import java.util.ArrayList;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.bit.base.vo.BaseVo;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.bit.sc.module.equipment.pojo.EquipmentType;
import com.bit.sc.module.equipment.vo.EquipmentTypeVO;
import com.bit.sc.module.equipment.dao.EquipmentTypeMapper;
import com.bit.sc.module.equipment.service.EquipmentTypeService;
import com.bit.base.service.BaseService;

/**
 * EquipmentType的Service实现类
 * @author liuyancheng
 *
 */
@Service("equipmentTypeService")
public class EquipmentTypeServiceImpl extends BaseService implements EquipmentTypeService{
	
	private static final Logger logger = LoggerFactory.getLogger(EquipmentTypeServiceImpl.class);
	
	@Autowired
	private EquipmentTypeMapper equipmentTypeMapper;
	
	/**
	 * 查询所有EquipmentType
	 * @return
	 */
	@Override
	public List<EquipmentType> findAll(){
		return equipmentTypeMapper.findAllByStatus();
	}
}
