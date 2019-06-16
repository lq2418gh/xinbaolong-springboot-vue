package com.bit.sc.module.user.pojo;

import java.util.Date;
import lombok.Data;

/**
 * User
 * @author generator
 */
@Data
public class User {

	//columns START

    /**
     * id
     */	
	private Long id;
    /**
     * 用户名
     */	
	private String username;

    /**
     * 真实姓名
     */
    private String realName;
    /**
     * 手机号
     */	
	private String mobile;
    /**
     * 邮箱
     */	
	private String email;
    /**
     * 密码
     */	
	private String password;
    /**
     * 添加该用户的用户id
     */	
	private Integer insertUid;
    /**
     * 注册时间
     */	
	private Date insertTime;
    /**
     * 修改时间
     */	
	private Date updateTime;
    /**
     * 是否删除（0：正常；1：已删）
     */	
	private Integer isDel;
    /**
     * 是否在职（0：正常；1，离职）
     */	
	private Integer isJob;
    /**
     * 短信验证码
     */	
	private String mcode;
    /**
     * 短信发送时间
     */	
	private Date sendTime;
    /**
     * 更新版本
     */	
	private Integer version;

	//columns END

}


