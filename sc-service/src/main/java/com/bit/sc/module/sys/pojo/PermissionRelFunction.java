package com.bit.sc.module.sys.pojo;

import java.util.Date;

public class PermissionRelFunction {

    /**
     * id
     */
    private Long id;
    /**
     * 角色的id
     */
    private Long roleId;
    /**
     * t_function和t_function表中的功能id
     */
    private Long functionId;
    /**
     * 应用的类型： 1：web  使用t_function表  2: app使用 t_function表
     */
    private Integer permissionType;
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
     * 功能排序值
     */
    private Integer orderValue;
    /**
     * 是否显示，1：显示 0：不显示
     */
    private Integer isDisplay;
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

}
