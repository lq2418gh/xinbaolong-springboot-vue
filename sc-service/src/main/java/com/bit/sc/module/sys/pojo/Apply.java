package com.bit.sc.module.sys.pojo;

import java.util.Date;
import lombok.Data;

/**
 * Apply
 * @author generator
 */
@Data
public class Apply {

	//columns START

    /**
     * id
     */	
	private Long id;
    /**
     * 应用的code
     */	
	private String applicationCode;
    /**
     * 应用名称
     */	
	private String applicationName;
    /**
     * 应用的类型： 1：web  2:app
     */	
	private Integer applicationType;
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


