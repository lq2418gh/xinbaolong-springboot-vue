package com.bit.sc.module.sys.service;

import java.util.List;
import org.springframework.stereotype.Service;
import com.bit.sc.module.sys.pojo.Apply;
import com.bit.sc.module.sys.vo.ApplyVO;
import com.bit.base.vo.BaseVo;
/**
 * Apply的Service
 * @author codeGenerator
 */
@Service
public interface ApplyService {
	/**
	 * 根据条件查询Apply
	 * @param applyVO
	 * @return
	 */
	public BaseVo findByConditionPage(ApplyVO applyVO);
	/**
	 * 查询所有Apply
	 * @param sorter 排序字符串
	 * @return
	 */
	public List<Apply> findAll(String sorter);
	/**
	 * 通过主键查询单个Apply
	 * @param id
	 * @return
	 */
	public Apply findById(Long id);
	/**
	 * 批量保存Apply
	 * @param applys
	 */
	public void batchAdd(List<Apply> applys);
	/**
	 * 保存Apply
	 * @param apply
	 */
	public void add(Apply apply);
	/**
	 * 批量更新Apply
	 * @param applys
	 */
	public void batchUpdate(List<Apply> applys);
	/**
	 * 更新Apply
	 * @param apply
	 */
	public void update(Apply apply);
	/**
	 * 删除Apply
	 * @param id
	 */
	public void delete(Long id);
	/**
	 * 批量删除Apply
	 * @param ids
	 */
	public void batchDelete(List<Long> ids);

	/**
	 * 根据参数查询
	 * @param applyVO
	 * @return
	 */
	List<Apply> findByParm(ApplyVO applyVO);

    /**
     * 查询非居民app的平台
     * @param
     * @return
     */
	List<Apply> getNoneResident();
}
