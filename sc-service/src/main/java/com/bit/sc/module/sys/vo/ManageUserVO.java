package com.bit.sc.module.sys.vo;

import java.util.Date;
import com.bit.base.vo.BasePageVo;
import lombok.Data;

/**
 * ManageUser
 * @author generator
 */
@Data
public class ManageUserVO extends BasePageVo{

	//columns START

    /**
     * id
     */	
	private Long id;
    /**
     * 用户名
     */	
	private String userName;
    /**
     * realName
     */	
	private String realName;
    /**
     * 昵称
     */	
	private String nickName;
    /**
     * 密码
     */	
	private String password;
    /**
     * email
     */	
	private String email;
    /**
     * 联系电话
     */	
	private String phone;
    /**
     * 1可用用，0不可用
     */	
	private Integer status;
    /**
     * 创建时间
     */	
	private Date createTime;
    /**
     * 创建者id
     */	
	private Long createUserId;
    /**
     * 更新时间
     */	
	private Date updateTime;
    /**
     * 更新人id
     */	
	private Long updateUserId;

	//columns END

}


