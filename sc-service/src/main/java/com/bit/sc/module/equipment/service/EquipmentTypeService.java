package com.bit.sc.module.equipment.service;

import java.util.List;
import org.springframework.stereotype.Service;
import com.bit.sc.module.equipment.pojo.EquipmentType;
import com.bit.sc.module.equipment.vo.EquipmentTypeVO;
import com.bit.base.vo.BaseVo;
/**
 * EquipmentType的Service
 * @author liuyancheng
 */
public interface EquipmentTypeService {

	/**
	 * 查询所有EquipmentType
	 * @return
	 */
	List<EquipmentType> findAll();
}
