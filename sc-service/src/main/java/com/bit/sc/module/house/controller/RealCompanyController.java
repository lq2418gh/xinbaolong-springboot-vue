package com.bit.sc.module.house.controller;

import java.io.IOException;
import java.util.List;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import com.bit.sc.module.house.pojo.RealCompany;
import com.bit.sc.module.house.service.RealCompanyService;
import com.bit.sc.module.house.vo.RealCompanyVO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import com.bit.base.exception.BusinessException;
import com.bit.base.vo.BaseVo;

/**
 * RealCompany的相关请求
 * @author chenduo
 */
@RestController
@RequestMapping(value = "/realCompany")
public class RealCompanyController {
	private static final Logger logger = LoggerFactory.getLogger(RealCompanyController.class);
	@Autowired
	private RealCompanyService realCompanyService;
	

    /**
     * 分页查询RealCompany列表
     * @author chenduo
     *
     */
    @PostMapping("/listAllByPage")
    public BaseVo listAllByPage(@RequestBody RealCompanyVO realCompanyVO) {
    	//分页对象，前台传递的包含查询的参数

        return realCompanyService.findByConditionPage(realCompanyVO);
    }

    /**
     * 根据主键ID查询RealCompany
     *
     * @param id
     * @return
     * @author chenduo
     */
    @GetMapping("/query/{id}")
    public BaseVo query(@PathVariable(value = "id") Long id) {

        RealCompany realCompany = realCompanyService.findById(id);
		BaseVo baseVo = new BaseVo();
		baseVo.setData(realCompany);
		return baseVo;
    }


    /**
    * 查询全部 不分页RealCompany
    *
    * @param sorter
    * @return
    * @author chenduo
    */
    @GetMapping("/queryAll")
    public BaseVo queryAll(String sorter){

        List<RealCompany> realCompanyList=realCompanyService.findAll(sorter);
        BaseVo baseVo=new BaseVo();
        baseVo.setData(realCompanyList);
        return baseVo;
    }
    
    /**
     * 新增RealCompany
     *
     * @param realCompany RealCompany实体
     * @return
     * @author chenduo
     */
    @PostMapping("/add")
    public BaseVo add(@Valid @RequestBody RealCompany realCompany) {
        realCompanyService.add(realCompany);
		BaseVo baseVo = new BaseVo();
        return baseVo;
    }


    /**
    * 批量新增RealCompany
    *
    * @param realCompanyList RealCompany实体
    * @return
    * @author chenduo
    */
    @PostMapping("/batchAdd")
    public BaseVo batchAdd(@RequestBody List<RealCompany> realCompanyList) {
        realCompanyService.batchAdd(realCompanyList);
        BaseVo baseVo = new BaseVo();
        return baseVo;
    }



    /**
     * 修改RealCompany
     *
     * @param realCompany RealCompany实体
     * @return
     * @author chenduo
     */
    @PostMapping("/modify")
    public BaseVo modify(@Valid @RequestBody RealCompany realCompany) {
		realCompanyService.update(realCompany);
		BaseVo baseVo = new BaseVo();
        return baseVo;
    }
    
    /**
     * 根据主键ID删除RealCompany
     *
     * @param id
     * @return
     * @author chenduo
     */
    @GetMapping("/delete/{id}")
    public BaseVo delete(@PathVariable(value = "id") Long id) {
        realCompanyService.delete(id);
		BaseVo baseVo = new BaseVo();
        return baseVo;
    }
    /**
     *
     * 功能描述:联合字典表分页查询
     *
     * @param:
     * @return:
     * @author: chenduo
     * @date: 2018/11/23 15:12
     */

    @PostMapping("/querylistAllDictByPage")
    public BaseVo querylistAllDictByPage(@RequestBody RealCompanyVO realCompanyVO){
        return realCompanyService.querylistAllDictByPage(realCompanyVO);
    }

}
