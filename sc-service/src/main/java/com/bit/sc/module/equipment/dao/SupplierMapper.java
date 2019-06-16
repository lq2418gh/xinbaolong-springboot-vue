package com.bit.sc.module.equipment.dao;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import com.bit.sc.module.equipment.pojo.Supplier;
import com.bit.sc.module.equipment.vo.SupplierVO;

/**
 * Supplier管理的Dao
 * @author liuyancheng
 *
 */
public interface SupplierMapper {
	/**
	 * 根据条件查询Supplier
	 * @param supplierVO
	 * @return
	 */
	public List<Supplier> findByConditionPage(SupplierVO supplierVO);
	/**
	 * 查询所有Supplier
	 * @return
	 */
	public List<Supplier> findAll(@Param(value = "sorter") String sorter);
	/**
	 * 通过主键查询单个Supplier
	 * @param id	 	 
	 * @return
	 */
	public Supplier findById(@Param(value = "id") Long id);
	/**
	 * 批量保存Supplier
	 * @param suppliers
	 */
	public void batchAdd(List<Supplier> suppliers);
	/**
	 * 保存Supplier
	 * @param supplier
	 */
	public void add(Supplier supplier);
	/**
	 * 批量更新Supplier
	 * @param suppliers
	 */
	public void batchUpdate(List<Supplier> suppliers);
	/**
	 * 更新Supplier
	 * @param supplier
	 */
	public void update(Supplier supplier);
	/**
	 * 删除Supplier
	 * @param suppliers
	 */
	public void batchDelete(List<Long> ids);
	/**
	 * 删除Supplier
	 * @param id
	 */
	public void delete(@Param(value = "id") Long id);
}
