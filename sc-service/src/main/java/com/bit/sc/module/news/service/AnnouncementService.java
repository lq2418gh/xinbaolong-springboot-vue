package com.bit.sc.module.news.service;

import com.bit.base.vo.BaseVo;
import com.bit.sc.module.news.pojo.Announcement;
import com.bit.sc.module.news.vo.AnnouncementAddressVO;
import com.bit.sc.module.news.vo.AnnouncementVO;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * Announcement的Service
 * @author chenduo
 */
@Service
public interface AnnouncementService {
	/**
	 * 根据条件查询Announcement
	 * @param announcementVO
	 * @return
	 */
	BaseVo findByConditionPage(AnnouncementVO announcementVO);
	/**
	 * 查询所有Announcement
	 * @param announcementVO 排序字符串
	 * @return
	 */
	List<Announcement> findAll(AnnouncementVO announcementVO);
	/**
	 * 通过主键查询单个Announcement
	 * @param id
	 * @return
	 */
	Announcement findById(Long id);

	/**
	 * 前台详情接口（查看时阅读量加1）
	 * @param id
	 * @return
	 */
	Announcement readNum(Long id);


	/**
	 * 保存Announcement
	 * @param announcementVO
	 */
	void add(AnnouncementVO announcementVO);

	/**
	 * 更新Announcement
	 * @param announcementAddressVO
	 */
	void update(AnnouncementAddressVO announcementAddressVO);
	/**
	 * 删除Announcement
	 * @param id
	 */
	void delete(Long id);


	/**
	 *
	 * 功能描述: 查询横幅
	 *
	 * @param: AnnouncementVO announcementVO
	 * @return: basevo
	 * @author: chenduo
	 * @date: 2018/11/16 10:22
	 */

	BaseVo queryBanner(AnnouncementVO announcementVO);
	/**
	 * 根据条件查询Announcement web端用
	 * @param announcementVO
	 * @return
	 */
	BaseVo queryListPage(AnnouncementVO announcementVO);
    /**
     * 根据条件查询Announcement 反显使用
     * @param id
     * @return
     */
	BaseVo reflect(Long id);
    /**
     * 置顶
     * @param id
     * @return
     */
	BaseVo top(Long id);
	/**
	 * 上传照片
	 * @param file
	 * @return
	 */
	BaseVo uploadpicture(MultipartFile file);
	/**
	 *
	 * 功能描述:修改发布状态
	 *
	 * @param:
	 * @return:
	 * @author: chenduo
	 * @date: 2018/12/24 11:24
	 */
	BaseVo changePublish(Announcement announcement);
}
