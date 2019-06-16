package com.bit.sc.module.resident.service.impl;

import com.bit.base.bean.UserAddress;
import com.bit.base.exception.BusinessException;
import com.bit.base.service.BaseService;
import com.bit.base.vo.BaseVo;
import com.bit.base.vo.UserInfo;
import com.bit.sc.common.Const;
import com.bit.sc.common.ModuleFileType;
import com.bit.sc.module.attachment.pojo.Attachment;
import com.bit.sc.module.attachment.service.AttachmentService;
import com.bit.sc.module.attachment.vo.AttachmentThirdVO;
import com.bit.sc.module.group.dao.GroupMapper;
import com.bit.sc.module.group.dao.GroupRelMapper;
import com.bit.sc.module.group.pojo.Group;
import com.bit.sc.module.group.pojo.GroupRel;
import com.bit.sc.module.group.vo.GroupVO;
import com.bit.sc.module.resident.dao.ResidentMapper;
import com.bit.sc.module.resident.pojo.Resident;
import com.bit.sc.module.resident.service.ResidentCommonService;
import com.bit.sc.module.resident.service.ResidentService;
import com.bit.sc.module.resident.vo.ResidentBatchAuthorizeVO;
import com.bit.sc.module.resident.vo.ResidentVO;
import com.bit.sc.module.restTemplate.dto.CertCardInfo;
import com.bit.sc.module.restTemplate.dto.MemberDTO;
import com.bit.sc.module.restTemplate.dto.MemberVO;
import com.bit.sc.module.restTemplate.service.ThirdPartyInterfaceService;
import com.bit.sc.module.sys.dao.DictMapper;
import com.bit.sc.module.sys.dao.ResidentRelAddressMapper;
import com.bit.sc.module.sys.pojo.Address;
import com.bit.sc.module.sys.pojo.Dict;
import com.bit.sc.module.sys.pojo.ResidentRelAddress;
import com.bit.sc.module.sys.service.AddressService;
import com.bit.sc.module.sys.vo.ResidentRelAddressActionVO;
import com.bit.sc.utils.AddressUtil;
import com.bit.sc.utils.DateUtil;
import com.bit.utils.StringUtil;
import com.bit.utils.UUIDUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service("residentCommonService")
public class ResidentCommonServiceImpl extends BaseService implements ResidentCommonService {

    @Autowired
    private ResidentService residentService;
    @Autowired
    private ThirdPartyInterfaceService thirdPartyInterfaceService;
    @Autowired
    private ResidentMapper residentMapper;
    @Autowired
    private GroupRelMapper groupRelMapper;
    @Autowired
    private AttachmentService attachmentService;
    @Autowired
    private GroupMapper groupMapper;
    @Autowired
    private AddressService addressService;

    @Override
    public BaseVo add(ResidentVO residentVO) {
        BaseVo baseVo = new BaseVo();

        MemberDTO memberDTO = new MemberDTO();
        memberDTO.setMemberName(residentVO.getRealName());
        memberDTO.setMobile(residentVO.getMobile());
        memberDTO.setFaceImgId(residentVO.getFileId());
        memberDTO.setCertNo(residentVO.getCardId());
        memberDTO.setCertType(1);
        //人员id
        String memberId = UUIDUtil.getUUID();
        memberDTO.setMemberId(memberId);
        //获取当前登录人员的信息
        UserInfo userInfo = getCurrentUserInfo();
        UserAddress userAddress = userInfo.getCurrentUserAddresses();
        String communityCode = "";
        String orgId ="";
        if (userAddress!=null){
            communityCode = userAddress.getAddressCode();
            Address address = addressService.findByAddressCode(communityCode);
            orgId = address.getOrgId();
            memberDTO.setOrgId(orgId);
        }


        CertCardInfo certCardInfo = new CertCardInfo();
        certCardInfo.setName(residentVO.getRealName());
        if (residentVO.getSex().equals(3)){
            certCardInfo.setSex(Const.PLATFORM_SEX_FEMALE);
        }
        if (residentVO.getSex().equals(5)){
            certCardInfo.setSex(Const.PLATFORM_SEX_MALE);
        }
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        certCardInfo.setBirthday(sdf.format(residentVO.getBirthday()));
        certCardInfo.setAddress(residentVO.getHjdz());
        memberDTO.setCertCardInfo(certCardInfo);
        List<String> tagNames = new ArrayList<>();
        tagNames.add("添加居民");
        memberDTO.setTagName(tagNames);
        MemberDTO result = thirdPartyInterfaceService.createMember(memberDTO);

        residentVO.setOrgId(orgId);
        residentVO.setMemberId(memberId);
        residentService.add(residentVO);
        return baseVo;
    }

