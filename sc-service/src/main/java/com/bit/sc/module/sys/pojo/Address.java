package com.bit.sc.module.sys.pojo;

import java.util.Date;
import java.util.List;

import com.bit.sc.module.attachment.pojo.Attachment;

import com.bit.sc.module.equipment.pojo.EquipmentType;
import lombok.Data;

/**
 * Address
 * @author liqi
 */
@Data
public class Address {

    //columns START
    /**
     * id
     */
    private Long id;
    /**
     * 地址名称
     */
    private String addressName;
    /**
     * 地址编码关联code
     */
    private String addressCode;
    /**
     * 数据的等级
     */
    private Integer addressLevel;
    /**
     * 父id
     */
    private Long fid;

    /**
     * 对应 sys_area_code表 的code
     */
    private  String areaCode;
    /**
     * 小区编码 --只有小区有需要传递给公安用
     */
    private Integer valueCode;
    /**
     * 街道
     */
    private String street;
    /**
     * 门牌号
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
     * 街道门牌
     */
    private String streetPlate;
    /**
     * 地址详情
     */
    private String addressDetail;
    /**
     * 行政代码名称
     */
    private String arName;
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
    /**
     * 文件list
     */
    private List<Attachment> fileList;
    /**
     * 方便查询树结构
     */
    private List<Address> childrenList;
    /**
     * 地址的组织id
     */
    private String orgId;

    /**
     * 平台返回码
     */
    private String authKey;
    /**
     * 楼栋大门类型
     */
    private Integer buildGateType;
    //columns END

    private AddressContact addressContact;

}
