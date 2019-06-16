package com.bit.sc.module.sys.dao;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import com.bit.sc.module.sys.pojo.Dict;
import com.bit.sc.module.sys.vo.DictVO;

/**
 * Dict管理的Dao
 * @author 
 *
 */
public interface DictMapper {
	/**
	 * 根据条件查询Dict
	 * @param dictVO
	 * @return
	 */
	public List<Dict> findByConditionPage(DictVO dictVO);
	/**
	 * 查询所有Dict
	 * @return
	 */
	public List<Dict> findAll(@Param(value = "sorter") String sorter);
	/**
	 * 通过主键查询单个Dict
	 * @param id	 	 
	 * @return
	 */
	public Dict findById(@Param(value = "id") Long id);
	/**
	 * 批量保存Dict
	 * @param dicts
	 */
	public void batchAdd(List<Dict> dicts);
	/**
	 * 保存Dict
	 * @param dict
	 */
	public void add(Dict dict);
	/**
	 * 批量更新Dict
	 * @param dicts
	 */
	public void batchUpdate(List<Dict> dicts);
	/**
	 * 更新Dict
	 * @param dict
	 */
	public void update(Dict dict);
	/**
	 * 删除Dict
	 * @param dicts
	 */
	public void batchDelete(List<Long> ids);
	/**
	 * 删除Dict
	 * @param id
	 */
	public void delete(@Param(value = "id") Long id);





	//手写方法 start

	/**
	 *
	 * 功能描述: 根据模块查询所有的该模块信息
	 *
	 * @param:
	 * @return:
	 * @author: chenduo
	 * @date: 2018/11/14 11:37
	 */
	List<Dict> listModule(@Param(value = "module") String module);


	/**
	 *
	 * 功能描述: 根据模块和code查询该模块信息 单查
	 *
	 * @param:
	 * @return:
	 * @author: chenduo
	 * @date: 2018/11/14 11:37
	 */
	Dict queryDictByModuleCode(@Param(value = "module") String module,@Param(value = "dictCode") String dictCode);


	/**
	 *
	 * 功能描述: 根据模块和value模糊查询该模块信息
	 *
	 * @param:
	 * @return:
	 * @author: chenduo
	 * @date: 2018/11/14 11:37
	 */
	List<Dict> getDictByModuleLikeValue(@Param(value = "module") String module,@Param(value = "value") String value);



}
