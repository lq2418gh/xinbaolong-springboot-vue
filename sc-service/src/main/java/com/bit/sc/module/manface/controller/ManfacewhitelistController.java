package com.bit.sc.module.manface.controller;

import com.bit.base.vo.BaseVo;
import com.bit.sc.module.manface.pojo.Manfacewhitelist;
import com.bit.sc.module.manface.service.ManfacewhitelistService;
import com.bit.sc.module.manface.vo.ManfacewhitelistVO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * Manfacewhitelist的相关请求
 * @author chenduo
 */
@RestController
@RequestMapping(value = "/manfacewhitelist")
public class ManfacewhitelistController {
	private static final Logger logger = LoggerFactory.getLogger(ManfacewhitelistController.class);
	@Autowired
	private ManfacewhitelistService manfacewhitelistService;
	


    /**
     * 根据主键ID查询Manfacewhitelist
     *
     * @param id
     * @return
     * @author chenduo
     */
    @GetMapping("/query/{id}")
    public BaseVo query(@PathVariable(value = "id") Long id) {

        Manfacewhitelist manfacewhitelist = manfacewhitelistService.findById(id);
		BaseVo baseVo = new BaseVo();
		baseVo.setData(manfacewhitelist);
		return baseVo;
    }



    
    /**
     * 新增Manfacewhitelist
     *
     * @param manfacewhitelist Manfacewhitelist实体
     * @return
     * @author chenduo
     */
    @PostMapping("/add")
    public BaseVo add(@RequestBody Manfacewhitelist manfacewhitelist) {
        manfacewhitelistService.add(manfacewhitelist);
		BaseVo baseVo = new BaseVo();
        return baseVo;
    }






    /**
     * 修改Manfacewhitelist
     *
     * @param manfacewhitelist Manfacewhitelist实体
     * @return
     * @author chenduo
     */
    @PostMapping("/modify")
    public BaseVo modify(@Valid @RequestBody Manfacewhitelist manfacewhitelist) {
		manfacewhitelistService.update(manfacewhitelist);
		BaseVo baseVo = new BaseVo();
        return baseVo;
    }
    
    /**
     * 根据主键ID删除Manfacewhitelist
     *
     * @param id
     * @return
     * @author chenduo
     */
    @GetMapping("/delete/{id}")
    public BaseVo delete(@PathVariable(value = "id") Long id) {
        manfacewhitelistService.delete(id);
		BaseVo baseVo = new BaseVo();
        return baseVo;
    }

    /**
     * 分页查询Manfacewhitelist列表 页面显示使用
     * @author chenduo
     *
     */
    @PostMapping("/queryAllByPage")
    public BaseVo queryAllByPage(@RequestBody ManfacewhitelistVO manfacewhitelistVO) {
        //分页对象，前台传递的包含查询的参数

        return manfacewhitelistService.queryAllByPage(manfacewhitelistVO);
    }


    /**
     *
     * 功能描述:同步数据
     *
     * @param:
     * @return:
     * @author: chenduo
     * @date: 2018/11/27 13:13
     */

    @GetMapping("/synchronize/{id}")
    public BaseVo synchronize(@PathVariable(value = "id") Long id){
        BaseVo baseVo = manfacewhitelistService.synchronize(id);
        return baseVo;
    }

}
