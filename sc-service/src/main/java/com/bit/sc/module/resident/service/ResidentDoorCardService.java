package com.bit.sc.module.resident.service;

import java.util.List;
import org.springframework.stereotype.Service;
import com.bit.sc.module.resident.pojo.ResidentDoorCard;
import com.bit.sc.module.resident.vo.ResidentDoorCardVO;
import com.bit.base.vo.BaseVo;
/**
 * ResidentDoorCard的Service
 * @author liuyancheng
 */
public interface ResidentDoorCardService {
	/**
	 * 根据条件查询ResidentDoorCard
	 * @param residentDoorCardVO
	 * @return
	 */
	BaseVo findByConditionPage(ResidentDoorCardVO residentDoorCardVO);
	/**
	 * 查询所有ResidentDoorCard
	 * @param sorter 排序字符串
	 * @return
	 */
	List<ResidentDoorCard> findAll(String sorter);
	/**
	 * 通过主键查询单个ResidentDoorCard
	 * @param id
	 * @return
	 */
	ResidentDoorCard findById(Long id);

	/**
	 * 批量保存ResidentDoorCard
	 * @param residentDoorCards
	 */
	void batchAdd(List<ResidentDoorCard> residentDoorCards);
	/**
	 * 保存ResidentDoorCard
	 * @param residentDoorCard
	 */
	void add(ResidentDoorCard residentDoorCard);
	/**
	 * 批量更新ResidentDoorCard
	 * @param residentDoorCards
	 */
	void batchUpdate(List<ResidentDoorCard> residentDoorCards);
	/**
	 * 更新ResidentDoorCard
	 * @param residentDoorCard
	 */
	void update(ResidentDoorCard residentDoorCard);
	/**
	 * 删除ResidentDoorCard
	 * @param id
	 */
	void delete(Long id);
	/**
	 * 批量删除ResidentDoorCard
	 * @param ids
	 */
	void batchDelete(List<Long> ids);

	/**
	 * 根据居民id查询门卡列表
	 * @param residentDoorCardVO
	 * @return
	 */
	BaseVo findByResidentId(ResidentDoorCardVO residentDoorCardVO);
	/**
	 * 校验门卡是否重复
	 * @param residentDoorCard
	 * @return
	 */
	BaseVo checkCard(ResidentDoorCard residentDoorCard);
}
