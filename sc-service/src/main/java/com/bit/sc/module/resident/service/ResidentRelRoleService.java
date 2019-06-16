package com.bit.sc.module.resident.service;

import java.util.List;

import com.bit.sc.module.resident.pojo.ResidentRelRole;
import com.bit.sc.module.resident.vo.ResidentRelRoleVO;
import org.springframework.stereotype.Service;


import com.bit.base.vo.BaseVo;
/**
 * ResidentRelRole的Service
 * @author zhangjie
 */
@Service
public interface ResidentRelRoleService {
	/**
	 * 根据条件查询ResidentRelRole
	 * @param residentRelRoleVO
	 * @return
	 */
	public BaseVo findByConditionPage(ResidentRelRoleVO residentRelRoleVO);
	/**
	 * 查询所有ResidentRelRole
	 * @param sorter 排序字符串
	 * @return
	 */
	public List<ResidentRelRole> findAll(String sorter);
	/**
	 * 通过主键查询单个ResidentRelRole
	 * @param id
	 * @return
	 */
	public ResidentRelRole findById(Long id);
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
	 * @param id
	 */
	public void delete(Long id);
	/**
	 * 批量删除ResidentRelRole
	 * @param ids
	 */
	public void batchDelete(List<Long> ids);
}
