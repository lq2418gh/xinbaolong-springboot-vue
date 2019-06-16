package com.bit.sc.module.sys.dao;

import com.bit.sc.module.sys.pojo.PermissionRelFunction;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface PermissionRelFunctionMapper {

    /**
     * 查询所有PermissionRelFunction
     * @param permissionType
     * @return
     */
    public List<PermissionRelFunction> findAll(@Param(value = "permissionType") Integer permissionType);

}
