package com.bit.sc.module.equipment.vo;

import java.util.Date;
import com.bit.base.vo.BasePageVo;
import lombok.Data;

/**
 * EquipmentModelNumber
 * @author liuyancheng
 */
@Data
public class EquipmentModelNumberVO extends BasePageVo{

	//columns START

    /**
     * 主键id
     */	
	private Long id;
    /**
     * 设备型号
     */	
	private String equipmentModelNumber;
    /**
     * 设备类型 1人脸门禁   2人脸闸道  3车闸
     */	
	private Integer equipmentType;
    /**
     * 设备状态(1 启用  0 停用)
     */	
	private Integer equipmentStatus;
    /**
     * 创建时间
     */	
	private Date createTime;
    /**
     * 创建人id
     */	
	private Long createUserId;
    /**
     * 创建人名称
     */	
	private String createUserName;
    /**
     * 备注
     */	
	private String remarks;

	//columns END

}


