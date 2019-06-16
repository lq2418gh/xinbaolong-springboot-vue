package com.bit.sc.module.user.pojo;

import lombok.Data;

@Data
public class UserRole {

    /**
     * 用户名
     */
    private String username;
    /**
     * 应用的id
     */
    private Long applicationId;
    /**
     * 角色名称
     */
    private String roleName;
    /**
     * 应用名称
     */
    private String applicationName;
}
