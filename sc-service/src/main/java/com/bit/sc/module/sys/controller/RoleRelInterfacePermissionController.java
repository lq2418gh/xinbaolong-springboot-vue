package com.bit.sc.module.sys.controller;

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
import com.bit.sc.module.sys.pojo.RoleRelInterfacePermission;
import com.bit.sc.module.sys.vo.RoleRelInterfacePermissionVO;
import com.bit.sc.module.sys.service.RoleRelInterfacePermissionService;
import com.bit.base.vo.BaseVo;

/**
 * RoleRelInterfacePermission的相关请求
 * @author generator
 */
@RestController
@RequestMapping(value = "/roleRelInterfacePermission")
public class RoleRelInterfacePermissionController {
	private static final Logger logger = LoggerFactory.getLogger(RoleRelInterfacePermissionController.class);
	@Autowired
	private RoleRelInterfacePermissionService roleRelInterfacePermissionService;
	

    /**
     * 分页查询RoleRelInterfacePermission列表
     *
     */
    @PostMapping("/listPage")
    public BaseVo listPage(@RequestBody RoleRelInterfacePermissionVO roleRelInterfacePermissionVO) {
    	//分页对象，前台传递的包含查询的参数

        return roleRelInterfacePermissionService.findByConditionPage(roleRelInterfacePermissionVO);
    }

    /**
     * 根据主键ID查询RoleRelInterfacePermission
     *
     * @param id
     * @return
     */
    @GetMapping("/query/{id}")
    public BaseVo query(@PathVariable(value = "id") Long id) {

        RoleRelInterfacePermission roleRelInterfacePermission = roleRelInterfacePermissionService.findById(id);
		BaseVo baseVo = new BaseVo();
		baseVo.setData(roleRelInterfacePermission);
		return baseVo;
    }
    
    /**
     * 新增RoleRelInterfacePermission
     *
     * @param roleRelInterfacePermission RoleRelInterfacePermission实体
     * @return
     */
    @PostMapping("/add")
    public BaseVo add(@Valid @RequestBody RoleRelInterfacePermission roleRelInterfacePermission) {
        roleRelInterfacePermissionService.add(roleRelInterfacePermission);
		BaseVo baseVo = new BaseVo();
        return baseVo;
    }
    /**
     * 修改RoleRelInterfacePermission
     *
     * @param roleRelInterfacePermission RoleRelInterfacePermission实体
     * @return
     */
    @PostMapping("/modify")
    public BaseVo modify(@Valid @RequestBody RoleRelInterfacePermission roleRelInterfacePermission) {
		roleRelInterfacePermissionService.update(roleRelInterfacePermission);
		BaseVo baseVo = new BaseVo();
        return baseVo;
    }
    
    /**
     * 根据主键ID删除RoleRelInterfacePermission
     *
     * @param id
     * @return
     */
    @GetMapping("/delete/{id}")
    public BaseVo delete(@PathVariable(value = "id") Long id) {
        roleRelInterfacePermissionService.delete(id);
		BaseVo baseVo = new BaseVo();
        return baseVo;
    }
    /**
     * 更新接口权限
     * @return
     */
    @PostMapping("/updateInterFacePermission")
    public  BaseVo updateInterFacePermission(@RequestBody RoleRelInterfacePermission roleRelInterfacePermission){
        roleRelInterfacePermissionService.updateInterFacePermission(roleRelInterfacePermission);
        BaseVo baseVo = new BaseVo();
        return baseVo;
    }

    /**
     * 根据角色id 查询list
     * @return
     */
    @GetMapping("/findListByRole/{roleId}")
    public  BaseVo findListByRole(@PathVariable(value = "roleId") Long roleId){
        List<RoleRelInterfacePermission> listByRole = roleRelInterfacePermissionService.findListByRole(roleId);
        BaseVo baseVo = new BaseVo();
        baseVo.setData(listByRole);
        return baseVo;
    }
}
