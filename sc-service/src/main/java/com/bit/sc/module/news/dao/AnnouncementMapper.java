package com.bit.sc.module.news.dao;

import java.util.List;

import com.bit.sc.module.news.pojo.Announcement;
import com.bit.sc.module.news.vo.AnnouncementVO;
import org.apache.ibatis.annotations.Param;

/**
 * Announcement管理的Dao
 *
 * @author chenduo
 */
public interface AnnouncementMapper {
    /**
     * 根据条件查询Announcement
     *
     * @param announcementVO
     * @return
     */
    List<Announcement> findByConditionPage(AnnouncementVO announcementVO);

    /**
     * 查询所有Announcement
     *
     * @return
     */
    List<Announcement> findAll(AnnouncementVO announcementVO);

    /**
     * 通过主键查询单个Announcement
     *
     * @param id
     * @return
     */
    Announcement findById(@Param(value = "id") Long id);

    /**
     * 查询4条Announcement
     *
     * @return
     */
    List<Announcement> findFour(AnnouncementVO announcementVO);

    /**
     * 保存Announcement
     *
     * @param announcement
     */
    void add(Announcement announcement);

    /**
     * 更新Announcement
     *
     * @param announcement
     */
    void update(Announcement announcement);

    /**
     * 删除Announcement
     *
     * @param ids
     */
    void batchDelete(@Param(value = "ids") List<Long> ids);

    /**
     * 删除Announcement
     *
     * @param id
     */
    void delete(@Param(value = "id") Integer id);

    /**
     * 删除Announcement
     *
     * @param id
     */
    void deleteLogicById(@Param(value = "id") Long id);


    /**
     * 功能描述: 查询横幅 分页 后台直接order排序
     *
     * @param: AnnouncementVO announcementVO
     * @return: basevo
     * @author: chenduo
     * @date: 2018/11/16 10:22
     */
    List<Announcement> queryBanner(AnnouncementVO announcementVO);
    /**
     * 根据条件查询Announcement web端用
     * @param announcementVO
     * @return
     */
    List<Announcement> queryListPage(AnnouncementVO announcementVO);
}
