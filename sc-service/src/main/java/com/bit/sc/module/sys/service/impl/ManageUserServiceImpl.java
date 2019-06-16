package com.bit.sc.module.sys.service.impl;

import java.util.List;
import java.util.ArrayList;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.bit.base.vo.BaseVo;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.bit.sc.module.sys.pojo.ManageUser;
import com.bit.sc.module.sys.vo.ManageUserVO;
import com.bit.sc.module.sys.dao.ManageUserMapper;
import com.bit.sc.module.sys.service.ManageUserService;
import org.springframework.transaction.annotation.Transactional;

/**
 * ManageUser的Service实现类
 * @author codeGenerator
 *
 */
@Service("manageUserService")
public class ManageUserServiceImpl implements ManageUserService{
	
	private static final Logger logger = LoggerFactory.getLogger(ManageUserServiceImpl.class);
	
	@Autowired
	private ManageUserMapper manageUserMapper;
	
	/**
	 * 根据条件查询ManageUser
	 * @param manageUserVO
	 * @return
	 */
	@Override
	public BaseVo findByConditionPage(ManageUserVO manageUserVO){
		PageHelper.startPage(manageUserVO.getPageNum(), manageUserVO.getPageSize());
		List<ManageUser> list = manageUserMapper.findByConditionPage(manageUserVO);
		PageInfo<ManageUser> pageInfo = new PageInfo<ManageUser>(list);
		manageUserVO.setData(pageInfo);
		return manageUserVO;
	}
	/**
	 * 查询所有ManageUser
	 * @param sorter 排序字符串
	 * @return
	 */
	@Override
	public List<ManageUser> findAll(String sorter){
		return manageUserMapper.findAll(sorter);
	}
	/**
	 * 通过主键查询单个ManageUser
	 * @param id
	 * @return
	 */
	@Override
	public ManageUser findById(Long id){
		return manageUserMapper.findById(id);
	}
	
	/**
	 * 批量保存ManageUser
	 * @param manageUsers
	 */
	@Override
	@Transactional
	public void batchAdd(List<ManageUser> manageUsers){
		manageUserMapper.batchAdd(manageUsers);
	}
	/**
	 * 保存ManageUser
	 * @param manageUser
	 */
	@Override
	@Transactional
	public void add(ManageUser manageUser){
		manageUserMapper.add(manageUser);
	}
	/**
	 * 批量更新ManageUser
	 * @param manageUsers
	 */
	@Override
	@Transactional
	public void batchUpdate(List<ManageUser> manageUsers){
		manageUserMapper.batchUpdate(manageUsers);
	}
	/**
	 * 更新ManageUser
	 * @param manageUser
	 */
	@Override
	@Transactional
	public void update(ManageUser manageUser){
		manageUserMapper.update(manageUser);
	}
	/**
	 * 删除ManageUser
	 * @param ids
	 */
	@Override
	@Transactional
	public void batchDelete(List<Long> ids){
		manageUserMapper.batchDelete(ids);
	}
	/**
	 * 删除ManageUser
	 * @param id
	 */
	@Override
	@Transactional
	public void delete(Long id){
		manageUserMapper.delete(id);
	}
}
