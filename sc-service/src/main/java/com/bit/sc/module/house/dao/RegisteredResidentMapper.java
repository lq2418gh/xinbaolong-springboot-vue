package com.bit.sc.module.house.dao;

import java.util.List;

import com.bit.sc.module.house.pojo.RegisteredResident;
import com.bit.sc.module.house.vo.RegisteredResidentDictVO;
import com.bit.sc.module.house.vo.RegisteredResidentHouseVO;
import com.bit.sc.module.house.vo.RegisteredResidentVO;
import org.apache.ibatis.annotations.Param;

/**
 * RegisteredResident管理的Dao
 * @author chenduo
 *
 */
public interface RegisteredResidentMapper {
	/**
	 * 根据条件查询RegisteredResident
	 * @param registeredResidentVO
	 * @return
	 * @author chenduo
	 */
	public List<RegisteredResident> findByConditionPage(RegisteredResidentVO registeredResidentVO);
	/**
	 * 查询所有RegisteredResident
	 * @return
	 * @author chenduo
	 */
	public List<RegisteredResident> findAll(@Param(value = "sorter") String sorter);
	/**
	 * 通过主键查询单个RegisteredResident
	 * @param id	 	 
	 * @return
	 * @author chenduo
	 */
	public RegisteredResident findById(@Param(value = "id") Long id);
	/**
	 * 批量保存RegisteredResident
	 * @param registeredResidents
	 *         @author chenduo
	 */
	public void batchAdd(@Param(value = "registeredResidentList") List<RegisteredResident> registeredResidentList);
	/**
	 * 保存RegisteredResident
	 * @param registeredResident
	 *         @author chenduo
	 */
	public void add(RegisteredResident registeredResident);
	/**
	 * 批量更新RegisteredResident
	 * @param registeredResidents
	 *         @author chenduo
	 */
	public void batchUpdate(List<RegisteredResident> registeredResidents);
	/**
	 * 更新RegisteredResident
	 * @param registeredResident
	 *         @author chenduo
	 */
	public void update(RegisteredResident registeredResident);
	/**
	 * 删除RegisteredResident
	 * @param registeredResidents
	 *         @author chenduo
	 */
	public void batchDelete(List<Long> ids);
	/**
	 * 删除RegisteredResident
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
	List<RegisteredResidentDictVO> querylistAllDictByPage(RegisteredResidentVO registeredResidentVO);

    /**
     *
     * 功能描述:根据户籍人口查询房子
     *
     * @param:
     * @return:
     * @author: chenduo
     * @date: 2018/11/26 14:33
     */
	List<RegisteredResidentHouseVO> gethouseByPage(RegisteredResidentVO registeredResidentVO);

	List<RegisteredResident> findByHouseId(@Param(value = "houseId") Long houseId);
}
