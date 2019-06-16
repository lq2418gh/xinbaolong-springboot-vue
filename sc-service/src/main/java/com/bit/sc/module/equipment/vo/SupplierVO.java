package com.bit.sc.module.equipment.vo;

import java.util.Date;
import com.bit.base.vo.BasePageVo;
import lombok.Data;

/**
 * Supplier
 * @author liuyancheng
 */
@Data
public class SupplierVO extends BasePageVo{

	//columns START

    /**
     * 主键id
     */	
	private Long id;
    /**
     * 供应商名称
     */	
	private String supplierName;
    /**
     * 供应商电话
     */	
	private String supplierPhone;
    /**
     * 供应商地址
     */	
	private String supplierAddress;
    /**
     * 创建时间
     */	
	private Date createTime;
    /**
     * 创建人id
     */	
	private Long createUserId;
    /**
     * 是否删除(0-是 1-否)
     */	
	private Integer isDelete;
    /**
     * 设备id
     */	
	private Long equipmentId;

	//columns END

}


