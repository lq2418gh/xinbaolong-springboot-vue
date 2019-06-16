package com.bit.sc.module.sys.service;

import java.util.List;
import java.util.Map;

import com.bit.sc.module.sys.vo.RoleCodeName;
import com.bit.sc.module.user.pojo.UserRole;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Service;
import com.bit.sc.module.sys.pojo.Role;
import com.bit.sc.module.sys.vo.RoleVO;
import com.bit.base.vo.BaseVo;

/**
 * Role的Service
 * @author codeGenerator
 */
@Service
public interface RoleService {
	/**
	 * 根据条件查询Role
	 * @param roleVO
	 * @return
	 */
	 BaseVo findByConditionPage(RoleVO roleVO);
	/**
	 * 查询所有Role
	 * @param sorter 排序字符串
	 * @return
	 */
	 List<Role> findAll(String sorter);
	/**
	 * 通过主键查询单个Role
	 * @param id
	 * @return
	 */
	 Role findById(Long id);

	/**
	 * 通过主键查询自身所有Role
	 * @param id
	 * @return
	 */
	 List<UserRole> queryAllById(Long id);




	/**
	 * 删除Role
	 * @param id
	 */
	 void delete(Long id);


	/**
	 * Role列表
	 * @param
	 */
	 PageInfo<RoleVO>  findRoleListByCondition(RoleVO roleVO);


	/**
	 * 保存Role
	 * @param role
	 */
	 void add(Role role);

	/**
	 * 更新Role
	 * @param role
	 */
	 void update(Role role);
	/**
	 * RoleCodeName列表
	 * @param
	 */
	 PageInfo<RoleCodeName> findAllRoleCodeName(RoleVO roleVO);

	/**
	 * 启动事务对人物和角色关系表进行 先删除后新增 的操作
	 * @param list
	 * @param id
	 */
	 void updateRole(Map<String,Object> list, Long id);




	/**
	 * 通过用户的id来查询所有角色的id
	 * @param Id
	 * @return
	 */
     List<Long> findRoleByUserId(Long id);
}
