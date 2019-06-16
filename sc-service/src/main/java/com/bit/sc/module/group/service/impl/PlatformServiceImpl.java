package com.bit.sc.module.group.service.impl;

import com.bit.sc.common.Const;
import com.bit.sc.module.group.pojo.Group;
import com.bit.sc.module.group.service.GroupService;
import com.bit.sc.module.group.service.PlatformService;
import com.bit.sc.module.restTemplate.dto.*;
import com.bit.sc.module.restTemplate.service.ThirdPartyInterfaceService;
import com.bit.sc.module.sys.pojo.Address;
import com.bit.sc.module.sys.service.AddressService;
import com.bit.sc.utils.AddressUtil;
import com.bit.utils.UUIDUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * 调第三方接口的Service实现类
 * @author zhangjie
 * @date 2018-12-12
 */
@Service("platformService")
public class PlatformServiceImpl implements PlatformService {
    @Autowired
    private GroupService groupService;
    @Autowired
    private ThirdPartyInterfaceService thirdPartyInterfaceService;
    @Autowired
    private AddressService addressService;



    /**
     * 新增组
     */
    @Override
    public void addGroup(Group group) {
        //todo 调第三方接口新增组
        GroupDTO groupDTO = new GroupDTO();
        groupDTO.setGroupName(group.getName());
        groupDTO.setDayBeginTime(Const.DAY_BEGIN_TIME);
        groupDTO.setDayEndTime(Const.DAY_END_TIME);
        groupDTO.setWeekdays(Const.WEEKDAYS);
        String groupId = UUIDUtil.getUUID();
        groupDTO.setGroupId(groupId);

        String str = AddressUtil.sub(group.getAddressCode());
        Address address = addressService.findByAddressCode(str);
        groupDTO.setOrgId(address.getOrgId());
        GroupDTO resultGroup = thirdPartyInterfaceService.createGroup(groupDTO);
//        String groupId = resultGroup.getGroupId();

        //第三方接口调用成功后，将返回的groupId(String)入自己库中
        group.setGroupCode(groupId);
        groupService.add(group);
    }

    /**
     * 更新组
     * @param group
     */
    @Override
    public void updateGroup(Group group) {
        //todo 调第三方接口更新组
        GroupDTO groupDTO = new GroupDTO();
        groupDTO.setGroupId(group.getGroupCode());
        groupDTO.setGroupName(group.getName());
        groupDTO.setDayBeginTime(Const.DAY_BEGIN_TIME);
        groupDTO.setDayEndTime(Const.DAY_END_TIME);
        groupDTO.setWeekdays(Const.WEEKDAYS);
        String str = AddressUtil.sub(group.getAddressCode());
        Address address = addressService.findByAddressCode(str);
        groupDTO.setOrgId(address.getOrgId());
        GroupDTO resultGroup = thirdPartyInterfaceService.updateGroup(groupDTO);

        //更新自己库数据
        groupService.update(group);
    }

    /**
     * 停用组
     * @param group
     */
    @Override
    public void deleteGroup(Group group) {
        //todo  调第三方接口删除组
        GroupDTO groupDTO = new GroupDTO();
        List<String> groupList = new ArrayList<>();
        groupList.add(group.getGroupCode());
        groupDTO.setGroupIdList(groupList);
        String str = AddressUtil.sub(group.getAddressCode());
        Address address = addressService.findByAddressCode(str);
        groupDTO.setOrgId(address.getOrgId());
        GroupDTO resultGroup = thirdPartyInterfaceService.deleteGroup(groupDTO);

        groupService.update(group);
    }

    /**
     * 启用组
     * @param group
     */
    @Override
    public void startGroup(Group group) {
        //todo  调第三方接口新增组
        GroupDTO groupDTO = new GroupDTO();
        groupDTO.setGroupName(group.getName());
        groupDTO.setDayBeginTime(Const.DAY_BEGIN_TIME);
        groupDTO.setDayEndTime(Const.DAY_END_TIME);
        groupDTO.setWeekdays(Const.WEEKDAYS);
        String str = AddressUtil.sub(group.getAddressCode());
        Address address = addressService.findByAddressCode(str);
        groupDTO.setOrgId(address.getOrgId());
        GroupDTO resultGroup = thirdPartyInterfaceService.createGroup(groupDTO);
        String groupId = resultGroup.getGroupId();

        group.setGroupCode(groupId);
        groupService.update(group);
    }
}
