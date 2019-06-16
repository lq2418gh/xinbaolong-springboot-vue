package com.bit.sc.module.equipment.service.impl;

import com.bit.base.exception.BusinessException;
import com.bit.base.vo.BaseVo;
import com.bit.sc.common.Const;
import com.bit.sc.module.equipment.dao.EquipmentMapper;
import com.bit.sc.module.equipment.dao.EquipmentModelNumberMapper;
import com.bit.sc.module.equipment.dao.EquipmentTypeMapper;
import com.bit.sc.module.equipment.pojo.Equipment;
import com.bit.sc.module.equipment.pojo.EquipmentModelNumber;
import com.bit.sc.module.equipment.pojo.EquipmentType;
import com.bit.sc.module.equipment.service.EquipmentService;
import com.bit.sc.module.equipment.service.EquipmentThirdInterfaceService;
import com.bit.sc.module.equipment.vo.EquipmentAuthorizeVO;
import com.bit.sc.module.group.dao.GroupMapper;
import com.bit.sc.module.group.dao.GroupRelMapper;
import com.bit.sc.module.group.pojo.Group;
import com.bit.sc.module.group.pojo.GroupRel;
import com.bit.sc.module.restTemplate.dto.DeviceDTO;
import com.bit.sc.module.restTemplate.dto.DeviceVO;
import com.bit.sc.module.restTemplate.dto.PasswordDTO;
import com.bit.sc.module.restTemplate.service.ThirdPartyInterfaceService;
import com.bit.sc.module.sys.pojo.Address;
import com.bit.sc.module.sys.service.AddressService;
import com.bit.sc.utils.AddressUtil;
import com.bit.sc.utils.AuthKeyUtil;
import com.bit.utils.UUIDUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @author liuyancheng
 * @create 2018-12-13 13:06
 */
@Service("EquipmentThirdInterfaceService")
public class EquipmentThirdInterfaceServiceImpl implements EquipmentThirdInterfaceService {

    private static final Logger logger = LoggerFactory.getLogger(EquipmentThirdInterfaceService.class);

    @Autowired
    private ThirdPartyInterfaceService thirdPartyInterfaceService;
    @Autowired
    private EquipmentService equipmentService;
    @Autowired
    private AddressService addressService;
    @Autowired
    private GroupRelMapper groupRelMapper;
    @Autowired
    private EquipmentModelNumberMapper equipmentModelNumberMapper;
    @Autowired
    private EquipmentTypeMapper equipmentTypeMapper;
    @Autowired
    private EquipmentMapper equipmentMapper;
    @Autowired
    private GroupMapper groupMapper;

    @Override
    public BaseVo addEquipment(Equipment equipment) {

        DeviceDTO deviceDTO = new DeviceDTO();
        //设备id
        deviceDTO.setDeviceId(UUIDUtil.getUUID());
        //设备序列号
        if (equipment.getEquipmentCode() != null){
            deviceDTO.setDeviceCode(equipment.getEquipmentCode());
        }
        //设备名
        deviceDTO.setDeviceName(equipment.getEquipmentName());
        //设备类别0：facepad 1：faceball
        //通过设备型号查询型号id
        EquipmentModelNumber equipmentNumber = equipmentModelNumberMapper.findById(equipment.getEquipmentModelNumberId());
        if (equipmentNumber != null){
            equipment.setEquipmentModelNumberId(equipmentNumber.getId());
            equipment.setEquipmentType(equipmentNumber.getEquipmentType());
            //通过设备型号type查询设备类型表中的值
            EquipmentType equipmentType = equipmentTypeMapper.findById(Long.valueOf(equipmentNumber.getEquipmentType()));
            deviceDTO.setDeviceType(equipmentType.getTypeNum());
        }
        //部署场所
        //通过code查询addressDetail
        Address address = addressService.findByAddressCode(equipment.getAddressCode());
        //替换地址详情中所有特殊符号,否则云平台接口报错
        String addressDetail = AddressUtil.replaceAll(address.getAddressDetail());
        deviceDTO.setPosition(addressDetail);
        //处理addressCode，得出顶级小区code
        String sub= AddressUtil.sub(equipment.getAddressCode());
        //authKey
        Address topAdress = addressService.findByAddressCode(sub);
        //orgId
        deviceDTO.setOrgId(topAdress.getOrgId());
        //备注
        deviceDTO.setMemo(equipment.getRemarks());
        try {
            DeviceDTO device = thirdPartyInterfaceService.createDevice(deviceDTO);
            if (device != null){
                equipment.setEquipmentId(device.getDeviceId());
            }
            //第三方接口返回成功，调用自己的接口存库
            equipmentService.add(equipment);
            BaseVo baseVo = new BaseVo();
            return baseVo;
        }catch (Exception e){
            e.printStackTrace();
            throw new BusinessException(e.getMessage());
        }
    }

