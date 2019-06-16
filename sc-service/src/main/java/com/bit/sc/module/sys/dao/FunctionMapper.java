package com.bit.sc.module.sys.dao;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import com.bit.sc.module.sys.pojo.Function;
import com.bit.sc.module.sys.vo.FunctionVO;

/**
 * Function管理的Dao
 * @author liqi
 *
 */
public interface FunctionMapper {
	/**
	 * 根据条件查询Function
	 * @param functionVO
	 * @return
	 */
	public List<Function> findByConditionPage(FunctionVO functionVO);
	/**
	 * 查询所有Function
	 * @return
	 */
	public List<Function> findAll(@Param(value = "sorter") String sorter);
	/**
	 * 通过主键查询单个Function
	 * @param id	 	 
	 * @return
	 */
	public Function findById(@Param(value = "id") Long id);
	/**
	 * 批量保存Function
	 * @param functions
	 */
	public void batchAdd(List<Function> functions);
	/**
	 * 保存Function
	 * @param function
	 */
	public void add(Function function);
	/**
	 * 批量更新Function
	 * @param functions
	 */
	public void batchUpdate(List<Function> functions);
	/**
	 * 更新Function
	 * @param function
	 */
	public void update(Function function);
	/**
	 * 删除Function
	 * @param ids
	 */
	public void batchDelete(@Param(value = "ids")List<Long> ids);
	/**
	 * 删除Function
	 * @param id
	 */
	public void delete(@Param(value = "id") Long id);
	/**
	 * functionCode 查询统计
	 * @param functionCode
	 * @return
	 */
	public int findCountByFunctionCode(String functionCode);
}
