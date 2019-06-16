package com.bit.sc.module.equipment.service;

import java.util.List;
import org.springframework.stereotype.Service;
import com.bit.sc.module.equipment.pojo.Equipment;
import com.bit.sc.module.equipment.vo.EquipmentVO;
import com.bit.base.vo.BaseVo;
/**
 * Equipment的Service
 * @author liuyancheng
 */
public interface EquipmentService {
	/**
	 * 根据条件查询Equipment
	 * @param equipmentVO
	 * @return
	 */
	BaseVo findByConditionPage(EquipmentVO equipmentVO);
	/**
	 * 查询所有Equipment
	 * @param sorter 排序字符串
	 * @return
	 */
	List<Equipment> findAll(String sorter);
	/**
	 * 通过主键查询单个Equipment
	 * @param id
	 * @return
	 */
	Equipment findById(Long id);

	/**
	 * 保存Equipment
	 * @param equipment
	 */
	void add(Equipment equipment);
	/**
	 * 更新Equipment
	 * @param equipment
	 */
	void update(Equipment equipment);
	/**
	 * 删除Equipment
	 * @param id
	 */
	void delete(Long id);
	/**
	 * 批量删除Equipment
	 * @param ids
	 */
	void batchDelete(List<Long> ids);

	/**
	 * 分页查询设备列表
	 * @param equipmentVO
	 * @return
	 */
	BaseVo findAllPage(EquipmentVO equipmentVO);

	/**
	 * 通过设备UUID查询设备对象
	 * @param deviceId
	 * @return
	 */
	Equipment findByDeviceId(String deviceId);

}
