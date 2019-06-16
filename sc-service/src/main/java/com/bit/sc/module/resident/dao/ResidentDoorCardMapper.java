package com.bit.sc.module.resident.dao;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import com.bit.sc.module.resident.pojo.ResidentDoorCard;
import com.bit.sc.module.resident.vo.ResidentDoorCardVO;

/**
 * ResidentDoorCard管理的Dao
 * @author liuyancheng
 *
 */
public interface ResidentDoorCardMapper {
	/**
	 * 根据条件查询ResidentDoorCard
	 * @param residentDoorCardVO
	 * @return
	 */
	public List<ResidentDoorCard> findByConditionPage(ResidentDoorCardVO residentDoorCardVO);
	/**
	 * 查询所有ResidentDoorCard
	 * @return
	 */
	public List<ResidentDoorCard> findAll(@Param(value = "sorter") String sorter);
	/**
	 * 通过主键查询单个ResidentDoorCard
	 * @param id	 	 
	 * @return
	 */
	public ResidentDoorCard findById(@Param(value = "id") Long id);
	/**
	 * 批量保存ResidentDoorCard
	 * @param residentDoorCards
	 */
	public void batchAdd(List<ResidentDoorCard> residentDoorCards);
	/**
	 * 保存ResidentDoorCard
	 * @param residentDoorCard
	 */
	public void add(ResidentDoorCard residentDoorCard);
	/**
	 * 批量更新ResidentDoorCard
	 * @param residentDoorCards
	 */
	public void batchUpdate(List<ResidentDoorCard> residentDoorCards);
	/**
	 * 更新ResidentDoorCard
	 * @param residentDoorCard
	 */
	public void update(ResidentDoorCard residentDoorCard);
	/**
	 * 删除ResidentDoorCard
	 * @param residentDoorCards
	 */
	public void batchDelete(List<Long> ids);
	/**
	 * 删除ResidentDoorCard
	 * @param id
	 */
	public void delete(@Param(value = "id") Long id);

	/**
	 * 根据卡号查询是否有重复
	 * @param residentDoorCard
	 */
	public ResidentDoorCard findByCardNum(ResidentDoorCard residentDoorCard);
	/**
	 * 根据参数查询数据
	 * @param residentDoorCard
	 */
	List<ResidentDoorCard> findByParam(ResidentDoorCard residentDoorCard);
}
