package com.bit.sc.module.mobile.service.impl;

import java.util.List;
import java.util.ArrayList;

import com.bit.sc.module.mobile.dao.MobileVersionMapper;
import com.bit.sc.module.mobile.pojo.MobileVersion;
import com.bit.sc.module.mobile.service.MobileVersionService;
import com.bit.sc.module.mobile.vo.MobileVersionVO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.bit.base.vo.BaseVo;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
/**
 * MobileVersion的Service实现类
 * @author codeGenerator
 *
 */
@Service("mobileVersionService")
public class MobileVersionServiceImpl implements MobileVersionService {
	
	private static final Logger logger = LoggerFactory.getLogger(MobileVersionServiceImpl.class);
	
	@Autowired
	private MobileVersionMapper mobileVersionMapper;
	
	/**
	 * 根据条件查询MobileVersion
	 * @param mobileVersionVO
	 * @return
	 */
	@Override
	public BaseVo findByConditionPage(MobileVersionVO mobileVersionVO){
		PageHelper.startPage(mobileVersionVO.getPageNum(), mobileVersionVO.getPageSize());
		List<MobileVersion> list = mobileVersionMapper.findByConditionPage(mobileVersionVO);
		PageInfo<MobileVersion> pageInfo = new PageInfo<MobileVersion>(list);
		mobileVersionVO.setData(pageInfo);
		return mobileVersionVO;
	}
	/**
	 * 查询所有MobileVersion
	 * @param sorter 排序字符串
	 * @return
	 */
	@Override
	public List<MobileVersion> findAll(String sorter){
		return mobileVersionMapper.findAll(sorter);
	}
	/**
	 * 通过主键查询单个MobileVersion
	 * @param id
	 * @return
	 */
	@Override
	public MobileVersion findById(String id){
		return mobileVersionMapper.findById(id);
	}
	
	/**
	 * 批量保存MobileVersion
	 * @param mobileVersions
	 */
	@Override
	public void batchAdd(List<MobileVersion> mobileVersions){
		mobileVersionMapper.batchAdd(mobileVersions);
	}
	/**
	 * 保存MobileVersion
	 * @param mobileVersion
	 */
	@Override
	public void add(MobileVersion mobileVersion){
		mobileVersionMapper.add(mobileVersion);
	}
	/**
	 * 批量更新MobileVersion
	 * @param mobileVersions
	 */
	@Override
	public void batchUpdate(List<MobileVersion> mobileVersions){
		mobileVersionMapper.batchUpdate(mobileVersions);
	}
	/**
	 * 更新MobileVersion
	 * @param mobileVersion
	 */
	@Override
	public void update(MobileVersion mobileVersion){
		mobileVersionMapper.update(mobileVersion);
	}
	/**
	 * 删除MobileVersion
	 * @param ids
	 */
	@Override
	public void batchDelete(List<Long> ids){
		mobileVersionMapper.batchDelete(ids);
	}
	/**
	 * 删除MobileVersion
	 * @param id
	 */
	@Override
	public void delete(String id){
		mobileVersionMapper.delete(id);
	}
}
