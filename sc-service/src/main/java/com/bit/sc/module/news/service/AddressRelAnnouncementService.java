package com.bit.sc.module.news.service;

import java.util.List;

import com.bit.sc.module.news.pojo.AddressRelAnnouncement;
import com.bit.sc.module.news.vo.AddressRelAnnouncementVO;
import org.springframework.stereotype.Service;
import com.bit.base.vo.BaseVo;
/**
 * AddressRelAnnouncement的Service
 * @author chenduo
 */
public interface AddressRelAnnouncementService {
	/**
	 * 根据条件查询AddressRelAnnouncement
	 * @param addressRelAnnouncementVO
	 * @return
	 * @author chenduo
	 */
	BaseVo findByConditionPage(AddressRelAnnouncementVO addressRelAnnouncementVO);
	/**
	 * 查询所有AddressRelAnnouncement
	 * @param sorter 排序字符串
	 * @return
	 * @author chenduo
	 */
	List<AddressRelAnnouncement> findAll(String sorter);
	/**
	 * 通过主键查询单个AddressRelAnnouncement
	 * @param id
	 * @return
	 * @author chenduo
	 */
	AddressRelAnnouncement findById(Long id);

	/**
	 * 批量保存AddressRelAnnouncement
	 * @param addressRelAnnouncements
	 *         @author chenduo
	 */
	void batchAdd(List<AddressRelAnnouncement> addressRelAnnouncementList);
	/**
	 * 保存AddressRelAnnouncement
	 * @param addressRelAnnouncement
	 *         @author chenduo
	 */
	void add(AddressRelAnnouncement addressRelAnnouncement);
	/**
	 * 批量更新AddressRelAnnouncement
	 * @param addressRelAnnouncements
	 *         @author chenduo
	 */
	void batchUpdate(List<AddressRelAnnouncement> addressRelAnnouncements);
	/**
	 * 更新AddressRelAnnouncement
	 * @param addressRelAnnouncement
	 *         @author chenduo
	 */
	void update(AddressRelAnnouncement addressRelAnnouncement);
	/**
	 * 删除AddressRelAnnouncement
	 * @param id
	 *                                             @author chenduo
	 */
	void delete(Long id);
	/**
	 * 批量删除AddressRelAnnouncement
	 * @param ids
	 *                                         @author chenduo
	 */
	void batchDelete(List<Long> ids);
	/**
	 *
	 * 功能描述:页面上使用 查询分页查询地址 新闻 中间表关系
	 *
	 * @param:
	 * @return:
	 * @author: chenduo
	 * @date: 2018/11/20 15:17
	 */

	BaseVo queryAddressNewsByConditionPage(AddressRelAnnouncementVO addressRelAnnouncementVO);
	/**
	 *
	 * 功能描述:修改关联关系
	 *
	 * @param:
	 * @return:
	 * @author: chenduo
	 * @date: 2018/11/20 16:24
	 */

	void editRelation(AddressRelAnnouncementVO addressRelAnnouncementVO);

    /**
     *
     * 功能描述:删除关联关系
     *
     * @param:
     * @return:
     * @author: chenduo
     * @date: 2018/11/20 16:24
     */

    void deleteRelation(AddressRelAnnouncementVO addressRelAnnouncementVO);
}
