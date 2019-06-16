package com.bit.sc.module.restTemplate.service.impl;

import com.bit.base.exception.BusinessException;
import com.bit.sc.common.FaceCloudConstants;
import com.bit.sc.module.restTemplate.dto.*;
import com.bit.sc.module.restTemplate.service.RestTemplateService;
import com.bit.sc.module.restTemplate.service.ThirdPartyInterfaceService;
import com.bit.sc.module.restTemplate.vo.ResultDTO;
import com.bit.sc.utils.BeanUtils;
import com.bit.sc.utils.YmlUtil;
import org.apache.logging.log4j.util.Strings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author liuyancheng
 * @create 2018-12-04 9:51
 * 第三方接口实现
 */
@Service("ThirdPartyInterfaceService")
public class ThirdPartyInterfaceServiceImpl implements ThirdPartyInterfaceService {

    @Autowired
    private  RestTemplateService restTemplateService;
    @Value("${facestar.address}")
    private String address;

    @Override
    public GroupDTO createGroup(GroupDTO groupDTO) {
        //拼请求地址
        String url = address + "/api/v2/group/create";
        Map<String,Object> map = new HashMap<>();
        map.put("obj", groupDTO);
        setOrgId(groupDTO,map);
        GroupDTO resultGroup = restTemplateService.sendPostRequest(url, map, GroupDTO.class);
        return resultGroup;
    }

    @Override
    public GroupDTO updateGroup(GroupDTO groupDTO) {
        //拼请求地址
        String url = address + "/api/v2/group/update";
        Map<String,Object> map = new HashMap<>();
        map.put("obj", groupDTO);
        setOrgId(groupDTO,map);
        GroupDTO resultGroup = restTemplateService.sendPostRequest(url, map, GroupDTO.class);
        return resultGroup;
    }

    @Override
    public GroupDTO deleteGroup(GroupDTO groupDTO) {
        //拼请求地址
        String url = address + "/api/v2/group/delete";
        Map<String,Object> map = new HashMap<>();
        map.put("obj", groupDTO);
        setOrgId(groupDTO,map);
        GroupDTO resultGroup = restTemplateService.sendPostRequest(url, map, GroupDTO.class);
        return resultGroup;
    }

    @Override
    public GroupDTO configGroup(GroupDTO groupDTO) {
        //拼请求地址
        String url = address + "/api/v2/group/config";
        Map<String,Object> map = new HashMap<>();
        map.put("obj", groupDTO);
        setOrgId(groupDTO,map);
        GroupDTO resultGroup = restTemplateService.sendPostRequest(url, map, GroupDTO.class);
        return resultGroup;
    }

    @Override
    public MemberDTO createMember(MemberDTO memberDTO) {
        //拼请求地址
        String url = address + "/api/v2/member/create";
        Map<String,Object> map = new HashMap<>();
        map.put("obj", memberDTO);
        setOrgId(memberDTO,map);
        MemberDTO resultMember = restTemplateService.sendPostRequest(url, map, MemberDTO.class);
        return resultMember;
    }

    @Override
    public MemberDTO updateMember(MemberDTO memberDTO) {
        //拼请求地址
        String url = address + "/api/v2/member/update";
        Map<String,Object> map = new HashMap<>();
        map.put("obj", memberDTO);
        setOrgId(memberDTO,map);
       /* map.put("secretKey","");
        map.put("deviceCode","");*/
        MemberDTO resultMember = restTemplateService.sendPostRequest(url, map, MemberDTO.class);
        return resultMember;
    }

    @Override
    public MemberVO deleteMember(MemberVO memberVO) {
        //拼请求地址
        String url = address + "/api/v2/member/delete";
        Map<String,Object> map = new HashMap<>();
        map.put("obj",memberVO);
        setOrgId(memberVO,map);
        MemberVO resultMember = restTemplateService.sendPostRequest(url, map, MemberVO.class);
        return resultMember;
    }

    @Override
    public MemberVO batchPicMember(MemberVO memberVO) {
        //拼请求地址
        String url = address + "/api/v2/member/batch";
        Map<String,Object> map = new HashMap<>();
        map.put("obj",memberVO);
        setOrgId(memberVO,map);
        MemberVO resultMember = restTemplateService.sendPostRequest(url, map, MemberVO.class);
        return resultMember;
    }

    @Override
    public MemberVO addMemberGroup(MemberVO memberVO) {
        //拼请求地址
        String url = address + "/api/v2/member/bind_group";
        Map<String,Object> map = new HashMap<>();
        map.put("obj",memberVO);
        setOrgId(memberVO,map);
        MemberVO resultMember = restTemplateService.sendPostRequest(url, map, MemberVO.class);
        return resultMember;
    }

    @Override
    public MemberVO deleteMemberGroup(MemberVO memberVO) {
        //拼请求地址
        String url = address + "/api/v2/member/unbind_group";
        Map<String,Object> map = new HashMap<>();
        map.put("obj",memberVO);
        setOrgId(memberVO,map);
        MemberVO resultMember = restTemplateService.sendPostRequest(url, map,MemberVO.class);
        return resultMember;
    }

