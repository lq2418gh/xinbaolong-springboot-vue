package com.bit.sc.module.car.pojo;

import lombok.Data;

import java.util.Date;

/**
 * CarWihteList
 * @author zhangjie
 * @date 2018-11-27
 */
@Data
public class CarWihteListPage {

    /**
     * id
     */
    private Long id;
    /**
     * 设备ID
     */
    private String deviceId;
    /**
     * 车辆ID
     */
    private String carId;
    /**
     * 同步结果
     */
    private Integer synchroStatus;
    /**
     * 同步时间
     */
    private Date synchroTime;
    /**
     * 同步次数
     */
    private Long synchroCount;
    /**
     * 设备ID
     */
    private String equipmentId;

    /**车辆信息表*/
    /**
     * 车牌号
     */
    private String carNumber;
    /**
     * 创建时间
     */
    private Date createTime;

    /**设备表*/
    /**
     * 绑定地址code
     */
    private String addressCode;

    /**area_code表*/
    /**
     * 行政名
     */
    private String arName;

    /** 地址表*/
    /**
     * 地址名称
     */
    private String addressName;
    /**
     * 详细地址
     */
    private String addressDetail;

    /**居民表*/
    /**
     * 真实名称
     */
    private String realName;

    /**设备类型表*/
    /**
     * 设备类型
     */
    private String equipmentType;

    /**附件表*/
    /**
     * 附件路径
     */
    private String attachmentPath;

}
