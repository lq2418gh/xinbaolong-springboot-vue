package com.bit.sc.module.restTemplate.dto;

import com.bit.sc.module.restTemplate.vo.ResultDTO;
import lombok.Data;

import java.util.List;

/**
 * @author liuyancheng
 * @create 2018-12-04 13:24
 * 第三方接口人员实体类
 */
@Data
public class MemberDTO extends ResultDTO {
    /**
     * 用户ID
     */
    private String memberId;
    /**
     * 姓名
     */
    private String memberName;
    /**
     * 工号
     */
    private String employeeNum;
    /**
     * 手机号码
     */
    private String mobile;
    /**
     * 显示名称
     */
    private String dialLabel;
    /**
     * 短缩号
     */
    private String shortNum;
    /**
     * IC卡号
     */
    private String icNum;
    /**
     * ID卡号
     */
    private String idNum;
    /**
     * 注册头像照片FileId
     */
    private String faceImgId;
    /**
     * 特征值1
     */
    private String feature1;
    /**
     * 特征值2
     */
    private String feature2;
    /**
     * 证件类型
     */
    private Integer certType;
    /**
     * 身份证号码
     */
    private String certNo;
    /**
     * 额外数据(JSON)
     */
    private String extraData;
    /**
     * 账号生效时间 YYYY-MM-DD hh:mm:ss
     */
    private String validDatetimeBegin;
    /**
     * 账号失效时间
     */
    private String validDatetimeEnd;
    /**
     * 标签名 最多10个标签名
     */
    private List tagName;
    /**
     * 卡信息
     */
    private CertCardInfo certCardInfo;
    /**
     * 设备ID
     */
    private String deviceId;
    /**
     * 未导入文件错误信息
     */
    private String errorFile;
    /**
     *  人员列表
     */
    private List memberList;
    /**
     *  组织ID
     */
    private String orgId;
}
