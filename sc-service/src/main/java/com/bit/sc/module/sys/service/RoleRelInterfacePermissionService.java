package com.bit.sc.module.sys.service;

import java.util.List;
import org.springframework.stereotype.Service;
import com.bit.sc.module.sys.pojo.RoleRelInterfacePermission;
import com.bit.sc.module.sys.vo.RoleRelInterfacePermissionVO;
import com.bit.base.vo.BaseVo;
/**
 * RoleRelInterfacePermission的Service
 * @author codeGenerator
 */
public interface RoleRelInterfacePermissionService {
	/**
	 * 根据条件查询RoleRelInterfacePermission
	 * @param roleRelInterfacePermissionVO
	 * @return
	 */
	BaseVo findByConditionPage(RoleRelInterfacePermissionVO roleRelInterfacePermissionVO);
	/**
	 * 查询所有RoleRelInterfacePermission
	 * @param sorter 排序字符串
	 * @return
	 */
	List<RoleRelInterfacePermission> findAll(String sorter);
	/**
	 * 通过主键查询单个RoleRelInterfacePermission
	 * @param id
	 * @return
	 */
	RoleRelInterfacePermission findById(Long id);

	/**
	 * 批量保存RoleRelInterfacePermission
	 * @param roleRelInterfacePermissions
	 */
	void batchAdd(List<RoleRelInterfacePermission> roleRelInterfacePermissions);
	/**
	 * 保存RoleRelInterfacePermission
	 * @param roleRelInterfacePermission
	 */
	void add(RoleRelInterfacePermission roleRelInterfacePermission);
	/**
	 * 批量更新RoleRelInterfacePermission
	 * @param roleRelInterfacePermissions
	 */
	void batchUpdate(List<RoleRelInterfacePermission> roleRelInterfacePermissions);
	/**
	 * 更新RoleRelInterfacePermission
	 * @param roleRelInterfacePermission
	 */
	void update(RoleRelInterfacePermission roleRelInterfacePermission);
	/**
	 * 删除RoleRelInterfacePermission
	 * @param id
	 */
	void delete(Long id);
	/**
	 * 批量删除RoleRelInterfacePermission
	 * @param ids
	 */
	void batchDelete(List<Long> ids);
	/**
	 * 根据roleId查询权限
	 * @param roleId
	 * @return
	 */
	List<RoleRelInterfacePermission> findByRoleId(Long roleId);

	/**
	 * 更新接口权限
	 * @param roleRelInterfacePermission
	 */
    void updateInterFacePermission(RoleRelInterfacePermission roleRelInterfacePermission);

	/**
	 * 根据roleid 查询
	 * @param roleId
	 */
	List<RoleRelInterfacePermission> findListByRole(Long roleId);
}
