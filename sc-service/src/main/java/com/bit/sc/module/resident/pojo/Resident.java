package com.bit.sc.module.resident.pojo;

import lombok.Data;

import java.util.Date;

/**
 * Resident
 * @author generator
 */
@Data
public class Resident {

	//columns START

    /**
     * id
     */	
	private Long id;
    /**
     * userName
     */	
	private String userName;
    /**
     * 真实名称
     */	
	private String realName;
    /**
     * password
     */	
	private String password;
    /**
     * 证件类型
     */
    private Long cardType;
    /**
     * 身份证号码
     */	
	private String cardId;
    /**
     * 用户类别：
     */	
	private String userType;

    /**
     * 性别  0-男 1-女
     */
    private Integer sex;
    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 户籍地址
     */
    private String hjdz;
    /**
     * 身份类型  0-特殊人群 1-关怀对象 2-失业人员 3-育龄妇女
     */
    private Integer personType;
    /**
     * 民族
     */
    private Long people;
    /**
     * 文化水平
     */
    private Long education;
    /**
     * 政治面貌
     */
    private Long political;
    /**
     * 婚姻状况
     */
    private Long marriage;
    /**
     * 人脸图片附件id
     */
    private Long imageId;
    /**
     * 状态  0-正常 1-未授权 2-待完善
     */
    private Integer status;
    /**
     * 平台回传id
     */
    private String memberId;
    /**
     * 小区id
     */
    private String communityCode;

    /**
     * 出生年月
     */
    private Date birthday;
    /**
     * 手机号
     */
    private String mobile;
    /**
     * 组织id
     */
    private String orgId;
    //columns END

}