    @Override
    public BaseVo modify(ResidentVO residentVO) {
        BaseVo baseVo = new BaseVo();
        Resident resident = residentMapper.findById(residentVO.getId());

        MemberDTO memberDTO = new MemberDTO();
        memberDTO.setMemberId(resident.getMemberId());
        memberDTO.setMemberName(residentVO.getRealName());
        if (!resident.getMobile().equals(residentVO.getMobile())){
            memberDTO.setMobile(residentVO.getMobile());
        }

        if (!resident.getCardId().equals(residentVO.getCardId())){
            memberDTO.setMobile(residentVO.getCardId());
        }

        if (!resident.getCardType().equals(residentVO.getCardType())){
            memberDTO.setCertType(residentVO.getCardType().intValue());
        }

        if (StringUtil.isNotEmpty(residentVO.getFileId())){
            Attachment attachment = new Attachment();
            attachment.setFileId(residentVO.getFileId());
            List<Attachment> attachments = attachmentService.findAllByParams(attachment);
            for (Attachment attachment1 : attachments){

                if (!attachment1.getAttachmentId().equals(resident.getImageId())){
                    memberDTO.setFaceImgId(residentVO.getFileId());
                    break;
                }
            }
        }


        Address address = addressService.findByAddressCode(residentVO.getCommunityCode());
        String orgId = address.getOrgId();
        memberDTO.setOrgId(orgId);



        List<String> tagNames = new ArrayList<>();
        memberDTO.setTagName(tagNames);
        tagNames.add("更新居民");


        MemberDTO result = thirdPartyInterfaceService.updateMember(memberDTO);

        residentService.update(residentVO);
        return baseVo;
    }

    @Override
    public BaseVo delete(Long id) {
        Resident resident = residentMapper.findById(id);
        List<String> stringList = new ArrayList<>();
        stringList.add(resident.getMemberId());
        MemberVO memberVO = new MemberVO();
        memberVO.setMemberIdList(stringList);



        Address address = addressService.findByAddressCode(resident.getCommunityCode());
        String orgId = address.getOrgId();
        memberVO.setOrgId(orgId);
        //删除组
        try {
            thirdPartyInterfaceService.deleteMember(memberVO);
        }catch (Exception e){
            e.printStackTrace();
        }


        residentService.delete(id);
        return new BaseVo();
    }

    @Override
    public BaseVo reflect(Long id) {
        return residentService.reflect(id);
    }

    @Override
    public BaseVo authorize(ResidentVO residentVO) {
        BaseVo baseVo = new BaseVo();
        MemberVO memberVO = new MemberVO();
        Resident resident = residentMapper.findById(residentVO.getId());
        List<String> stringList = new ArrayList<>();
        stringList.add(resident.getMemberId());
        memberVO.setMemberIdList(stringList);

        String str = AddressUtil.sub(resident.getCommunityCode());
        Address address = addressService.findByAddressCode(str);
        memberVO.setOrgId(address.getOrgId());

        List<String> groupCodes = residentVO.getGroupCode();
        if (groupCodes.size()>0){
            for (int i =0 ;i<groupCodes.size();i++){
                try{
                    memberVO.setGroupId(groupCodes.get(i));
                    thirdPartyInterfaceService.addMemberGroup(memberVO);
                }catch (Exception e){

                }

            }
            baseVo = residentService.authorize(residentVO);
        }

        return baseVo;
    }

    @Override
    public BaseVo batchAuthorize(ResidentBatchAuthorizeVO residentBatchAuthorizeVO) {
        BaseVo baseVo = new BaseVo();
        ResidentBatchAuthorizeVO temp = new ResidentBatchAuthorizeVO();
        //传递的参数
        String newcode = residentBatchAuthorizeVO.getGroupCode();
        //传递的参数
        List<Long> ids = residentBatchAuthorizeVO.getIds();
        List<Long> newids = new ArrayList<>();
        List<String> memberIdList = new ArrayList<>();




        for (int i =0 ;i<ids.size();i++) {
            GroupRel groupRel = new GroupRel();
            groupRel.setType(Const.GROUP_REL_TYPE_MAN);
            groupRel.setRelId(ids.get(i));

            GroupVO groupVO = new GroupVO();
            groupVO.setGroupCode(newcode);
            List<Group> groupList = groupMapper.findAllByParam(groupVO);

            groupRel.setGroupId(groupList.get(0).getId());
            //根据选中的人的id 去groupRel里查询
            List<GroupRel> groupRels = groupRelMapper.findByParam(groupRel);

            if (groupRels.size()>0){
                continue;
                //如果有相同的就不添加   如果没有相同的就添加

            }else {
                newids.add(ids.get(i));
                memberIdList.add(residentMapper.findById(ids.get(i)).getMemberId());

            }
        }
        //参数不为空 在调接口
        if (memberIdList.size()>0&&newcode!=null){
            MemberVO memberVO = new MemberVO();
            memberVO.setMemberIdList(memberIdList);
            memberVO.setGroupId(newcode);

            //获取当前登录人员的信息
            UserInfo userInfo = getCurrentUserInfo();
            UserAddress userAddress = userInfo.getCurrentUserAddresses();
            String communityCode = "";
            String orgId ="";
            if (userAddress!=null){
                communityCode = userAddress.getAddressCode();
                Address address = addressService.findByAddressCode(communityCode);
                orgId = address.getOrgId();
                memberVO.setOrgId(orgId);
            }

            thirdPartyInterfaceService.addMemberGroup(memberVO);

        }
        //参数不为空 在批量添加
        if (newids.size()>0&&newcode!=null){
            temp.setGroupCode(newcode);
            temp.setIds(newids);
            baseVo = residentService.batchAuthorize(temp);
        }
        return baseVo;
    }

