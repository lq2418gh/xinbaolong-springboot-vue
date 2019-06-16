package com.bit.sc.module.group.vo;

import com.bit.base.vo.BasePageVo;
import lombok.Data;

import java.util.Date;

@Data
public class GroupResEquVo extends BasePageVo {
    /**
     * 组id
     */
    private Long groupId;
    /**
     * id
     */
    private Long relId;
    /**
     * 种类:0人,1设备
     */
    private Integer type;
    /**
     * 组名称
     */
    private String groupName;

    /**
     * 人或设备的名称
     */
    private String relName;

    //------------居民
    /**
     * 真实名称
     */
    private String realName;
    /**
     * 证件类型
     */
    private Long cardType;
    /**
     * 身份证号码
     */
    private String cardId;

    /**
     * 性别  0-男 1-女
     */
    private Integer sex;
    /**
     * 户籍地址
     */
    private String hjdz;

    /**
     * 人脸图片附件id
     */
    private Long imageId;
    /**
     * 出生年月
     */
    private Date birthday;
    /**
     * 图片路径
     */
    private String attachmentPath;
    //--------------设备
    /***
     * 具体地址
     */
    private String addressDetail;

    /**
     * 备注
     */
    private String remarks;
    /**
     * 设备型号
     */
    private String equipmentModelNumber;
    /**
     * 设备码
     */
    private String equipmentCode;
}
