package com.bit.sc.module.sys.service;

import java.util.List;
import org.springframework.stereotype.Service;
import com.bit.sc.module.sys.pojo.ManageUser;
import com.bit.sc.module.sys.vo.ManageUserVO;
import com.bit.base.vo.BaseVo;
/**
 * ManageUser的Service
 * @author codeGenerator
 */
@Service
public interface ManageUserService {
	/**
	 * 根据条件查询ManageUser
	 * @param manageUserVO
	 * @return
	 */
	public BaseVo findByConditionPage(ManageUserVO manageUserVO);
	/**
	 * 查询所有ManageUser
	 * @param sorter 排序字符串
	 * @return
	 */
	public List<ManageUser> findAll(String sorter);
	/**
	 * 通过主键查询单个ManageUser
	 * @param id
	 * @return
	 */
	public ManageUser findById(Long id);
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
	 * @param id
	 */
	public void delete(Long id);
	/**
	 * 批量删除ManageUser
	 * @param ids
	 */
	public void batchDelete(List<Long> ids);
}
