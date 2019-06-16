package com.bit.sc.module.group.service.impl;

import com.bit.base.bean.UserAddress;
import com.bit.base.service.BaseService;
import com.bit.base.vo.BaseVo;
import com.bit.base.vo.UserInfo;
import com.bit.sc.common.Const;
import com.bit.sc.module.equipment.vo.EquipmentVO;
import com.bit.sc.module.group.dao.GroupMapper;
import com.bit.sc.module.group.pojo.Group;
import com.bit.sc.module.group.service.GroupService;
import com.bit.sc.module.group.vo.GroupVO;
import com.bit.sc.module.sys.dao.AddressMapper;
import com.bit.sc.module.sys.pojo.Address;
import com.bit.utils.StringUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

/**
 * Group的Service实现类
 * @author codeGenerator
 *
 */
@Service("groupService")
public class GroupServiceImpl extends BaseService implements GroupService {
	
	private static final Logger logger = LoggerFactory.getLogger(GroupServiceImpl.class);
	
	@Autowired
	private GroupMapper groupMapper;
	@Autowired
	private AddressMapper addressMapper;
	
	/**
	 * 根据条件查询Group
	 * @param
	 * @return
	 */
	@Override
	public BaseVo findByConditionPage(GroupVO groupVO){
		PageHelper.startPage(groupVO.getPageNum(), groupVO.getPageSize());
		groupVO.setOrderBy("create_time desc");
		List<Group> list = groupMapper.findByConditionPage(groupVO);
		PageInfo<Group> pageInfo = new PageInfo<Group>(list);
		BaseVo baseVo = new BaseVo();
		baseVo.setData(pageInfo);
		return baseVo;
	}
	/**
	 * 查询所有Group
	 * @param sorter 排序字符串
	 * @return
	 */
	@Override
	public List<Group> findAll(String sorter){
		return groupMapper.findAll(sorter);
	}
	/**
	 * 通过主键查询单个Group
	 * @param 
	 * @return
	 */
	@Override
	public Group findById(Long id){
		return groupMapper.findById(id);
	}
	/**
	 * 保存Group
	 * @param group
	 */
	@Override
	@Transactional
	public void add(Group group){
		//待第三方接口调试完成之后，此处需要调用创建分组接口,成功之后存入数据库当中
		//默认一天24小时，周一-周日
		group.setDayBegintime(Const.DAY_BEGIN_TIME);
		group.setDayEndtime(Const.DAY_END_TIME);
		group.setWeekdays(Const.LOCAL_WEEKDAYS);
		group.setCreateTime(new Date());
		//默认启用状态
		group.setStatus(1);
		groupMapper.add(group);
	}
	/**
	 * 更新Group
	 * @param group
	 */
	@Override
	@Transactional
	public void update(Group group){
		group.setDayBegintime(Const.DAY_BEGIN_TIME);
		group.setDayEndtime(Const.DAY_END_TIME);
		group.setWeekdays(Const.LOCAL_WEEKDAYS);
		groupMapper.update(group);
	}

	@Override
	@Transactional
	public void delete(Long id) {
		groupMapper.delete(id);
	}

	@Override
	public BaseVo findAllPage(GroupVO groupVO) {
		//1.根据地址code查询地址父集或者子集
		Address address = new Address();
		List<String> list = new ArrayList();
		if (!"".equals(groupVO.getAddressCode()) && groupVO.getAddressCode() != null){
			address.setAddressCode(groupVO.getAddressCode());
			List<Address> addressList = addressMapper.findAllByAddressCode(address);
			for (Address address1 : addressList) {
				list.add(address1.getAddressCode());
			}
		}
		//2.处理地址code，，号分割
		Map<String,Object> map = new HashMap<>();
		map.put("addressCodeList",list);
		//3.传给mapper
		PageHelper.startPage(groupVO.getPageNum(), groupVO.getPageSize());
		groupVO.setOrderBy("create_time desc");
		//判断分组名是否为空，不为空则加%，模糊查询
		if (groupVO.getName() != null && !"".equals(groupVO.getName())){
			String name = groupVO.getName() + "%";
			groupVO.setName(name);
		}
		//判断备注是否为空，不为空则加%，模糊查询
		if (groupVO.getRemarks() != null && !"".equals(groupVO.getRemarks())){
			String remarks = groupVO.getRemarks() + "%";
			groupVO.setRemarks(remarks);
		}
		map.put("vo",groupVO);
		//获取当前登录人员的信息
		UserInfo userInfo = getCurrentUserInfo();
		UserAddress userAddress = userInfo.getCurrentUserAddresses();
		if (userAddress!=null){
			String addressCode = userAddress.getAddressCode();
			if (StringUtil.isNotEmpty(addressCode)){
				String str = userInfo.getCurrentUserAddresses().getAddressCode();
				str=str+"%";
				map.put("addressCode",str);
			}
		}

		List<Group> pageList = groupMapper.findAllPage(map);
		PageInfo<Group> pageInfo = new PageInfo<>(pageList);
		BaseVo baseVo = new BaseVo();
		baseVo.setData(pageInfo);
		return baseVo;
	}

	@Override
	public BaseVo findAllByStatus() {
		return null;
	}

    @Override
    public BaseVo findAllByName(String name) {
        GroupVO groupVO = new GroupVO();

        UserInfo userInfo = getCurrentUserInfo();
        UserAddress userAddress = userInfo.getCurrentUserAddresses();
        if (userAddress!=null){
            String addressCode = userAddress.getAddressCode()+'%';
            groupVO.setAddressCode(addressCode);
        }
        groupVO.setName(name+'%');

        List<Group> groupList = groupMapper.findAllByParam(groupVO);
        BaseVo baseVo = new BaseVo();
        baseVo.setData(groupList);
        return baseVo;
    }
}
