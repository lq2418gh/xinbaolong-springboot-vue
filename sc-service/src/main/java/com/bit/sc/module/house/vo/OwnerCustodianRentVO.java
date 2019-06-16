package com.bit.sc.module.house.vo;

import java.util.Date;
import com.bit.base.vo.BasePageVo;
import lombok.Data;

/**
 * OwnerCustodianRent
 * @author chenduo
 */
@Data
public class OwnerCustodianRentVO extends BasePageVo{

	//columns START

    /**
     * id
     */	
	private Long id;
    /**
     * 姓名
     */	
	private String realName;
    /**
     * 身份证号码
     */	
	private String cardId;
    /**
     * 联系方式
     */	
	private String phone;
    /**
     * 人员类型 0-房东 1-代管人 2-租赁人
     */	
	private Integer type;

	//columns END

}


