package com.bit.sc.module.sys.pojo;

import java.util.Date;

import lombok.Data;

/**
 * ResidentRelAddress
 * @author liqi
 */
@Data
public class ResidentRelAddress {

	//columns START

    //-----------------------居民地址中间表-------------------
    /**
     * id
     */
    private Long id;

    /**
     * 地址编码关联code
     */
    private String addressCode ;
    /**
     * residentId
     */
    private Long residentId;
    //------------------       地址表     --------------
    /**
     * 地址名称
     */
    private String addressName;
    /**
     * addressId
     */
    private Long addressId;
    /**
     * 数据的等级
     */
    private Integer addressLevel;
    /**
     * 父id
     */
    private Long fid;
    /**
     * 对应 sys_area_code 的code
     */
    private  String areaCode;
    /**
     * 小区编码 --只有小区有需要传递给公安用
     */
    private String valueCode;
    /**
     * 街道
     */
    private String street;
    /**
     * 门派号
     */
    private String plate;
    /**
     * 小区状态  0：停止 1：启动
     */
    private Integer villageState;
    /**
     * 备注
     */
    private String remark;
    /**
     * 地址详情
     */
    private String addressDetail;
    /**
     *创建时间
     */
    private Date createTime;
    /**
     *  是否正式命名 0  是  1 否
     */
    private Integer isFormal;
    /**
     * 是否临时编制 0  是  1 否
     */
    private Integer isTemporary;
    /**
     * 设备预留字段
     */
    private Integer equipmentValue;

    //-----------------------------  居民表-   ----
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
     * 身份证号码
     */
    private String cardId;
    /**
     * 用户类别：
     */
    private String userType;

    /**
     * 小区code：
     */
    private String communityCode;
    //columns END

}


