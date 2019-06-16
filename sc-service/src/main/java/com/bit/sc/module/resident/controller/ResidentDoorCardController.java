package com.bit.sc.module.resident.controller;

import java.io.IOException;
import java.util.List;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import com.bit.base.exception.BusinessException;
import com.bit.sc.module.resident.pojo.ResidentDoorCard;
import com.bit.sc.module.resident.vo.ResidentDoorCardVO;
import com.bit.sc.module.resident.service.ResidentDoorCardService;
import com.bit.base.vo.BaseVo;

/**
 * ResidentDoorCard的相关请求
 * @author liuyancheng
 */
@RestController
@RequestMapping(value = "/residentDoorCard")
public class ResidentDoorCardController {
	private static final Logger logger = LoggerFactory.getLogger(ResidentDoorCardController.class);
	@Autowired
	private ResidentDoorCardService residentDoorCardService;
	

    /**
     * 分页查询ResidentDoorCard列表
     *
     */
    @PostMapping("/listPage")
    public BaseVo listPage(@RequestBody ResidentDoorCardVO residentDoorCardVO) {
    	//分页对象，前台传递的包含查询的参数

        return residentDoorCardService.findByConditionPage(residentDoorCardVO);
    }

    /**
     * 根据主键ID查询ResidentDoorCard
     *
     * @param id
     * @return
     */
    @GetMapping("/query/{id}")
    public BaseVo query(@PathVariable(value = "id") Long id) {

        ResidentDoorCard residentDoorCard = residentDoorCardService.findById(id);
		BaseVo baseVo = new BaseVo();
		baseVo.setData(residentDoorCard);
		return baseVo;
    }
    
    /**
     * 新增ResidentDoorCard
     *
     * @param residentDoorCard ResidentDoorCard实体
     * @return
     */
    @PostMapping("/add")
    public BaseVo add(@Valid @RequestBody ResidentDoorCard residentDoorCard) {
        residentDoorCardService.add(residentDoorCard);
		BaseVo baseVo = new BaseVo();
        return baseVo;
    }
    /**
     * 修改ResidentDoorCard
     *
     * @param residentDoorCard ResidentDoorCard实体
     * @return
     */
    @PostMapping("/modify")
    public BaseVo modify(@Valid @RequestBody ResidentDoorCard residentDoorCard) {
		residentDoorCardService.update(residentDoorCard);
		BaseVo baseVo = new BaseVo();
        return baseVo;
    }
    
    /**
     * 根据主键ID删除ResidentDoorCard
     *
     * @param id
     * @return
     */
    @GetMapping("/delete/{id}")
    public BaseVo delete(@PathVariable(value = "id") Long id) {
        residentDoorCardService.delete(id);
		BaseVo baseVo = new BaseVo();
        return baseVo;
    }

    /**
     * 根据居民id查询卡列表
     * @param residentDoorCardVO
     * @return
     */
    @PostMapping("/listByresidentId")
    public BaseVo listByresidentId(@Valid @RequestBody ResidentDoorCardVO residentDoorCardVO){
        BaseVo cardList = residentDoorCardService.findByResidentId(residentDoorCardVO);
        return cardList;
    }
    /**
     * 校验门卡是否重复
     * @param residentDoorCard
     * @return
     */
    @PostMapping("/checkCard")
    public BaseVo checkCard(@RequestBody ResidentDoorCard residentDoorCard){
        BaseVo baseVo = residentDoorCardService.checkCard(residentDoorCard);
        return baseVo;
    }
}
