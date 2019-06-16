package com.bit.sc.module.resident.service.impl;

import java.util.List;
import java.util.ArrayList;

import com.bit.sc.module.resident.dao.ResidentMobileMapper;
import com.bit.sc.module.resident.pojo.ResidentMobile;
import com.bit.sc.module.resident.service.ResidentMobileService;
import com.bit.sc.module.resident.vo.ResidentMobileVO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.bit.base.vo.BaseVo;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.transaction.annotation.Transactional;

/**
 * ResidentMobile的Service实现类
 * @author chenduo
 *
 */
@Service("residentMobileService")
public class ResidentMobileServiceImpl implements ResidentMobileService {
	
	private static final Logger logger = LoggerFactory.getLogger(ResidentMobileServiceImpl.class);
	
	@Autowired
	private ResidentMobileMapper residentMobileMapper;
	
	/**
	 * 根据条件查询ResidentMobile
	 * @param residentMobileVO
	 * @return
	 * @author chenduo
	 */
	@Override
	public BaseVo findByConditionPage(ResidentMobileVO residentMobileVO){
		PageHelper.startPage(residentMobileVO.getPageNum(), residentMobileVO.getPageSize());
		List<ResidentMobile> list = residentMobileMapper.findByConditionPage(residentMobileVO);
		PageInfo<ResidentMobile> pageInfo = new PageInfo<ResidentMobile>(list);
		residentMobileVO.setData(pageInfo);
		return residentMobileVO;
	}
	/**
	 * 查询所有ResidentMobile
	 * @param sorter 排序字符串
	 * @return
	 * @author chenduo
	 */
	@Override
	public List<ResidentMobile> findAll(String sorter){
		return residentMobileMapper.findAll(sorter);
	}
	/**
	 * 通过主键查询单个ResidentMobile
	 * @param id
	 * @return
	 * @author chenduo
	 */
	@Override
	public ResidentMobile findById(Long id){
		return residentMobileMapper.findById(id);
	}
	
	/**
	 * 批量保存ResidentMobile
	 * @param residentMobiles
	 * @author chenduo
	 */
	@Override
	@Transactional
	public void batchAdd(List<ResidentMobile> residentMobiles){
		residentMobileMapper.batchAdd(residentMobiles);
	}
	/**
	 * 保存ResidentMobile
	 * @param residentMobile
	 * @author chenduo
	 */
	@Override
	@Transactional
	public void add(ResidentMobile residentMobile){
		residentMobileMapper.add(residentMobile);
	}
	/**
	 * 批量更新ResidentMobile
	 * @param residentMobiles
	 * @author chenduo
	 */
	@Override
	@Transactional
	public void batchUpdate(List<ResidentMobile> residentMobiles){
		residentMobileMapper.batchUpdate(residentMobiles);
	}
	/**
	 * 更新ResidentMobile
	 * @param residentMobile
	 * @author chenduo
	 */
	@Override
	@Transactional
	public void update(ResidentMobile residentMobile){
		residentMobileMapper.update(residentMobile);
	}
	/**
	 * 删除ResidentMobile
	 * @param ids
	 * @author chenduo
	 */
	@Override
	@Transactional
	public void batchDelete(List<Long> ids){
		residentMobileMapper.batchDelete(ids);
	}
	/**
	 * 删除ResidentMobile
	 * @param id
	 * @author chenduo
	 */
	@Override
	@Transactional
	public void delete(Long id){
		residentMobileMapper.delete(id);
	}
}
