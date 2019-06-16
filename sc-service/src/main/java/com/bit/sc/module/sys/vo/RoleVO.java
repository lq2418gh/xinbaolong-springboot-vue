package com.bit.sc.module.sys.vo;

import java.util.Date;
import com.bit.base.vo.BasePageVo;
import lombok.Data;

/**
 * Role
 * @author generator
 */
@Data
public class RoleVO extends BasePageVo{

	//columns START

    /**
     * id
     */	
	private Long id;
    /**
     * roleName
     */	
	private String roleName;
    /**
     * 所属的应用的code
     */	
	private Long applicationId;
    /**
     * createTime
     */	
	private Date createTime;
    /**
     * createUserId
     */	
	private Long createUserId;
    /**
     * updateTime
     */	
	private Date updateTime;
    /**
     * updateUserId
     */	
	private Long updateUserId;


    /**
     * createUserName
     */
    private String createUserName;

    /**
     * applyName
     */
    private String applicationName;

    /**
     * isDelete
     */
    private Integer isDelete;
	//columns END

}


