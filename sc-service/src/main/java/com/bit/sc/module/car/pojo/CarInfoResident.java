package com.bit.sc.module.car.pojo;

import lombok.Data;

import java.util.Date;

/**
 * CarInfo实体类
 * @author zhangjie
 * @date 2018-11-24
 */
@Data
public class CarInfoResident {

    /**
     * id(CarInfo的id)
     */
    private Long id;
    /**
     * 车牌号
     */
    private String carNumber;
    /**
     * 创建时间
     */
    private Date createTime;
    /**
     * 车辆图片id
     */
    private Long imageId;

    /**居民表*/
    /**
     * 真实名称
     */
    private String realName;
    /**
     * 身份证号码
     */
    private String cardId;
    /**
     * 户籍地址
     */
    private String hjdz;

    /**附件表*/
    /**
     * 附件路径
     */
    private String attachmentPath;
    /**
     * 附件名称
     */
    private String attachmentName;

}
