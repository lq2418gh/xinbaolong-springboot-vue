package com.bit.sc.module.sys.vo;

import com.bit.base.vo.BasePageVo;
import lombok.Data;

import java.util.Date;

/**
 * PermissionRelMenu
 * @author zhangjie
 */
@Data
public class PermissionRelMenuVO extends BasePageVo {

    /**
     * id
     */
    private Long id;
    /**
     * 角色的id
     */
    private Long roleId;
    /**
     * t_function和t_menu表中的功能id
     */
    private Long functionId;
    /**
     * 应用的类型： 1：web  使用t_menu表  2: app使用 t_function表
     */
    private Integer permissionType;
    /**
     * 菜单名称
     */
    private String menuName;
    /**
     * 菜单图标
     */
    private String menuIcon;
    /**
     * 菜单等级
     */
    private Integer menuLevel;
    /**
     * 菜单地址
     */
    private String menuUrl;
    /**
     * 功能编码
     */
    private String menuCode;
    /**
     * 用户名称
     */
    private String userName;
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
    /**
     * 应用的编码
     */
    private Long applicationId;


}
