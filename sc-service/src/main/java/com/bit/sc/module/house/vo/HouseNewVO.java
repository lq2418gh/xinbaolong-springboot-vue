package com.bit.sc.module.house.vo;

import lombok.Data;

import java.util.Date;

@Data
public class HouseNewVO {
    private Long id;
    /**
     * 地址编码
     */
    private String dzbm;
    /**
     * 地址元素类型
     */
    private String dzyslx;
    /**
     * 地址
     */
    private Long dz;
    /**
     * 建筑物名称
     */
    private String jzwmc;
    /**
     * 别名简称
     */
    private String bmjc;
    /**
     * 中心点横坐标
     */
    private String zxdhzb;
    /**
     * 中心点纵坐标
     */
    private String zxdzzb;
    /**
     * 警务责任区代码
     */
    private String jwzrqdm;
    /**
     * 所属最低一级行政区域_地址编码
     */
    private String sszdyjxzqxdzbm;
    /**
     * 所属街路巷（小区）_地址编码
     */
    private String ssjlxxqdzbm;
    /**
     * 地（住）址存在标识
     */
    private String dzzczbs;
    /**
     * 地（住）址在用标识
     */
    private String dzzzybs;
    /**
     * 更新时间
     */
    private Date gxsj;
    /**
     * 启用日期
     */
    private Date qyrq;
    /**
     * 停用日期
     */
    private Date tyrq;
    /**
     * 登记单位
     */
    private String djdw;
    /**
     * 房屋用途
     */
    private Long houseUsage;
    /**
     * 产权类型
     */
    private Long propertyType;
    /**
     * 是否是单位产权 0-是 1-不是
     */
    private Integer isCompanyOwned;
    /**
     * 实有单位信息
     */
    private Long companyInfo;
    /**
     * 房屋类别
     */
    private Long houseType;
    /**
     * 房屋性质
     */
    private Long houseCharacter;
    /**
     * 房间数
     */
    private Integer roomNumbers;
    /**
     * 房屋面积
     */
    private String houseSquare;
    /**
     * 房间使用用途
     */
    private Long houseUsagePurpose;
    /**
     * 居住(从业)人数
     */
    private Integer livingNumbers;
    /**
     * 境内人数
     */
    private Integer domesticNumber;
    /**
     * 境外人数
     */
    private Integer foreignerNumber;
    /**
     * 无户口人数
     */
    private Integer noHukouNumber;
    /**
     * 房东信息
     */
    private Long houseOwnerInfo;
    /**
     * 代管人信息
     */
    private Long custodianInfo;
    /**
     * 租赁人信息
     */
    private Long rentPersonInfo;


    /**
     * 同步状态  1 已同步  0 未同步
     */
    private Integer synchroStatus;
    /**
     * 同步时间
     */
    private Date synchroTime;
    /**
     * 同步次数
     */
    private Integer synchroCount;
    /**
     * 创建时间
     */
    private Date createTime;
    //columns END
    /**
     * 是否是租用 0-是 1-不是
     */
    private Integer isRent;
    //不是数据库中字段
    private String addressCode;
}
