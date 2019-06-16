package com.bit.sc.module.sys.dao;

import java.util.List;

import com.bit.sc.module.sys.pojo.AreaCode;
import com.bit.sc.module.sys.vo.AreaCodeVO;
import org.apache.ibatis.annotations.Param;

/**
 * AreaCode管理的Dao
 * @author liqi
 *
 */
public interface AreaCodeMapper {
	/**
	 * 根据条件查询AreaCode
	 * @param areaCodeVO
	 * @return
	 */
	public List<AreaCode> findByConditionPage(AreaCodeVO areaCodeVO);
	/**
	 * 查询所有AreaCode
	 * @return
	 */
	public List<AreaCode> findAll(@Param(value = "sorter") String sorter);
	/**
	 * 保存AreaCode
	 * @param areaCode
	 */
	public void add(AreaCode areaCode);
	/**
	 * 更新AreaCode
	 * @param areaCode
	 */
	public void update(AreaCode areaCode);
	/**
	 * 删除AreaCode
	 * @param ids
	 */
	public void batchDelete(@Param(value = "ids")List<Long> ids);
	/**
	 * 删除AreaCode
	 * @param code
	 */
	public void delete(@Param(value = "code") String code);
	/**
	 * 通过主键查询单个AreaCode
	 * @param arCode
	 * @return
	 */
	AreaCode findByArCode(String arCode);

	List<AreaCode> findByParentCode(@Param(value = "parentCode")String parentCode);

	/**
	 * 查询上一层对象
	 * @param arCode
	 * @return
	 */
	AreaCode findParentByArCode(String arCode);
}
