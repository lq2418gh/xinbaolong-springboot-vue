package com.bit.sc.module.house.service;

import com.bit.base.vo.BaseVo;
import com.bit.sc.module.house.pojo.RegisteredResident;

import java.util.List;
/**
 * RegisteredResident的Service
 * @author chenduo
 */
public interface RegisteredResidentService {


	/**
	 * 通过主键查询单个RegisteredResident
	 * @param id
	 * @return
	 * @author chenduo
	 */
	RegisteredResident findById(Long id);


	/**
	 * 保存RegisteredResident
	 * @param registeredResident
	 *         @author chenduo
	 */
	BaseVo add(RegisteredResident registeredResident);

	/**
	 * 更新RegisteredResident
	 * @param registeredResident
	 * @author chenduo
	 */
	void update(RegisteredResident registeredResident);
	/**
	 * 删除RegisteredResident
	 * @param id
	 * @author chenduo
	 */
	void delete(Long id);




	/**
	 *
	 * 功能描述:根据houseid查询结果
	 *
	 * @param:
	 * @return:
	 * @author: chenduo
	 * @date: 2018/12/3 11:55
	 */
	List<RegisteredResident> findByHouseId(Long id);
}
