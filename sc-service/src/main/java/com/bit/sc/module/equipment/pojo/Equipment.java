package com.bit.sc.module.equipment.pojo;

import java.util.Date;
import lombok.Data;

/**
 * Equipment
 * @author liuyancheng
 */
@Data
public class Equipment {

	//columns START

    /**
     * 主键id
     */	
	private Long id;
    /**
     * 设备UUID  对接三方平台的
     */	
	private String equipmentId;
    /**
     * 设备mac地址
     */	
	private String equipmentMac;
    /**
     * 设备类型 1人脸门禁   2人脸闸道  3车闸
     */	
	private Integer equipmentType;
    /**
     * 绑定地址code
     */	
	private String addressCode;
    /**
     * 创建时间
     */	
	private Date createTime;
    /**
     * 创建人id
     */	
	private Long createUserId;
    /**
     * 设备是否启用(1 启用  0 停用)
     */	
	private Integer equipmentStatus;
    /**
     * 设备运行状态
     */	
	private Integer equipmentOnlineStatus;
    /**
     * 备注
     */	
	private String remarks;
    /**
     * 设备型号id
     */	
	private Long equipmentModelNumberId;
    /**
     * 设备码
     */
    private String equipmentCode;
    /**
     * 设备名
     */
    private String equipmentName;

	//columns END

}


