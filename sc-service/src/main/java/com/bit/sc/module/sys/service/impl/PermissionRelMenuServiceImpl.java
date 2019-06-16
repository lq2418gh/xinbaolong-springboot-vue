package com.bit.sc.module.sys.service.impl;

import com.bit.base.service.BaseService;
import com.bit.base.vo.BaseVo;
import com.bit.sc.module.sys.dao.PermissionRelMenuMapper;
import com.bit.sc.module.sys.pojo.PermissionRelMenu;
import com.bit.sc.module.sys.service.PermissionRelMenuService;
import com.bit.sc.module.sys.vo.PermissionRelMenuVO;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * PermissionRelMenu的Service实现类
 * @author zhangjie
 *
 */
@Service
public class PermissionRelMenuServiceImpl extends BaseService implements PermissionRelMenuService {

    private static final Logger logger = LoggerFactory.getLogger(PermissionRelMenuServiceImpl.class);

    @Autowired
    private PermissionRelMenuMapper permissionRelMenuMapper;

    /**
     * 查询所有PermissionRelMenu
     * @param permissionType
     * @return
     */
    @Override
    public List<PermissionRelMenu> findPermissionByCondition(Integer permissionType) {
        return permissionRelMenuMapper.findPermissionByCondition(permissionType);
    }
    /**
     * 查询列表Menu and Function
     * @param permissionRelMenuVO
     * @return
     */
    @Override
    public PageInfo<PermissionRelMenu>  findAll(PermissionRelMenuVO permissionRelMenuVO) {
        BaseVo vo=new BaseVo();
        PageHelper.startPage(permissionRelMenuVO.getPageNum(), permissionRelMenuVO.getPageSize());
        List<PermissionRelMenu> list = permissionRelMenuMapper.findAll(permissionRelMenuVO);
        PageInfo<PermissionRelMenu> pageInfo = new PageInfo<>(list);
        return pageInfo;
    }
}
