package com.bit.sc.module.house.service.impl;

import java.util.List;
import java.util.ArrayList;

import com.bit.sc.module.house.dao.OwnerCustodianRentMapper;
import com.bit.sc.module.house.pojo.OwnerCustodianRent;
import com.bit.sc.module.house.service.OwnerCustodianRentService;
import com.bit.sc.module.house.vo.OwnerCustodianRentVO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.bit.base.vo.BaseVo;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.bit.base.service.BaseService;
import org.springframework.transaction.annotation.Transactional;

/**
 * OwnerCustodianRent的Service实现类
 * @author chenduo
 *
 */
@Service("ownerCustodianRentService")
public class OwnerCustodianRentServiceImpl extends BaseService implements OwnerCustodianRentService {
	
	private static final Logger logger = LoggerFactory.getLogger(OwnerCustodianRentServiceImpl.class);
	
	@Autowired
	private OwnerCustodianRentMapper ownerCustodianRentMapper;
	
	/**
	 * 根据条件查询OwnerCustodianRent
	 * @param page
	 * @return
	 */
	@Override
	public BaseVo findByConditionPage(OwnerCustodianRentVO ownerCustodianRentVO){
		PageHelper.startPage(ownerCustodianRentVO.getPageNum(), ownerCustodianRentVO.getPageSize());
		List<OwnerCustodianRent> list = ownerCustodianRentMapper.findByConditionPage(ownerCustodianRentVO);
		PageInfo<OwnerCustodianRent> pageInfo = new PageInfo<OwnerCustodianRent>(list);
		BaseVo baseVo = new BaseVo();
		baseVo.setData(pageInfo);
		return baseVo;
	}
	/**
	 * 查询所有OwnerCustodianRent
	 * @param sorter 排序字符串
	 * @return
	 */
	@Override
	public List<OwnerCustodianRent> findAll(String sorter){
		return ownerCustodianRentMapper.findAll(sorter);
	}
	/**
	 * 通过主键查询单个OwnerCustodianRent
	 * @param id
	 * @return
	 */
	@Override
	public OwnerCustodianRent findById(Long id){
		return ownerCustodianRentMapper.findById(id);
	}
	
	/**
	 * 批量保存OwnerCustodianRent
	 * @param ownerCustodianRents
	 */
	@Override
	@Transactional
	public void batchAdd(List<OwnerCustodianRent> ownerCustodianRents){
		ownerCustodianRentMapper.batchAdd(ownerCustodianRents);
	}
	/**
	 * 保存OwnerCustodianRent
	 * @param ownerCustodianRent
	 */
	@Override
	@Transactional
	public void add(OwnerCustodianRent ownerCustodianRent){
		ownerCustodianRentMapper.add(ownerCustodianRent);
	}
	/**
	 * 批量更新OwnerCustodianRent
	 * @param ownerCustodianRents
	 */
	@Override
	@Transactional
	public void batchUpdate(List<OwnerCustodianRent> ownerCustodianRents){
		ownerCustodianRentMapper.batchUpdate(ownerCustodianRents);
	}
	/**
	 * 更新OwnerCustodianRent
	 * @param ownerCustodianRent
	 */
	@Override
	@Transactional
	public void update(OwnerCustodianRent ownerCustodianRent){
		ownerCustodianRentMapper.update(ownerCustodianRent);
	}
	/**
	 * 删除OwnerCustodianRent
	 * @param ids
	 */
	@Override
	@Transactional
	public void batchDelete(List<Long> ids){
		ownerCustodianRentMapper.batchDelete(ids);
	}
	/**
	 * 删除OwnerCustodianRent
	 * @param id
	 */
	@Override
	@Transactional
	public void delete(Long id){
		ownerCustodianRentMapper.delete(id);
	}
}
