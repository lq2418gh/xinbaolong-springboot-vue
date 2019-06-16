package com.bit.sc.module.resident.pojo;

import java.util.Date;
import lombok.Data;

/**
 * ResidentRelRole
 * @author zhangjie
 */
@Data
public class ResidentRelRole {

	//columns START

    /**
     * id
     */	
	private Long id;
    /**
     * 用户的id
     */	
	private Long residentId;
    /**
     * 角色id
     */	
	private Long roleId;

	//columns END

}


