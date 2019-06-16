package com.bit.sc.module.car.pojo;

import lombok.Data;

import java.util.Date;

/**
 * CarInfo实体类
 * @author zhangjie
 * @date 2018-11-21
 */
@Data
public class CarInfo {

    /**
     * id
     */
    private Long id;
    /**
     * 车辆id
     */
    private String carId;
    /**
     * 车牌号
     */
    private String carNumber;
    /**
     * 车辆类型
     */
    private Integer carType;
    /**
     * 车辆所属人
     */
    private Long residentId;
    /**
     * 创建时间
     */
    private Date createTime;
    /**
     * 创建用户id
     */
    private Long createUserId;
    /**
     * 更新时间
     */
    private Date updateTime;
    /**
     * 更新者id
     */
    private Long updateUserId;
    /**
     * 车品牌
     */
    private String carBrand;
    /**
     * 车颜色
     */
    private String carColour;
    /**
     * 载客数
     */
    private Integer carPassengers;
    /**
     * 号牌类型（0蓝牌 1黄牌 2白牌 3其他）
     */
    private Integer carFlapper;
    /**
     * 动力类型
     */
    private Integer carPower;
    /**
     * 发动机型号
     */
    private String carEngine;
    /**
     * 大架号
     */
    private String carBigframe;
    /**
     * 车辆图片id
     */
    private Long imageId;

}