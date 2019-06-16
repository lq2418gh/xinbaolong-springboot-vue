package com.bit.sc.module.resident.dao;

import java.util.List;

import com.bit.sc.module.resident.pojo.ResidentRelRole;
import com.bit.sc.module.resident.vo.ResidentRelRoleVO;
import org.apache.ibatis.annotations.Param;



/**
 * ResidentRelRole管理的Dao
 * @author zhangjie
 *
 */
public interface ResidentRelRoleMapper {
	/**
	 * 根据条件查询ResidentRelRole
	 * @param residentRelRoleVO
	 * @return
	 */
	public List<ResidentRelRole> findByConditionPage(ResidentRelRoleVO residentRelRoleVO);
	/**
	 * 查询所有ResidentRelRole
	 * @return
	 */
	public List<ResidentRelRole> findAll(@Param(value = "sorter") String sorter);
	/**
	 * 通过主键查询单个ResidentRelRole
	 * @param id	 	 
	 * @return
	 */
	public ResidentRelRole findById(@Param(value = "id") Long id);
	/**
	 * 批量保存ResidentRelRole
	 * @param residentRelRoles
	 */
	public void batchAdd(List<ResidentRelRole> residentRelRoles);
	/**
	 * 保存ResidentRelRole
	 * @param residentRelRole
	 */
	public void add(ResidentRelRole residentRelRole);
	/**
	 * 批量更新ResidentRelRole
	 * @param residentRelRoles
	 */
	public void batchUpdate(List<ResidentRelRole> residentRelRoles);
	/**
	 * 更新ResidentRelRole
	 * @param residentRelRole
	 */
	public void update(ResidentRelRole residentRelRole);
	/**
	 * 删除ResidentRelRole
	 * @param ids
	 */
	public void batchDelete(List<Long> ids);
	/**
	 * 删除ResidentRelRole
	 * @param id
	 */
	public void delete(@Param(value = "id") Long id);
}
