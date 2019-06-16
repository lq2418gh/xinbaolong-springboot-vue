package com.bit.sc.module.resident.vo;

import com.bit.sc.module.sys.pojo.Address;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class ResidentMobileAddressAttachmentVO {

    /**
     * id
     */
    private Long id;
    /**
     * 真实名称
     */
    private String realName;
    /**
     * 证件类型
     */
    private Integer cardType;
    /**
     * 身份证号码
     */
    private String cardId;
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
     * 状态  0-未授权 1-正常
     */
    private Integer status;
    /**
     * 附件图片地址
     */
    private String attachmentPath;
    /**
     * 年龄
     */
    private Integer age;
    /**
     * 地址代码
     */
//    private String addressCode;
    /**
     * 所在小区房产列表
     */
//    private List<Address> addressList;
}
