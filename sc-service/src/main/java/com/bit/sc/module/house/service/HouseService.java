package com.bit.sc.module.house.service;

import com.bit.base.vo.BaseVo;
import com.bit.sc.module.house.vo.HouseCompanyOwnerVO;
import com.bit.sc.module.house.vo.HouseVO;

/**
 * House的Service
 * @author chenduo
 */
public interface HouseService {
	/**
	 * 根据条件查询House
	 * @param houseVO
	 * @return
	 * @author chenduo
	 */
	BaseVo findByConditionPage(HouseVO houseVO);

	/**
	 * 通过主键查询单个House
	 * @param id
	 * @return
	 * @author chenduo
	 */
	HouseCompanyOwnerVO findById(Long id);

	/**
	 * 保存House
	 * @param houseVO
	 * @author chenduo
	 */

	BaseVo add(HouseVO houseVO);



	/**
	 * 更新House
	 * @param houseVO
	 * @author chenduo
	 */
	void update(HouseVO houseVO);
	/**
	 * 删除House
	 * @param id
	 * @author chenduo
	 */
	void delete(Long id);




	/**
	 *
	 * 功能描述:一标三实页面初始化加载已填报的房屋地址
	 *
	 * @param:
	 * @return:
	 * @author: chenduo
	 * @date: 2018/11/27 10:33
	 */
    BaseVo getAddress(HouseVO houseVO);
    /**
     *
     * 功能描述:同步一标三实
     *
     * @param:
     * @return:
     * @author: chenduo
     * @date: 2018/11/27 13:13
     */
    BaseVo synchronize(Long id);


}
