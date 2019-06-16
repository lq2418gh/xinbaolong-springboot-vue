package com.bit.sc.module.sys.pojo;

import java.util.Date;
import lombok.Data;

/**
 * UserRelPhone
 * @author generator
 */
@Data
public class UserRelPhone {

	//columns START

    /**
     * 数据的id,主键
     */	
	private Long id;
    /**
     * 用户id
     */	
	private Long userId;
    /**
     * 电话号码
     */	
	private Long phone;
    /**
     * 绑定状态，0：未绑定，1已绑定，2不可用
     */	
	private Integer status;
    /**
     * 后期会推送时会用到
     */	
	private String registrationId;

	//columns END

}


