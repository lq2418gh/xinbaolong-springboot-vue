package com.bit.sc.module.house.vo;

import lombok.Data;

@Data
public class RealCompanyDictVO {

    /**
     * id
     */
    private Long id;
    /**
     * 单位名称
     */
    private String companyName;
    /**
     * 单位类别
     */
    private Long companyType;
    /**
     * 组织机构代码
     */
    private String organisationCode;
    /**
     * 联系电话
     */
    private String contactPhone;
    /**
     * 法人姓名
     */
    private String juridicalPersonName;
    /**
     * 法人身份证号码
     */
    private String juridicalPersonCardid;
    /**
     * 法人证件类型
     */
    private Integer juridicalPersonCertificateType;
    /**
     * 法人证件号码
     */
    private String juridicalPersonCertificateNumber;
    /**
     * 法人联系电话
     */
    private String juridicalPersonContactPhone;

    //字典表值
    private String value;

    //字典表值
    private String value1;



}
