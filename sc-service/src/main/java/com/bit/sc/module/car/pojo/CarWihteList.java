package com.bit.sc.module.car.pojo;

import lombok.Data;

import java.util.Date;

/**
 * CarWihteList的实体类
 * @author zhangjie
 * @date 2018-11-22
 */
@Data
public class CarWihteList {

    /**
     * id
     */
    private Long id;
    /**
     * 设备ID(uuid生成对接第三方id)
     */
    private String deviceId;
    /**
     * 车辆ID
     */
    private Long carId;
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
    private Long equipmentId;

}
