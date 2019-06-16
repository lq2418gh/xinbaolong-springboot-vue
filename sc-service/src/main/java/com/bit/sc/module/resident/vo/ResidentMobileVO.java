package com.bit.sc.module.resident.vo;

import java.util.Date;
import com.bit.base.vo.BasePageVo;
import lombok.Data;

/**
 * ResidentMobile
 * @author generator
 */
@Data
public class ResidentMobileVO extends BasePageVo{

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

    /**
     * 真实姓名
     */
	private String realName;

    /**
     * 密码
     */
	private String password;

    /**
     * 应用的code
     */
    private String applicationCode;

    /**
     * 应用名称
     */
    private String applicationName;

    /**
     * 应用的类型： 1：web  2:app
     */
    private Integer applicationType;

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
     * 更新者id
     */
    private Long updateUserId;

    /**
     * 是否删除
     */
    private Integer isDelete;

    /**
     * 角色id
     */
    private Long roleId;

    /**
     * 角色名称
     */
    private String roleName;

    /**
     * 应用的编码
     */
    private Long applicationId;

    /**
     * 身份证号
     */
    private String cardId;

    /**
     * 短信验证码
     */
    private String smsCaptcha;

    /**
     * 地址名称
     */
    private String addressName;



}


