package com.bit.sc.module.house.dao;

import java.util.List;

import com.bit.sc.module.house.pojo.RealCompany;
import com.bit.sc.module.house.vo.RealCompanyDictVO;
import com.bit.sc.module.house.vo.RealCompanyVO;
import org.apache.ibatis.annotations.Param;

/**
 * RealCompany管理的Dao
 * @author chenduo
 *
 */
public interface RealCompanyMapper {
	/**
	 * 根据条件查询RealCompany
	 * @param realCompanyVO
	 * @return
	 * @author chenduo
	 */
	public List<RealCompany> findByConditionPage(RealCompanyVO realCompanyVO);
	/**
	 * 查询所有RealCompany
	 * @return
	 * @author chenduo
	 */
	public List<RealCompany> findAll(@Param(value = "sorter") String sorter);
	/**
	 * 通过主键查询单个RealCompany
	 * @param id	 	 
	 * @return
	 * @author chenduo
	 */
	public RealCompany findById(@Param(value = "id") Long id);
	/**
	 * 批量保存RealCompany
	 * @param realCompanyList
	 *         @author chenduo
	 */
	public void batchAdd(@Param(value = "realCompanyList") List<RealCompany> realCompanyList);
	/**
	 * 保存RealCompany
	 * @param realCompany
	 *         @author chenduo
	 */
	public void add(RealCompany realCompany);
	/**
	 * 批量更新RealCompany
	 * @param realCompanys
	 *         @author chenduo
	 */
	public void batchUpdate(List<RealCompany> realCompanys);
	/**
	 * 更新RealCompany
	 * @param realCompany
	 *         @author chenduo
	 */
	public void update(RealCompany realCompany);
	/**
	 * 删除RealCompany
	 * @param ids
	 *         @author chenduo
	 */
	public void batchDelete(List<Long> ids);
	/**
	 * 删除RealCompany
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
	 * @date: 2018/11/23 15:12
	 */
    List<RealCompanyDictVO> querylistAllDictByPage(RealCompanyVO realCompanyVO);
}
