package com.bit.sc.module.resident.service.impl;

import java.util.List;
import java.util.ArrayList;

import com.bit.sc.module.resident.dao.ResidentRelRoleMapper;
import com.bit.sc.module.resident.pojo.ResidentRelRole;
import com.bit.sc.module.resident.service.ResidentRelRoleService;
import com.bit.sc.module.resident.vo.ResidentRelRoleVO;
import com.bit.sc.module.user.pojo.User;
import com.github.pagehelper.PageInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.bit.base.vo.BaseVo;
import com.github.pagehelper.PageHelper;
import org.springframework.transaction.annotation.Transactional;

/**
 * ResidentRelRole的Service实现类
 * @author zhangjie
 *
 */
@Service("residentRelRoleService")
public class ResidentRelRoleServiceImpl implements ResidentRelRoleService {
	
	private static final Logger logger = LoggerFactory.getLogger(ResidentRelRoleServiceImpl.class);
	
	@Autowired
	private ResidentRelRoleMapper residentRelRoleMapper;
	
	/**
	 * 根据条件查询ResidentRelRole
	 * @param
	 * @return
	 */
	@Override
	public BaseVo findByConditionPage(ResidentRelRoleVO residentRelRoleVO){
		PageHelper.startPage(residentRelRoleVO.getPageNum(), residentRelRoleVO.getPageSize());
		List<ResidentRelRole> list = residentRelRoleMapper.findByConditionPage(residentRelRoleVO);
		PageInfo<ResidentRelRole> pageInfo = new PageInfo<ResidentRelRole>(list);
		residentRelRoleVO.setData(pageInfo);
		return residentRelRoleVO;
	}
	/**
	 * 查询所有ResidentRelRole
	 * @param sorter 排序字符串
	 * @return
	 */
	@Override
	public List<ResidentRelRole> findAll(String sorter){
		return residentRelRoleMapper.findAll(sorter);
	}
	/**
	 * 通过主键查询单个ResidentRelRole
	 * @param id
	 * @return
	 */
	@Override
	public ResidentRelRole findById(Long id){
		return residentRelRoleMapper.findById(id);
	}
	
	/**
	 * 批量保存ResidentRelRole
	 * @param residentRelRoles
	 */
	@Override
	@Transactional
	public void batchAdd(List<ResidentRelRole> residentRelRoles){
		residentRelRoleMapper.batchAdd(residentRelRoles);
	}
	/**
	 * 保存ResidentRelRole
	 * @param residentRelRole
	 */
	@Override
	@Transactional
	public void add(ResidentRelRole residentRelRole){
		residentRelRoleMapper.add(residentRelRole);
	}
	/**
	 * 批量更新ResidentRelRole
	 * @param residentRelRoles
	 */
	@Override
	@Transactional
	public void batchUpdate(List<ResidentRelRole> residentRelRoles){
		residentRelRoleMapper.batchUpdate(residentRelRoles);
	}
	/**
	 * 更新ResidentRelRole
	 * @param residentRelRole
	 */
	@Override
	@Transactional
	public void update(ResidentRelRole residentRelRole){
		residentRelRoleMapper.update(residentRelRole);
	}
	/**
	 * 删除ResidentRelRole
	 * @param ids
	 */
	@Override@Transactional
	public void batchDelete(List<Long> ids){
		residentRelRoleMapper.batchDelete(ids);
	}
	/**
	 * 删除ResidentRelRole
	 * @param id
	 */
	@Override@Transactional
	public void delete(Long id){
		residentRelRoleMapper.delete(id);
	}
}
