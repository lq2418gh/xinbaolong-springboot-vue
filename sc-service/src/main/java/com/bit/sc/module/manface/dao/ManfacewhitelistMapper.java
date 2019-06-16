package com.bit.sc.module.manface.dao;

import java.util.List;

import com.bit.sc.module.manface.pojo.Manfacewhitelist;
import com.bit.sc.module.manface.vo.ManfaceResidentEquipmentVO;
import com.bit.sc.module.manface.vo.ManfacewhitelistVO;
import com.bit.sc.module.resident.vo.ResidentVO;
import org.apache.ibatis.annotations.Param;

/**
 * Manfacewhitelist管理的Dao
 * @author chenduo
 *
 */
public interface ManfacewhitelistMapper {
	/**
	 * 根据条件查询Manfacewhitelist
	 * @param manfacewhitelistVO
	 * @return
	 * @author chenduo
	 */
	public List<Manfacewhitelist> findByConditionPage(ManfacewhitelistVO manfacewhitelistVO);
	/**
	 * 查询所有Manfacewhitelist
	 * @return
	 * @author chenduo
	 */
	public List<Manfacewhitelist> findAll(@Param(value = "sorter") String sorter);
	/**
	 * 通过主键查询单个Manfacewhitelist
	 * @param id	 	 
	 * @return
	 * @author chenduo
	 */
	public Manfacewhitelist findById(@Param(value = "id") Long id);
	/**
	 * 批量保存Manfacewhitelist
	 * @param manfacewhitelistList
	 *         @author chenduo
	 */
	public void batchAdd(@Param(value = "manfacewhitelistList") List<Manfacewhitelist> manfacewhitelistList);
	/**
	 * 保存Manfacewhitelist
	 * @param manfacewhitelist
	 *         @author chenduo
	 */
	public void add(Manfacewhitelist manfacewhitelist);
	/**
	 * 批量更新Manfacewhitelist
	 * @param manfacewhitelists
	 *         @author chenduo
	 */
	public void batchUpdate(List<Manfacewhitelist> manfacewhitelists);
	/**
	 * 更新Manfacewhitelist
	 * @param manfacewhitelist
	 *         @author chenduo
	 */
	public void update(Manfacewhitelist manfacewhitelist);
	/**
	 * 删除Manfacewhitelist
	 * @param ids
	 *         @author chenduo
	 */
	public void batchDelete(@Param(value = "ids") List<Long> ids);
	/**
	 * 删除Manfacewhitelist
	 * @param id
	 * @author chenduo
	 */
	public void delete(@Param(value = "id") Long id);


	/**
	 * 分页查询Manfacewhitelist列表 页面显示使用
	 * @param manfacewhitelistVO
	 * @return
	 * @author chenduo
	 */
	List<ManfaceResidentEquipmentVO> queryAllByPage(ManfacewhitelistVO manfacewhitelistVO);
	/**
	 *
	 * 功能描述:查询符合居民和地址参数的记录数
	 *
	 * @param:
	 * @return:
	 * @author: chenduo
	 * @date: 2018/11/28 10:02
	 */

	Integer countList(ManfacewhitelistVO manfacewhitelistVO);

	void updateStatusByResidentId(@Param(value = "residentId") Long residentId);


}
