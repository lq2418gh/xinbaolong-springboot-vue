package com.bit.sc.module.sys.vo;

import lombok.Data;

@Data
public class RoleCodeName {

    /**
     * 角色名
     */
    private String roleName;
    /**
     * 角色编号
     */
    private Integer id;
    /**
     * app代号
     */
    private String applicationCode;
    /**
     * app名称
     */
    private String applicationName;
}
