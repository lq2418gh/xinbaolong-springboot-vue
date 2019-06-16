package com.bit.sc.module.equipment.dao;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import com.bit.sc.module.equipment.pojo.EquipmentModelNumber;
import com.bit.sc.module.equipment.vo.EquipmentModelNumberVO;

/**
 * EquipmentModelNumber管理的Dao
 * @author liuyancheng
 *
 */
public interface EquipmentModelNumberMapper {
	/**
	 * 根据条件查询EquipmentModelNumber
	 * @param equipmentModelNumberVO
	 * @return
	 */
	public List<EquipmentModelNumber> findByConditionPage(EquipmentModelNumberVO equipmentModelNumberVO);
	/**
	 * 查询所有EquipmentModelNumber
	 * @return
	 */
	public List<EquipmentModelNumber> findAll(@Param(value = "sorter") String sorter);
	/**
	 * 通过主键查询单个EquipmentModelNumber
	 * @param id	 	 
	 * @return
	 */
	public EquipmentModelNumber findById(@Param(value = "id") Long id);
	/**
	 * 保存EquipmentModelNumber
	 * @param equipmentModelNumber
	 */
	public void add(EquipmentModelNumber equipmentModelNumber);
	/**
	 * 更新EquipmentModelNumber
	 * @param equipmentModelNumber
	 */
	public void update(EquipmentModelNumber equipmentModelNumber);
	/**
	 * 删除EquipmentModelNumber
	 */
	public void batchDelete(List<Long> ids);
	/**
	 * 删除EquipmentModelNumber
	 * @param id
	 */
	public void delete(@Param(value = "id") Long id);

	/**
	 * 根据设备型号查询
	 * @param equipmentModelNumber
	 * @return
	 */
	EquipmentModelNumber findByEquipmentNumber(EquipmentModelNumber equipmentModelNumber);

	/**
	 * 根据设备类型查询
	 * @param equipmentModelNumber
	 * @return
	 */
	List<EquipmentModelNumber> findByType(EquipmentModelNumber equipmentModelNumber);

	/**
	 * 查询所有已启用的设备型号
	 * @return
	 */
	List<EquipmentModelNumber> findAllByStatus();
}
