package com.bit.sc;

import com.bit.base.exception.BusinessException;
import com.bit.sc.common.Const;
import com.bit.sc.module.restTemplate.dto.*;
import com.bit.sc.module.restTemplate.service.ThirdPartyInterfaceService;
import com.bit.sc.module.restTemplate.service.impl.FaceCloudTokenService;
import com.bit.sc.module.restTemplate.vo.ResultDTO;
import com.bit.sc.utils.BeanUtils;
import com.bit.sc.utils.MD5;
import com.bit.utils.CacheUtil;
import com.bit.utils.UUIDUtil;
import org.apache.http.entity.ContentType;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.*;

/**
 * @author liuyancheng
 * @create 2018-12-11 16:32
 */
@RunWith(SpringJUnit4ClassRunner.class) // SpringJUnit支持，由此引入Spring-Test框架支持！
@SpringBootTest(classes = ScServerApplication.class)
@WebAppConfiguration
public class TestThirdInterface {

    @Autowired
    private ThirdPartyInterfaceService thirdPartyInterfaceService;
    @Autowired
    private CacheUtil cacheUtil;
    @Autowired
    private FaceCloudTokenService faceCloudTokenService;

    private String orgId="56fe534ebd3e4912b0c7c1a71dc3957d";//组织id

    private String fileId="4028c68167edafa90167ee3ca18f002d";//上传文件陈铎头像

    private  String  groupId="f3967d7710814b7a9a75447f8eee6ffe";//组的id

    private  String  memberId="4028c68167edafa90167ee4595cc002f";//成员id

    private String authKey = "2028482846772224";//authKey

    @Test
    public void openDoor(){
        DeviceVO deviceVO = new DeviceVO();
        deviceVO.setDeviceId("4028c68167c9b9ac0167ca0e538800ba");

        deviceVO.setCtrlCode(12);
        try {
            thirdPartyInterfaceService.remoteCtrlDevice(deviceVO);
        }catch (Exception e){
            e.printStackTrace();
        }
    }




    /**
     * 获取token
     */
    @Test
    public void getToken1(){
//        cacheUtil.del(Const.REDIS_KEY_FACE_CLOUD_TOKEN);
        MemberDTO memberDTO = new MemberDTO();
        System.out.println(memberDTO.toString());
    }

    /**
     * 获取token
     */
    @Test
    public void getToken(){
//        cacheUtil.del(Const.REDIS_KEY_FACE_CLOUD_TOKEN);
//        faceCloudTokenService.creatOrRefreshToken();
    }

    /**
     * 创建分组
     */
    @Test
    public void createGroup(){
        GroupDTO groupDTO = new GroupDTO();
        groupDTO.setGroupName("Test01");
        groupDTO.setDayBeginTime("00:00:00");
        groupDTO.setDayEndTime("23:59:59");
        groupDTO.setOrgId(orgId);
        groupDTO.setGroupId(UUIDUtil.getUUID());
        List<String> list = new ArrayList<>();
        list.add("0");
        list.add("1");
        list.add("2");
        list.add("3");
        list.add("4");
        list.add("5");
        list.add("6");
        groupDTO.setWeekdays(list);
        try {
            GroupDTO group = thirdPartyInterfaceService.createGroup(groupDTO);
            System.out.println(group.getGroupId());
        }catch (Exception e){

        }
    }

    /**
     * 更新分组
     */
    @Test
    public void updateGroup(){
        GroupDTO groupDTO = new GroupDTO();
        groupDTO.setGroupId("4028c68167a6adc20167a6b9d2770000");
        groupDTO.setGroupName("测试分组13");
        groupDTO.setDayBeginTime("00:00:00");
        groupDTO.setDayEndTime("23:59:59");
        groupDTO.setOrgId(orgId);
        List<String> list = new ArrayList<>();
        list.add("0");
        list.add("1");
        list.add("2");
        list.add("3");
        list.add("4");
        list.add("5");
        list.add("6");
        groupDTO.setWeekdays(list);
        GroupDTO groupDTO1 = thirdPartyInterfaceService.updateGroup(groupDTO);
    }

