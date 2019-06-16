package com.bit.sc.module.car.vo;

import com.bit.base.vo.BasePageVo;
import lombok.Data;

import java.util.Date;

/**
 * CarInfo
 * @author zhangjie
 * @date 2018-11-22
 */
@Data
public class CarInfoVO extends BasePageVo {

    /**
     * id
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
     * 车辆图片url
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
    /**
     * 小区地址code
     */
    private String communityCode;

}
