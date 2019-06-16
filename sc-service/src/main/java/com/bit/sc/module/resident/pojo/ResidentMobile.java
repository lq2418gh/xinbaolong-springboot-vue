package com.bit.sc.module.resident.pojo;

import java.util.Date;
import lombok.Data;

/**
 * ResidentMobile
 * @author generator
 */
@Data
public class ResidentMobile {

	//columns START

    /**
     * 数据的id,主键
     */	
	private Long id;
    /**
     * 用户id
     */	
	private Long residentId;
    /**
     * 电话号码
     */	
	private Long phone;
    /**
     * 绑定状态，0：未绑定，1已绑定，2不可用
     */	
	private Integer status;
    /**
     * 后期会推送时会用到,极光的推送的设备id号
     */	
	private String registrationId;

	//columns END

}


