package com.bit.sc.module.resident.vo;

import java.util.Date;
import com.bit.base.vo.BasePageVo;
import lombok.Data;

/**
 * ResidentRelRole
 * @author zhangjie
 */
@Data
public class ResidentRelRoleVO extends BasePageVo{

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


