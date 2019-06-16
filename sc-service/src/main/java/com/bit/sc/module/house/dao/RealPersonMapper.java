package com.bit.sc.module.house.dao;

import java.util.List;

import com.bit.sc.module.house.pojo.RealPerson;
import com.bit.sc.module.house.vo.RealPersonDictVO;
import com.bit.sc.module.house.vo.RealPersonHouseVO;
import com.bit.sc.module.house.vo.RealPersonVO;
import org.apache.ibatis.annotations.Param;

/**
 * RealPerson管理的Dao
 * @author chenduo
 *
 */
public interface RealPersonMapper {
	/**
	 * 根据条件查询RealPerson
	 * @param realPersonVO
	 * @return
	 * @author chenduo
	 */
	public List<RealPerson> findByConditionPage(RealPersonVO realPersonVO);
	/**
	 * 查询所有RealPerson
	 * @return
	 * @author chenduo
	 */
	public List<RealPerson> findAll(@Param(value = "sorter") String sorter);
	/**
	 * 通过主键查询单个RealPerson
	 * @param id	 	 
	 * @return
	 * @author chenduo
	 */
	public RealPerson findById(@Param(value = "id") Long id);
	/**
	 * 批量保存RealPerson
	 * @param realPersons
	 *         @author chenduo
	 */
	public void batchAdd(@Param(value = "realPersonList") List<RealPerson> realPersonList);
	/**
	 * 保存RealPerson
	 * @param realPerson
	 *         @author chenduo
	 */
	public void add(RealPerson realPerson);
	/**
	 * 批量更新RealPerson
	 * @param realPersons
	 *         @author chenduo
	 */
	public void batchUpdate(List<RealPerson> realPersons);
	/**
	 * 更新RealPerson
	 * @param realPerson
	 *         @author chenduo
	 */
	public void update(RealPerson realPerson);
	/**
	 * 删除RealPerson
	 * @param realPersons
	 *         @author chenduo
	 */
	public void batchDelete(List<Long> ids);
	/**
	 * 删除RealPerson
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
	List<RealPersonDictVO> querylistAllDictByPage(RealPersonVO realPersonVO);
	/**
	 *
	 * 功能描述:根据实有人员信息找房子信息
	 *
	 * @param:
	 * @return:
	 * @author: chenduo
	 * @date: 2018/11/26 11:52
	 */
	List<RealPersonHouseVO> gethouseByPage(RealPersonVO realPersonVO);

	List<RealPerson> findByHouseId(@Param(value = "houseId") Long houseId);
}
