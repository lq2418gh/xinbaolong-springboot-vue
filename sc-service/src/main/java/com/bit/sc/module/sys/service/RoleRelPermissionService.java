package com.bit.sc.module.sys.service;

import java.util.List;

import com.bit.sc.module.sys.vo.RoleRelPersmissionMenuVO;
import org.springframework.stereotype.Service;
import com.bit.sc.module.sys.pojo.RoleRelPermission;
import com.bit.sc.module.sys.vo.RoleRelPermissionVO;
import com.bit.base.vo.BaseVo;
/**
 * RoleRelPermission的Service
 * @author codeGenerator
 */
@Service
public interface RoleRelPermissionService {
	/**
	 * 根据条件查询RoleRelPermission
	 * @param roleRelPermissionVO
	 * @return
	 */
	public BaseVo findByConditionPage(RoleRelPermissionVO roleRelPermissionVO);
	/**
	 * 查询所有RoleRelPermission
	 * @param sorter 排序字符串
	 * @return
	 */
	public List<RoleRelPermission> findAll(String sorter);
	/**
	 * 通过主键查询单个RoleRelPermission
	 * @param id
	 * @return
	 */
	public RoleRelPermission findById(Long id);
	/**
	 * 批量保存RoleRelPermission
	 * @param roleRelPermissions
	 */
	public void batchAdd(List<RoleRelPermission> roleRelPermissions);
	/**
	 * 保存RoleRelPermission
	 * @param roleRelPermission
	 */
	public void add(RoleRelPermission roleRelPermission);
	/**
	 * 批量更新RoleRelPermission
	 * @param roleRelPermissions
	 */
	public void batchUpdate(List<RoleRelPermission> roleRelPermissions);
	/**
	 * 更新RoleRelPermission
	 * @param roleRelPermission
	 */
	public void update(RoleRelPermission roleRelPermission);
	/**
	 * 删除RoleRelPermission
	 * @param id
	 */
	public void delete(Long id);
	/**
	 * 批量删除RoleRelPermission
	 * @param ids
	 */
	public void batchDelete(List<Long> ids);


	BaseVo queryFunctionByPage(RoleRelPermissionVO roleRelPermissionVO);
	/**
	 * 查询RoleRelPermission列表 只查询web平台的 符合角色id的菜单id
	 * @author chenduo
	 *
	 */
	BaseVo listAllFunction(RoleRelPermission roleRelPermission);
	/**
	 * 改变角色与菜单的关系
	 * @author chenduo
	 *
	 */
	BaseVo change(RoleRelPersmissionMenuVO roleRelPersmissionMenuVO);
}
