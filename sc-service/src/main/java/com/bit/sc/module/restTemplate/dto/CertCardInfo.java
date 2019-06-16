package com.bit.sc.module.restTemplate.dto;

import com.bit.sc.module.restTemplate.vo.ResultDTO;
import lombok.Data;

/**
 * @author liuyancheng
 * @create 2018-12-04 13:33
 * 卡信息
 */
@Data
public class CertCardInfo extends ResultDTO {
    /**
     * 身份证内置照片FileId
     */
    private String certFaceImgId;
    /**
     * 姓名（证件）
     */
    private String name;
    /**
     * 性别
     */
    private Integer sex;
    /**
     * 生日
     */
    private String birthday;
    /**
     * 籍贯
     */
    private String nation;
    /**
     * 地址
     */
    private String address;
    /**
     * 组织
     */
    private String organization;
    /**
     * 生效日期
     */
    private String startDate;
    /**
     * 失效日期
     */
    private String endDate;
}
