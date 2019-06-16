package com.bit.sc.module.group.dao;

import com.bit.sc.module.equipment.vo.EquipmentVO;
import com.bit.sc.module.group.pojo.Group;
import com.bit.sc.module.group.vo.GroupVO;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * Group管理的Dao
 * @author 
 *
 */
public interface GroupMapper {
	/**
	 * 根据条件查询Group
	 * @param groupVO
	 * @return
	 */
	public List<Group> findByConditionPage(GroupVO groupVO);
	/**
	 * 查询所有Group
	 * @return
	 */
	public List<Group> findAll(@Param(value = "sorter") String sorter);
	/**
	 * 通过主键查询单个Group
	 * @return
	 */
	Group findById(Long id);
	/**
	 * 保存Group
	 * @param group
	 */
	public void add(Group group);
	/**
	 * 更新Group
	 * @param group
	 */
	public void update(Group group);

	/**
	 * 删除Group
	 * @param id
	 */
	public void delete(@Param(value="id")Long id);

	/**
	 * 分页查询分组列表，包括模糊查询
	 * @param map
	 * @return
	 */
	List<Group> findAllPage(@Param("params") Map<String,Object> map);

	/**
	 * 查询所有已启用的分组
	 * @return
	 */
	List<Group> findAllByStatus();
    /**
     * 根据用户和名称模糊查询
     * @return
     */
	List<Group> findAllByParam(GroupVO groupVO);
}
