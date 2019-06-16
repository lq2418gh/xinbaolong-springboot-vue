package com.bit.sc.module.equipment.controller;

import java.io.IOException;
import java.util.List;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import io.swagger.models.auth.In;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import com.bit.base.exception.BusinessException;
import com.bit.sc.module.equipment.pojo.Supplier;
import com.bit.sc.module.equipment.vo.SupplierVO;
import com.bit.sc.module.equipment.service.SupplierService;
import com.bit.base.vo.BaseVo;

/**
 * Supplier的相关请求
 * @author liuyancheng
 */
@RestController
@RequestMapping(value = "/supplier")
public class SupplierController {
	private static final Logger logger = LoggerFactory.getLogger(SupplierController.class);
	@Autowired
	private SupplierService supplierService;
	

    /**
     * 分页查询Supplier列表
     *
     */
    @PostMapping("/listPage")
    public BaseVo listPage(@RequestBody SupplierVO supplierVO) {
    	//分页对象，前台传递的包含查询的参数

        return supplierService.findByConditionPage(supplierVO);
    }

    /**
     * 根据主键ID查询Supplier
     *
     * @param id
     * @return
     */
    @GetMapping("/query/{id}")
    public BaseVo query(@PathVariable(value = "id") Long id) {

        Supplier supplier = supplierService.findById(id);
		BaseVo baseVo = new BaseVo();
		baseVo.setData(supplier);
		return baseVo;
    }
    
    /**
     * 新增Supplier
     *
     * @param supplier Supplier实体
     * @return
     */
    @PostMapping("/add")
    public BaseVo add(@Valid @RequestBody Supplier supplier) {
        supplierService.add(supplier);
		BaseVo baseVo = new BaseVo();
        return baseVo;
    }
    /**
     * 修改Supplier
     *
     * @param supplier Supplier实体
     * @return
     */
    @PostMapping("/modify")
    public BaseVo modify(@Valid @RequestBody Supplier supplier) {
		supplierService.update(supplier);
		BaseVo baseVo = new BaseVo();
        return baseVo;
    }
    
    /**
     * 根据主键ID删除Supplier
     *
     * @param id
     * @return
     */
    @GetMapping("/delete/{id}")
    public BaseVo delete(@PathVariable(value = "id") Long id) {
        supplierService.delete(id);
		BaseVo baseVo = new BaseVo();
        return baseVo;
    }

}
