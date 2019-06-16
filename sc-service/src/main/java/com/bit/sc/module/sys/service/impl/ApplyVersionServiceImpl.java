package com.bit.sc.module.sys.service.impl;

import java.util.List;
import java.util.ArrayList;

import com.bit.sc.module.sys.vo.RoleVO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.bit.base.vo.BaseVo;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.bit.sc.module.sys.pojo.ApplyVersion;
import com.bit.sc.module.sys.vo.ApplyVersionVO;
import com.bit.sc.module.sys.dao.ApplyVersionMapper;
import com.bit.sc.module.sys.service.ApplyVersionService;
/**
 * ApplyVersion的Service实现类
 * @author codeGenerator
 *
 */
@Service("applyVersionService")
public class ApplyVersionServiceImpl implements ApplyVersionService{
	
	private static final Logger logger = LoggerFactory.getLogger(ApplyVersionServiceImpl.class);
	
	@Autowired
	private ApplyVersionMapper applyVersionMapper;
	
	/**
	 * 根据条件查询ApplyVersion
	 * @param applyVersionVO
	 * @return
	 */
	@Override
	public BaseVo findByConditionPage(ApplyVersionVO applyVersionVO){
		PageHelper.startPage(applyVersionVO.getPageNum(), applyVersionVO.getPageSize());
		List<ApplyVersion> list = applyVersionMapper.findByConditionPage(applyVersionVO);
		PageInfo<ApplyVersion> pageInfo = new PageInfo<ApplyVersion>(list);
		applyVersionVO.setData(pageInfo);
		return applyVersionVO;
	}
	/**
	 * 查询所有ApplyVersion
	 * @param sorter 排序字符串
	 * @return
	 */
	@Override
	public List<ApplyVersion> findAll(String sorter){
		return applyVersionMapper.findAll(sorter);
	}
	/**
	 * 通过主键查询单个ApplyVersion
	 * @param id
	 * @return
	 */
	@Override
	public ApplyVersion findById(Long id){
		return applyVersionMapper.findById(id);
	}
	
	/**
	 * 批量保存ApplyVersion
	 * @param applyVersions
	 */
	@Override
	public void batchAdd(List<ApplyVersion> applyVersions){
		applyVersionMapper.batchAdd(applyVersions);
	}
	/**
	 * 保存ApplyVersion
	 * @param applyVersion
	 */
	@Override
	public void add(ApplyVersion applyVersion){
		applyVersionMapper.add(applyVersion);
	}
	/**
	 * 批量更新ApplyVersion
	 * @param applyVersions
	 */
	@Override
	public void batchUpdate(List<ApplyVersion> applyVersions){
		applyVersionMapper.batchUpdate(applyVersions);
	}
	/**
	 * 更新ApplyVersion
	 * @param applyVersion
	 */
	@Override
	public void update(ApplyVersion applyVersion){
		applyVersionMapper.update(applyVersion);
	}
	/**
	 * 删除ApplyVersion
	 * @param ids
	 */
	@Override
	public void batchDelete(List<Long> ids){
		applyVersionMapper.batchDelete(ids);
	}
	/**
	 * 删除ApplyVersion
	 * @param id
	 */
	@Override
	public void delete(Long id){
		applyVersionMapper.delete(id);
	}


	/**
	 * 查询app发布的版本
	 * @param vo
	 * @return List<ApplyVersionVO>
	 */
	@Override
	public PageInfo<ApplyVersionVO> findVersionListByCondition(ApplyVersionVO vo){

		PageHelper.startPage(vo.getPageNum(), vo.getPageSize());
		List<ApplyVersionVO>list = applyVersionMapper.findVersionListByCondition(vo);
		PageInfo<ApplyVersionVO> pageInfo = new PageInfo(list);
		return pageInfo;
	}
}
