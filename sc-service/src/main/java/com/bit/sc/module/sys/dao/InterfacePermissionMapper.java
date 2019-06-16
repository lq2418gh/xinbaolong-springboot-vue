package com.bit.sc.module.sys.dao;

import com.bit.sc.module.sys.pojo.InterfacePermission;
import com.bit.sc.module.sys.vo.InterfacePermissionVO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * InterfacePermission管理的Dao
 * @author 
 *
 */
public interface InterfacePermissionMapper {
	/**
	 * 根据条件查询InterfacePermission
	 * @param interfacePermissionVO
	 * @return
	 */
	public List<InterfacePermission> findByConditionPage(InterfacePermissionVO interfacePermissionVO);
	/**
	 * 查询所有InterfacePermission
	 * @return
	 */
	public List<InterfacePermission> findAll(@Param(value = "sorter") String sorter);
	/**
	 * 通过主键查询单个InterfacePermission
	 * @param id	 	 
	 * @return
	 */
	public InterfacePermission findById(@Param(value = "id") Long id);
	/**
	 * 批量保存InterfacePermission
	 * @param interfacePermissions
	 */
	public void batchAdd(@Param(value = "list")List<InterfacePermission> interfacePermissions);
	/**
	 * 保存InterfacePermission
	 * @param interfacePermission
	 */
	public void add(InterfacePermission interfacePermission);
	/**
	 * 批量更新InterfacePermission
	 * @param interfacePermissions
	 */
	public void batchUpdate(List<InterfacePermission> interfacePermissions);
	/**
	 * 更新InterfacePermission
	 * @param interfacePermission
	 */
	public void update(InterfacePermission interfacePermission);
	/**
	 * 删除InterfacePermission
	 * @param interfacePermissions
	 */
	public void batchDelete(List<Long> ids);
	/**
	 * 删除InterfacePermission
	 * @param id
	 */
	public void delete(@Param(value = "id") Long id);

	/**
	 * 通过角色信息查询权限信息
	 * @param id
	 * @return
	 */
	public List<InterfacePermission> findPermissionById(Long id);

	public List<Long> findRoleAll();
}
