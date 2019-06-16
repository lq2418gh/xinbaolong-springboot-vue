package com.bit.sc.module.sys.service.impl;

import java.util.List;
import java.util.ArrayList;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.bit.base.vo.BaseVo;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.bit.sc.module.sys.pojo.RoleRelInterfacePermission;
import com.bit.sc.module.sys.vo.RoleRelInterfacePermissionVO;
import com.bit.sc.module.sys.dao.RoleRelInterfacePermissionMapper;
import com.bit.sc.module.sys.service.RoleRelInterfacePermissionService;
import com.bit.base.service.BaseService;
import org.springframework.transaction.annotation.Transactional;

/**
 * RoleRelInterfacePermission的Service实现类
 * @author codeGenerator
 *
 */
@Service("roleRelInterfacePermissionService")
public class RoleRelInterfacePermissionServiceImpl extends BaseService implements RoleRelInterfacePermissionService{
	
	private static final Logger logger = LoggerFactory.getLogger(RoleRelInterfacePermissionServiceImpl.class);
	
	@Autowired
	private RoleRelInterfacePermissionMapper roleRelInterfacePermissionMapper;
	
	/**
	 * 根据条件查询RoleRelInterfacePermission
	 * @return
	 */
	@Override
	public BaseVo findByConditionPage(RoleRelInterfacePermissionVO roleRelInterfacePermissionVO){
		PageHelper.startPage(roleRelInterfacePermissionVO.getPageNum(), roleRelInterfacePermissionVO.getPageSize());
		List<RoleRelInterfacePermission> list = roleRelInterfacePermissionMapper.findByConditionPage(roleRelInterfacePermissionVO);
		PageInfo<RoleRelInterfacePermission> pageInfo = new PageInfo<RoleRelInterfacePermission>(list);
		BaseVo baseVo = new BaseVo();
		baseVo.setData(pageInfo);
		return baseVo;
	}
	/**
	 * 查询所有RoleRelInterfacePermission
	 * @param sorter 排序字符串
	 * @return
	 */
	@Override
	public List<RoleRelInterfacePermission> findAll(String sorter){
		return roleRelInterfacePermissionMapper.findAll(sorter);
	}
	/**
	 * 通过主键查询单个RoleRelInterfacePermission
	 * @param id
	 * @return
	 */
	@Override
	public RoleRelInterfacePermission findById(Long id){
		return roleRelInterfacePermissionMapper.findById(id);
	}
	
	/**
	 * 批量保存RoleRelInterfacePermission
	 * @param roleRelInterfacePermissions
	 */
	@Override
	@Transactional
	public void batchAdd(List<RoleRelInterfacePermission> roleRelInterfacePermissions){
		roleRelInterfacePermissionMapper.batchAdd(roleRelInterfacePermissions);
	}
	/**
	 * 保存RoleRelInterfacePermission
	 * @param roleRelInterfacePermission
	 */
	@Override
	@Transactional
	public void add(RoleRelInterfacePermission roleRelInterfacePermission){
		roleRelInterfacePermissionMapper.add(roleRelInterfacePermission);
	}
	/**
	 * 批量更新RoleRelInterfacePermission
	 * @param roleRelInterfacePermissions
	 */
	@Override
	@Transactional
	public void batchUpdate(List<RoleRelInterfacePermission> roleRelInterfacePermissions){
		roleRelInterfacePermissionMapper.batchUpdate(roleRelInterfacePermissions);
	}
	/**
	 * 更新RoleRelInterfacePermission
	 * @param roleRelInterfacePermission
	 */
	@Override
	@Transactional
	public void update(RoleRelInterfacePermission roleRelInterfacePermission){
		roleRelInterfacePermissionMapper.update(roleRelInterfacePermission);
	}
	/**
	 * 删除RoleRelInterfacePermission
	 * @param ids
	 */
	@Override
	@Transactional
	public void batchDelete(List<Long> ids){
		roleRelInterfacePermissionMapper.batchDelete(ids);
	}
	/**
	 * 根据roleId查询权限
	 * @param roleId
	 * @return
	 */
	@Override
	public List<RoleRelInterfacePermission> findByRoleId(Long roleId) {
		return roleRelInterfacePermissionMapper.findByRoleId(roleId);
	}

    /**
     * 更新 中间表 角色-接口
     * @param roleRelInterfacePermission
     */
	@Override
	@Transactional
	public void updateInterFacePermission(RoleRelInterfacePermission roleRelInterfacePermission) {
        roleRelInterfacePermissionMapper.deleteByRoleId(roleRelInterfacePermission.getRoleId());
        List<Long> permissionIds = roleRelInterfacePermission.getPermissionIds();
        if (permissionIds!=null){
            List<RoleRelInterfacePermission> roleRelInterfacePermissions = new ArrayList<>();
            for (Long permissionId : permissionIds) {
                RoleRelInterfacePermission obj = new RoleRelInterfacePermission();
                obj.setPermissionId(permissionId);
                obj.setRoleId(roleRelInterfacePermission.getRoleId());
                roleRelInterfacePermissions.add(obj);
            }
            roleRelInterfacePermissionMapper.batchAdd(roleRelInterfacePermissions);
        }

    }

    /***
     * 根据roleId 查询
     * @param roleId
     */
    @Override
    public List<RoleRelInterfacePermission> findListByRole(Long roleId) {
       return roleRelInterfacePermissionMapper.findListByRole(roleId);
    }

    /**
	 * 删除RoleRelInterfacePermission
	 * @param id
	 */
	@Override
	@Transactional
	public void delete(Long id){
		roleRelInterfacePermissionMapper.delete(id);
	}
}
