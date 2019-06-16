package com.bit.sc.module.sys.dao;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import com.bit.sc.module.sys.pojo.RoleRelInterfacePermission;
import com.bit.sc.module.sys.vo.RoleRelInterfacePermissionVO;

/**
 * RoleRelInterfacePermission管理的Dao
 * @author 
 *
 */
public interface RoleRelInterfacePermissionMapper {
	/**
	 * 根据条件查询RoleRelInterfacePermission
	 * @param roleRelInterfacePermissionVO
	 * @return
	 */
	public List<RoleRelInterfacePermission> findByConditionPage(RoleRelInterfacePermissionVO roleRelInterfacePermissionVO);
	/**
	 * 查询所有RoleRelInterfacePermission
	 * @return
	 */
	public List<RoleRelInterfacePermission> findAll(@Param(value = "sorter") String sorter);
	/**
	 * 通过主键查询单个RoleRelInterfacePermission
	 * @param id	 	 
	 * @return
	 */
	public RoleRelInterfacePermission findById(@Param(value = "id") Long id);
	/**
	 * 批量保存RoleRelInterfacePermission
	 * @param roleRelInterfacePermissions
	 */
	public void batchAdd(@Param(value = "list") List<RoleRelInterfacePermission> roleRelInterfacePermissions);
	/**
	 * 保存RoleRelInterfacePermission
	 * @param roleRelInterfacePermission
	 */
	public void add(RoleRelInterfacePermission roleRelInterfacePermission);
	/**
	 * 批量更新RoleRelInterfacePermission
	 * @param roleRelInterfacePermissions
	 */
	public void batchUpdate(List<RoleRelInterfacePermission> roleRelInterfacePermissions);
	/**
	 * 更新RoleRelInterfacePermission
	 * @param roleRelInterfacePermission
	 */
	public void update(RoleRelInterfacePermission roleRelInterfacePermission);
	/**
	 * 删除RoleRelInterfacePermission
	 */
	public void batchDelete(List<Long> ids);
	/**
	 * 删除RoleRelInterfacePermission
	 * @param id
	 */
	public void delete(@Param(value = "id") Long id);

	/**
	 * 根据roleId查询权限Id
	 * @param id
	 * @return
	 */
	List<RoleRelInterfacePermission> findByRoleId(Long id);

	/**
	 *  根据roleId删除
	 * @param roleId
	 */
	void deleteByRoleId(@Param(value = "roleId")Long roleId);

	List<RoleRelInterfacePermission> findListByRole(Long roleId);
}
