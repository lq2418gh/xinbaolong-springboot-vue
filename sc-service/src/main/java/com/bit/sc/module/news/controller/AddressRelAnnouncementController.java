package com.bit.sc.module.news.controller;

import java.io.IOException;
import java.util.List;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import com.bit.sc.module.news.pojo.AddressRelAnnouncement;
import com.bit.sc.module.news.service.AddressRelAnnouncementService;
import com.bit.sc.module.news.vo.AddressRelAnnouncementVO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import com.bit.base.exception.BusinessException;
import com.bit.base.vo.BaseVo;

/**
 * AddressRelAnnouncement的相关请求
 * @author chenduo
 */
@RestController
@RequestMapping(value = "/addressRelAnnouncement")
public class AddressRelAnnouncementController {
	private static final Logger logger = LoggerFactory.getLogger(AddressRelAnnouncementController.class);
	@Autowired
	private AddressRelAnnouncementService addressRelAnnouncementService;
	

    /**
     * 分页查询AddressRelAnnouncement列表
     * @author chenduo
     *
     */
    @PostMapping("/listAllByPage")
    public BaseVo listAllByPage(@RequestBody AddressRelAnnouncementVO addressRelAnnouncementVO) {
    	//分页对象，前台传递的包含查询的参数

        return addressRelAnnouncementService.findByConditionPage(addressRelAnnouncementVO);
    }

    /**
     * 根据主键ID查询AddressRelAnnouncement
     *
     * @param id
     * @return
     * @author chenduo
     */
    @GetMapping("/query/{id}")
    public BaseVo query(@PathVariable(value = "id") Long id) {

        AddressRelAnnouncement addressRelAnnouncement = addressRelAnnouncementService.findById(id);
		BaseVo baseVo = new BaseVo();
		baseVo.setData(addressRelAnnouncement);
		return baseVo;
    }


    /**
    * 查询全部 不分页AddressRelAnnouncement
    *
    * @param sorter
    * @return
    * @author chenduo
    */
    @GetMapping("/queryAll")
    public BaseVo queryAll(String sorter){

        List<AddressRelAnnouncement> addressRelAnnouncementList=addressRelAnnouncementService.findAll(sorter);
        BaseVo baseVo=new BaseVo();
        baseVo.setData(addressRelAnnouncementList);
        return baseVo;
    }
    
    /**
     * 新增AddressRelAnnouncement
     *
     * @param addressRelAnnouncement AddressRelAnnouncement实体
     * @return
     * @author chenduo
     */
    @PostMapping("/add")
    public BaseVo add(@Valid @RequestBody AddressRelAnnouncement addressRelAnnouncement) {
        addressRelAnnouncementService.add(addressRelAnnouncement);
		BaseVo baseVo = new BaseVo();
        return baseVo;
    }


    /**
    * 批量新增AddressRelAnnouncement
    *
    * @param addressRelAnnouncementList AddressRelAnnouncement实体
    * @return
    * @author chenduo
    */
    @PostMapping("/batchAdd")
    public BaseVo batchAdd(@RequestBody List<AddressRelAnnouncement> addressRelAnnouncementList) {
        addressRelAnnouncementService.batchAdd(addressRelAnnouncementList);
        BaseVo baseVo = new BaseVo();
        return baseVo;
    }



    /**
     * 修改AddressRelAnnouncement
     *
     * @param addressRelAnnouncement AddressRelAnnouncement实体
     * @return
     * @author chenduo
     */
    @PostMapping("/modify")
    public BaseVo modify(@Valid @RequestBody AddressRelAnnouncement addressRelAnnouncement) {
		addressRelAnnouncementService.update(addressRelAnnouncement);
		BaseVo baseVo = new BaseVo();
        return baseVo;
    }
    
    /**
     * 根据主键ID删除AddressRelAnnouncement
     *
     * @param id
     * @return
     * @author chenduo
     */
    @GetMapping("/delete/{id}")
    public BaseVo delete(@PathVariable(value = "id") Long id) {
        addressRelAnnouncementService.delete(id);
		BaseVo baseVo = new BaseVo();
        return baseVo;
    }

    /**
     * 页面上使用 查询分页查询地址 新闻 中间表关系
     *
     * @param addressRelAnnouncementVO
     * @return
     * @author chenduo
     */
    @PostMapping("/queryAddressNewsByConditionPage")
    public BaseVo queryAddressNewsByConditionPage(@RequestBody AddressRelAnnouncementVO addressRelAnnouncementVO){

        BaseVo baseVo = addressRelAnnouncementService.queryAddressNewsByConditionPage(addressRelAnnouncementVO);
        return baseVo;
    }

    /**
     *
     * 功能描述:修改新闻与小区的关系
     *
     * @param:
     * @return:
     * @author: chenduo
     * @date: 2018/11/20 16:46
     */

    @PostMapping("/editRelation")
    public BaseVo editRelation(@RequestBody AddressRelAnnouncementVO addressRelAnnouncementVO){
        BaseVo baseVo = new BaseVo();
        addressRelAnnouncementService.editRelation(addressRelAnnouncementVO);
        return baseVo;
    }


    /**
     *
     * 功能描述:删除新闻与小区的关系 以新闻为主 单条
     *
     * @param:
     * @return:
     * @author: chenduo
     * @date: 2018/11/20 16:46
     */

    @PostMapping("/deleteRelation")
    public BaseVo deleteRelationByNews(@RequestBody AddressRelAnnouncementVO addressRelAnnouncementVO){
        BaseVo baseVo = new BaseVo();
        addressRelAnnouncementService.deleteRelation(addressRelAnnouncementVO);
        return baseVo;
    }


}
