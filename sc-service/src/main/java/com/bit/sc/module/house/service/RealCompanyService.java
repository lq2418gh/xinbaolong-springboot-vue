package com.bit.sc.module.house.service;

import java.util.List;

import com.bit.sc.module.house.pojo.RealCompany;
import com.bit.sc.module.house.vo.RealCompanyDictVO;
import com.bit.sc.module.house.vo.RealCompanyVO;
import org.springframework.stereotype.Service;
import com.bit.base.vo.BaseVo;
/**
 * RealCompany的Service
 * @author chenduo
 */
public interface RealCompanyService {
	/**
	 * 根据条件查询RealCompany
	 * @param realCompanyVO
	 * @return
	 * @author chenduo
	 */
	BaseVo findByConditionPage(RealCompanyVO realCompanyVO);
	/**
	 * 查询所有RealCompany
	 * @param sorter 排序字符串
	 * @return
	 * @author chenduo
	 */
	List<RealCompany> findAll(String sorter);
	/**
	 * 通过主键查询单个RealCompany
	 * @param id
	 * @return
	 * @author chenduo
	 */
	RealCompany findById(Long id);

	/**
	 * 批量保存RealCompany
	 * @param realCompanys
	 *         @author chenduo
	 */
	void batchAdd(List<RealCompany> realCompanyList);
	/**
	 * 保存RealCompany
	 * @param realCompany
	 *         @author chenduo
	 */
	void add(RealCompany realCompany);
	/**
	 * 批量更新RealCompany
	 * @param realCompanys
	 *         @author chenduo
	 */
	void batchUpdate(List<RealCompany> realCompanys);
	/**
	 * 更新RealCompany
	 * @param realCompany
	 *         @author chenduo
	 */
	void update(RealCompany realCompany);
	/**
	 * 删除RealCompany
	 * @param id
	 *                                             @author chenduo
	 */
	void delete(Long id);
	/**
	 * 批量删除RealCompany
	 * @param ids
	 *                                         @author chenduo
	 */
	void batchDelete(List<Long> ids);

    /**
     *
     * 功能描述:联合字典表分页查询
     *
     * @param:
     * @return:
     * @author: chenduo
     * @date: 2018/11/23 15:12
     */
    BaseVo querylistAllDictByPage(RealCompanyVO realCompanyVO);

}
