package com.bit.sc.module.sys.dao;

import java.util.List;

import com.bit.sc.module.sys.vo.RoleCodeName;
import com.bit.sc.module.user.pojo.UserRole;
import org.apache.ibatis.annotations.Param;
import com.bit.sc.module.sys.pojo.Role;
import com.bit.sc.module.sys.vo.RoleVO;

/**
 * Role管理的Dao
 * @author 
 *
 */
public interface RoleMapper {
	/**
	 * 根据条件查询Role
	 * @param roleVO
	 * @return
	 */
	public List<Role> findByConditionPage(RoleVO roleVO);
	/**
	 * 查询所有Role
	 * @return
	 */
	public List<Role> findAll(@Param(value = "sorter") String sorter);
	/**
	 * 通过主键查询单个Role
	 * @param id	 	 
	 * @return
	 */
	public Role findById(@Param(value = "id") Long id);
	/**
	 * 根据主键ID查询自身所有Role
	 *
	 * @param id
	 * @return
	 */
	public List<UserRole> queryAllById(@Param(value = "id") Long id);
	/**
	 * 批量保存Role
	 * @param roles
	 */
	public void batchAdd(List<Role> roles);
	/**
	 * 保存Role
	 * @param role
	 */
	public void add(Role role);
	/**
	 * 批量更新Role
	 * @param roles
	 */
	public void batchUpdate(List<Role> roles);
	/**
	 * 更新Role
	 * @param role
	 */
	public void update(Role role);
	/**
	 * 删除Role
	 * @param ids
	 */
	public void batchDelete(List<Long> ids);
	/**
	 * 删除Role
	 * @param id
	 */
	public void delete(@Param(value = "id") Long id);
	/**
	 * 查询Role列表
	 * @param
	 */
	public List<RoleVO> findRoleListByCondition(RoleVO roleVO);

	public List<RoleCodeName> findAllRoleCodeName();


	/**
	 * 根据参数查询角色
	 * @param roleVO
	 * @return
	 * @Author zhangjie
	 * @date 2018-11-10
	 */
    List<Role> findByParam(RoleVO roleVO);

	/**
	 * 根据用户的id来查询所有的角色id
	 * @param id
	 * @return
	 */
	 List<Long> findRoleByUserId(Long id);
}