    @Override
    public BaseVo revoke(ResidentVO residentVO) {
        BaseVo baseVo = new BaseVo();
        MemberVO memberVO = new MemberVO();
        Resident resident = residentMapper.findById(residentVO.getId());
        List<String> stringList = new ArrayList<>();
        stringList.add(resident.getMemberId());
        memberVO.setMemberIdList(stringList);

        String str = AddressUtil.sub(resident.getCommunityCode());
        Address address = addressService.findByAddressCode(str);
        memberVO.setOrgId(address.getOrgId());

        List<String> groupCodes = residentVO.getGroupCode();
        if (groupCodes.size()>0){
            for (int i =0 ;i<groupCodes.size();i++){

                try {
                    memberVO.setGroupId(groupCodes.get(i));
                    thirdPartyInterfaceService.deleteMemberGroup(memberVO);
                }catch (Exception e){
//                    e.printStackTrace();
                }
            }
            baseVo = residentService.revoke(residentVO);
        }

        return baseVo;
    }

    @Override
    public BaseVo batchRevoke(ResidentBatchAuthorizeVO residentBatchAuthorizeVO) {
        BaseVo baseVo = new BaseVo();
        ResidentBatchAuthorizeVO temp = new ResidentBatchAuthorizeVO();
        //传递的参数
        String newcode = residentBatchAuthorizeVO.getGroupCode();
        //传递的参数
        List<Long> ids = residentBatchAuthorizeVO.getIds();
        List<Long> newids = new ArrayList<>();
        List<String> memberIdList = new ArrayList<>();

        for (int i =0 ;i<ids.size();i++) {
            GroupRel groupRel = new GroupRel();
            groupRel.setType(Const.GROUP_REL_TYPE_MAN);
            groupRel.setRelId(ids.get(i));

            GroupVO groupVO = new GroupVO();
            groupVO.setGroupCode(newcode);
            List<Group> groupList = groupMapper.findAllByParam(groupVO);
            groupRel.setGroupId(groupList.get(0).getId());

            //根据选中的人的id 去groupRel里查询
            List<GroupRel> groupRels = groupRelMapper.findByParam(groupRel);
            //如果有相同的就删除  如果没有相同的不删除
            if (groupRels.size()>0){
                newids.add(ids.get(i));
                memberIdList.add(residentMapper.findById(ids.get(i)).getMemberId());
            }else {
                continue;
            }

        }
        //参数不为空 在调接口
        if (memberIdList.size()>0&&newcode!=null){
            MemberVO memberVO = new MemberVO();
            memberVO.setMemberIdList(memberIdList);
            memberVO.setGroupId(newcode);

            //获取当前登录人员的信息
            UserInfo userInfo = getCurrentUserInfo();
            UserAddress userAddress = userInfo.getCurrentUserAddresses();
            String communityCode = "";
            String orgId ="";
            if (userAddress!=null){
                communityCode = userAddress.getAddressCode();
                Address address = addressService.findByAddressCode(communityCode);
                orgId = address.getOrgId();
                memberVO.setOrgId(orgId);
            }

            thirdPartyInterfaceService.deleteMemberGroup(memberVO);

        }
        //参数不为空 在批量添加
        if (newids.size()>0&&newcode!=null){
            temp.setGroupCode(newcode);
            temp.setIds(newids);
            baseVo = residentService.batchRevoke(temp);
        }
        return baseVo;

    }

    @Override
    public BaseVo batchImport() {
        return null;
    }

    @Override
    public BaseVo authorizationReflect(Long id) {
        return residentService.authorizationReflect(id);
    }

    @Override
    public BaseVo uploadpicture(MultipartFile file) {
        Attachment attachment = new Attachment();
        attachment.setBusinessId(ModuleFileType.RESIDENT_IMAGE);
        AttachmentThirdVO attachmentThirdVO = attachmentService.addAttachmentThird(attachment,file);
        Attachment att = attachmentService.findByAttachmentId(attachmentThirdVO.getAttachmentId());
        BaseVo baseVo = new BaseVo();
        baseVo.setData(att);
        return baseVo;
    }



}
