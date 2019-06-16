package com.bit.sc.module.house.service;

import java.util.List;

import com.bit.sc.module.house.pojo.OwnerCustodianRent;
import com.bit.sc.module.house.vo.OwnerCustodianRentVO;
import org.springframework.stereotype.Service;
import com.bit.base.vo.BaseVo;
/**
 * OwnerCustodianRent的Service
 * @author chenduo
 */
public interface OwnerCustodianRentService {
	/**
	 * 根据条件查询OwnerCustodianRent
	 * @param ownerCustodianRentVO
	 * @return
	 * @author chenduo
	 */
	BaseVo findByConditionPage(OwnerCustodianRentVO ownerCustodianRentVO);
	/**
	 * 查询所有OwnerCustodianRent
	 * @param sorter 排序字符串
	 * @return
	 * @author chenduo
	 */
	List<OwnerCustodianRent> findAll(String sorter);
	/**
	 * 通过主键查询单个OwnerCustodianRent
	 * @param id
	 * @return
	 * @author chenduo
	 */
	OwnerCustodianRent findById(Long id);

	/**
	 * 批量保存OwnerCustodianRent
	 * @param ownerCustodianRents
	 *         @author chenduo
	 */
	void batchAdd(List<OwnerCustodianRent> ownerCustodianRentList);
	/**
	 * 保存OwnerCustodianRent
	 * @param ownerCustodianRent
	 *         @author chenduo
	 */
	void add(OwnerCustodianRent ownerCustodianRent);
	/**
	 * 批量更新OwnerCustodianRent
	 * @param ownerCustodianRents
	 *         @author chenduo
	 */
	void batchUpdate(List<OwnerCustodianRent> ownerCustodianRents);
	/**
	 * 更新OwnerCustodianRent
	 * @param ownerCustodianRent
	 *         @author chenduo
	 */
	void update(OwnerCustodianRent ownerCustodianRent);
	/**
	 * 删除OwnerCustodianRent
	 * @param id
	 *                                             @author chenduo
	 */
	void delete(Long id);
	/**
	 * 批量删除OwnerCustodianRent
	 * @param ids
	 *                                         @author chenduo
	 */
	void batchDelete(List<Long> ids);
}
