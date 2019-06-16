package com.bit.sc.module.sys.controller;

import java.io.IOException;
import java.util.List;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import com.bit.sc.module.sys.vo.RoleRelPersmissionMenuVO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import com.bit.base.exception.BusinessException;
import com.bit.sc.module.sys.pojo.RoleRelPermission;
import com.bit.sc.module.sys.vo.RoleRelPermissionVO;
import com.bit.sc.module.sys.service.RoleRelPermissionService;
import com.bit.base.vo.BaseVo;

/**
 * RoleRelPermission的相关请求
 * @author chenduo
 */
@RestController
@RequestMapping(value = "/roleRelPermission")
public class RoleRelPermissionController {
	private static final Logger logger = LoggerFactory.getLogger(RoleRelPermissionController.class);
	@Autowired
	private RoleRelPermissionService roleRelPermissionService;
	

    /**
     * 分页查询RoleRelPermission列表
     * @author chenduo
     *
     */
    @PostMapping("/listAllByPage")
    public BaseVo listAllByPage(@RequestBody RoleRelPermissionVO roleRelPermissionVO) {
    	//分页对象，前台传递的包含查询的参数

        return roleRelPermissionService.findByConditionPage(roleRelPermissionVO);
    }

    /**
     * 根据主键ID查询RoleRelPermission
     *
     * @param id
     * @return
     * @author chenduo
     */
    @GetMapping("/query/{id}")
    public BaseVo query(@PathVariable(value = "id") Long id) {

        RoleRelPermission roleRelPermission = roleRelPermissionService.findById(id);
		BaseVo baseVo = new BaseVo();
		baseVo.setData(roleRelPermission);
		return baseVo;
    }


    /**
    * 查询全部 不分页RoleRelPermission
    *
    * @param sorter
    * @return
    * @author chenduo
    */
    @GetMapping("/queryAll")
    public BaseVo queryAll(String sorter){

        List<RoleRelPermission> roleRelPermissionList=roleRelPermissionService.findAll(sorter);
        BaseVo baseVo=new BaseVo();
        baseVo.setData(roleRelPermissionList);
        return baseVo;
    }
    
    /**
     * 新增RoleRelPermission
     *
     * @param roleRelPermission RoleRelPermission实体
     * @return
     * @author chenduo
     */
    @PostMapping("/add")
    public BaseVo add(@Valid @RequestBody RoleRelPermission roleRelPermission) {
        roleRelPermissionService.add(roleRelPermission);
		BaseVo baseVo = new BaseVo();
        return baseVo;
    }


    /**
    * 批量新增RoleRelPermission
    *
    * @param roleRelPermissionList RoleRelPermission实体
    * @return
    * @author chenduo
    */
    @PostMapping("/batchAdd")
    public BaseVo batchAdd(@RequestBody List<RoleRelPermission> roleRelPermissionList) {
        roleRelPermissionService.batchAdd(roleRelPermissionList);
        BaseVo baseVo = new BaseVo();
        return baseVo;
    }



    /**
     * 修改RoleRelPermission
     *
     * @param roleRelPermission RoleRelPermission实体
     * @return
     * @author chenduo
     */
    @PostMapping("/modify")
    public BaseVo modify(@Valid @RequestBody RoleRelPermission roleRelPermission) {
		roleRelPermissionService.update(roleRelPermission);
		BaseVo baseVo = new BaseVo();
        return baseVo;
    }
    
    /**
     * 根据主键ID删除RoleRelPermission
     *
     * @param id
     * @return
     * @author chenduo
     */
    @GetMapping("/delete/{id}")
    public BaseVo delete(@PathVariable(value = "id") Long id) {
        roleRelPermissionService.delete(id);
		BaseVo baseVo = new BaseVo();
        return baseVo;
    }

    /**
     * 分页查询RoleRelPermission列表 根据平台显示不同功能
     * @author chenduo
     *
     */
    @PostMapping("/queryFunctionByPage")
    public BaseVo queryFunctionByPage(@RequestBody RoleRelPermissionVO roleRelPermissionVO) {
        //分页对象，前台传递的包含查询的参数

        return roleRelPermissionService.queryFunctionByPage(roleRelPermissionVO);
    }
    /**
     * 查询RoleRelPermission列表 只查询web平台的 符合角色id的菜单id
     * @author chenduo
     *
     */
    @PostMapping("/listAllFunction")
    public BaseVo listAllFunction(@RequestBody RoleRelPermission roleRelPermission){
        return  roleRelPermissionService.listAllFunction(roleRelPermission);
    }
    /**
     * 添加或修改RoleRelPermission列表 先根据roleid删除 然后在批量添加
     * @author chenduo
     *
     */
    @PostMapping("/change")
    public BaseVo change(@RequestBody RoleRelPersmissionMenuVO roleRelPersmissionMenuVO){
        return roleRelPermissionService.change(roleRelPersmissionMenuVO);
    }


}
