package com.bit.sc.module.news.controller;

import com.bit.base.vo.BaseVo;
import com.bit.sc.module.news.pojo.Announcement;
import com.bit.sc.module.news.service.AnnouncementService;
import com.bit.sc.module.news.vo.AnnouncementAddressVO;
import com.bit.sc.module.news.vo.AnnouncementVO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * Announcement的相关请求
 * @author chenduo
 */
@RestController
@RequestMapping(value = "/announcement")
public class AnnouncementController {
	private static final Logger logger = LoggerFactory.getLogger(AnnouncementController.class);
	@Autowired
	private AnnouncementService announcementService;
	

    /**
     * 分页查询Announcement列表
     * @author: chenduo
     */
    @PostMapping("/listPage")
    public BaseVo listPage(@RequestBody AnnouncementVO announcementVO) {
    	//分页对象，前台传递的包含查询的参数
        return announcementService.findByConditionPage(announcementVO);
    }

    /**
     * 前台详情接口（查看时阅读量加1）
     *
     * @param id
     * @return
     * @author: chenduo
     */
    @GetMapping("/readNum/{id}")
    public BaseVo readNum(@PathVariable(value = "id") Long id) {
        Announcement announcement = announcementService.readNum(id);
        BaseVo baseVo = new BaseVo();
        baseVo.setData(announcement);
        return baseVo;
    }

    /**
     *
     * 功能描述: 查询所有新闻 无条件
     *
     * @param: announcementVO
     * @return:
     * @author: chenduo
     * @date: 2018/11/16 10:16
     */

    @PostMapping("/queryAll")
    public BaseVo queryAll(@RequestBody AnnouncementVO announcementVO){
        BaseVo baseVo = new BaseVo();
        List<Announcement> announcementList = announcementService.findAll(announcementVO);
        baseVo.setData(announcementList);
        return baseVo;
    }




    /**
     * 根据主键ID查询Announcement
     *
     * @param id
     * @return
     */
    @GetMapping("/query/{id}")
    public BaseVo query(@PathVariable(value = "id") Long id) {

        Announcement announcement = announcementService.findById(id);
		BaseVo baseVo = new BaseVo();
		baseVo.setData(announcement);
		return baseVo;
    }


    
    /**
     * 新增Announcement
     *
     * @param announcementVO Announcement实体
     * @return
     */
    @PostMapping("/add")
    public BaseVo add(@RequestBody AnnouncementVO announcementVO) {
		BaseVo baseVo = new BaseVo();
        announcementService.add(announcementVO);
        return baseVo;
    }
    /**
     * 修改Announcement
     *
     * @param announcementAddressVO Announcement实体
     * @return
     */
    @PostMapping("/modify")
    public BaseVo modify(@RequestBody AnnouncementAddressVO announcementAddressVO) {
        BaseVo baseVo = new BaseVo();
		announcementService.update(announcementAddressVO);
        return baseVo;
    }
    
    /**
     * 根据主键ID删除Announcement
     *
     * @param id
     * @return
     */
    @GetMapping("/delete/{id}")
    public BaseVo delete(@PathVariable(value = "id") Long id) {
        BaseVo baseVo = new BaseVo();
        announcementService.delete(id);
        return baseVo;
    }





    /**
     *
     * 功能描述: 查询横幅
     *
     * @param: AnnouncementVO announcementVO 已发布的 未删除的
     * @return: basevo
     * @author: chenduo
     * @date: 2018/11/16 10:22
     */

    @PostMapping("/queryBanner")
    public BaseVo queryBanner(@RequestBody AnnouncementVO announcementVO){
        BaseVo baseVo = new BaseVo();
        //todo 查询banner 按时间倒序 选3条
        baseVo = announcementService.queryBanner(announcementVO);
        return baseVo;
    }


    /**
     * 分页查询Announcement列表
     * @author: chenduo
     */
    @PostMapping("/queryListPage")
    public BaseVo queryListPage(@RequestBody AnnouncementVO announcementVO) {
        //分页对象，前台传递的包含查询的参数
        return announcementService.queryListPage(announcementVO);
    }
    /**
     * 根据条件查询Announcement 反显使用
     * @param id
     * @return
     */
    @GetMapping("/reflect/{id}")
    public BaseVo reflect(@PathVariable(value = "id") Long id){
        return announcementService.reflect(id);
    }
    /**
     * 置顶
     * @param id
     * @return
     */
    @GetMapping("/top/{id}")
    public BaseVo top(@PathVariable(value = "id") Long id){
        return announcementService.top(id);
    }


    /**
     *
     * 功能描述:上传照片
     *
     * @param:
     * @return:
     * @author: chenduo
     * @date: 2018/11/27 13:39
     */

    @PostMapping("/uploadpicture")
    public BaseVo uploadPicture(@RequestParam("file") MultipartFile file){

        return announcementService.uploadpicture(file);
    }
    /**
     *
     * 功能描述:修改发布状态
     *
     * @param:
     * @return:
     * @author: chenduo
     * @date: 2018/12/24 11:24
     */


    @PostMapping("/changePublish")
    public BaseVo changePublish(@RequestBody Announcement announcement){
        return announcementService.changePublish(announcement);
    }
}
