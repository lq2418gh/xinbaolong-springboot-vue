package com.bit.sc.module.sys.pojo;

import java.util.Date;
import lombok.Data;

/**
 * Role
 * @author generator
 */
@Data
public class Role {

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
     * 应用的编码
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
     * isDelete
     */
    private Integer isDelete;

	//columns END

}


