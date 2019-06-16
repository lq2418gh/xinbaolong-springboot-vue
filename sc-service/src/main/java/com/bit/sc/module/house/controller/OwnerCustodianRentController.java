package com.bit.sc.module.house.controller;

import java.io.IOException;
import java.util.List;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import com.bit.sc.module.house.pojo.OwnerCustodianRent;
import com.bit.sc.module.house.service.OwnerCustodianRentService;
import com.bit.sc.module.house.vo.OwnerCustodianRentVO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import com.bit.base.exception.BusinessException;
import com.bit.base.vo.BaseVo;

/**
 * OwnerCustodianRent的相关请求
 * @author chenduo
 */
@RestController
@RequestMapping(value = "/ownerCustodianRent")
public class OwnerCustodianRentController {
	private static final Logger logger = LoggerFactory.getLogger(OwnerCustodianRentController.class);
	@Autowired
	private OwnerCustodianRentService ownerCustodianRentService;
	

    /**
     * 分页查询OwnerCustodianRent列表
     * @author chenduo
     *
     */
    @PostMapping("/listAllByPage")
    public BaseVo listAllByPage(@RequestBody OwnerCustodianRentVO ownerCustodianRentVO) {
    	//分页对象，前台传递的包含查询的参数

        return ownerCustodianRentService.findByConditionPage(ownerCustodianRentVO);
    }

    /**
     * 根据主键ID查询OwnerCustodianRent
     *
     * @param id
     * @return
     * @author chenduo
     */
    @GetMapping("/query/{id}")
    public BaseVo query(@PathVariable(value = "id") Long id) {

        OwnerCustodianRent ownerCustodianRent = ownerCustodianRentService.findById(id);
		BaseVo baseVo = new BaseVo();
		baseVo.setData(ownerCustodianRent);
		return baseVo;
    }


    /**
    * 查询全部 不分页OwnerCustodianRent
    *
    * @param id
    * @return
    * @author chenduo
    */
    @GetMapping("/queryAll")
    public BaseVo queryAll(String sorter){

        List<OwnerCustodianRent> ownerCustodianRentList=ownerCustodianRentService.findAll(sorter);
        BaseVo baseVo=new BaseVo();
        baseVo.setData(ownerCustodianRentList);
        return baseVo;
    }
    
    /**
     * 新增OwnerCustodianRent
     *
     * @param ownerCustodianRent OwnerCustodianRent实体
     * @return
     * @author chenduo
     */
    @PostMapping("/add")
    public BaseVo add(@Valid @RequestBody OwnerCustodianRent ownerCustodianRent) {
        ownerCustodianRentService.add(ownerCustodianRent);
		BaseVo baseVo = new BaseVo();
        return baseVo;
    }


    /**
    * 批量新增OwnerCustodianRent
    *
    * @param ownerCustodianRentList OwnerCustodianRent实体
    * @return
    * @author chenduo
    */
    @PostMapping("/batchAdd")
    public BaseVo batchAdd(@RequestBody List<OwnerCustodianRent> ownerCustodianRentList) {
        ownerCustodianRentService.batchAdd(ownerCustodianRentList);
        BaseVo baseVo = new BaseVo();
        return baseVo;
    }



    /**
     * 修改OwnerCustodianRent
     *
     * @param ownerCustodianRent OwnerCustodianRent实体
     * @return
     * @author chenduo
     */
    @PostMapping("/modify")
    public BaseVo modify(@Valid @RequestBody OwnerCustodianRent ownerCustodianRent) {
		ownerCustodianRentService.update(ownerCustodianRent);
		BaseVo baseVo = new BaseVo();
        return baseVo;
    }
    
    /**
     * 根据主键ID删除OwnerCustodianRent
     *
     * @param id
     * @return
     * @author chenduo
     */
    @GetMapping("/delete/{id}")
    public BaseVo delete(@PathVariable(value = "id") Long id) {
        ownerCustodianRentService.delete(id);
		BaseVo baseVo = new BaseVo();
        return baseVo;
    }

}
