package com.bit.sc.module.sys.service;

import com.bit.sc.module.sys.pojo.PermissionRelMenu;
import com.bit.sc.module.sys.vo.PermissionRelMenuVO;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * PermissionRelMenu接口
 * @author zhangjie
 */
@Service
public interface PermissionRelMenuService {
    /**
     * 查询所有PermissionRelMenu
     * @param permissionType
     * @return
     */
    public List<PermissionRelMenu> findPermissionByCondition(Integer permissionType);
    /**
     * 查询列表Menu and Function
     * @param permissionRelMenuVO
     * @return
     */
    public PageInfo<PermissionRelMenu> findAll(PermissionRelMenuVO permissionRelMenuVO);
}
