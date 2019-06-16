package com.bit.sc.module.sys.service;

import java.util.List;


import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Service;
import com.bit.sc.module.sys.pojo.UserRelRole;
import com.bit.sc.module.sys.vo.UserRelRoleVO;
import com.bit.base.vo.BaseVo;
/**
 * UserRelRole的Service
 * @author codeGenerator
 */
@Service
public interface UserRelRoleService {
	/**
	 * 根据条件查询UserRelRole
	 * @param userRelRoleVO
	 * @return
	 */
	public BaseVo findByConditionPage(UserRelRoleVO userRelRoleVO);
	/**
	 * 查询所有UserRelRole
	 * @param sorter 排序字符串
	 * @return
	 */
	public List<UserRelRole> findAll(String sorter);
	/**
	 * 通过主键查询单个UserRelRole
	 * @param id
	 * @return
	 */
	public UserRelRole findById(Long id);
	/**
	 * 批量保存UserRelRole
	 * @param userRelRoles
	 */
	public void batchAdd(List<UserRelRoleVO> userRelRoles);
	/**
	 * 保存UserRelRole
	 * @param userRelRole
	 */
	public void add(UserRelRole userRelRole);
	/**
	 * 批量更新UserRelRole
	 * @param userRelRoles
	 */
	public void batchUpdate(List<UserRelRole> userRelRoles);
	/**
	 * 更新UserRelRole
	 * @param userRelRole
	 */
	public void update(UserRelRole userRelRole);
	/**
	 * 删除UserRelRole
	 * @param id
	 */
	public void delete(Long id);
	/**
	 * 批量删除UserRelRole
	 * @param ids
	 */
	public void batchDelete(List<Long> ids);

	/**
	 * 根据人物id删除人物角色关系表中的关系
	 * @param id
	 */
	public void deleteRole(Long id);

	/**
	 *
	 * @param list
	 * @param id
	 */
	public void addUserRelRole(List<String> list, Long id);


}
