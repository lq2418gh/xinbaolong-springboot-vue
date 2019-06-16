package com.bit.sc.module.equipment.service.impl;

import java.util.Date;
import java.util.List;
import java.util.ArrayList;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.bit.base.vo.BaseVo;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.bit.sc.module.equipment.pojo.Supplier;
import com.bit.sc.module.equipment.vo.SupplierVO;
import com.bit.sc.module.equipment.dao.SupplierMapper;
import com.bit.sc.module.equipment.service.SupplierService;
import com.bit.base.service.BaseService;
import org.springframework.transaction.annotation.Transactional;

/**
 * Supplier的Service实现类
 * @author liuyancheng
 *
 */
@Service("SupplierService")
public class SupplierServiceImpl extends BaseService implements SupplierService{
	
	private static final Logger logger = LoggerFactory.getLogger(SupplierServiceImpl.class);
	
	@Autowired
	private SupplierMapper supplierMapper;
	
	/**
	 * 根据条件查询Supplier
	 * @param supplierVO
	 * @return
	 */
	@Override
	public BaseVo findByConditionPage(SupplierVO supplierVO){
		PageHelper.startPage(supplierVO.getPageNum(), supplierVO.getPageSize());
		//根据创建时间排序
		supplierVO.setOrderBy("create_time desc");
		List<Supplier> list = supplierMapper.findByConditionPage(supplierVO);
		PageInfo<Supplier> pageInfo = new PageInfo<Supplier>(list);
		BaseVo baseVo = new BaseVo();
		baseVo.setData(pageInfo);
		return baseVo;
	}
	/**
	 * 查询所有Supplier
	 * @param sorter 排序字符串
	 * @return
	 */
	@Override
	public List<Supplier> findAll(String sorter){
		return supplierMapper.findAll(sorter);
	}
	/**
	 * 通过主键查询单个Supplier
	 * @param id
	 * @return
	 */
	@Override
	public Supplier findById(Long id){
		return supplierMapper.findById(id);
	}
	
	/**
	 * 批量保存Supplier
	 * @param suppliers
	 */
	@Override
	public void batchAdd(List<Supplier> suppliers){
		supplierMapper.batchAdd(suppliers);
	}
	/**
	 * 保存Supplier
	 * @param supplier
	 */
	@Override
	@Transactional
	public void add(Supplier supplier){
		Long id = getCurrentUserInfo().getId();
		supplier.setCreateUserId(id);
		supplier.setCreateTime(new Date());
//		supplier.setIsDelete(1);
		supplierMapper.add(supplier);
	}
	/**
	 * 批量更新Supplier
	 * @param suppliers
	 */
	@Override
	public void batchUpdate(List<Supplier> suppliers){
		supplierMapper.batchUpdate(suppliers);
	}
	/**
	 * 更新Supplier
	 * @param supplier
	 */
	@Override
	@Transactional
	public void update(Supplier supplier){
		supplierMapper.update(supplier);
	}
	/**
	 * 删除Supplier
	 * @param ids
	 */
	@Override
	public void batchDelete(List<Long> ids){
		supplierMapper.batchDelete(ids);
	}
	/**
	 * 删除Supplier
	 * @param id
	 */
	@Override
	@Transactional
	public void delete(Long id){
		supplierMapper.delete(id);
	}
}
