package com.bit.sc.module.sys.vo;

import lombok.Data;

import java.util.List;
@Data
public class RoleRelPersmissionMenuVO {

    private Long roleId;

    private List<Long> functionList;

    private Integer permissionType;

}
