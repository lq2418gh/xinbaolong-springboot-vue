package com.bit.sc.module.equipment.dao;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import com.bit.sc.module.equipment.pojo.EquipmentStatus;
import com.bit.sc.module.equipment.vo.EquipmentStatusVO;

/**
 * EquipmentStatus管理的Dao
 * @author liuyancheng
 *
 */
public interface EquipmentStatusMapper {
	/**
	 * 根据条件查询EquipmentStatus
	 * @param equipmentStatusVO
	 * @return
	 */
	public List<EquipmentStatus> findByConditionPage(EquipmentStatusVO equipmentStatusVO);
	/**
	 * 查询所有EquipmentStatus
	 * @return
	 */
	public List<EquipmentStatus> findAll(@Param(value = "sorter") String sorter);
	/**
	 * 通过主键查询单个EquipmentStatus
	 * @param id	 	 
	 * @return
	 */
	public EquipmentStatus findById(@Param(value = "id") Long id);
	/**
	 * 批量保存EquipmentStatus
	 * @param equipmentStatuss
	 */
	public void batchAdd(List<EquipmentStatus> equipmentStatuss);
	/**
	 * 保存EquipmentStatus
	 * @param equipmentStatus
	 */
	public void add(EquipmentStatus equipmentStatus);
	/**
	 * 批量更新EquipmentStatus
	 * @param equipmentStatuss
	 */
	public void batchUpdate(List<EquipmentStatus> equipmentStatuss);
	/**
	 * 更新EquipmentStatus
	 * @param equipmentStatus
	 */
	public void update(EquipmentStatus equipmentStatus);
	/**
	 * 删除EquipmentStatus
	 * @param equipmentStatuss
	 */
	public void batchDelete(List<Long> ids);
	/**
	 * 删除EquipmentStatus
	 * @param id
	 */
	public void delete(@Param(value = "id") Long id);
}
