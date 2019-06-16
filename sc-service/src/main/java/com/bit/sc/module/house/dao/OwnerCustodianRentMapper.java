package com.bit.sc.module.house.dao;

import java.util.List;

import com.bit.sc.module.house.pojo.OwnerCustodianRent;
import com.bit.sc.module.house.vo.OwnerCustodianRentVO;
import org.apache.ibatis.annotations.Param;

/**
 * OwnerCustodianRent管理的Dao
 * @author chenduo
 *
 */
public interface OwnerCustodianRentMapper {
	/**
	 * 根据条件查询OwnerCustodianRent
	 * @param ownerCustodianRentVO
	 * @return
	 * @author chenduo
	 */
	public List<OwnerCustodianRent> findByConditionPage(OwnerCustodianRentVO ownerCustodianRentVO);
	/**
	 * 查询所有OwnerCustodianRent
	 * @return
	 * @author chenduo
	 */
	public List<OwnerCustodianRent> findAll(@Param(value = "sorter") String sorter);
	/**
	 * 通过主键查询单个OwnerCustodianRent
	 * @param id	 	 
	 * @return
	 * @author chenduo
	 */
	public OwnerCustodianRent findById(@Param(value = "id") Long id);
	/**
	 * 批量保存OwnerCustodianRent
	 * @param ownerCustodianRents
	 *         @author chenduo
	 */
	public void batchAdd(@Param(value = "ownerCustodianRentList") List<OwnerCustodianRent> ownerCustodianRentList);
	/**
	 * 保存OwnerCustodianRent
	 * @param ownerCustodianRent
	 *         @author chenduo
	 */
	public void add(OwnerCustodianRent ownerCustodianRent);
	/**
	 * 批量更新OwnerCustodianRent
	 * @param ownerCustodianRents
	 *         @author chenduo
	 */
	public void batchUpdate(List<OwnerCustodianRent> ownerCustodianRents);
	/**
	 * 更新OwnerCustodianRent
	 * @param ownerCustodianRent
	 *         @author chenduo
	 */
	public void update(OwnerCustodianRent ownerCustodianRent);
	/**
	 * 删除OwnerCustodianRent
	 * @param ownerCustodianRents
	 *         @author chenduo
	 */
	public void batchDelete(List<Long> ids);
	/**
	 * 删除OwnerCustodianRent
	 * @param id
	 * @author chenduo
	 */
	public void delete(@Param(value = "id") Long id);
}
