package com.bit.sc.module.sys.service.impl;

import java.util.ArrayList;
import java.util.List;



import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.bit.base.vo.BaseVo;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.bit.sc.module.sys.pojo.UserRelRole;
import com.bit.sc.module.sys.vo.UserRelRoleVO;
import com.bit.sc.module.sys.dao.UserRelRoleMapper;
import com.bit.sc.module.sys.service.UserRelRoleService;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 * UserRelRole的Service实现类
 * @author codeGenerator
 *
 */
@Service("userRelRoleService")
public class UserRelRoleServiceImpl implements UserRelRoleService{
	
	private static final Logger logger = LoggerFactory.getLogger(UserRelRoleServiceImpl.class);
	
	@Autowired
	private UserRelRoleMapper userRelRoleMapper;
	
	/**
	 * 根据条件查询UserRelRole
	 * @param userRelRoleVO
	 * @return
	 */
	@Override
	public BaseVo findByConditionPage(UserRelRoleVO userRelRoleVO){
		PageHelper.startPage(userRelRoleVO.getPageNum(), userRelRoleVO.getPageSize());
		List<UserRelRole> list = userRelRoleMapper.findByConditionPage(userRelRoleVO);
		PageInfo<UserRelRole> pageInfo = new PageInfo<UserRelRole>(list);
		userRelRoleVO.setData(pageInfo);
		return userRelRoleVO;
	}
	/**
	 * 查询所有UserRelRole
	 * @param sorter 排序字符串
	 * @return
	 */
	@Override
	public List<UserRelRole> findAll(String sorter){
		return userRelRoleMapper.findAll(sorter);
	}
	/**
	 * 通过主键查询单个UserRelRole
	 * @param id
	 * @return
	 */
	@Override
	public UserRelRole findById(Long id){
		return userRelRoleMapper.findById(id);
	}
	
	/**
	 * 批量保存UserRelRole
	 * @param userRelRoles
	 */
	@Override
	public void batchAdd(List<UserRelRoleVO> userRelRoles){
		userRelRoleMapper.batchAdd(userRelRoles);
	}
	/**
	 * 保存UserRelRole
	 * @param userRelRole
	 */
	@Override
	public void add(UserRelRole userRelRole){
		userRelRoleMapper.add(userRelRole);
	}
	/**
	 * 批量更新UserRelRole
	 * @param userRelRoles
	 */
	@Override
	public void batchUpdate(List<UserRelRole> userRelRoles){
		userRelRoleMapper.batchUpdate(userRelRoles);
	}
	/**
	 * 更新UserRelRole
	 * @param userRelRole
	 */
	@Override
	public void update(UserRelRole userRelRole){
		userRelRoleMapper.update(userRelRole);
	}
	/**
	 * 删除UserRelRole
	 * @param ids
	 */
	@Override
	public void batchDelete(List<Long> ids){
		userRelRoleMapper.batchDelete(ids);
	}

	/**
	 * 根据人物id删除人物角色关系表中的关系
	 * @param id
	 */
	@Override
    @Transactional(rollbackFor = {Exception.class},propagation = Propagation.REQUIRED)
	public void deleteRole(Long id) {
	    try{
            userRelRoleMapper.deleteRole(id);
        }catch (Exception e){
            throw new RuntimeException();
        }
	}

	@Override
    @Transactional(rollbackFor = {Exception.class},propagation = Propagation.REQUIRED)
	public void addUserRelRole(List<String> list, Long id) {
		List<UserRelRoleVO> userRelRoleList =new ArrayList<>();

		try{
            for (int i=0;i<list.size();i++){
				UserRelRoleVO userRelRole =new UserRelRoleVO();
                userRelRole.setUserId(id);
                Long roleid = Long.parseLong(list.get(i));
                userRelRole.setRoleId(roleid);
                userRelRoleList.add(userRelRole);
            }
            //批量加入
            userRelRoleMapper.batchAdd(userRelRoleList);
        }catch (Exception e){
            e.printStackTrace();
        }
	}

	/**
	 * 删除UserRelRole
	 * @param id
	 */
	@Override
	public void delete(Long id){
		userRelRoleMapper.delete(id);
	}
}
