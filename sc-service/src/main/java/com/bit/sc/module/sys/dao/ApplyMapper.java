package com.bit.sc.module.sys.dao;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import com.bit.sc.module.sys.pojo.Apply;
import com.bit.sc.module.sys.vo.ApplyVO;

/**
 * Apply管理的Dao
 * @author 
 *
 */
public interface ApplyMapper {
	/**
	 * 根据条件查询Apply
	 * @param applyVO
	 * @return
	 */
	public List<Apply> findByConditionPage(ApplyVO applyVO);
	/**
	 * 查询所有Apply
	 * @return
	 */
	public List<Apply> findAll(@Param(value = "sorter") String sorter);
	/**
	 * 通过主键查询单个Apply
	 * @param id	 	 
	 * @return
	 */
	public Apply findById(@Param(value = "id") Long id);
	/**
	 * 批量保存Apply
	 * @param applys
	 */
	public void batchAdd(List<Apply> applys);
	/**
	 * 保存Apply
	 * @param apply
	 */
	public void add(Apply apply);
	/**
	 * 批量更新Apply
	 * @param applys
	 */
	public void batchUpdate(List<Apply> applys);
	/**
	 * 更新Apply
	 * @param apply
	 */
	public void update(Apply apply);
	/**
	 * 删除Apply
	 * @param ids
	 */
	public void batchDelete(List<Long> ids);
	/**
	 * 删除Apply
	 * @param id
	 */
	public void delete(@Param(value = "id") Long id);

	/**
	 * 根据参数查询所有
	 * @param applyVO
	 * @return
	 */
	List<Apply> findByParm(ApplyVO applyVO);

	/**
	 * 查询非居民app的平台
	 * @param
	 * @return
	 */
	List<Apply> getNoneResident();
}