    @Override
    public MemberVO addOrDelMemberGroupByTag(MemberVO memberVO) {
        //拼请求地址
        String url = address + "/api/v2/member/bind_group_by_tag";
        Map<String,Object> map = new HashMap<>();
        map.put("obj",memberVO);
        setOrgId(memberVO,map);
        MemberVO resultMember = restTemplateService.sendPostRequest(url, map,MemberVO.class);
        return resultMember;
    }

    @Override
    public ResultDTO deleteMemberAll() {
        //拼请求地址  ???
        //todo  存疑问
        String url = address + "/api/v2/member/delete_all";
        Map<String,Object> map = new HashMap<>();
        ResultDTO resultDTO = restTemplateService.sendPostRequest(url, map,ResultDTO.class);
        return resultDTO;
    }

    @Override
    public MemberVO getDeviceMemberStatus(MemberVO memberVO) {
        //拼请求地址
        String url = address + "/api/v2/member/get_device_member_status";
        Map<String,Object> map = new HashMap<>();
        map.put("obj",memberVO);
        setOrgId(memberVO,map);
        MemberVO resultMember = restTemplateService.sendPostRequest(url, map,MemberVO.class);
        return resultMember;
    }

    @Override
    public DeviceDTO createDevice(DeviceDTO deviceDTO) {
        //拼请求地址
        String url = address + "/api/v2/device/create";
        Map<String,Object> map = new HashMap<>();
        map.put("obj", deviceDTO);
        setOrgId(deviceDTO,map);
        DeviceDTO resultDevice = restTemplateService.sendPostRequest(url, map, DeviceDTO.class);
        return resultDevice;
    }

    @Override
    public DeviceDTO updateDevice(DeviceDTO deviceDTO) {
        //拼请求地址
        String url = address + "/api/v2/device/update";
        Map<String,Object> map = new HashMap<>();
        map.put("obj", deviceDTO);
        setOrgId(deviceDTO,map);
        DeviceDTO resultDevice = restTemplateService.sendPostRequest(url, map, DeviceDTO.class);
        return resultDevice;
    }

    @Override
    public DeviceDTO deleteDevice(DeviceDTO deviceDTO) {
        //拼请求地址
        String url = address + "/api/v2/device/delete";
        Map<String,Object> map = new HashMap<>();
        map.put("obj",deviceDTO);
        setOrgId(deviceDTO,map);
        DeviceDTO resultDevice = restTemplateService.sendPostRequest(url, map, DeviceDTO.class);
        return resultDevice;
    }

    @Override
    public ResultDTO getDeviceConfigList(String deviceId,String orgId) {
        //拼请求地址
        String url = address + "/api/v2/device/get_device_conf_list";
        Map<String,Object> map = new HashMap<>();
        map.put("obj",deviceId);
        map.put("orgId",orgId);
        ResultDTO resultDTO = restTemplateService.sendPostRequest(url, map,ResultDTO.class);
        return resultDTO;
    }

    @Override
    public DeviceVO updateUploadDeviceConf(DeviceVO deviceVO) {
        //拼请求地址
        String url = address + "/api/v2/device/update_conf";
        Map<String,Object> map = new HashMap<>();
        map.put("obj",deviceVO);
        setOrgId(deviceVO,map);
        DeviceVO resultDevice = restTemplateService.sendPostRequest(url, map, DeviceVO.class);
        return resultDevice;
    }

    @Override
    public DeviceVO remoteCtrlDevice(DeviceVO deviceVO) {
        //拼请求地址
        String url = address + "/api/v2/device/remote_ctrl";
        Map<String,Object> map = new HashMap<>();
        map.put("obj",deviceVO);
        setOrgId(deviceVO,map);
        DeviceVO resultDevice = restTemplateService.sendPostRequest(url, map, DeviceVO.class);
        return resultDevice;
    }

    @Override
    public DeviceVO bindDevicesPermission(DeviceVO deviceVO) {
        //拼请求地址
        String url = address + "/api/v2/permission/bind_devices";
        Map<String,Object> map = new HashMap<>();
        map.put("obj",deviceVO);
        setOrgId(deviceVO,map);
        DeviceVO resultDevice = restTemplateService.sendPostRequest(url, map, DeviceVO.class);
        return resultDevice;
    }

    @Override
    public DeviceVO unBindDevicesPermission(DeviceVO deviceVO) {
        //拼请求地址
        String url = address + "/api/v2/permission/unbind_devices";
        Map<String,Object> map = new HashMap<>();
        map.put("obj",deviceVO);
        setOrgId(deviceVO,map);
        DeviceVO resultDevice = restTemplateService.sendPostRequest(url, map, DeviceVO.class);
        return resultDevice;
    }