    @Override
    public BaseVo updateEquipment(Equipment equipment) {

        DeviceDTO deviceDTO = new DeviceDTO();
        //设备id
        deviceDTO.setDeviceId(equipment.getEquipmentId());
        //设备名
        deviceDTO.setDeviceName(equipment.getEquipmentName());
        //部署场所
        if (equipment.getAddressCode() != null && !"".equals(equipment.getAddressCode())){
            //通过code查询addressDetail
            Address address = addressService.findByAddressCode(equipment.getAddressCode());
            //替换地址详情中所有特殊符号,否则云平台接口报错
            String addressDetail = AddressUtil.replaceAll(address.getAddressDetail());
            deviceDTO.setPosition(addressDetail);
            //处理addressCode，得出顶级小区code
            Address topAdress = addressService.findTopBySubset(equipment.getAddressCode());
            //orgId
            deviceDTO.setOrgId(topAdress.getOrgId());
        }
        //备注
        deviceDTO.setMemo(equipment.getRemarks());
        //通过设备型号查询型号id
        EquipmentModelNumber equipmentNumber = equipmentModelNumberMapper.findById(equipment.getEquipmentModelNumberId());
        if (equipmentNumber != null){
            equipment.setEquipmentModelNumberId(equipmentNumber.getId());
            equipment.setEquipmentType(equipmentNumber.getEquipmentType());
            //通过设备型号type查询设备类型表中的值
            EquipmentType equipmentType = equipmentTypeMapper.findById(Long.valueOf(equipmentNumber.getEquipmentType()));
            deviceDTO.setDeviceType(equipmentType.getTypeNum());
        }

        try {
            thirdPartyInterfaceService.updateDevice(deviceDTO);
            //第三方接口返回成功，调用自己的接口存库
            equipmentService.update(equipment);
            BaseVo baseVo = new BaseVo();
            return baseVo;
        }catch (Exception e){
            throw new BusinessException(e.getMessage());
        }
    }

    @Override
    public BaseVo deleteUpdate(Long id) {
        //判断该设备是否在分组中
        //根据设备主键id查询设备code
        Equipment equipment = equipmentMapper.findById(id);
        //存的是设备云平台id
        List<String> deviceIdList = new ArrayList<>();
        deviceIdList.add(equipment.getEquipmentId());
        //查询分组中间表,是否存在分组数据
        GroupRel groupRel = new GroupRel();
        groupRel.setRelId(id);
        groupRel.setType(Const.GROUP_REL_EQUIPMENT_TYPE);
        List<GroupRel> groupRelList = groupRelMapper.findByParam(groupRel);
        if (groupRelList != null && groupRelList.size() > 0){
            for (GroupRel rel : groupRelList) {
                //遍历，把设备移除分组
                //查询分组code
                Group group = groupMapper.findById(rel.getGroupId());
                DeviceVO deviceVO = new DeviceVO();
                deviceVO.setGroupId(group.getGroupCode());
                deviceVO.setDeviceIdList(deviceIdList);
                //地址
                if (equipment.getAddressCode() != null && !"".equals(equipment.getAddressCode())){
                    //处理addressCode，得出顶级小区code
                    Address topAdress = addressService.findTopBySubset(equipment.getAddressCode());
                    //orgId
                    deviceVO.setOrgId(topAdress.getOrgId());
                }
                //调用云平台接口移除分组
                try {
                    thirdPartyInterfaceService.unBindDevicesPermission(deviceVO);
                    groupRelMapper.delete(rel.getId());
                }catch (Exception e){
                    throw new BusinessException(e.getMessage());
                }
            }
        }
        DeviceDTO deviceDTO = new DeviceDTO();
        //设备idList
        deviceDTO.setDeviceIdList(deviceIdList);
        try {
            thirdPartyInterfaceService.deleteDevice(deviceDTO);
            //第三方接口返回成功，调用自己的接口存库
            equipmentService.delete(id);
            BaseVo baseVo = new BaseVo();
            return baseVo;
        }catch (Exception e){
            throw new BusinessException(e.getMessage());
        }
    }

