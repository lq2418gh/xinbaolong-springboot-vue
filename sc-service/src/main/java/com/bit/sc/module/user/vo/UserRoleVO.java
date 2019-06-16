package com.bit.sc.module.user.vo;

import com.bit.base.vo.BasePageVo;
import lombok.Data;

/**
 * @author liuyancheng
 * @create 2018-11-23 8:48
 */
@Data
public class UserRoleVO extends BasePageVo {
    /**
     * user主键id
     */
    private Long id;
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
