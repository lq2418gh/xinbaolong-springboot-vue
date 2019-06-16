package com.bit.sc.module.manface.service;

import com.bit.base.vo.BaseVo;
import com.bit.sc.module.manface.pojo.Manfacewhitelist;
import com.bit.sc.module.manface.vo.ManfacewhitelistVO;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

/**
 * Manfacewhitelist的Service
 * @author chenduo
 */
public interface ManfacewhitelistService {

	/**
	 * 查询所有Manfacewhitelist
	 * @param sorter 排序字符串
	 * @return
	 * @author chenduo
	 */
	List<Manfacewhitelist> findAll(String sorter);
	/**
	 * 通过主键查询单个Manfacewhitelist
	 * @param id
	 * @return
	 * @author chenduo
	 */
	Manfacewhitelist findById(Long id);


	/**
	 * 保存Manfacewhitelist
	 * @param manfacewhitelist
	 *         @author chenduo
	 */
	void add(Manfacewhitelist manfacewhitelist);

	/**
	 * 更新Manfacewhitelist
	 * @param manfacewhitelist
	 *         @author chenduo
	 */
	void update(Manfacewhitelist manfacewhitelist);
	/**
	 * 删除Manfacewhitelist
	 * @param id
	 * @author chenduo
	 */
	void delete(Long id);


	/**
	 *
	 * 功能描述: 分页查询Manfacewhitelist列表 页面显示使用
	 *
	 * @param: manfacewhitelistVO
	 * @return:
	 * @author: chenduo
	 * @date: 2018/11/21 16:01
	 */

	BaseVo queryAllByPage(@RequestBody ManfacewhitelistVO manfacewhitelistVO);

	/**
	 *
	 * 功能描述:同步数据
	 *
	 * @param:
	 * @return:
	 * @author: chenduo
	 * @date: 2018/11/27 13:13
	 */
	BaseVo synchronize(Long id);
}
