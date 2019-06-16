package com.bit.sc.module.car.vo;

import com.bit.base.vo.BasePageVo;
import lombok.Data;

import java.util.Date;
import java.util.List;

/**
 * CarWihteList
 * @author zhangjie
 * @date 2018-11-22
 */
@Data
public class CarWihteListVO extends BasePageVo {

    /**
     * id
     */
    private Long id;
    /**
     * 设备ID(uuid生成对接硬件)
     */
    private String deviceId;
    /**
     * 车辆ID(uuid生成对接硬件)
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
     * 设备id
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

    /**area_code表*/
    /**
     * name
     */
    private String arName;

    /** 地址表*/
    /**
     * 地址名称
     */
    private String addressName;
    /**
     * 地址code
     */
    private String addressCode;
    /**
     * 地址id
     */
    private List<Long> addressIds;

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
    /**
     * 小区地址code
     */
    private String communityCode;
    /**
     * 车辆ID集合
     */
    private List<Long> carIds;

}
