package com.bit.sc.module.mobile.service;

import java.util.List;

import com.bit.sc.module.mobile.pojo.MobileVersion;
import com.bit.sc.module.mobile.vo.MobileVersionVO;
import org.springframework.stereotype.Service;
import com.bit.base.vo.BaseVo;
/**
 * MobileVersion的Service
 * @author codeGenerator
 */
@Service
public interface MobileVersionService {
	/**
	 * 根据条件查询MobileVersion
	 * @param mobileVersionVO
	 * @return
	 */
	BaseVo findByConditionPage(MobileVersionVO mobileVersionVO);
	/**
	 * 查询所有MobileVersion
	 * @param sorter 排序字符串
	 * @return
	 */
	List<MobileVersion> findAll(String sorter);
	/**
	 * 通过主键查询单个MobileVersion
	 * @param id
	 * @return
	 */
	MobileVersion findById(String id);

	/**
	 * 批量保存MobileVersion
	 * @param mobileVersions
	 */
	void batchAdd(List<MobileVersion> mobileVersions);
	/**
	 * 保存MobileVersion
	 * @param mobileVersion
	 */
	void add(MobileVersion mobileVersion);
	/**
	 * 批量更新MobileVersion
	 * @param mobileVersions
	 */
	void batchUpdate(List<MobileVersion> mobileVersions);
	/**
	 * 更新MobileVersion
	 * @param mobileVersion
	 */
	void update(MobileVersion mobileVersion);
	/**
	 * 删除MobileVersion
	 * @param id
	 */
	void delete(String id);
	/**
	 * 批量删除MobileVersion
	 * @param ids
	 */
	void batchDelete(List<Long> ids);
}