    /**
     * 删除分组（逻辑删除）
     */
    @Test
    public void deleteGroup(){
        GroupDTO groupDTO = new GroupDTO();
        List<String> list = new ArrayList<>();
        list.add("4028c68167a6adc20167a6b9d2770000");
        groupDTO.setGroupIdList(list);
        groupDTO.setOrgId(orgId);
        ResultDTO<GroupDTO> resultDTO = thirdPartyInterfaceService.deleteGroup(groupDTO);
        System.out.println(resultDTO.getData());
        System.out.println(resultDTO.getCode());
        System.out.println(resultDTO.getMessage());
    }
    /**
     * 分组的配置更新
     */
    @Test
    public void configGroup(){
        GroupDTO groupDTO = new GroupDTO();
        groupDTO.setGroupIdList(null);
        groupDTO.setDayBeginTime("");
        groupDTO.setDayEndTime("");
        groupDTO.setWeekdays(null);
        groupDTO.setOrgId(orgId);
        thirdPartyInterfaceService.configGroup(groupDTO);
    }

    /**
     * 添加人员
     */
    @Test
    public void createMember(){
        MemberDTO memberDTO = new MemberDTO();
        memberDTO.setMemberName("测试人员01");
        memberDTO.setCertNo("120101197008190037");
        memberDTO.setCertType(1);
        memberDTO.setOrgId(orgId);
        memberDTO.setMemberId(UUIDUtil.getUUID());
        memberDTO.setFaceImgId(fileId);
        List<String> list = new ArrayList();
        list.add("测试人员01");
        memberDTO.setTagName(list);
//        memberDTO.setEmployeeNum("");
        memberDTO.setMobile("13821885881");
//        memberDTO.setDialLabel("");
//        memberDTO.setShortNum("");
//        memberDTO.setIcNum("");
//        memberDTO.setIdNum("");
//        memberDTO.setFaceImgId("");
//        memberDTO.setFeature1("");
//        memberDTO.setFeature2("");
//        memberDTO.setCertType("");
//        memberDTO.setCertNo("");
//        memberDTO.setExtraData("");
//        memberDTO.setValidDatetimeBegin("");
//        memberDTO.setValidDatetimeEnd("");
//
//
        CertCardInfo certCardInfoDTO = new CertCardInfo();

        certCardInfoDTO.setName("测试");
        certCardInfoDTO.setSex(1);
        certCardInfoDTO.setBirthday("2018-11-12");
        certCardInfoDTO.setAddress("天津市南开区");
        memberDTO.setCertCardInfo(certCardInfoDTO);

        MemberDTO member = thirdPartyInterfaceService.createMember(memberDTO);
        System.out.println("memberId= " + member.getMemberId());
//        System.out.println(member.getCode());
//        System.out.println(member.getMessage());
    }

    /**
     * 更新人员
     */
    @Test
    public void updateMember(){
        MemberDTO memberDTO = new MemberDTO();
        memberDTO.setMemberId("4028c68167a6adc20167b02a007503b3");
        memberDTO.setMemberName("羊驼");
        List<String> list = new ArrayList();
        list.add("更新居民");
        memberDTO.setTagName(list);
//        memberDTO.setEmployeeNum("");
        memberDTO.setMobile("13001122008");
//        memberDTO.setDialLabel("");
//        memberDTO.setShortNum("");
//        memberDTO.setIcNum("");
//        memberDTO.setIdNum("");
//        memberDTO.setFaceImgId("");
//        memberDTO.setFeature1("");
//        memberDTO.setFeature2("");
        memberDTO.setCertType(167);
        memberDTO.setCertNo("120101199008190075");
        memberDTO.setOrgId(orgId);
//        memberDTO.setExtraData("");
//        memberDTO.setValidDatetimeBegin("");
//        memberDTO.setValidDatetimeEnd("");
//
//
        CertCardInfo certCardInfoDTO = new CertCardInfo();
        certCardInfoDTO.setName("羊驼");
        certCardInfoDTO.setSex(0);
        certCardInfoDTO.setBirthday("2010-07-14");
        certCardInfoDTO.setNation("");
        certCardInfoDTO.setAddress("天津市南开区");
//        memberDTO.setCertCardInfo(certCardInfoDTO);

        MemberDTO memberDTO1 = thirdPartyInterfaceService.updateMember(memberDTO);

        System.out.println(memberDTO1.getMessage());
//        System.out.println(member.getMessage());
    }

