package com.bit.sc.module.sys.vo;

import java.util.Date;
import com.bit.base.vo.BasePageVo;
import lombok.Data;

/**
 * Dict
 * @author generator
 */
@Data
public class DictVO extends BasePageVo{

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


