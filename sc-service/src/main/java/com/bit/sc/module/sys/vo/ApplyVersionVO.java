package com.bit.sc.module.sys.vo;

import java.util.Date;
import com.bit.base.vo.BasePageVo;
import lombok.Data;

/**
 * ApplyVersion
 * @author generator
 */
@Data
public class ApplyVersionVO extends BasePageVo{


    //columns START

    /**
     * id
     */
    private Long id;
    /**
     * applicationId
     */
    private Long applicationId;
    /**
     * applicationVersion
     */
    private String applicationVersion;

    /**
     * applicationName
     */
    private String applicationName;
    /**
     * 是否已发布可用 1：已发布可用  0：未发布不可用
     */
    private Integer isAble;
    /**
     * 是否强制更新：1：强制更新   0：不强制更新
     */
    private Integer isForceUpdate;
    /**
     * 版本更新说明,以json 结构展示
     */
    private String buildingNote;
    /**
     * 发布日期
     */
    private Date publishTime;
    /**
     * 数据创建日期
     */
    private Date createTime;
    /**
     * createUserId
     */
    private Long createUserId;
    /**
     * 数据更新日期
     */
    private Date updateTime;
    /**
     * 数据更新人
     */
    private Date updateUserId;
    /**
     * 针对移动端  安卓或者ios设备：1：安 卓，0：ios
     */
    private String applyOs;

    /**
     * 针对移动端 设备名称
     */
    private String applyOsName;

    /**
     * 安装包更新地址
     */
    private String downloadUrl;

    //columns END
}


