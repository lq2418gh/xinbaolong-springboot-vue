package com.bit.sc.module.sys.vo;

import java.util.Date;
import java.util.List;

import com.bit.base.vo.BasePageVo;
import com.bit.sc.module.sys.pojo.Address;
import com.bit.sc.module.sys.pojo.AreaCode;
import lombok.Data;

/**
 * AreaCode
 * @author liqi
 */
@Data
public class AreaCodeVO extends BasePageVo{

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


	private List<AreaCode> childrenAreaList;
	
	private List<Address> childrenAddressList;
	//columns END

}


