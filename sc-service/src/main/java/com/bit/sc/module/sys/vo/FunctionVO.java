package com.bit.sc.module.sys.vo;

import java.util.Date;
import com.bit.base.vo.BasePageVo;
import lombok.Data;

/**
 * Function
 * @author liqi
 */
@Data
public class FunctionVO extends BasePageVo{

    //columns START

    /**
     * 数据id
     */
    private Long id;
    /**
     * 应用的编码
     */
    private Long applicationId;
    /**
     * 菜单名称
     */
    private String functionName;
    /**
     * 菜单图标
     */
    private String functionIcon;
    /**
     * 功能编码
     */
    private String functionCode;
    /**
     * 是否显示，1：显示 0：不显示
     */
    private Integer isDisplay;
    /**
     * 功能排序值
     */
    private Integer orderValue;
    /**
     * 创建时间
     */
    private Date createTime;
    /**
     * 创建者id
     */
    private Long createUserId;
    /**
     * 更新时间
     */
    private Date updateTime;
    /**
     * 更新者id
     */
    private Long updateUserId;
    /**
     * 应用名称
     */
    private String applicationName;
    /**
     * 创建人名字
     */
    private String createUserName;
    /**
     * 更新人名字
     */
    private String updateUserName;

    //columns END
}


