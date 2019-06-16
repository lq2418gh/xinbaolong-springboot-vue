package com.bit.sc.module.sys.controller;

import com.bit.base.vo.BaseVo;
import com.bit.common.ResultCode;
import com.bit.sc.module.sys.service.PermissionRelMenuService;
import com.bit.sc.module.sys.vo.PermissionRelMenuVO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * 权限的相关请求
 * @author zhangjie
 * @date 2018/11/06
 */
@RestController
@RequestMapping(value = "/permission")
public class PermissionController {
    private static final Logger logger = LoggerFactory.getLogger(RoleController.class);
    @Autowired
    private PermissionRelMenuService permissionRelMenuService;

    /**
     * 分页查询权限列表
     * @param permissionRelMenuVO
     * @return
     */
    @PostMapping("/listPage")
    public BaseVo listPage(@RequestBody PermissionRelMenuVO permissionRelMenuVO){
        //分页查询权限列表
        BaseVo vo=new BaseVo();
        try {
            vo.setData( permissionRelMenuService.findAll(permissionRelMenuVO));
        }catch (Exception e){
            vo.setCode(ResultCode.WRONG.getCode());
            vo.setMsg(e.getMessage());
        }
        return vo;
    }
    /**
     * 根据条件查询权限
     * @param permissionType
     * @return
     */
    @PostMapping("/query/{permissionType}")
    public BaseVo listPage(@PathVariable(value = "permissionType") Integer permissionType){
        //
        BaseVo baseVo=new BaseVo();
        try {
            baseVo.setData( permissionRelMenuService.findPermissionByCondition(permissionType));
        }catch (Exception e){
            baseVo.setCode(ResultCode.WRONG.getCode());
            baseVo.setMsg(e.getMessage());
        }
        return baseVo;
    }
}
