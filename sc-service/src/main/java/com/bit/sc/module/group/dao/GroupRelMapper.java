package com.bit.sc.module.group.dao;

import com.bit.sc.module.group.pojo.GroupRel;
import com.bit.sc.module.group.pojo.GroupResEqu;
import com.bit.sc.module.group.vo.GroupRelVO;
import com.bit.sc.module.group.vo.GroupResEquVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * GroupRel管理的Dao
 * @author
 *
 */
public interface GroupRelMapper {
	/**
	 * 根据条件查询GroupRel
	 * @param groupRelVO
	 * @return
	 */
	public List<GroupRel> findByConditionPage(GroupRelVO groupRelVO);
	/**
	 * 查询所有GroupRel
	 * @return
	 */
	public List<GroupRel> findAll(@Param(value = "sorter") String sorter);
	/**
	 * 通过主键查询单个GroupRel
	 * @param id
	 * @return
	 */
	public GroupRel findById(@Param(value = "id") Long id);
	/**
	 * 保存GroupRel
	 * @param groupRel
	 */
	public void add(GroupRel groupRel);
	/**
	 * 更新GroupRel
	 * @param groupRel
	 */
	public void update(GroupRel groupRel);
	/**
	 * 删除GroupRel
	 * @param
	 */
	public void batchDelete(@Param(value = "ids") List<Long> ids);
	/**
	 * 删除GroupRel
	 * @param id
	 */
	public void delete(@Param(value = "id") Long id);

	public List<GroupRel> findGroupRelList(Long groupId);

	/**
	 * 批量保存
	 * @param groupRels
	 */
	public void batchAdd(@Param(value = "list") List<GroupRel> groupRels);

	/**
	 * 根据参数删除
	 * @param groupRels
	 */
	void delByParam(@Param(value = "list") List<GroupRel>  groupRels);

	/**
	 * 根据参数 批量查询list
	 * @param groupRel
	 * @return
	 */
	List<GroupRel> findByParam(GroupRel groupRel);
	/**
	 * 根据条件查询GroupRel
	 * @param groupRelVO
	 * @return
	 */
	List<GroupRel> findListPage(GroupRelVO groupRelVO);
    /**
     * 根据groupid 和type 查询设备  分页 查询居民
     * @param groupResEquVo
     * @return
     */
    List<GroupResEqu> findPageResByParam(GroupResEquVo groupResEquVo);
    /**
     * 根据groupid 和type 查询设备  分页 查询设备
     * @param groupResEquVo
     * @return
     */
    List<GroupResEqu> findPageEquByParam(GroupResEquVo groupResEquVo);

	/**
	 * 根据groupId，relId，type查询
	 * @param groupRel
	 * @return
	 */
	GroupRel findByIdAndType(GroupRel groupRel);
}