    @Override
    public BaseVo bindDevices(DeviceVO deviceVO) {
        //设备id
        //组code List
        List<String> groupIdList = new ArrayList<>();
        //只存组id
        List<Long> groupIdNewList = new ArrayList<>();
        //组ID , 分割拆分
        String groupId = deviceVO.getGroupId();
        if (!"".equals(groupId)){
            String[] split = groupId.split(",");
            for (String s : split) {
                groupIdNewList.add(Long.valueOf(s));
            }
            for (Long s : groupIdNewList) {
                Group group = groupMapper.findById(Long.valueOf(s));
                groupIdList.add(group.getGroupCode());
            }
            //入参 groupCode
            deviceVO.setGroupIdList(groupIdList);
        }
        //通过relId查询设备对象,拿到主键id
        Equipment equipment = equipmentMapper.findById(Long.valueOf(deviceVO.getDeviceId()));
        deviceVO.setDeviceId(equipment.getEquipmentId());
        //地址
        if (equipment.getAddressCode() != null && !"".equals(equipment.getAddressCode())){
            //处理addressCode，得出顶级小区code
            Address topAddress = addressService.findTopBySubset(equipment.getAddressCode());
            //orgId
            deviceVO.setOrgId(topAddress.getOrgId());
        }
        //由于授权和取消授权是一个按钮，所以要先去中间表查询是否有数据
        GroupRel groupRelParam = new GroupRel();
        groupRelParam.setRelId(equipment.getId());
        groupRelParam.setType(Const.GROUP_REL_TYPE_DEVICE);
        //根据relid和type查询分组中间表列表
        List<GroupRel> groupRelList = groupRelMapper.findByParam(groupRelParam);
        // 通过中间表中的groupId查询数据库中groupCode
        List<String> groupCodeListOld = new ArrayList<>();
        //通过中间表中的groupId查询数据库中group表中的主键id
        List<Long> groupIdListOld = new ArrayList<>();
        for (GroupRel groupRel : groupRelList) {
            Group group = groupMapper.findById(groupRel.getGroupId());
            groupCodeListOld.add(group.getGroupCode());
            groupIdListOld.add(group.getId());
        }
        //库中数据为空，表示新增
        if (groupRelList.size() == 0){
            try {
                thirdPartyInterfaceService.bindGroupsPermission(deviceVO);
                //调用分组中间表，插入数据
                for (Long str : groupIdNewList) {
                    GroupRel groupRel = new GroupRel();
                    groupRel.setGroupId(str);
                    //type 0人,1设备
                    groupRel.setType(Const.GROUP_REL_TYPE_DEVICE);
                    //设备表主键id
                    groupRel.setRelId(equipment.getId());
                    groupRelMapper.add(groupRel);
                }
                BaseVo baseVo = new BaseVo();
                return baseVo;
            }catch (Exception e){
                throw new BusinessException(e.getMessage());
            }
        }else if (groupIdNewList.size() == 0 && groupRelList.size() > 0){
            try {
                deviceVO.setGroupIdList(groupCodeListOld);
                thirdPartyInterfaceService.unBindGroupsPermission(deviceVO);
                //调用分组中间表，插入数据
                List<GroupRel> groupRelList1 = new ArrayList<>();
                for (Long relId : groupIdListOld) {
                    GroupRel groupRel = new GroupRel();
                    groupRel.setGroupId(relId);
                    //type 0人,1设备
                    groupRel.setType(Const.GROUP_REL_TYPE_DEVICE);
                    //设备表主键id
                    groupRel.setRelId(equipment.getId());
                    groupRelList1.add(groupRel);
                }
                groupRelMapper.delByParam(groupRelList);
                BaseVo baseVo = new BaseVo();
                return baseVo;
            }catch (Exception e){
                throw new BusinessException(e.getMessage());
            }
        }else if (groupRelList.size() > groupIdList.size()){
            //库中数据大于传进来的id，表示修改
            //遍历走接口移出分组
            try {
                //移除先有分组
                deviceVO.setGroupIdList(groupCodeListOld);
                thirdPartyInterfaceService.unBindGroupsPermission(deviceVO);
                List<GroupRel> groupRelBeanList = new ArrayList<>();
                for (Long l : groupIdListOld) {
                    GroupRel groupRel = new GroupRel();
                    groupRel.setGroupId(l);
                    //type 0人,1设备
                    groupRel.setType(Const.GROUP_REL_TYPE_DEVICE);
                    //设备表主键id
                    groupRel.setRelId(equipment.getId());
                    groupRelBeanList.add(groupRel);
                }
                //中间表删除
                groupRelMapper.delByParam(groupRelList);
                //重新添加分组
                deviceVO.setGroupIdList(groupIdList);
                thirdPartyInterfaceService.bindGroupsPermission(deviceVO);
                //调用分组中间表，插入数据
                for (Long str : groupIdNewList) {
                    GroupRel groupRel = new GroupRel();
                    groupRel.setGroupId(str);
                    //type 0人,1设备
                    groupRel.setType(Const.GROUP_REL_TYPE_DEVICE);
                    //设备表主键id
                    groupRel.setRelId(equipment.getId());
                    groupRelMapper.add(groupRel);
                }
                BaseVo baseVo = new BaseVo();
                return baseVo;
            }catch (Exception e){
                throw new BusinessException(e.getMessage());
            }
        }else if (groupRelList.size() < groupIdList.size()){
            //查出来小于新的，新增
            //取差集
            groupIdNewList.removeAll(groupIdListOld);
            try {
                deviceVO.setGroupIdList(groupIdList);
                thirdPartyInterfaceService.bindGroupsPermission(deviceVO);
                //调用分组中间表，插入数据
                for (Long str : groupIdNewList) {
                    GroupRel groupRel = new GroupRel();
                    groupRel.setGroupId(str);
                    //type 0人,1设备
                    groupRel.setType(Const.GROUP_REL_TYPE_DEVICE);
                    //设备表主键id
                    groupRel.setRelId(equipment.getId());
                    groupRelMapper.add(groupRel);
                }
                BaseVo baseVo = new BaseVo();
                return baseVo;
            }catch (Exception e){
                throw new BusinessException(e.getMessage());
            }
        }
        return null;
    }

