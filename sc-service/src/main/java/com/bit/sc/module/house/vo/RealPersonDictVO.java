package com.bit.sc.module.house.vo;

import com.bit.sc.module.attachment.pojo.Attachment;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class RealPersonDictVO {

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
     * 性别
     */
    private Long sex;
    /**
     * 户籍地址
     */
    private String hjdz;
    /**
     * 与户主关系
     */
    private String relationOwner;
    /**
     * 联系电话
     */
    private String mobile;
    /**
     * 是否流动人口  0-是 1-不是
     */
//    private Integer isldrk;
    /**
     * 流入原因
     */
    private String reason;
    /**
     * 流入时间
     */
    private Date arriveTime;
    /**
     * 预计离开时间
     */
    private Date leaveTime;
    /**
     * 身份类型 0-特殊人群 1-关怀对象 2-失业人员 3-育龄妇女
     */
    private Integer personType;
    /**
     * 民族
     */
    private Long people;
    /**
     * 政治面貌
     */
    private Long political;
    /**
     * 文化程度
     */
    private Long education;
    /**
     * 婚姻状况
     */
    private Long marriage;
    /**
     * 工作地点
     */
    private String worklocation;
    /**
     * 人像id
     */
    private Long picture;

    //columns END

    //字典表
    private String value;
    //字典表
    private String value1;
    //字典表
    private String value2;
    //字典表
    private String value3;
    //字典表
    private String value4;
    //图片路径
    private String path;

    /**
     * house表外键
     */
    private Long houseId;

    /**
     * 是否户主 0-是 1-不是
     */
    private Integer isHouseholder;
    /**
     * 图片列表
     */
    private List<Attachment> attachmentList;
    /**
     *   0-常驻 1-暂住
     */
    private Integer isStay;
    /**
     *   证件类型
     */
    private Integer certificateType;

}
