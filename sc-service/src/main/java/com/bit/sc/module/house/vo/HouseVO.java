package com.bit.sc.module.house.vo;

import java.util.Date;
import com.bit.base.vo.BasePageVo;
import lombok.Data;

/**
 * House
 * @author chenduo
 */
@Data
public class HouseVO extends BasePageVo{

	//columns START

    /**
     * id
     */	
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
    /**
     * 一标三实页面搜索用
     */
    private String addressDetail;

    //columns END


    //添加公司表内容
    //公司id
    private Long companyId;
    /**
     * 单位名称
     */
    private String companyName;
    /**
     * 单位类别
     */
    private Long companyType;
    /**
     * 组织机构代码
     */
    private String organisationCode;
    /**
     * 联系电话
     */
    private String contactPhone;
    /**
     * 法人姓名
     */
    private String juridicalPersonName;
    /**
     * 法人身份证号码
     */
    private String juridicalPersonCardid;
    /**
     * 法人证件类型
     */
    private Integer juridicalPersonCertificateType;
    /**
     * 法人证件号码
     */
    private String juridicalPersonCertificateNumber;
    /**
     * 法人联系电话
     */
    private String juridicalPersonContactPhone;
    /**
     * 负责人姓名
     */
    private String chargemanName;





    //添加房东代管租赁信息
    //房东代管租赁人id
    private Long ownerId;
    /**
     * 姓名
     */
    private String realName;
    /**
     * 身份证号码
     */
    private String cardId;
    /**
     * 联系方式
     */
    private String phone;
    /**
     * 人员类型 0-房东 1-代管人 2-租赁人
     */
    private Integer type;



    //添加页面上多出来的信息
    /**
     * 房东姓名
     */
    private String ownerName;
    /**
     * 房东身份证号
     */
    private String ownerCardId;
    /**
     * 房东电话
     */
    private String ownerPhone;

    /**
     * 代管人姓名
     */
    private String cusName;
    /**
     * 代管人身份证号
     */
    private String cusCardId;
    /**
     * 代管人电话
     */
    private String cusPhone;

    /**
     * 租赁人姓名
     */
    private String rentName;
    /**
     * 租赁人身份证号
     */
    private String rentCardId;
    /**
     * 租赁人电话
     */
    private String rentPhone;
    /**
     * 是否租用 0-是 1-不是
     */
    private Integer isRent;
    /**
     * 前端传递的地址code
     */
    private String addressCode;

}


