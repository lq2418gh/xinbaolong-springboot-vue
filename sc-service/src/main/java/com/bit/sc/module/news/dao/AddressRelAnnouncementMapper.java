package com.bit.sc.module.news.dao;

import java.util.List;

import com.bit.sc.module.news.pojo.AddressRelAnnouncement;
import com.bit.sc.module.news.vo.AddressAnnouncementVO;
import com.bit.sc.module.news.vo.AddressRelAnnouncementVO;
import org.apache.ibatis.annotations.Param;

/**
 * AddressRelAnnouncement管理的Dao
 * @author chenduo
 *
 */
public interface AddressRelAnnouncementMapper {
	/**
	 * 根据条件查询AddressRelAnnouncement
	 * @param addressRelAnnouncementVO
	 * @return
	 * @author chenduo
	 */
	public List<AddressRelAnnouncement> findByConditionPage(AddressRelAnnouncementVO addressRelAnnouncementVO);
	/**
	 * 查询所有AddressRelAnnouncement
	 * @return
	 * @author chenduo
	 */
	public List<AddressRelAnnouncement> findAll(@Param(value = "sorter") String sorter);
	/**
	 * 通过主键查询单个AddressRelAnnouncement
	 * @param id	 	 
	 * @return
	 * @author chenduo
	 */
	public AddressRelAnnouncement findById(@Param(value = "id") Long id);
	/**
	 * 批量保存AddressRelAnnouncement
	 * @param addressRelAnnouncementList
	 *         @author chenduo
	 */
	public void batchAdd(@Param(value = "addressRelAnnouncementList") List<AddressRelAnnouncement> addressRelAnnouncementList);
	/**
	 * 保存AddressRelAnnouncement
	 * @param addressRelAnnouncement
	 *         @author chenduo
	 */
	public void add(AddressRelAnnouncement addressRelAnnouncement);

	/**
	 * 更新AddressRelAnnouncement
	 * @param addressRelAnnouncement
	 *         @author chenduo
	 */
	public void update(AddressRelAnnouncement addressRelAnnouncement);
	/**
	 * 删除AddressRelAnnouncement
	 * @param ids
	 * @author chenduo
	 */
	public void batchDelete(List<Long> ids);
	/**
	 * 删除AddressRelAnnouncement
	 * @param id
	 * @author chenduo
	 */
	public void delete(@Param(value = "id") Long id);


	List<AddressAnnouncementVO> queryAddressNewsByConditionPage(AddressRelAnnouncementVO addressRelAnnouncementVO);

	/**
	 * 根据新闻id删除所有记录
	 * @param addressRelAnnouncementVO
	 */
	void deleteRelation(AddressRelAnnouncementVO addressRelAnnouncementVO);



}
