package com.bit.sc.module.sys.vo;

import java.util.Date;
import com.bit.base.vo.BasePageVo;
import lombok.Data;

/**
 * UserRelRole
 * @author generator
 */
@Data
public class UserRelRoleVO extends BasePageVo{

	//columns START

    /**
     * id
     */	
	private Long id;
    /**
     * 用户的id
     */	
	private Long userId;
    /**
     * 角色id
     */	
	private Long roleId;

	//columns END

}


