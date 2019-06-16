package com.bit.sc.module.sys.service;

import java.util.List;

import com.bit.sc.module.sys.vo.RoleVO;
import com.github.pagehelper.PageInfo;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Service;
import com.bit.sc.module.sys.pojo.ApplyVersion;
import com.bit.sc.module.sys.vo.ApplyVersionVO;
import com.bit.base.vo.BaseVo;
/**
 * ApplyVersion的Service
 * @author codeGenerator
 */
@Service
public interface ApplyVersionService {
	/**
	 * 根据条件查询ApplyVersion
	 * @param applyVersionVO
	 * @return
	 */
	public BaseVo findByConditionPage(ApplyVersionVO applyVersionVO);
	/**
	 * 查询所有ApplyVersion
	 * @param sorter 排序字符串
	 * @return
	 */
	public List<ApplyVersion> findAll(String sorter);
	/**
	 * 通过主键查询单个ApplyVersion
	 * @param id
	 * @return
	 */
	public ApplyVersion findById(Long id);
	/**
	 * 批量保存ApplyVersion
	 * @param applyVersions
	 */
	public void batchAdd(List<ApplyVersion> applyVersions);
	/**
	 * 保存ApplyVersion
	 * @param applyVersion
	 */
	public void add(ApplyVersion applyVersion);
	/**
	 * 批量更新ApplyVersion
	 * @param applyVersions
	 */
	public void batchUpdate(List<ApplyVersion> applyVersions);
	/**
	 * 更新ApplyVersion
	 * @param applyVersion
	 */
	public void update(ApplyVersion applyVersion);
	/**
	 * 删除ApplyVersion
	 * @param id
	 */
	public void delete(Long id);
	/**
	 * 批量删除ApplyVersion
	 * @param ids
	 */
	public void batchDelete(List<Long> ids);



	/**
	 * 查询app发布的版本
	 * @param vo
	 * @return List<ApplyVersionVO>
	 */
	public PageInfo<ApplyVersionVO>  findVersionListByCondition(ApplyVersionVO vo);
}