    @Override
    public DeviceVO bindGroupsPermission(DeviceVO deviceVO) {
        //拼请求地址
        String url = address + "/api/v2/permission/bind_groups";
        Map<String,Object> map = new HashMap<>();
        map.put("obj",deviceVO);
        setOrgId(deviceVO,map);
        DeviceVO resultDevice = restTemplateService.sendPostRequest(url, map, DeviceVO.class);
        return resultDevice;
    }

    @Override
    public DeviceVO unBindGroupsPermission(DeviceVO deviceVO) {
        //拼请求地址
        String url = address + "/api/v2/permission/unbind_groups";
        Map<String,Object> map = new HashMap<>();
        map.put("obj",deviceVO);
        setOrgId(deviceVO,map);
        DeviceVO resultDevice = restTemplateService.sendPostRequest(url, map, DeviceVO.class);
        return resultDevice;
    }

    @Override
    public PasswordDTO setDeviceAccessPasswd(PasswordDTO passwordDTO) {
        //拼请求地址
        String url = address + "/api/v2/device/set_device_access_passwd";
        Map<String,Object> map = new HashMap<>();
        map.put("obj",passwordDTO);
        setOrgId(passwordDTO,map);
        PasswordDTO passwordDTO1 = restTemplateService.sendPostRequest(url, map, PasswordDTO.class);
        return passwordDTO1;
    }

    @Override
    public FileDTO uploadFile(MultipartFile multipartFile) {
        //拼请求地址
        String url = address + "/api/v2/upload_file";
        FileDTO resultFile = restTemplateService.sendPostFileRequest(url,multipartFile);
        return resultFile;
    }

    @Override
    public FileDTO uploadFileNew(MultipartFile multipartFile,Map<String,Object> headersParams){
        //拼请求地址
        String url = address + "/api/v2/upload_file";

        FileDTO resultFile = restTemplateService.sendPostFileRequestNew(url,multipartFile,headersParams);
        return resultFile;
    }
    @Override
    public CallBackDTO createCallBack(CallBackDTO callBackDTO) {
        //拼请求地址
        String url = address + "/api/v2/callback/create";
        Map<String,Object> map = new HashMap<>();
        map.put("obj",callBackDTO);
        setOrgId(callBackDTO,map);
        CallBackDTO callBack = restTemplateService.sendPostRequest(url, map, CallBackDTO.class);
        return callBack;
    }

    @Override
    public CallBackDTO updateCallBack(CallBackDTO callBackDTO) {
        //拼请求地址
        String url = address + "/api/v2/callback/update";
        Map<String,Object> map = new HashMap<>();
        map.put("obj",callBackDTO);
        setOrgId(callBackDTO,map);
        CallBackDTO callBack = restTemplateService.sendPostRequest(url, map, CallBackDTO.class);
        return callBack;
    }

    @Override
    public CallBackDTO deleteCallBack(CallBackDTO callBackDTO) {
        //拼请求地址
        String url = address + "/api/v2/callback/delete";
        Map<String,Object> map = new HashMap<>();
        map.put("obj",callBackDTO);
        setOrgId(callBackDTO,map);
        CallBackDTO callBack = restTemplateService.sendPostRequest(url, map, CallBackDTO.class);
        return callBack;
    }

    @Override
    public OrgDTO createOrg(OrgDTO orgDTO) {
        //拼请求地址
        String url = address + "/api/v2/org/create";
        Map<String,Object> map = new HashMap<>();
        map.put("obj",orgDTO);

        // setOrgId(orgDTO,map);
        OrgDTO result = restTemplateService.sendPostRequest(url, map, OrgDTO.class);
        return result;
    }

    @Override
    public OrgDTO updateOrg(OrgDTO orgDTO) {
        //拼请求地址
        String url = address + "/api/v2/org/update";
        Map<String,Object> map = new HashMap<>();
        map.put("obj",orgDTO);
        setOrgId(orgDTO,map);
        OrgDTO result = restTemplateService.sendPostRequest(url, map, OrgDTO.class);
        return result;
    }

    @Override
    public OrgDTO deleteOrg(OrgDTO orgDTO) {
        //拼请求地址
        String url = address + "/api/v2/org/delete";
        Map<String,Object> map = new HashMap<>();
        map.put("obj",orgDTO);
        setOrgId(orgDTO,map);
        OrgDTO result = restTemplateService.sendPostRequest(url, map, OrgDTO.class);
        return result;
    }

    /**
     * @description: 组织公共头部分
     * @author liyujun
     * @date 2018-12-27
     * @param dto :
     * @param map :
     * @return :
     */
    private void setOrgId(Object  dto,Map<String,Object> map){
        if( BeanUtils.checkFileExist(dto,FaceCloudConstants.ORGID)){
            BeanUtils.readAttributeValue(dto,FaceCloudConstants.ORGID);
            map.put(FaceCloudConstants.ORGID, BeanUtils.readAttributeValue(dto,FaceCloudConstants.ORGID).toString());
        }
    }


}
