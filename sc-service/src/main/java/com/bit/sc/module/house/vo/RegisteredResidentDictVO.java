package com.bit.sc.module.house.vo;

import lombok.Data;

@Data
public class RegisteredResidentDictVO {

    //columns START

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


    //columns END

    //字典表
    private String value;


    /**
     * house表外键
     */
    private Long houseId;
    /**
     * 是否户主 0-是 1-不是
     */
    private Integer isHouseholder;


}
