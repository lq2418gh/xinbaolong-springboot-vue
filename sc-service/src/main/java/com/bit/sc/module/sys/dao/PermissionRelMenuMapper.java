package com.bit.sc.module.sys.dao;

import com.bit.sc.module.sys.pojo.PermissionRelMenu;
import com.bit.sc.module.sys.vo.PermissionRelMenuVO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface PermissionRelMenuMapper {

    /**
     * 查询所有PermissionRelMenu
     * @param permissionType
     * @return
     */
    public List<PermissionRelMenu> findPermissionByCondition(@Param(value = "permissionType") Integer permissionType);

    /**
     * 查询列表Menu and Function
     * @param permissionRelMenuVO
     * @return
     */
    public List<PermissionRelMenu> findAll(PermissionRelMenuVO permissionRelMenuVO);

}
