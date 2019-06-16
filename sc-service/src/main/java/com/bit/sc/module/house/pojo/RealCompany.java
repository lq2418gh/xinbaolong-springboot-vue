package com.bit.sc.module.house.pojo;

import java.util.Date;
import lombok.Data;

/**
 * RealCompany
 * @author chenduo
 */
@Data
public class RealCompany {

	//columns START

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
    /**
     * 负责人姓名
     */
    private String chargemanName;

	//columns END

}


