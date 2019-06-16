package com.bit.sc.module.sys.service;

import com.bit.base.vo.BaseVo;
import com.bit.sc.module.sys.pojo.Resource;
import com.bit.sc.module.sys.vo.ResourceVO;

import java.util.List;
/**
 * Resource的Service
 * @author liqi
 */
public interface ResourceService {
	/**
	 * 根据条件查询Resource
	 * @param resourceVO
	 * @return
	 */
	BaseVo findByConditionPage(ResourceVO resourceVO);
	/**
	 * 查询所有Resource
	 * @param sorter 排序字符串
	 * @return
	 */
	List<Resource> findAll(String sorter);
	/**
	 * 通过主键查询单个Resource
	 * @param id
	 * @return
	 */
	Resource findById(Long id);

	/**
	 * 批量保存Resource
	 * @param resources
	 */
	void batchAdd(List<Resource> resources);
	/**
	 * 保存Resource
	 * @param resource
	 */
	void add(Resource resource);
	/**
	 * 更新Resource
	 * @param resource
	 */
	void update(Resource resource);
	/**
	 * 删除Resource
	 * @param id
	 */
	void delete(Long id);
	/**
	 * 批量删除Resource
	 * @param ids
	 */
	void batchDelete(List<Long> ids);
	/**
	 * 查询资源树 0查询菜单  1查询按钮  2查询所有
	 * @param applicationCode
	 * @param type
	 * @return
	 */
	List<Resource> resourceListByParam( String applicationCode,Integer type);
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
