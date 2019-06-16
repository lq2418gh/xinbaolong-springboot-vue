package com.bit.sc.module.resident.controller;


import java.util.List;

import javax.validation.Valid;

import com.bit.sc.module.resident.pojo.ResidentRelRole;
import com.bit.sc.module.resident.service.ResidentRelRoleService;
import com.bit.sc.module.resident.vo.ResidentRelRoleVO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.bit.base.vo.BaseVo;

/**
 * ResidentRelRole的相关请求
 * @author generator
 */
@RestController
@RequestMapping(value = "/residentRelRole")
public class ResidentRelRoleController {
	private static final Logger logger = LoggerFactory.getLogger(ResidentRelRoleController.class);
	@Autowired
	private ResidentRelRoleService residentRelRoleService;
	

    /**
     * 分页查询ResidentRelRole列表
     *
     */
    @GetMapping("/listPage")
    public BaseVo listPage(@RequestBody ResidentRelRoleVO residentRelRoleVO) {
    	//分页对象，前台传递的包含查询的参数

        return residentRelRoleService.findByConditionPage(residentRelRoleVO);
    }

    /**
     * 根据主键ID查询ResidentRelRole
     *
     * @param id
     * @return
     */
    @GetMapping("/query/{id}")
    public BaseVo query(@PathVariable(value = "id") Long id) {

        ResidentRelRole residentRelRole = residentRelRoleService.findById(id);
		BaseVo baseVo = new BaseVo();
		baseVo.setData(residentRelRole);
		return baseVo;
    }
    
    /**
     * 新增ResidentRelRole
     *
     * @param residentRelRole ResidentRelRole实体
     * @return
     */
    @PostMapping("/add")
    public BaseVo add(@Valid @RequestBody ResidentRelRoleVO residentRelRole) {
//        residentRelRoleService.add(residentRelRole);
		BaseVo baseVo = new BaseVo();
        return baseVo;
    }
    /**
     * 修改ResidentRelRole
     *
     * @param residentRelRole ResidentRelRole实体
     * @return
     */
    @PostMapping("/modify")
    public BaseVo modify(@Valid @RequestBody ResidentRelRole residentRelRole) {
		residentRelRoleService.update(residentRelRole);
		BaseVo baseVo = new BaseVo();
        return baseVo;
    }
    
    /**
     * 根据主键ID删除ResidentRelRole
     *
     * @param id
     * @return
     */
    @GetMapping("/delete/{id}")
    public BaseVo delete(@PathVariable(value = "id") Long id) {
        residentRelRoleService.delete(id);
		BaseVo baseVo = new BaseVo();
        return baseVo;
    }

    /**
     * 根据ResidentRelRoleID集合批量删除ResidentRelRole
     *
     * @param ids ResidentRelRoleID集合
     * @return BaseVo
     *
     */
    @PostMapping("/delBatchByIds")
    public BaseVo delBatchByIds(@RequestBody List<Long> ids) {
        residentRelRoleService.batchDelete(ids);
		BaseVo baseVo = new BaseVo();
        return baseVo;
   }
}
