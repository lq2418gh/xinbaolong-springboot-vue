package com.bit.sc.module.equipment.dao;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import com.bit.sc.module.equipment.pojo.EquipmentType;
import com.bit.sc.module.equipment.vo.EquipmentTypeVO;

/**
 * EquipmentType管理的Dao
 * @author liuyancheng
 *
 */
public interface EquipmentTypeMapper {
	/**
	 * 根据条件查询EquipmentType
	 * @param equipmentTypeVO
	 * @return
	 */
	public List<EquipmentType> findByConditionPage(EquipmentTypeVO equipmentTypeVO);
	/**
	 * 查询所有EquipmentType
	 * @return
	 */
	public List<EquipmentType> findAll(@Param(value = "sorter") String sorter);
	/**
	 * 通过主键查询单个EquipmentType
	 * @param id	 	 
	 * @return
	 */
	public EquipmentType findById(@Param(value = "id") Long id);
	/**
	 * 保存EquipmentType
	 * @param equipmentType
	 */
	public void add(EquipmentType equipmentType);
	/**
	 * 更新EquipmentType
	 * @param equipmentType
	 */
	public void update(EquipmentType equipmentType);
	/**
	 * 删除EquipmentType
	 */
	public void batchDelete(List<Long> ids);
	/**
	 * 删除EquipmentType
	 * @param id
	 */
	public void delete(@Param(value = "id") Long id);

	/**
	 * 查询所有已启用的类型
	 * @return
	 */
	List<EquipmentType> findAllByStatus();
}
