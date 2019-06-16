package com.bit.sc.module.mobile.dao;

import java.util.List;

import com.bit.sc.module.mobile.pojo.MobileVersion;
import com.bit.sc.module.mobile.vo.MobileVersionVO;
import org.apache.ibatis.annotations.Param;

/**
 * MobileVersion管理的Dao
 * @author 
 *
 */
public interface MobileVersionMapper {
	/**
	 * 根据条件查询MobileVersion
	 * @param mobileVersionVO
	 * @return
	 */
	public List<MobileVersion> findByConditionPage(MobileVersionVO mobileVersionVO);
	/**
	 * 查询所有MobileVersion
	 * @return
	 */
	public List<MobileVersion> findAll(@Param(value = "sorter") String sorter);
	/**
	 * 通过主键查询单个MobileVersion
	 * @param id	 	 
	 * @return
	 */
	/**
	 * 批量保存MobileVersion
	 * @param mobileVersions
	 */
	public void batchAdd(List<MobileVersion> mobileVersions);
	/**
	 * 保存MobileVersion
	 * @param mobileVersion
	 */
	public void add(MobileVersion mobileVersion);
	/**
	 * 批量更新MobileVersion
	 * @param mobileVersions
	 */
	public void batchUpdate(List<MobileVersion> mobileVersions);
	/**
	 * 更新MobileVersion
	 * @param mobileVersion
	 */
	public void update(MobileVersion mobileVersion);
	/**
	 * 删除MobileVersion
	 * @param ids
	 */
	public void batchDelete(List<Long> ids);
	/**
	 * 删除MobileVersion
	 * @param id
	 */
	public void delete(@Param(value = "id") String id);

	MobileVersion findById(String id);
}
