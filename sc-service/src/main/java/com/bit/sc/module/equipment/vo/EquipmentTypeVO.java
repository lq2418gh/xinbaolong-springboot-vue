package com.bit.sc.module.equipment.vo;

import java.util.Date;
import com.bit.base.vo.BasePageVo;
import lombok.Data;

/**
 * EquipmentType
 * @author generator
 */
@Data
public class EquipmentTypeVO extends BasePageVo{

	//columns START

    /**
     * 主键id
     */	
	private Long id;
    /**
     * 设备类型
     */	
	private String equipmentType;
    /**
     * 创建时间
     */	
	private Date createTime;
    /**
     * 与设备厂商关联id
     */	
	private Long equipmentSupplierId;
    /**
     * 状态(1 启用  0 停用)
     */
    private Integer status;
    /**
     * 云平台需要的类别参数
     */
    private Integer typeNum;
	//columns END

}


