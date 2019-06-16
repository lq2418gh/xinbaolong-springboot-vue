package com.bit.sc.module.sys.pojo;

import java.util.Date;
import lombok.Data;

/**
 * Dict
 * @author generator
 */
@Data
public class Dict {

	//columns START

    /**
     * id
     */	
	private Long id;
    /**
     * 模块/表单/类型
     */	
	private String module;
    /**
     * key
     */	
	private String dictCode;
    /**
     * 值
     */	
	private String value;
    /**
     * 排序
     */	
	private Integer rank;

	//columns END

}


