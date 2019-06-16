package com.bit.sc.module.sys.service.impl;

import com.bit.base.service.BaseService;
import com.bit.base.vo.BaseVo;
import com.bit.base.vo.UserInfo;
import com.bit.sc.common.Const;
import com.bit.sc.module.sys.dao.ResourceMapper;
import com.bit.sc.module.sys.dao.RoleRelPermissionMapper;
import com.bit.sc.module.sys.pojo.Resource;
import com.bit.sc.module.sys.pojo.RoleRelPermission;
import com.bit.sc.module.sys.service.ResourceService;
import com.bit.sc.module.sys.vo.ResourceVO;
import com.bit.sc.utils.CheckUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Resource的Service实现类
 * @author codeGenerator
 *
 */
@Service("resourceService")
public class ResourceServiceImpl extends BaseService implements ResourceService{
	
	@Autowired
	private ResourceMapper resourceMapper;

	@Autowired
	private RoleRelPermissionMapper roleRelPermissionMapper;

	/**
	 * 根据条件查询Resource
	 * @param resourceVO
	 * @return
	 */
	@Override
	public BaseVo findByConditionPage(ResourceVO resourceVO){
		PageHelper.startPage(resourceVO.getPageNum(), resourceVO.getPageSize());
		List<Resource> list = resourceMapper.findByConditionPage(resourceVO);
		PageInfo<Resource> pageInfo = new PageInfo<Resource>(list);
		BaseVo baseVo = new BaseVo();
		baseVo.setData(pageInfo);
		return baseVo;
	}
	/**
	 * 查询所有Resource
	 * @param sorter 排序字符串
	 * @return
	 */
	@Override
	public List<Resource> findAll(String sorter){
		return resourceMapper.findAll(sorter);
	}
	/**
	 * 通过主键查询单个Resource
	 * @param id
	 * @return
	 */
	@Override
	public Resource findById(Long id){
		return resourceMapper.findById(id);
	}
	
	/**
	 * 批量保存Resource
	 * @param resources
	 */
	@Override
	@Transactional
	public void batchAdd(List<Resource> resources){
		resourceMapper.batchAdd(resources);
	}
	/**
	 * 保存Resource
	 * @param resource
	 */
	@Override
	@Transactional
	public void add(Resource resource){
		if (resource.getType()==0){
			this.check(resource);
			if (resource.getResourceLevel()!=Const.IS_DISPLAY_Y){
				resource.setIsDisplay(Const.IS_DISPLAY_Y);
			}else{
				resource.setIsDisplay(Const.IS_DISPLAY_N);
			}
		}else{
			resource.setIsDisplay(Const.IS_DISPLAY_Y);
		}
		resource.setCreateTime(new Date());
		resource.setCreateUserId(getCurrentUserInfo().getId());
		resourceMapper.add(resource);
	}

	/**
	 * 校验非空
	 * @param resource
	 */
	private void check(Resource resource) {
		CheckUtil.notNull(resource.getType(),"类型不能为空");
		CheckUtil.notEmpty(resource.getResourceName().trim(),"菜单名称不能为空");
		CheckUtil.notEmpty(resource.getResourceCode().trim(),"菜单编码不能为空");
		CheckUtil.notNull(resource.getApplicationId(),"应用编码不能为空");
		CheckUtil.notNull(resource.getPid(),"菜单父节点不能为空");
		CheckUtil.notNull(resource.getIsDisplay(),"是否显示是非空字段");
	}

	/**
	 * 更新Resource
	 * @param resource
	 */
	@Override
	@Transactional
	public void update(Resource resource){
		this.check(resource);
		resource.setUpdateTime(new Date());
		resource.setUpdateUserId(getCurrentUserInfo().getId());
		resourceMapper.update(resource);
	}
	/**
	 * 批量删除Resource
	 * @param ids
	 */
	@Override
	@Transactional
	public void batchDelete(List<Long> ids){
		resourceMapper.batchDelete(ids);
	}
	/**
	 * 查询资源树 0查询菜单  1查询按钮  2查询所有
	 * @param applicationCode
	 * @param type
	 * @return
	 */
	@Override
	public List<Resource> resourceListByParam(String applicationCode,Integer type) {
		UserInfo userInfo=getCurrentUserInfo();
		List<Long> roleIds = userInfo.getRoles();
		List<Resource> rootResource=new ArrayList<>();
		if (type==2){
			type=null;
		}
		List<Resource> resourceList=resourceMapper.resourceListByParam(roleIds,applicationCode,Const.show,type);
		resourceList.stream().forEach(resource -> {
			if(resource.getPid().equals(Const.ROOT_MENU_PID)){
				rootResource.add(resource);
			}
		});
		rootResource.stream().forEach(resource -> {
			resource.setChildResources(getChild(resource.getId(), resourceList));
		});
		return rootResource;
	}
	/**
	 * 统计  根据资源编码  不能重复
	 * @param resourceCode
	 * @return
	 */
	@Override
	public int findCountByResourceCode(String resourceCode) {
		return resourceMapper.findCountByResourceCode(resourceCode);
	}
    /**
     * 查询resource--web--所有--树
     * @param applicationCode
     * @return
     */
    @Override
    public List<Resource> findByApplication(String applicationCode) {
        List<Resource> resourceList = resourceMapper.findByApplication(applicationCode);
        List<Resource> rootResource=new ArrayList<>();
        resourceList.stream().forEach(resource -> {
            if(resource.getPid().equals(Const.ROOT_MENU_PID)){
                rootResource.add(resource);
            }
        });
        rootResource.stream().forEach(resource -> {
            resource.setChildResources(getChild(resource.getId(), resourceList));
        });
        return rootResource;
    }

    /**
	 * 递归资源
	 * @param id
	 * @param resourceList
	 * @return
	 */
	private List<Resource> getChild(Long id, List<Resource> resourceList) {
		// 子菜单
		List<Resource> childList = new ArrayList<>();
		for (Resource resource : resourceList) {
			if (resource.getPid()!=null) {
				if (resource.getPid().equals(id)) {
					childList.add(resource);
				}
			}
		}
		for (Resource resource : childList) {
			resource.setChildResources(getChild(resource.getId(), resourceList));
		}
		if (childList.size() == 0) {
			return null;
		}
		return childList;
	}

	/**
	 * 删除Resource
	 * @param id
	 */
	@Override
	@Transactional
	public void delete(Long id){
		resourceMapper.delete(id);
		RoleRelPermission roleRelPermission = new RoleRelPermission();
		roleRelPermission.setFunctionId(id);
		roleRelPermission.setPermissionType(Const.PERMISSION_TYPE_MENU);
		roleRelPermissionMapper.deleteByFunction(roleRelPermission);
	}
}
