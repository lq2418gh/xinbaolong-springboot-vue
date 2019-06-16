package com.bit.sc.module.sys.dao;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import com.bit.sc.module.sys.pojo.Resource;
import com.bit.sc.module.sys.vo.ResourceVO;

/**
 * Resource管理的Dao
 * @author liqi
 *
 */
public interface ResourceMapper {
	/**
	 * 根据条件查询Resource
	 * @param resourceVO
	 * @return
	 */
	public List<Resource> findByConditionPage(ResourceVO resourceVO);
	/**
	 * 查询所有Resource
	 * @return
	 */
	public List<Resource> findAll(@Param(value = "sorter") String sorter);
	/**
	 * 通过主键查询单个Resource
	 * @param id	 	 
	 * @return
	 */
	public Resource findById(@Param(value = "id") Long id);
	/**
	 * 批量保存Resource
	 * @param resources
	 */
	public void batchAdd(List<Resource> resources);
	/**
	 * 保存Resource
	 * @param resource
	 */
	public void add(Resource resource);
	/**
	 * 更新Resource
	 * @param resource
	 */
	public void update(Resource resource);
	/**
	 * 删除Resource
	 * @param ids
	 */
	public void batchDelete(@Param(value = "ids") List<Long> ids);
	/**
	 * 删除Resource
	 * @param id
	 */
	public void delete(@Param(value = "id") Long id);
	/**
	 * 查询资源树 0查询菜单  1查询按钮  2查询所有
	 * @param applicationCode
	 * @param type
	 * @return
	 */
	List<Resource> resourceListByParam(@Param(value = "roleIds") List<Long> roleIds, @Param(value = "applicationCode") String applicationCode, @Param(value = "show") int show,@Param(value = "type") Integer type);
	/**
	 * 统计  根据资源编码  不能重复
	 * @param resourceCode
	 * @return
	 */
	int findCountByResourceCode(String resourceCode);
	/**
	 * 查询resource--web--所有--树
	 * @param applicationCode
	 * @return
	 */
	List<Resource> findByApplication(String applicationCode);
}
