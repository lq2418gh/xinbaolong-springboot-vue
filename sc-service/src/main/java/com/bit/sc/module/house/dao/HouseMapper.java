package com.bit.sc.module.house.dao;

import java.util.List;

import com.bit.sc.module.house.pojo.House;
import com.bit.sc.module.house.vo.*;
import org.apache.ibatis.annotations.Param;

/**
 * 一标三实的相关请求的dao
 * @author chenduo
 *
 */
public interface HouseMapper {
	/**
	 * 根据条件查询House
	 * @param houseVO
	 * @return
	 * @author chenduo
	 */
	public List<House> findByConditionPage(HouseVO houseVO);
	/**
	 * 查询所有House
	 * @return
	 * @author chenduo
	 */
	public List<House> findAll(@Param(value = "sorter") String sorter);
	/**
	 * 通过主键查询单个House
	 * @param id	 	 
	 * @return
	 * @author chenduo
	 */
	public House findById(@Param(value = "id") Long id);
	/**
	 * 批量保存House
	 * @param houses
	 *         @author chenduo
	 */
	public void batchAdd(@Param(value = "houseList") List<House> houseList);
	/**
	 * 保存House
	 * @param house
	 *         @author chenduo
	 */
	public void add(House house);

	/**
	 * 更新House
	 * @param house
	 *         @author chenduo
	 */
	public void update(House house);
	/**
	 * 删除House
	 * @param houses
	 *         @author chenduo
	 */
	public void batchDelete(List<Long> ids);
	/**
	 * 删除House
	 * @param id
	 * @author chenduo
	 */
	public void delete(@Param(value = "id") Long id);
	/**
	 *
	 * 功能描述:联合字典表分页查询
	 *
	 * @param:
	 * @return:
	 * @author: chenduo
	 * @date: 2018/11/24 11:15
	 */
	List<HouseDictVO> querylistAllDictByPage(HouseVO houseVO);
    /**
     *
     * 功能描述:联合字典表公司表房东表分页查询
     *
     * @param:
     * @return:
     * @author: chenduo
     * @date: 2018/11/24 11:15
     */
    List<HouseDictVO> querylistAllDictByOwnerPage(HouseVO houseVO);

	/**
	 *
	 * 功能描述:根据房子找实有人员分页查询
	 *
	 * @param:
	 * @return:
	 * @author: chenduo
	 * @date: 2018/11/26 13:32
	 */
    List<RealPersonHouseVO> getrealPersonByPage(HouseVO houseVO);

    /**
     *
     * 功能描述:根据房子找户籍人员分页查询
     *
     * @param:
     * @return:
     * @author: chenduo
     * @date: 2018/11/26 13:32
     */
    List<RegisteredResidentHouseVO> getregisteredResidentByPage(HouseVO houseVO);

    /**
     *
     * 功能描述:一标三实页面初始化加载已填报的房屋地址
     *
     * @param:
     * @return:
     * @author: chenduo
     * @date: 2018/11/27 10:33
     */
    List<HouseAddressVO> getAddress(HouseVO houseVO);
}
