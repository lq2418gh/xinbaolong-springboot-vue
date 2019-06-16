package com.bit.sc.module.sys.service;

import java.util.List;
import org.springframework.stereotype.Service;
import com.bit.sc.module.sys.pojo.Function;
import com.bit.sc.module.sys.vo.FunctionVO;
import com.bit.base.vo.BaseVo;
/**
 * Function的Service
 * @author liqi
 */
@Service
public interface FunctionService {
	/**
	 * 根据条件查询Function
	 * @param functionVO
	 * @return
	 */
	public BaseVo findByConditionPage(FunctionVO functionVO);
	/**
	 * 查询所有Function
	 * @param sorter 排序字符串
	 * @return
	 */
	public List<Function> findAll(String sorter);
	/**
	 * 通过主键查询单个Function
	 * @param id
	 * @return
	 */
	public Function findById(Long id);
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
	 * @param id
	 */
	public void delete(Long id);
	/**
	 * 批量删除Function
	 * @param ids
	 */
	public void batchDelete(List<Long> ids);
	/**
	 * functionCode 查询统计
	 * @param functionCode
	 * @return
	 */
	public int findCountByFunctionCode(String functionCode);
}
