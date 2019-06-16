package com.bit.sc.module.resident.service;

import java.util.List;

import com.bit.sc.module.resident.pojo.ResidentMobile;
import com.bit.sc.module.resident.vo.ResidentMobileVO;
import org.springframework.stereotype.Service;
import com.bit.base.vo.BaseVo;
/**
 * ResidentMobile的Service
 * @author codeGenerator
 */
@Service
public interface ResidentMobileService {
	/**
	 * 根据条件查询ResidentMobile
	 * @param residentMobileVO
	 * @return
	 */
	BaseVo findByConditionPage(ResidentMobileVO residentMobileVO);
	/**
	 * 查询所有ResidentMobile
	 * @param sorter 排序字符串
	 * @return
	 */
	List<ResidentMobile> findAll(String sorter);
	/**
	 * 通过主键查询单个ResidentMobile
	 * @param id
	 * @return
	 */
	ResidentMobile findById(Long id);

	/**
	 * 批量保存ResidentMobile
	 * @param residentMobiles
	 */
	void batchAdd(List<ResidentMobile> residentMobiles);
	/**
	 * 保存ResidentMobile
	 * @param residentMobile
	 */
	void add(ResidentMobile residentMobile);
	/**
	 * 批量更新ResidentMobile
	 * @param residentMobiles
	 */
	void batchUpdate(List<ResidentMobile> residentMobiles);
	/**
	 * 更新ResidentMobile
	 * @param residentMobile
	 */
	void update(ResidentMobile residentMobile);
	/**
	 * 删除ResidentMobile
	 * @param id
	 */
	void delete(Long id);
	/**
	 * 批量删除ResidentMobile
	 * @param ids
	 */
	void batchDelete(List<Long> ids);
}
