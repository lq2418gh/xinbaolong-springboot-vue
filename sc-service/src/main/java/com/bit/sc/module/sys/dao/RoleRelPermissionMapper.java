package com.bit.sc.module.sys.dao;

import java.util.List;

import com.bit.sc.module.sys.vo.RolePermissionMenuVO;
import org.apache.ibatis.annotations.Param;
import com.bit.sc.module.sys.pojo.RoleRelPermission;
import com.bit.sc.module.sys.vo.RoleRelPermissionVO;

/**
 * RoleRelPermission管理的Dao
 * @author 
 *
 */
public interface RoleRelPermissionMapper {
	/**
	 * 根据条件查询RoleRelPermission
	 * @param roleRelPermissionVO
	 * @return
	 */
	public List<RoleRelPermission> findByConditionPage(RoleRelPermissionVO roleRelPermissionVO);
	/**
	 * 查询所有RoleRelPermission
	 * @return
	 */
	public List<RoleRelPermission> findAll(@Param(value = "sorter") String sorter);
	/**
	 * 通过主键查询单个RoleRelPermission
	 * @param id	 	 
	 * @return
	 */
	public RoleRelPermission findById(@Param(value = "id") Long id);
	/**
	 * 批量保存RoleRelPermission
	 * @param list
	 */
	public void batchAdd(@Param(value = "list") List<RoleRelPermission> list);
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
	 * @param ids
	 */
	public void batchDelete(List<Long> ids);
	/**
	 * 删除RoleRelPermission
	 * @param id
	 */
	public void delete(@Param(value = "id") Long id);


	/**
	 * 删除RoleRelPermission
	 * @param roleIds
	 */
	public List<RoleRelPermission> batchFindRole(List<Long> roleIds);


//    List<RolePermissionMenuVO> queryFunctionByPage(RoleRelPermissionVO roleRelPermissionVO);

    /**
     * 分页查询RoleRelPermission列表 根据平台显示不同功能
     * @author chenduo
     *
     */
	List<RolePermissionMenuVO> queryMenuByPage(RoleRelPermissionVO roleRelPermissionVO);

    /**
     * 分页查询RoleRelPermission列表 根据平台显示不同功能
     * @author chenduo
     *
     */
	List<RolePermissionMenuVO> queryFunctionByPage(RoleRelPermissionVO roleRelPermissionVO);


	void deleteByFunction(RoleRelPermission roleRelPermission);
	/**
	 * 查询RoleRelPermission列表 只查询web平台的 符合角色id的菜单id
	 * @author chenduo
	 *
	 */
	List<RoleRelPermission> listAllFunction(RoleRelPermission roleRelPermission);


    void deleteByRoleId(@Param(value = "roleId") Long roleId);
}