    /**
     * 删除人员（逻辑删除）
     */
    @Test
    public void deleteMember(){
        MemberVO memberVO = new MemberVO();
        List<String> list = new ArrayList<>();
        list.add("4028c68167a6adc20167a72329310033");
        memberVO.setMemberIdList(list);
        memberVO.setOrgId(orgId);
        ResultDTO<ResultDTO> member = thirdPartyInterfaceService.deleteMember(memberVO);
        System.out.println(member.getData());
        System.out.println(member.getCode());
        System.out.println(member.getMessage());
    }

    /**
     * 批量导入人员照片
     */
    @Test
    public void batchPicMember(){
        MemberVO memberVO = new MemberVO();
        memberVO.setImageZipFileId("");
        memberVO.setTagName(null);
        thirdPartyInterfaceService.batchPicMember(memberVO);
    }

    @Test
    public void test(){
        List<String> list1 = new ArrayList<>();
        List<String> list2 = new ArrayList<>();


        list1.add("a");
        list1.add("b");
        list1.add("c");

        list2.add("a");
        list2.add("d");
        list2.add("e");
        list2.add("f");
        Set<String> set = new HashSet<String>();
        set.addAll(list1);
        set.addAll(list2);
        List<String> c = new ArrayList<String>(set);

        List<String> joinList = new ArrayList<>(list1);
        joinList.addAll(list2);

        List<String> compareList1 = new ArrayList<>(joinList);
        compareList1.removeAll(list1);

        List<String> compareList2 = new ArrayList<>(joinList);
        compareList2.removeAll(list2);
        for (int i =0;i<3;i++){
            try {
                throw new BusinessException("hello exception "+i);
            }catch (Exception e){
//                e.printStackTrace();
            }
            System.out.println("hello world "+i);
        }

    }


    /**
     * 增加人员分组
     */
    @Test
    public void addMemberGroup(){
        MemberVO memberVO = new MemberVO();
        //memberVO.setGroupId("4028c68167a6adc20167b051e3bc03d9");
        memberVO.setOrgId(orgId);
        memberVO.setGroupId(groupId);
        List<String> list = new ArrayList();
        list.add(memberId);
        memberVO.setMemberIdList(list);
        MemberVO memberVO1 = thirdPartyInterfaceService.addMemberGroup(memberVO);
        System.out.println(memberVO1.getData());
//        System.out.println(memberVO1.getCode());
//        System.out.println(memberVO1.getMessage());
    }

    /**
     * 移除人员分组
     */
    @Test
    public void deleteMemberGroup(){
        MemberVO memberVO = new MemberVO();
        memberVO.setGroupId("4028c68167a6adc20167b051e3bc03d9");
        List<String> list = new ArrayList();
        list.add("4028c68167a6adc20167b9e36fd90ae0");
        memberVO.setMemberIdList(list);
        memberVO.setOrgId(orgId);
        MemberVO memberVO1 =thirdPartyInterfaceService.deleteMemberGroup(memberVO);
    }

    /**
     * 增加/删除人员分组（通过标签）
     */
    @Test
    public void addOrDelMemberGroupByTag(){
        MemberVO memberVO = new MemberVO();
        memberVO.setGroupId("4028c68167a6adc20167a6d1accc0010");
        List<String> list = new ArrayList();
        list.add("测试人员01");
        memberVO.setTagIdList(list);
        memberVO.setOptionFlag(2);
        memberVO.setOrgId(orgId);
        ResultDTO<ResultDTO> member = thirdPartyInterfaceService.addOrDelMemberGroupByTag(memberVO);
        System.out.println(member.getData());
        System.out.println(member.getCode());
        System.out.println(member.getMessage());
    }

    /**
     * 清空人员（逻辑删除）
     */
    @Test
    public void deleteMemberAll(){
        thirdPartyInterfaceService.deleteMemberAll();
    }

    /**
     * 获取人员下发状态
     */
    @Test
    public void getDeviceMemberStatus(){
        MemberVO memberVO = new MemberVO();
        memberVO.setDeviceId("");
        memberVO.setOrgId(orgId);
        memberVO.setMemberIdList(null);
        thirdPartyInterfaceService.getDeviceMemberStatus(memberVO);
    }

