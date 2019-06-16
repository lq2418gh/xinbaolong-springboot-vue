package com.bit.sc.module.sys.pojo;

import lombok.Data;

/**
 * PermissionRelMenu
 *  @author zhagnjie
 */
@Data
public class PermissionRelMenu {

    /**
     * id
     */
    private Long id;
    /**
     * 应用名称
     */
    private String applicationName;
    /**
     * 角色名称
     */
    private String roleName;
    /**
     * 菜单名称
     */
    private String menuName;
    /**
     * 功能编码
     */
    private String menuCode;
    /**
     * 用户名称
     */
    private String realName;

}