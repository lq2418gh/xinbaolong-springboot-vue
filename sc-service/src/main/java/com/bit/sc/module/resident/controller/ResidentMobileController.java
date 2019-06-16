package com.bit.sc.module.resident.controller;

import java.io.IOException;
import java.util.List;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import com.bit.sc.module.resident.pojo.ResidentMobile;
import com.bit.sc.module.resident.service.ResidentMobileService;
import com.bit.sc.module.resident.vo.ResidentMobileVO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import com.bit.base.exception.BusinessException;
import com.bit.base.vo.BaseVo;

/**
 * ResidentMobile的相关请求
 * @author generator
 */
@RestController
@RequestMapping(value = "/residentMobile")
public class ResidentMobileController {
	private static final Logger logger = LoggerFactory.getLogger(ResidentMobileController.class);
	@Autowired
	private ResidentMobileService residentMobileService;
	

    /**
     * 分页查询ResidentMobile列表
     *
     */
    @PostMapping("/listPage")
    public BaseVo listPage(@RequestBody ResidentMobileVO residentMobileVO) {
    	//分页对象，前台传递的包含查询的参数

        return residentMobileService.findByConditionPage(residentMobileVO);
    }

    /**
     * 根据主键ID查询ResidentMobile
     *
     * @param id
     * @return
     */
    @GetMapping("/query/{id}")
    public BaseVo query(@PathVariable(value = "id") Long id) {

        ResidentMobile residentMobile = residentMobileService.findById(id);
		BaseVo baseVo = new BaseVo();
		baseVo.setData(residentMobile);
		return baseVo;
    }
    
    /**
     * 新增ResidentMobile
     *
     * @param residentMobile ResidentMobile实体
     * @return
     */
    @PostMapping("/add")
    public BaseVo add(@Valid @RequestBody ResidentMobile residentMobile) {
        residentMobileService.add(residentMobile);
		BaseVo baseVo = new BaseVo();
        return baseVo;
    }
    /**
     * 修改ResidentMobile
     *
     * @param residentMobile ResidentMobile实体
     * @return
     */
    @PostMapping("/modify")
    public BaseVo modify(@Valid @RequestBody ResidentMobile residentMobile) {
		residentMobileService.update(residentMobile);
		BaseVo baseVo = new BaseVo();
        return baseVo;
    }
    
    /**
     * 根据主键ID删除ResidentMobile
     *
     * @param id
     * @return
     */
    @GetMapping("/delete/{id}")
    public BaseVo delete(@PathVariable(value = "id") Long id) {
        residentMobileService.delete(id);
		BaseVo baseVo = new BaseVo();
        return baseVo;
    }

    /**
     * 根据ResidentMobileID集合批量删除ResidentMobile
     *
     * @param ids ResidentMobileID集合
     * @return BaseVo
     *
     */
    @PostMapping("/delBatchByIds")
    public BaseVo delBatchByIds(@RequestBody List<Long> ids) {
        residentMobileService.batchDelete(ids);
		BaseVo baseVo = new BaseVo();
        return baseVo;
   }
}
