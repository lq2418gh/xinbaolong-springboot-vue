package com.bit.sc.module.sys.service.impl;

import java.util.List;
import java.util.ArrayList;

import com.bit.base.exception.BusinessException;
import com.bit.sc.common.Const;
import com.bit.sc.module.sys.vo.RolePermissionMenuVO;
import com.bit.sc.module.sys.vo.RoleRelPersmissionMenuVO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.bit.base.vo.BaseVo;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.bit.sc.module.sys.pojo.RoleRelPermission;
import com.bit.sc.module.sys.vo.RoleRelPermissionVO;
import com.bit.sc.module.sys.dao.RoleRelPermissionMapper;
import com.bit.sc.module.sys.service.RoleRelPermissionService;
import org.springframework.transaction.annotation.Transactional;

/**
 * RoleRelPermission的Service实现类
 * @author codeGenerator
 *
 */
@Service("roleRelPermissionService")
public class RoleRelPermissionServiceImpl implements RoleRelPermissionService{
	
	private static final Logger logger = LoggerFactory.getLogger(RoleRelPermissionServiceImpl.class);
	
	@Autowired
	private RoleRelPermissionMapper roleRelPermissionMapper;
	
	/**
	 * 根据条件查询RoleRelPermission
	 * @param roleRelPermissionVO
	 * @return
	 */
	@Override
	public BaseVo findByConditionPage(RoleRelPermissionVO roleRelPermissionVO){
		PageHelper.startPage(roleRelPermissionVO.getPageNum(), roleRelPermissionVO.getPageSize());
		List<RoleRelPermission> list = roleRelPermissionMapper.findByConditionPage(roleRelPermissionVO);
		PageInfo<RoleRelPermission> pageInfo = new PageInfo<RoleRelPermission>(list);
		roleRelPermissionVO.setData(pageInfo);
		return roleRelPermissionVO;
	}
	/**
	 * 查询所有RoleRelPermission
	 * @param sorter 排序字符串
	 * @return
	 */
	@Override
	public List<RoleRelPermission> findAll(String sorter){
		return roleRelPermissionMapper.findAll(sorter);
	}
	/**
	 * 通过主键查询单个RoleRelPermission
	 * @param id
	 * @return
	 */
	@Override
	public RoleRelPermission findById(Long id){
		return roleRelPermissionMapper.findById(id);
	}
	
	/**
	 * 批量保存RoleRelPermission
	 * @param roleRelPermissions
	 */
	@Override
	@Transactional
	public void batchAdd(List<RoleRelPermission> roleRelPermissions){
		roleRelPermissionMapper.batchAdd(roleRelPermissions);
	}
	/**
	 * 保存RoleRelPermission
	 * @param roleRelPermission
	 */
	@Override
	@Transactional
	public void add(RoleRelPermission roleRelPermission){
		roleRelPermissionMapper.add(roleRelPermission);
	}
	/**
	 * 批量更新RoleRelPermission
	 * @param roleRelPermissions
	 */
	@Override
	@Transactional
	public void batchUpdate(List<RoleRelPermission> roleRelPermissions){
		roleRelPermissionMapper.batchUpdate(roleRelPermissions);
	}
	/**
	 * 更新RoleRelPermission
	 * @param roleRelPermission
	 */
	@Override
	@Transactional
	public void update(RoleRelPermission roleRelPermission){
		roleRelPermissionMapper.update(roleRelPermission);
	}
	/**
	 * 删除RoleRelPermission
	 * @param ids
	 */
	@Override
	@Transactional
	public void batchDelete(List<Long> ids){
		roleRelPermissionMapper.batchDelete(ids);
	}

    /**
     * 分页查询RoleRelPermission列表 根据平台显示不同功能
     * @author chenduo
     *
     */
	@Override
	public BaseVo queryFunctionByPage(RoleRelPermissionVO roleRelPermissionVO) {
		PageHelper.startPage(roleRelPermissionVO.getPageNum(), roleRelPermissionVO.getPageSize());
        List<RolePermissionMenuVO> list = null;
	    if (roleRelPermissionVO.getPermissionType().equals(1)){
//	        roleRelPermissionVO.setTableName("t_sys_menu");
//            roleRelPermissionVO.setColumnName("t2.menu_name");
            list=roleRelPermissionMapper.queryMenuByPage(roleRelPermissionVO);
        }
        if (roleRelPermissionVO.getPermissionType().equals(2)){
//            roleRelPermissionVO.setTableName("t_sys_role_rel_permission");
//            roleRelPermissionVO.setColumnName("t2.function_name");
            list=roleRelPermissionMapper.queryFunctionByPage(roleRelPermissionVO);
        }

//		List<RolePermissionMenuVO> list = roleRelPermissionMapper.queryFunctionByPage(roleRelPermissionVO);
        PageInfo<RolePermissionMenuVO> pageInfo = new PageInfo<RolePermissionMenuVO>(list);
        roleRelPermissionVO.setData(pageInfo);
		return roleRelPermissionVO;
	}
	/**
	 * 查询RoleRelPermission列表 只查询web平台的 符合角色id的菜单id
	 * @author chenduo
	 *
	 */
	@Override
	public BaseVo listAllFunction(RoleRelPermission roleRelPermission) {
		roleRelPermission.setPermissionType(1);
		List<RoleRelPermission> list = roleRelPermissionMapper.listAllFunction(roleRelPermission);
		if (list.size()==0){
			throw new BusinessException("无满足此条件的数据");
		}
		List<Long> functions = new ArrayList<>();
		for (int i=0;i<list.size();i++){
			functions.add(list.get(i).getFunctionId());
		}
		BaseVo baseVo = new BaseVo();
		baseVo.setData(functions);
		return baseVo;
	}

	@Override
	public BaseVo change(RoleRelPersmissionMenuVO roleRelPersmissionMenuVO) {
		if (roleRelPersmissionMenuVO == null){
			throw new BusinessException("参数为空");
		}
		if (roleRelPersmissionMenuVO.getRoleId() == null){
			throw new BusinessException("角色参数为空");
		}
		//先按照roleid删除
		roleRelPermissionMapper.deleteByRoleId(roleRelPersmissionMenuVO.getRoleId());
		//批量添加
		List<Long> longList = roleRelPersmissionMenuVO.getFunctionList();
		if (longList != null){
			List<RoleRelPermission> roleRelPermissions = new ArrayList<>();
			for (int i =0 ;i<longList.size();i++){
				RoleRelPermission roleRelPermission = new RoleRelPermission();
				roleRelPermission.setRoleId(roleRelPersmissionMenuVO.getRoleId());
				roleRelPermission.setPermissionType(Const.PERMISSION_TYPE_MENU);
				roleRelPermission.setFunctionId(longList.get(i));
				roleRelPermissions.add(roleRelPermission);
			}
			roleRelPermissionMapper.batchAdd(roleRelPermissions);
		}

		return new BaseVo();
	}

	/**
	 * 删除RoleRelPermission
	 * @param id
	 */
	@Override
	@Transactional
	public void delete(Long id){
		roleRelPermissionMapper.delete(id);
	}
}
