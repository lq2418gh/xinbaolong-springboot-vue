package com.bit.sc.module.equipment.service;

import java.util.List;
import org.springframework.stereotype.Service;
import com.bit.sc.module.equipment.pojo.Supplier;
import com.bit.sc.module.equipment.vo.SupplierVO;
import com.bit.base.vo.BaseVo;
/**
 * Supplier的Service
 * @author liuyancheng
 */
public interface SupplierService {
	/**
	 * 根据条件查询Supplier
	 * @param supplierVO
	 * @return
	 */
	BaseVo findByConditionPage(SupplierVO supplierVO);
	/**
	 * 查询所有Supplier
	 * @param sorter 排序字符串
	 * @return
	 */
	List<Supplier> findAll(String sorter);
	/**
	 * 通过主键查询单个Supplier
	 * @param id
	 * @return
	 */
	Supplier findById(Long id);

	/**
	 * 批量保存Supplier
	 * @param suppliers
	 */
	void batchAdd(List<Supplier> suppliers);
	/**
	 * 保存Supplier
	 * @param supplier
	 */
	void add(Supplier supplier);
	/**
	 * 批量更新Supplier
	 * @param suppliers
	 */
	void batchUpdate(List<Supplier> suppliers);
	/**
	 * 更新Supplier
	 * @param supplier
	 */
	void update(Supplier supplier);
	/**
	 * 删除Supplier
	 * @param id
	 */
	void delete(Long id);
	/**
	 * 批量删除Supplier
	 * @param ids
	 */
	void batchDelete(List<Long> ids);
}
