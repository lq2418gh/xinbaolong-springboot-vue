package com.bit.sc.module.group.service;

import com.bit.base.vo.BaseVo;
import com.bit.sc.module.group.pojo.Group;
import com.bit.sc.module.group.vo.GroupVO;

import java.util.List;
/**
 * Group的Service
 * @author codeGenerator
 */
public interface GroupService {
	/**
	 * 根据条件查询Group
	 * @param groupVO
	 * @return
	 */
	BaseVo findByConditionPage(GroupVO groupVO);
	/**
	 * 查询所有Group
	 * @param sorter 排序字符串
	 * @return
	 */
	List<Group> findAll(String sorter);
	/**
	 * 通过主键查询单个Group
	 * @param 
	 * @return
	 */
	Group findById(Long id);
	/**
	 * 保存Group
	 * @param group
	 */
	void add(Group group);
	/**
	 * 更新Group
	 * @param group
	 */
	void update(Group group);
	/**
	 * 删除Group
	 * @param id
	 */
	void delete(Long id);

	/**
	 * 分页查询分组列表，包括模糊查询
	 * @param groupVO
	 * @return
	 */
	BaseVo findAllPage(GroupVO groupVO);

	/**
	 * 查询所有已启用的分组
	 * @return
	 */
	BaseVo findAllByStatus();
	/**
	 *
	 * 功能描述: 根据名称模糊查询
	 *
	 * @param:
	 * @return:
	 * @author: chenduo
	 * @date: 2018/12/12 17:01
	 */

	BaseVo findAllByName(String name);
}
