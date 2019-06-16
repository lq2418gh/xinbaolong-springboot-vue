package com.bit.sc.module.sys.pojo;

import lombok.Data;
import org.apache.poi.ss.formula.functions.T;

import java.util.List;

/**
 * AreaCode
 * @author liqi
 */
@Data
public class AreaCode {

	//columns START

    /**
     * code
     */	
	private String arCode;
    /**
     * name
     */	
	private String arName;
    /**
     * type
     */	
	private String arType;
    /**
     * parent
     */	
	private String parentCode;
    /**
     * leavel
     */	
	private Integer arLeavel;

	/**
	 * 子集合  居委会   --现在用到小区 所以用泛型
	 */
	private List<?> childrenList;
	/**
	 * 方便前段解析显示 从杨柳青到小区
	 */
	private String addressName;
	//columns END

}


