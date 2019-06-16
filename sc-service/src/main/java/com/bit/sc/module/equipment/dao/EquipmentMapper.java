package com.bit.sc.module.equipment.dao;

import java.util.List;
import java.util.Map;

import com.bit.sc.module.equipment.vo.EquipmentNewVO;
import org.apache.ibatis.annotations.Param;
import com.bit.sc.module.equipment.pojo.Equipment;
import com.bit.sc.module.equipment.vo.EquipmentVO;

/**
 * Equipment管理的Dao
 * @author liuyancheng
 *
 */
public interface EquipmentMapper {
	/**
	 * 根据条件查询Equipment
	 * @param equipmentVO
	 * @return
	 */
	public List<Equipment> findByConditionPage(EquipmentVO equipmentVO);
	/**
	 * 查询所有Equipment
	 * @return
	 */
	public List<Equipment> findAll(@Param(value = "sorter") String sorter);
	/**
	 * 通过主键查询单个Equipment
	 * @param id	 	 
	 * @return
	 */
	public Equipment findById(@Param(value = "id") Long id);
	/**
	 * 保存Equipment
	 * @param equipment
	 */
	public void add(Equipment equipment);
	/**
	 * 更新Equipment
	 * @param equipment
	 */
	public void update(Equipment equipment);
	/**
	 * 删除Equipment
	 * @param ids
	 */
	public void batchDelete(List<Long> ids);
	/**
	 * 删除Equipment
	 * @param id
	 */
	public void delete(@Param(value = "id") Long id);

	/**
	 * 分页查询设备列表
	 * @return
	 */
	public List<EquipmentNewVO> findAllPage(@Param("params") Map<String,Object> map);

	/**
	 * 根据设备码查询
	 * @param equipmentCode
	 * @return
	 */
	Equipment findByEquipmentCode(String equipmentCode);

	/**
	 * 根据deviceId查询对象
	 * @param deviceId
	 * @return
	 */
    Equipment findByDeviceId(String deviceId);
}
