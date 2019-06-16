package com.bit.sc.module.sys.pojo;

import java.util.Date;
import java.util.List;

import lombok.Data;

/**
 * RoleRelInterfacePermission
 * @author generator
 */
@Data
public class RoleRelInterfacePermission {

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
     * 权限的id
     */	
	private Long permissionId;

    /**
     * permissionIds 接口id   list
     */
	private List<Long> permissionIds;
	//columns END

}


