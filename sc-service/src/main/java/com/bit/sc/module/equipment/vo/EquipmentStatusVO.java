package com.bit.sc.module.equipment.vo;

import java.util.Date;
import com.bit.base.vo.BasePageVo;
import lombok.Data;

/**
 * EquipmentStatus
 * @author liuyancheng
 */
@Data
public class EquipmentStatusVO extends BasePageVo{

	//columns START

    /**
     * 主键id
     */	
	private Long id;
    /**
     * 设备UUID
     */	
	private String equipmentId;
    /**
     * 设备运行状态
     */	
	private Integer equipmentOnlineStatus;
    /**
     * 更新时间
     */	
	private Date updateTime;
    /**
     * 创建时间
     */	
	private Date createTime;
    /**
     * 地址code
     */	
	private String addressCode;

	//columns END

}