    /**
     * 添加设备
     */
    @Test
    public void createDevice(){
        DeviceDTO deviceDTO = new DeviceDTO();
        deviceDTO.setDeviceCode("BIT002");
        deviceDTO.setDeviceName("测试设备02");
        deviceDTO.setDeviceType(1);
        deviceDTO.setPosition("测试小区");
        List<String> tagList = new ArrayList<>();
        tagList.add("测试设备");
        deviceDTO.setTagName(tagList);
        deviceDTO.setMemo("这是一个测试设备");
        deviceDTO.setAuthKey("1234567890123457");
        ResultDTO<DeviceDTO> device = thirdPartyInterfaceService.createDevice(deviceDTO);
        System.out.println(device.getData());
        System.out.println(device.getCode());
        System.out.println(device.getMessage());
    }

    /**
     * 更新设备
     */
    @Test
    public void updateDevice(){
        DeviceDTO deviceDTO = new DeviceDTO();
        deviceDTO.setDeviceId("4028c68167a6adc20167a6c362430004");
        deviceDTO.setDeviceName("测试设备02");
        deviceDTO.setOrgId(orgId);
//        deviceDTO.setDeviceType(1);
//        deviceDTO.setPosition("");
//        deviceDTO.setTagName(null);
//        deviceDTO.setMemo("");
        ResultDTO<DeviceDTO> device = thirdPartyInterfaceService.updateDevice(deviceDTO);
        System.out.println(device.getData());
        System.out.println(device.getCode());
        System.out.println(device.getMessage());
    }

    /**
     * 移除设备
     */
    @Test
    public void deleteDevice(){
        DeviceDTO deviceDTO = new DeviceDTO();
        List<String> list = new ArrayList();
        list.add("4028c68167a6adc20167a6c362430004");
        deviceDTO.setDeviceIdList(list);
        deviceDTO.setOrgId(orgId);
        ResultDTO<DeviceDTO> device = thirdPartyInterfaceService.deleteDevice(deviceDTO);
        System.out.println(device.getData());
        System.out.println(device.getCode());
        System.out.println(device.getMessage());
    }

    /**
     * 获取设备配置（含配置分类信息）移除设备
     */
    @Test
    public void getDeviceConfigList(){
        String deviceId = "";
        thirdPartyInterfaceService.getDeviceConfigList(deviceId,orgId);
    }

    /**
     * 设备上传配置信息
     */
    @Test
    public void updateUploadDeviceConf(){
        DeviceVO deviceVO = new DeviceVO();
        deviceVO.setDeviceId("");
        DeviceConfigDTO deviceConfigDTO = new DeviceConfigDTO();
        deviceConfigDTO.setCategory("");
        deviceConfigDTO.setCategoryOrder(1);
        deviceConfigDTO.setLabel("");
        deviceConfigDTO.setK("");
        deviceConfigDTO.setV("");
        deviceConfigDTO.setType(1);
        deviceConfigDTO.setDefaultValue("");
        deviceConfigDTO.setDesc("");
        deviceConfigDTO.setSortOrder(1);
        List<DeviceConfigDTO> list  = new ArrayList<>();
        list.add(deviceConfigDTO);
        deviceVO.setConfList(list);
        thirdPartyInterfaceService.updateUploadDeviceConf(deviceVO);
    }

    /**
     * 远程控制设备
     */
    @Test
    public void remoteCtrlDevice(){
        DeviceVO deviceVO = new DeviceVO();
        deviceVO.setDeviceId("");
        deviceVO.setCtrlCode(1);
        deviceVO.setData("");
        thirdPartyInterfaceService.remoteCtrlDevice(deviceVO);
    }

    /**
     * 设置分组权限（添加设备到分组）
     */
    @Test
    public void bindDevicesPermission(){
        DeviceVO deviceVO = new DeviceVO();
        deviceVO.setGroupId("4028c68167a6adc20167a6d1accc0010");
        List<String> list = new ArrayList();
        list.add("4028c68167a6adc20167a6d01d2b000c");
        deviceVO.setDeviceIdList(list);
        ResultDTO<ResultDTO> resultDTO = thirdPartyInterfaceService.bindDevicesPermission(deviceVO);
        System.out.println(resultDTO.getData());
        System.out.println(resultDTO.getCode());
        System.out.println(resultDTO.getMessage());
    }

    /**
     * 设置分组权限（移除设备到分组）
     */
    @Test
    public void unBindDevicesPermission(){
        DeviceVO deviceVO = new DeviceVO();
        deviceVO.setGroupId("4028c68167a6adc20167a6d1accc0010");
        List<String> list = new ArrayList();
        list.add("4028c68167a6adc20167a6d01d2b000c");
        deviceVO.setDeviceIdList(list);
        ResultDTO<ResultDTO> resultDTO = thirdPartyInterfaceService.unBindDevicesPermission(deviceVO);
        System.out.println(resultDTO.getData());
        System.out.println(resultDTO.getCode());
        System.out.println(resultDTO.getMessage());
    }

