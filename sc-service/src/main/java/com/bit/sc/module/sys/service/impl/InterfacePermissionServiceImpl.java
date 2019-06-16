package com.bit.sc.module.sys.service.impl;

import com.bit.base.vo.BaseVo;
import com.bit.common.ResultCode;
import com.bit.sc.module.sys.dao.InterfacePermissionMapper;
import com.bit.sc.module.sys.pojo.InterfacePermission;
import com.bit.sc.module.sys.service.InterfacePermissionService;
import com.bit.sc.module.sys.vo.InterfacePermissionVO;
import com.bit.utils.CacheUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * InterfacePermission的Service实现类
 * @author zhanghaodong
 *
 */
@Service("interfacePermissionService")
public class InterfacePermissionServiceImpl implements InterfacePermissionService{

	private static final Logger logger = LoggerFactory.getLogger(InterfacePermissionServiceImpl.class);

	@Autowired
	private InterfacePermissionMapper interfacePermissionMapper;

	@Autowired
    private CacheUtil cacheUtil;

	/**
	 * 根据条件查询InterfacePermission
	 * @param page
	 * @return
	 */
	@Override
	public BaseVo findByConditionPage(InterfacePermissionVO interfacePermissionVO){
		PageHelper.startPage(interfacePermissionVO.getPageNum(), interfacePermissionVO.getPageSize());
		List<InterfacePermission> list = interfacePermissionMapper.findByConditionPage(interfacePermissionVO);
		PageInfo<InterfacePermission> pageInfo = new PageInfo<InterfacePermission>(list);
		interfacePermissionVO.setData(pageInfo);
		return interfacePermissionVO;
	}
	/**
	 * 查询所有InterfacePermission
	 * @param sorter 排序字符串
	 * @return
	 */
	@Override
	public List<InterfacePermission> findAll(String sorter){

	    return interfacePermissionMapper.findAll(sorter);
	}
	/**
	 * 通过主键查询单个InterfacePermission
	 * @param id
	 * @return
	 */
	@Override
	public InterfacePermission findById(Long id){
        return  interfacePermissionMapper.findById(id);
 }

	/**
	 * 批量保存InterfacePermission
	 * @param interfacePermissions
	 */
	@Override
	public void batchAdd(List<InterfacePermission> interfacePermissions){
		interfacePermissionMapper.batchAdd(interfacePermissions);
	}
	/**
	 * 保存InterfacePermission
	 * @param interfacePermission
	 */
	@Override
	public void add(InterfacePermission interfacePermission){
		interfacePermissionMapper.add(interfacePermission);
	}
	/**
	 * 批量更新InterfacePermission
	 * @param interfacePermissions
	 */
	@Override
	public void batchUpdate(List<InterfacePermission> interfacePermissions){
		interfacePermissionMapper.batchUpdate(interfacePermissions);
	}
	/**
	 * 更新InterfacePermission
	 * @param interfacePermission
	 */
	@Override
	public void update(InterfacePermission interfacePermission){
		interfacePermissionMapper.update(interfacePermission);
	}
	/**
	 * 删除InterfacePermission
	 * @param ids
	 */
	@Override
	public void batchDelete(List<Long> ids){
		interfacePermissionMapper.batchDelete(ids);
	}

    /**
     * 根据角色信息查询对应的权限信息
     * @param id
     * @return
     */
    @Override
    public List<InterfacePermission> findPermissionById(Long id) {
        List<InterfacePermission> permission = interfacePermissionMapper.findPermissionById(id);
		if (permission!=null){
			for (InterfacePermission interfacePermission : permission) {
				Long permissionId = interfacePermission.getId();
				String permissionUrl = interfacePermission.getUrl();
				Map map= new HashMap();
				map.put(permissionId.toString(),permissionUrl);
				cacheUtil.hmset("role",map);
			}
		}else {
			BaseVo baseVo= new BaseVo();
			baseVo.setData(permission);
			baseVo.setCode(ResultCode.UNAUTH.getCode());
			baseVo.setMsg(ResultCode.UNAUTH.getInfo());

		}


        return permission;
    }
    /**
	 * 删除InterfacePermission
	 * @param id
	 */
	@Override
	public void delete(Long id){
		interfacePermissionMapper.delete(id);
	}
}
