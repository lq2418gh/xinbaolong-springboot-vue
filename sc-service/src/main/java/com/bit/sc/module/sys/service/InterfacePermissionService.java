package com.bit.sc.module.sys.service;

import com.bit.base.vo.BaseVo;
import com.bit.sc.module.sys.pojo.InterfacePermission;
import com.bit.sc.module.sys.vo.InterfacePermissionVO;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * InterfacePermission的Service
 * @author zhanghaodong
 */
@Service
public interface InterfacePermissionService {
	/**
	 * 根据条件查询InterfacePermission
	 * @param interfacePermissionVO
	 * @return
	 */
	BaseVo findByConditionPage(InterfacePermissionVO interfacePermissionVO);
	/**
	 * 查询所有InterfacePermission
	 * @param sorter 排序字符串
	 * @return
	 */
	List<InterfacePermission> findAll(String sorter);
	/**
	 * 通过主键查询单个InterfacePermission
	 * @param id
	 * @return
	 */
	InterfacePermission findById(Long id);

	/**
	 * 批量保存InterfacePermission
	 * @param interfacePermissions
	 */
	void batchAdd(List<InterfacePermission> interfacePermissions);
	/**
	 * 保存InterfacePermission
	 * @param interfacePermission
	 */
	void add(InterfacePermission interfacePermission);
	/**
	 * 批量更新InterfacePermission
	 * @param interfacePermissions
	 */
	void batchUpdate(List<InterfacePermission> interfacePermissions);
	/**
	 * 更新InterfacePermission
	 * @param interfacePermission
	 */
	void update(InterfacePermission interfacePermission);
	/**
	 * 删除InterfacePermission
	 * @param id
	 */
	void delete(Long id);
	/**
	 * 批量删除InterfacePermission
	 * @param ids
	 */
	void batchDelete(List<Long> ids);
	/**
	 * 通过角色的id 查询出权限的信息
	 */
	List<InterfacePermission> findPermissionById(Long id);
}
