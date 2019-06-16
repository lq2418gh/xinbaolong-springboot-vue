package com.bit.sc.module.sys.vo;

import java.util.Date;
import com.bit.base.vo.BasePageVo;
import lombok.Data;

/**
 * RoleRelPermission
 * @author generator
 */
@Data
public class RoleRelPermissionVO extends BasePageVo{

	//columns START

    /**
     * id
     */	
	private Long id;
    /**
     * 角色的id
     */	
	private Long roleId;
    /**
     * t_function和t_menu表中的功能id
     */	
	private Long functionId;
    /**
     * 应用的类型： 1：web  使用t_menu表  2: app使用 t_function表
     */	
	private Integer permissionType;

	//columns END



}


