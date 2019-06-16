package com.bit.sc.module.user.vo;

import java.util.Date;
import com.bit.base.vo.BasePageVo;
import lombok.Data;

/**
 * User
 * @author generator
 */
@Data
public class UserVO extends BasePageVo {

    //columns START

    /**
     * id
     */
    private Long id;
    /**
     * 用户登录名
     */
    private String username;

    /**
     * 用户名
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
    /**
     * 图片验证码
     */
    private String captcha;

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
     *
     */
    private Long userId;

    /**
     * 新密码
     */
    private String newPassword;

    /**
     * 确认新密码
     */
    private String sureNewPassword;


}