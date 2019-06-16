package com.bit.sc.module.restTemplate.service;


import com.bit.sc.module.restTemplate.dto.*;
import com.bit.sc.module.restTemplate.vo.ResultDTO;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;

/**
 * @author liuyancheng
 * @create 2018-12-04 9:17
 * 第三方接口服务组件
 */
public interface ThirdPartyInterfaceService {

    //----------------- 分组开始 -------------------
    /**
     * 创建分组
     * @return
     */
    GroupDTO createGroup(GroupDTO groupDTO);

    /**
     * 更新分组
     * @return
     */
    GroupDTO updateGroup(GroupDTO groupDTO);

    /**
     * 删除分组（逻辑删除）
     * @param groupDTO
     * @return
     */
    GroupDTO deleteGroup(GroupDTO groupDTO);

    /**
     * 分组的配置更新
     * 有则更新，无则添加。不设置个别设置则适用默认配置。 每日启用时间和每日结束时间是空值（""）的情况，适用默认配置。 工作天数是空数组的情况，适用默认配置。
     * @param groupDTO
     * @return
     */
    GroupDTO configGroup(GroupDTO groupDTO);
    //----------------- 分组结束 -------------------

    //----------------- 人员开始 -------------------

    /**
     * 添加人员
     * @param memberDTO
     * @return
     */
    MemberDTO createMember(MemberDTO memberDTO);

    /**
     * 更新人员
     * @param memberDTO
     * @return
     */
    MemberDTO updateMember(MemberDTO memberDTO);

    /**
     * 删除人员（逻辑删除）
     * @param memberVO
     * @return
     */
    MemberVO deleteMember(MemberVO memberVO);

    /**
     * 批量导入人员照片
     * @param memberVO
     * @return
     */
    MemberVO batchPicMember(MemberVO memberVO);

    /**
     * 增加人员分组
     * @param memberVO
     * @return
     */
    MemberVO addMemberGroup(MemberVO memberVO);

    /**
     * 移除人员分组
     * @param memberVO
     * @return
     */
    MemberVO deleteMemberGroup(MemberVO memberVO);

    /**
     * 增加/删除人员分组（通过标签）
     * @param memberVO
     * @return
     */
    MemberVO addOrDelMemberGroupByTag(MemberVO memberVO);

    /**
     * 清空人员（逻辑删除）
     * @return
     */
    ResultDTO deleteMemberAll();

    /**
     * 获取人员下发状态
     * @param memberVO
     * @return
     */
    MemberVO getDeviceMemberStatus(MemberVO memberVO);
    //----------------- 人员结束 -------------------

    //----------------- 设备开始 -------------------

    /**
     * 添加设备
     * @param deviceDTO
     * @return
     */
    DeviceDTO createDevice(DeviceDTO deviceDTO);

    /**
     * 更新设备
     * @param deviceDTO
     * @return
     */
    DeviceDTO updateDevice(DeviceDTO deviceDTO);

    /**
     * 移除设备
     * @param deviceDTO
     * @return
     */
    DeviceDTO deleteDevice(DeviceDTO deviceDTO);

    /**
     * 获取设备配置（含配置分类信息）
     * @param deviceId
     * @param orgId  组织Id
     * @return
     */
    ResultDTO getDeviceConfigList(String deviceId,String orgId);

    /**
     * 设备上传配置信息
     * @param deviceVO
     * @return
     */
    DeviceVO updateUploadDeviceConf(DeviceVO deviceVO);

    /**
     * 远程控制设备
     * @param deviceVO
     * @return
     */
    DeviceVO remoteCtrlDevice(DeviceVO deviceVO);

    /**
     * 设置分组权限（添加设备到分组）
     * @param deviceVO
     * @return
     */
    DeviceVO bindDevicesPermission(DeviceVO deviceVO);

    /**
     * 设置分组权限（移除设备到分组）
     * @param deviceVO
     * @return
     */
    DeviceVO unBindDevicesPermission(DeviceVO deviceVO);

    /**
     * 设置设备权限（添加分组到设备）
     * @param deviceVO
     * @return
     */
    DeviceVO bindGroupsPermission(DeviceVO deviceVO);

    /**
     * 设置设备权限（移除分组到设备）
     * @param deviceVO
     * @return
     */
    DeviceVO unBindGroupsPermission(DeviceVO deviceVO);

    /**
     * 修改设备固定密码
     * @param passwordDTO
     * @return
     */
    PasswordDTO setDeviceAccessPasswd(PasswordDTO passwordDTO);

    //----------------- 设备结束 -------------------

    /**
     * 文件上传 返回fileId
     *  此方法废弃
     * @return
     */
    FileDTO uploadFile(MultipartFile multipartFile);

    /**
     * 文件上传 返回
     * @param multipartFile  文件
     * @param headersParams  附加的条件 orgId
     * @return
     */
    FileDTO uploadFileNew(MultipartFile multipartFile,Map<String,Object> headersParams);

    /**
     * 注册回调
     * @param callBackDTO
     * @return
     */
    CallBackDTO createCallBack(CallBackDTO callBackDTO);

    /**
     * 修改回调
     * @param callBackDTO
     * @return
     */
    CallBackDTO updateCallBack(CallBackDTO callBackDTO);
    /**
     * 删除回调
     * @param callBackDTO
     * @return
     */
    CallBackDTO deleteCallBack(CallBackDTO callBackDTO);

    //----------------- 组织机构开始 -------------------

    /**
     * 创建组织机构
     * @param orgDTO
     * @return
     */
    OrgDTO createOrg(OrgDTO orgDTO);

    /**
     * 修改组织机构
     * @param orgDTO
     * @return
     */
    OrgDTO updateOrg(OrgDTO orgDTO);

    /**
     * 删除组织机构（逻辑删除）
     * @param orgDTO
     * @return
     */
    OrgDTO deleteOrg(OrgDTO orgDTO);

/*    *//**
     * 删除组织机构（逻辑删除）
     * @param orgId
     * @return
     *//*
    OrgDTO deleteByOrgId(String orgId);*/
    //----------------- 组织机构结束 -------------------
}
