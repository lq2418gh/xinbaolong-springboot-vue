package com.bit.sc.module.resident.vo;

import java.util.Date;
import com.bit.base.vo.BasePageVo;
import lombok.Data;

/**
 * ResidentDoorCard
 * @author liuyancheng
 */
@Data
public class ResidentDoorCardVO extends BasePageVo{

	//columns START

    /**
     * 主键id
     */	
	private Long id;
    /**
     * 门卡号码
     */	
	private String cardNum;
    /**
     * 卡类型 1-IC卡 2-身份证
     */	
	private Integer cardType;
    /**
     * 创建时间
     */	
	private Date createTime;
    /**
     * 创建人id
     */	
	private Long createUserId;
    /**
     * 创建人姓名
     */	
	private String createUserName;
    /**
     * 居民ID
     */	
	private Long residentId;

	//columns END

}


