package com.bit.sc.module.resident.vo;

import com.bit.base.vo.BasePageVo;
import com.bit.base.vo.BaseVo;
import com.bit.sc.module.resident.pojo.ResidentDoorCard;
import com.bit.sc.module.sys.vo.ResidentRelAddressActionVO;
import lombok.Data;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 *
 * @author generator
 */
@Data
public class ResidentVO extends BasePageVo {


    /**
     * 电话号码
     */
	private Long phone;

    /**
     * 短信验证码
     */
    private String smsCaptcha;



    /**
     * id
     */
    private Long id;
    /**
     * userName
     */
    private String userName;
    /**
     * 真实名称
     */
    private String realName;
    /**
     * 密码
     */
    private String password;
    /**
     * 证件类型
     */
    private Long cardType;
    /**
     * 身份证号码
     */
    private String cardId;
    /**
     * 用户类别：
     */
    private String userType;
    /**
     * 性别  0-男 1-女
     */
    private Integer sex;
    /**
     * 创建时间
     */
    private Date createTime;
    /**
     * 联系电话
     */
    private String mobile;
    /**
     * 户籍地址
     */
    private String hjdz;
    /**
     * 身份类型  0-特殊人群 1-关怀对象 2-失业人员 3-育龄妇女
     */
    private Integer personType;
    /**
     * 民族
     */
    private Long people;
    /**
     * 文化水平
     */
    private Long education;
    /**
     * 政治面貌
     */
    private Long political;
    /**
     * 婚姻状况
     */
    private Long marriage;
    /**
     * 人脸图片url
     */
    private Long imageId;

    /**
     * 状态  0-正常 1-未授权 2-待完善
     */
    private Integer status;
    /**
     * 平台返回值
     */
    private String memberId;

    /**
     * 居住地址id
     */
    private List<Long> addressIds;
    /**
     * 小区id
     */
    private String communityCode;


	//columns END

    private String attachmentPath;
    /**
     * 授权地址id
     */
    private Long authorizeaddressId;
    /**
     * 设备id
     */
    private String deviceId;


    private List<ResidentRelAddressActionVO> residentRelAddressActionVOList;

    /**
     * 前端传递的地址code
     */
    private List<String> addressCodes;
    /**
     * 年龄 最小值
     */
    private Integer minAge;
    /**
     * 年龄 最大值
     */
    private Integer maxAge;


    /*
    * 居民授权组
    *
    * */
    private List<String> groupCode;

    /*
    * 居民id集合
    *
    * */
    private List<Long> residentIds;


    //IC卡
    private List<ResidentDoorCard> residentDoorCardList;

    /**
     * 居住地址
     */
    private String addressName;
    /**
     * 硬件平台使用注册头像照片
     */
    private String fileId;
    /**
     * 出生年月
     */
    private Date birthday;
    /**
     * 地址ordid
     */
    private String orgId;



}


