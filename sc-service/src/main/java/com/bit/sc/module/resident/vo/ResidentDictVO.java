package com.bit.sc.module.resident.vo;

import lombok.Data;

import java.util.Date;

@Data
public class ResidentDictVO {
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
     * 密码
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
     * 联系电话
     */
    private String mobile;
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
     * 人脸图片url
     */
    private Long imageId;

    /**
     * 状态  0-正常 1-未授权 2-待完善
     */
    private Integer status;
    /**
     * 附件地址
     */
    private String attachmentPath;
    /**
     * 民族值
     */
    private String value1;
    /**
     * 文化水平值
     */
    private String value2;
    /**
     * 政治面貌值
     */
    private String value3;
    /**
     * 婚姻状况值
     */
    private String value4;
    /**
     * 身份类型值
     */
    private String value5;
    /**
     * 出生年月
     */
    private Date birthday;
    /**
     * 第三方图片id
     */
    private String fileId;
    /**
     * 小区code
     */
    private String communityCode;
}
