package com.bit.sc.module.equipment.service;

import java.util.List;
import org.springframework.stereotype.Service;
import com.bit.sc.module.equipment.pojo.EquipmentStatus;
import com.bit.sc.module.equipment.vo.EquipmentStatusVO;
import com.bit.base.vo.BaseVo;
/**
 * EquipmentStatus的Service
 * @author liuyancheng
 */
public interface EquipmentStatusService {
	/**
	 * 根据条件查询EquipmentStatus
	 * @param equipmentStatusVO
	 * @return
	 */
	BaseVo findByConditionPage(EquipmentStatusVO equipmentStatusVO);
	/**
	 * 查询所有EquipmentStatus
	 * @param sorter 排序字符串
	 * @return
	 */
	List<EquipmentStatus> findAll(String sorter);
	/**
	 * 通过主键查询单个EquipmentStatus
	 * @param id
	 * @return
	 */
	EquipmentStatus findById(Long id);

	/**
	 * 批量保存EquipmentStatus
	 * @param equipmentStatuss
	 */
	void batchAdd(List<EquipmentStatus> equipmentStatuss);
	/**
	 * 保存EquipmentStatus
	 * @param equipmentStatus
	 */
	void add(EquipmentStatus equipmentStatus);
	/**
	 * 批量更新EquipmentStatus
	 * @param equipmentStatuss
	 */
	void batchUpdate(List<EquipmentStatus> equipmentStatuss);
	/**
	 * 更新EquipmentStatus
	 * @param equipmentStatus
	 */
	void update(EquipmentStatus equipmentStatus);
	/**
	 * 删除EquipmentStatus
	 * @param id
	 */
	void delete(Long id);
	/**
	 * 批量删除EquipmentStatus
	 * @param ids
	 */
	void batchDelete(List<Long> ids);
}
