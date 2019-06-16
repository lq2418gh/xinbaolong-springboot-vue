package com.bit.sc.module.sys.controller;


import com.bit.base.vo.BaseVo;
import com.bit.common.ResultCode;
import com.bit.sc.module.sys.pojo.Role;
import com.bit.sc.module.sys.service.RoleService;
import com.bit.sc.module.sys.vo.RoleVO;
import com.bit.sc.module.user.pojo.UserRole;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Map;

/**
 * Role的相关请求
 * @author generator
 */
@RestController
@RequestMapping(value = "/role")
public class RoleController {
	private static final Logger logger = LoggerFactory.getLogger(RoleController.class);

	@Autowired
	private RoleService roleService;

	

    /**
     * 分页查询Role列表
     *
     */
    @PostMapping("/listPage")
    public BaseVo listPage(@RequestBody RoleVO roleVO) {
    	//分页对象，前台传递的包含查询的参数
        BaseVo vo=new BaseVo();
        try {
            vo.setCode(ResultCode.SUCCESS.getCode());
            vo.setMsg(ResultCode.SUCCESS.getInfo());
            vo.setData( roleService.findRoleListByCondition(roleVO));
        }catch (Exception e){
            vo.setCode(ResultCode.WRONG.getCode());
            vo.setMsg(e.getMessage());
        }
        return vo;
    }
    /**
     * 新增Role
     *
     * @param role Role实体
     * @return
     */
    @PostMapping("/add")
    public BaseVo add( @RequestBody Role role) {
        BaseVo baseVo = new BaseVo();
        roleService.add(role);
        return baseVo;
    }



    /**
     * 根据主键ID查询自身所有Role
     *
     * @param id
     * @return
     */
    @PostMapping("/queryAll/{id}")
    public BaseVo queryAllById(@PathVariable(value = "id") Long id) {
        BaseVo baseVo = new BaseVo();
        List<UserRole> roleList = roleService.queryAllById(id);
        baseVo.setData(roleList);
        return baseVo;
    }
    /**
     * 修改Role
     *
     * @param role Role实体
     * @return
     */
    @PostMapping("/modify")
    public BaseVo modify(@Valid @RequestBody Role role) {
        roleService.update(role);
        BaseVo baseVo = new BaseVo();
        return baseVo;
    }

    
    /**
     * 根据主键ID删除Role
     *
     * @param id
     * @return
     */
    @GetMapping("/delete/{id}")
    public BaseVo delete(@PathVariable(value = "id") Long id) {
        BaseVo baseVo = new BaseVo();
        try {
            roleService.delete(id);
            baseVo.setCode(ResultCode.SUCCESS.getCode());
            baseVo.setMsg(ResultCode.SUCCESS.getInfo());
        }catch (Exception e){
            baseVo.setCode(ResultCode.WRONG.getCode());
            baseVo.setMsg(ResultCode.WRONG.getInfo());
        }
        return  baseVo;
    }



    /**
     * 查询所有的可用角色名称 app代号 和 app名称
     * @param roleVO
     * @return
     */
    @PostMapping("/findAllRoleCodeName")
    public BaseVo findAllRoleCodeName(@RequestBody RoleVO roleVO){
       //分页对象，前台传递的包含查询的参数
       BaseVo vo=new BaseVo();
       vo.setData( roleService.findAllRoleCodeName(roleVO));
       return vo;
   }

    /**
     * 删除现有的人物角色 并且重新更新人物角色
     * @return
     */
    @PostMapping("/updateAll/{id}")
    public BaseVo updateRole(@RequestBody Map<String,Object> list, @PathVariable(value = "id") Long id){
        BaseVo vo=new BaseVo();

        roleService.updateRole(list,id);

        return vo;
    }

    /**
     * 查询所有角色
     * @return
     */
    @GetMapping("/listAll")
    public BaseVo<List<Role>> listAll(){
        BaseVo <List<Role>> rs = new BaseVo();
        try{
            rs.setData(roleService.findAll(null));
            rs.setCode(ResultCode.SUCCESS.getCode());
            rs.setMsg(ResultCode.SUCCESS.getInfo());
        }catch (Exception e){
            rs.setCode(ResultCode.WRONG.getCode());
            rs.setMsg(e.getMessage());
        }
        return rs;
    }



    /**
     * 根据用户的id来查询全部角色的id
     * @param id
     * @return
     */
    @GetMapping("/findRoleByUserId")
    public BaseVo findRoleByUserId(@PathVariable(value = "id")Long id){
        BaseVo<List<Long>> rs = new BaseVo<>();
        rs.setData(roleService.findRoleByUserId(id)) ;
        return  rs;

    }

}
