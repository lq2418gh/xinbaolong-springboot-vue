package com.bit.sc.module.equipment.service;

import java.util.List;
import org.springframework.stereotype.Service;
import com.bit.sc.module.equipment.pojo.EquipmentModelNumber;
import com.bit.sc.module.equipment.vo.EquipmentModelNumberVO;
import com.bit.base.vo.BaseVo;
/**
 * EquipmentModelNumber的Service
 * @author liuyancheng
 */
public interface EquipmentModelNumberService {
	/**
	 * 根据条件查询EquipmentModelNumber
	 * @param equipmentModelNumberVO
	 * @return
	 */
	BaseVo findByConditionPage(EquipmentModelNumberVO equipmentModelNumberVO);
	/**
	 * 查询所有EquipmentModelNumber
	 * @param sorter 排序字符串
	 * @return
	 */
	List<EquipmentModelNumber> findAll(String sorter);
	/**
	 * 通过主键查询单个EquipmentModelNumber
	 * @param id
	 * @return
	 */
	EquipmentModelNumber findById(Long id);

	/**
	 * 保存EquipmentModelNumber
	 * @param equipmentModelNumber
	 */
	void add(EquipmentModelNumber equipmentModelNumber);
	/**
	 * 更新EquipmentModelNumber
	 * @param equipmentModelNumber
	 */
	void update(EquipmentModelNumber equipmentModelNumber);
	/**
	 * 删除EquipmentModelNumber
	 * @param id
	 */
	void delete(Long id);
	/**
	 * 批量删除EquipmentModelNumber
	 * @param ids
	 */
	void batchDelete(List<Long> ids);

	/**
	 * 根据设备类型查询型号
	 * @return
	 */
	BaseVo findByType(EquipmentModelNumber equipmentModelNumber);
	/**
	 * 查询所有已启用的设备型号
	 * @return
	 */
	BaseVo findAllByStatus();
}