    @Override
    public BaseVo bindDevicesToGroup(EquipmentAuthorizeVO equipmentAuthorizeVO) {
        //入参groupId
        Long groupId = equipmentAuthorizeVO.getGroupId();

        //入参设备id集合
        List<Long> deviceIds = equipmentAuthorizeVO.getDeviceIds();

        //云平台接口参数
        DeviceVO deviceVO = new DeviceVO();
        Group group = groupMapper.findById(groupId);
        deviceVO.setGroupId(group.getGroupCode());
        //存储deviceIdList
        List<String> deviceIdList = new ArrayList<>();

        //遍历设备id，查询中间表中有无授权数据
        for (Long deviceId : deviceIds) {
            GroupRel groupRel = new GroupRel();
            groupRel.setType(Const.GROUP_REL_EQUIPMENT_TYPE);
            groupRel.setRelId(deviceId);
            groupRel.setGroupId(groupId);
            GroupRel rel = groupRelMapper.findByIdAndType(groupRel);
            // 如果有，不做操作，如果没有则新增
            if (rel != null){
                //跳出本次循环，进入下次循环
                continue;
            }else {
                //新增操作
                //1.新增云平台
                //需要groupCode和deviceCode
                Equipment equipment = equipmentMapper.findById(deviceId);
                deviceIdList.add(equipment.getEquipmentId());
                //地址
                if (equipment.getAddressCode() != null && !"".equals(equipment.getAddressCode())){
                    //处理addressCode，得出顶级小区code
                    Address topAdress = addressService.findTopBySubset(equipment.getAddressCode());
                    //orgId
                    deviceVO.setOrgId(topAdress.getOrgId());
                }
                //调用云平台接口添加
                deviceVO.setDeviceIdList(deviceIdList);
                try {
                    thirdPartyInterfaceService.bindDevicesPermission(deviceVO);
                    //存入中间表
                    GroupRel groupRelParam = new GroupRel();
                    groupRelParam.setGroupId(groupId);
                    groupRelParam.setType(Const.GROUP_REL_EQUIPMENT_TYPE);
                    groupRelParam.setRelId(deviceId);
                    groupRelMapper.add(groupRelParam);
                }catch (Exception e){
                    throw new BusinessException(e.getMessage());
                }
            }
        }
        BaseVo baseVo = new BaseVo();
        return baseVo;
    }

