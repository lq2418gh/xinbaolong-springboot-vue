package com.bit.sc.module.sys.dao;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import com.bit.sc.module.sys.pojo.ManageUser;
import com.bit.sc.module.sys.vo.ManageUserVO;

/**
 * ManageUser管理的Dao
 * @author 
 *
 */
public interface ManageUserMapper {
	/**
	 * 根据条件查询ManageUser
	 * @param manageUserVO
	 * @return
	 */
	public List<ManageUser> findByConditionPage(ManageUserVO manageUserVO);
	/**
	 * 查询所有ManageUser
	 * @return
	 */
	public List<ManageUser> findAll(@Param(value = "sorter") String sorter);
	/**
	 * 通过主键查询单个ManageUser
	 * @param id	 	 
	 * @return
	 */
	public ManageUser findById(@Param(value = "id") Long id);
	/**
	 * 批量保存ManageUser
	 * @param manageUsers
	 */
	public void batchAdd(List<ManageUser> manageUsers);
	/**
	 * 保存ManageUser
	 * @param manageUser
	 */
	public void add(ManageUser manageUser);
	/**
	 * 批量更新ManageUser
	 * @param manageUsers
	 */
	public void batchUpdate(List<ManageUser> manageUsers);
	/**
	 * 更新ManageUser
	 * @param manageUser
	 */
	public void update(ManageUser manageUser);
	/**
	 * 删除ManageUser
	 * @param manageUsers
	 */
	public void batchDelete(List<Long> ids);
	/**
	 * 删除ManageUser
	 * @param id
	 */
	public void delete(@Param(value = "id") Long id);
}
