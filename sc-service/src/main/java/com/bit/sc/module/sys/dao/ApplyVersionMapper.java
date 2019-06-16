package com.bit.sc.module.sys.dao;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import com.bit.sc.module.sys.pojo.ApplyVersion;
import com.bit.sc.module.sys.vo.ApplyVersionVO;

/**
 * ApplyVersion管理的Dao
 * @author 
 *
 */
public interface ApplyVersionMapper {
	/**
	 * 根据条件查询ApplyVersion
	 * @param applyVersionVO
	 * @return
	 */
	public List<ApplyVersion> findByConditionPage(ApplyVersionVO applyVersionVO);
	/**
	 * 查询所有ApplyVersion
	 * @return
	 */
	public List<ApplyVersion> findAll(@Param(value = "sorter") String sorter);
	/**
	 * 通过主键查询单个ApplyVersion
	 * @param id	 	 
	 * @return
	 */
	public ApplyVersion findById(@Param(value = "id") Long id);
	/**
	 * 批量保存ApplyVersion
	 * @param applyVersions
	 */
	public void batchAdd(List<ApplyVersion> applyVersions);
	/**
	 * 保存ApplyVersion
	 * @param applyVersion
	 */
	public void add(ApplyVersion applyVersion);
	/**
	 * 批量更新ApplyVersion
	 * @param applyVersions
	 */
	public void batchUpdate(List<ApplyVersion> applyVersions);
	/**
	 * 更新ApplyVersion
	 * @param applyVersion
	 */
	public void update(ApplyVersion applyVersion);
	/**
	 * 删除ApplyVersion
	 * @param ids
	 */
	public void batchDelete(List<Long> ids);
	/**
	 * 删除ApplyVersion
	 * @param id
	 */
	public void delete(@Param(value = "id") Long id);

	/**
	 * 查询app发布的版本列表
	 * @param vo
	 * @return List<ApplyVersionVO>
	 */
	public List<ApplyVersionVO> findVersionListByCondition(ApplyVersionVO vo);


}
