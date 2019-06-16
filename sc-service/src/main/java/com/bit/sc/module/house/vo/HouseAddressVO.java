package com.bit.sc.module.house.vo;

import lombok.Data;

import java.util.Date;

@Data
public class HouseAddressVO {

    /**
     * 一标三实里的记录id
     */
    private Long id;
    /**
     * 一标三实里的地址id
     */
    private Long dz;
    /**
     * 同步状态  1 已同步  0 未同步
     */
    private Integer synchroStatus;
    /**
     * 创建时间
     */
    private Date createTime;
    /**
     * 地址表的地址信息
     */
    private String addressDetail;
    /**
     * 房屋使用类型
     */
    private Long houseUsage;
    /**
     * 产权类型
     */
    private Long propertyType;

    /**
     * 字典表的值
     */
    private String value1;
    /**
     * 字典表的值
     */
    private String value2;



}
