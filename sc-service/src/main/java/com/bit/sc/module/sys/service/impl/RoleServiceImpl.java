package com.bit.sc.module.sys.service.impl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import com.bit.base.service.BaseService;
import com.bit.sc.common.Const;
import com.bit.sc.module.sys.service.UserRelRoleService;
import com.bit.sc.module.sys.vo.RoleCodeName;
import com.bit.sc.module.user.pojo.UserRole;
import com.bit.sc.utils.DateUtil;
import org.aspectj.apache.bcel.generic.RET;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.bit.base.vo.BaseVo;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.bit.sc.module.sys.pojo.Role;
import com.bit.sc.module.sys.vo.RoleVO;
import com.bit.sc.module.sys.dao.RoleMapper;
import com.bit.sc.module.sys.service.RoleService;
import org.springframework.transaction.annotation.Transactional;

/**
 * Role的Service实现类
 * @author codeGenerator
 *
 */
@Service("roleService")
public class RoleServiceImpl  extends BaseService implements RoleService{
	
	private static final Logger logger = LoggerFactory.getLogger(RoleServiceImpl.class);


	@Autowired
    private UserRelRoleService userRelRoleService;
	
	@Autowired
	private RoleMapper roleMapper;
	
	/**
	 * 根据条件查询Role
	 * @param roleVO
	 * @return
	 */
	@Override
	public BaseVo findByConditionPage(RoleVO roleVO){
		PageHelper.startPage(roleVO.getPageNum(), roleVO.getPageSize());
		List<Role> list = roleMapper.findByConditionPage(roleVO);
		PageInfo<Role> pageInfo = new PageInfo<Role>(list);
		roleVO.setData(pageInfo);
		return roleVO;
	}
	/**
	 * 查询所有Role
	 * @param sorter 排序字符串
	 * @return
	 */
	@Override
	public List<Role> findAll(String sorter){
		return roleMapper.findAll(sorter);
	}
	/**
	 * 通过主键查询单个Role
	 * @param id
	 * @return
	 */
	@Override
	public Role findById(Long id){
		return roleMapper.findById(id);
	}

    /**
     * 根据主键ID查询自身所有Role
     *
     * @param id
     * @return
     */
	@Override
	public List<UserRole> queryAllById(Long id) {
		return roleMapper.queryAllById(id);
	}


	/**
	 * 删除Role
	 * @param id
	 */
	@Override
    @Transactional
	public void delete(Long id){
		Role role=new Role();
		role.setId(id);
		role.setIsDelete(Const.DELETE);
		role.setUpdateTime(new Date());
		role.setUpdateUserId(getCurrentUserInfo().getId());
		roleMapper.update(role);
	}

	/**
	 * 查询
	 * @param
	 */
	@Override
	public PageInfo<RoleVO>  findRoleListByCondition(RoleVO roleVO){
		PageHelper.startPage(roleVO.getPageNum(), roleVO.getPageSize());
		List<RoleVO> list = roleMapper.findRoleListByCondition(roleVO);
		PageInfo<RoleVO> pageInfo = new PageInfo<RoleVO>(list);
		return pageInfo;
	}

	/**
	 * 保存Role
	 * @param role
	 */
	@Override
    @Transactional
	public void add(Role role){
		role.setCreateUserId(getCurrentUserInfo().getId());
		role.setCreateTime(new Date());
		role.setIsDelete(Const.NOT_DELETE);
		roleMapper.add(role);
	}

	/**
	 * 更新Role
	 * @param role
	 */
	@Override
    @Transactional
	public void update(Role role){
		SimpleDateFormat sdf = new SimpleDateFormat(Const.DATE_FORMATE);
		String time = sdf.format(new Date());
		Date date = DateUtil.parse(time);
		role.setUpdateTime(date);
        role.setUpdateUserId(getCurrentUserInfo().getId());

		roleMapper.update(role);
	}

    /**
     * 查询所有的可用角色名称 app代号 和 app名称
     * @param roleVO
     * @author chenduo
     * @return
     */
	@Override
	public PageInfo<RoleCodeName> findAllRoleCodeName(RoleVO roleVO) {
		PageHelper.startPage(roleVO.getPageNum(), roleVO.getPageSize());
		List<RoleCodeName> list = roleMapper.findAllRoleCodeName();
		PageInfo<RoleCodeName> pageInfo = new PageInfo<RoleCodeName>(list);
		return pageInfo;
	}

    /**
     * 启动事务对人物和角色关系表进行 先删除后新增 的操作
     * @param list
     * @param id
     */
	@Override
	@Transactional
	public void updateRole(Map<String,Object> list, Long id) {
		try{
            //删除数据
            userRelRoleService.deleteRole(id);
		    if (list.get("list")!=null){
                List<Integer> temp = (List<Integer>) list.get("list");
                List<String> rolelist = new ArrayList<>();
                for (int i =0 ;i<temp.size();i++){
                    rolelist.add(temp.get(i)+"");
                }
                //重新添加数据
                userRelRoleService.addUserRelRole(rolelist,id);
            }
		}catch (Exception e){
            e.printStackTrace();
		}

	}


	/**
	 * 根据用户的id来查询全部角色的id
	 * @param id
     * @author chenduo
	 * @return
	 */
	@Override
	public List<Long> findRoleByUserId(Long id) {
        List<Long> roleList = roleMapper.findRoleByUserId(id);
        return  roleList;

    }

}