    /**
     * 设置设备权限（添加分组到设备）
     */
    @Test
    public void bindGroupsPermission(){
        DeviceVO deviceVO = new DeviceVO();
        deviceVO.setDeviceId("");
        deviceVO.setGroupIdList(null);
        thirdPartyInterfaceService.bindGroupsPermission(deviceVO);
    }

    /**
     * 设置设备权限（移除分组到设备）
     */
    @Test
    public void unBindGroupsPermission(){
        DeviceVO deviceVO = new DeviceVO();
        deviceVO.setDeviceId("");
        deviceVO.setGroupIdList(null);
        thirdPartyInterfaceService.unBindGroupsPermission(deviceVO);
    }

    @Test
    public void uploadFile(){

        try {
            FileDTO fileDTO = new FileDTO();
            File pdfFile = new File("C:\\Users\\Think\\Desktop\\1.jpg");
            FileInputStream fileInputStream = new FileInputStream(pdfFile);
            MultipartFile multipartFile = null;
            multipartFile = new MockMultipartFile(pdfFile.getName(), pdfFile.getName(),
                    ContentType.APPLICATION_OCTET_STREAM.toString(), fileInputStream);


            Map<String,Object> headersParams =new HashMap<>();
            headersParams.put("orgId",orgId);
            FileDTO fileDTO1 = thirdPartyInterfaceService.uploadFileNew(multipartFile,headersParams);
            System.out.println("-----------------------------    "+fileDTO1.getFileId());

           // "4028c68167edafa90167ee3ca18f002d";;
        } catch (IOException e) {
            e.printStackTrace();
        }


       /* long l = cacheUtil.getExpire(Const.REDIS_KEY_FACE_CLOUD_TOKEN);
        System.out.println(l);*/
    }

    /**
     * 创建组织机构
     */
    @Test
    public void createOrg(){
        //80f724f9306c4e38b0ea098efc7dce16
        String uuid = UUIDUtil.getUUID();
        System.out.println(uuid);
        OrgDTO orgDTO = new OrgDTO();
        orgDTO.setOrgId(uuid);
        orgDTO.setOrgName("天津比亦特时代豪庭04");
        orgDTO.setValidDateEnd("2025-12-27");
        orgDTO.setOrgEmail("729763190@qq.com");
        OrgDTO org = thirdPartyInterfaceService.createOrg(orgDTO);
        System.out.println(org.getOrgId());
    }

    @Test
    public void updateOrg(){
        //80f724f9306c4e38b0ea098efc7dce16
        OrgDTO orgDTO = new OrgDTO();
        orgDTO.setOrgId("80f724f9306c4e38b0ea098efc7dce16");
        orgDTO.setOrgName("天津比亦特网络");
        orgDTO.setValidDateEnd("2018-12-27");
        orgDTO.setOrgEmail("729763190@qq.com");
        thirdPartyInterfaceService.updateOrg(orgDTO);
    }

    @Test
    public void deletOrg(){
        //80f724f9306c4e38b0ea098efc7dce16
        OrgDTO orgDTO = new OrgDTO();
        orgDTO.setOrgId("80f724f9306c4e38b0ea098efc7dce16");
        thirdPartyInterfaceService.deleteOrg(orgDTO);
    }

    @Test
    public  void getMd5(){
        System.out.println(MD5.encodeMd5("12345678"));

//        Map<String,Object> map=new HashMap<>();
//        /*map.put("rr","");*/
//        System.out.println(map.get("rr")!=null);
//
//      DeviceDTO dto=new DeviceDTO();
//        System.out.println( BeanUtils.checkFileExist(dto,"orgid"));
    }

    @Test
    public void setDeviceAccessPasswd(){
        List<String> list = new ArrayList<>();
        list.add("567fabf4a2b54f449f9ceb0e335fe4d1");

        PasswordDTO passwordDTO = new PasswordDTO();
        passwordDTO.setDeviceIdList(list);
        passwordDTO.setPasswd("25d55ad283aa400af464c76d713c07ad");
        thirdPartyInterfaceService.setDeviceAccessPasswd(passwordDTO);
    }
}