    @Override
    public BaseVo unBindDevicesToGroup(EquipmentAuthorizeVO equipmentAuthorizeVO) {

        //入参groupId
        Long groupId = equipmentAuthorizeVO.getGroupId();

        //入参设备id集合
        List<Long> deviceIds = equipmentAuthorizeVO.getDeviceIds();

        //云平台接口参数
        DeviceVO deviceVO = new DeviceVO();
        Group group = groupMapper.findById(groupId);
        deviceVO.setGroupId(group.getGroupCode());
        //存储deviceIdList
        List<String> deviceIdList = new ArrayList<>();

        //遍历设备id，查询中间表中有无授权数据
        for (Long deviceId : deviceIds) {
            GroupRel groupRel = new GroupRel();
            groupRel.setType(Const.GROUP_REL_EQUIPMENT_TYPE);
            groupRel.setRelId(deviceId);
            groupRel.setGroupId(groupId);
            GroupRel rel = groupRelMapper.findByIdAndType(groupRel);

            //如果有，执行删除操作，如果没有，不做操作
            if (rel == null){
                continue;
            }else {
                //删除操作
                //1.云平台删除接口
                //需要groupCode和deviceCode
                Equipment equipment = equipmentMapper.findById(deviceId);
                deviceIdList.add(equipment.getEquipmentId());
                //地址
                if (equipment.getAddressCode() != null && !"".equals(equipment.getAddressCode())){
                    //处理addressCode，得出顶级小区code
                    Address topAdress = addressService.findTopBySubset(equipment.getAddressCode());
                    //orgId
                    deviceVO.setOrgId(topAdress.getOrgId());
                }
                //调用云平台接口添加
                deviceVO.setDeviceIdList(deviceIdList);
                try {
                    thirdPartyInterfaceService.unBindDevicesPermission(deviceVO);
                    //存入中间表
                    groupRelMapper.delete(rel.getId());
                }catch (Exception e){
                    throw new BusinessException(e.getMessage());
                }
            }
        }
        BaseVo baseVo = new BaseVo();
        return baseVo;
    }

    @Override
    public BaseVo blockUp(Equipment equipment) {
        //1.判断是启用还是停用
        if (equipment.getEquipmentStatus().equals(1)){
            //1为启用
            Equipment byId = equipmentMapper.findById(equipment.getId());
            DeviceDTO deviceDTO = new DeviceDTO();
            deviceDTO.setDeviceId(byId.getEquipmentId());
            deviceDTO.setDeviceCode(byId.getEquipmentCode());
            deviceDTO.setDeviceName(byId.getEquipmentName());
            //通过设备型号查询型号id
            EquipmentModelNumber equipmentNumber = equipmentModelNumberMapper.findById(byId.getEquipmentModelNumberId());
            if (equipmentNumber != null){
                //通过设备型号type查询设备类型表中的值
                EquipmentType equipmentType = equipmentTypeMapper.findById(Long.valueOf(equipmentNumber.getEquipmentType()));
                deviceDTO.setDeviceType(equipmentType.getTypeNum());
            }
            try {
                thirdPartyInterfaceService.createDevice(deviceDTO);
                equipmentMapper.update(equipment);
                BaseVo baseVo = new BaseVo();
                return baseVo;
            }catch (Exception e){
                throw new BusinessException(e.getMessage());
            }
        }else {
            //0 停用 需要传参为：id，equipmentStatus，equipmentId
            DeviceDTO deviceDTO = new DeviceDTO();
            List<String> deviceIdList = new ArrayList<>();
            deviceIdList.add(equipment.getEquipmentId());
            try {
                thirdPartyInterfaceService.deleteDevice(deviceDTO);
                equipmentMapper.update(equipment);
                BaseVo baseVo = new BaseVo();
                return baseVo;
            }catch (Exception e){
                throw new BusinessException(e.getMessage());
            }
        }
    }

}
