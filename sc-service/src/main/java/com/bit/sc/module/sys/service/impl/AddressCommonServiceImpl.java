package com.bit.sc.module.sys.service.impl;

import com.bit.base.service.BaseService;
import com.bit.base.vo.BaseVo;
import com.bit.sc.common.Const;
import com.bit.sc.common.ModuleFileType;
import com.bit.sc.module.attachment.pojo.Attachment;
import com.bit.sc.module.attachment.service.AttachmentService;
import com.bit.sc.module.attachment.vo.AttachmentThirdVO;
import com.bit.sc.module.restTemplate.dto.MemberDTO;
import com.bit.sc.module.restTemplate.dto.OrgDTO;
import com.bit.sc.module.restTemplate.service.ThirdPartyInterfaceService;
import com.bit.sc.module.sys.dao.AddressMapper;
import com.bit.sc.module.sys.pojo.Address;
import com.bit.sc.module.sys.service.AddressCommonService;
import com.bit.sc.module.sys.service.AddressService;
import com.bit.utils.UUIDUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * @author chenduo
 * @create 2018-12-28 9:20
 */
@Service("addressCommonService")
public class AddressCommonServiceImpl extends BaseService implements AddressCommonService {

    @Autowired
    private ThirdPartyInterfaceService thirdPartyInterfaceService;
    @Autowired
    private AddressService addressService;
    @Autowired
    private AddressMapper addressMapper;
    @Autowired
    private AttachmentService attachmentService;

    @Override
    public BaseVo villageAdd(Address address) {
        BaseVo baseVo = new BaseVo();
        OrgDTO orgDTO = new OrgDTO();
        String orgId = UUIDUtil.getUUID();
        orgDTO.setOrgId(orgId);
        orgDTO.setOrgName(address.getAddressName());
        orgDTO.setOrgEmail(orgId+"@qq.com");
        orgDTO.setValidDateEnd(Const.PLATFORM_VALIDATE_DATE_END);

//        Attachment attachment = new Attachment();
//        List<Attachment> fileList = address.getFileList();
//        attachment=fileList.get(0);
//        orgDTO.setLogo(attachment.getFileId());

        OrgDTO dto = thirdPartyInterfaceService.createOrg(orgDTO);
        address.setOrgId(orgId);
        address.setAuthKey(dto.getAuthKey());
        addressService.villageAdd(address);
        return baseVo;
    }

    @Override
    public BaseVo villageModify(Address address) {
        BaseVo baseVo = new BaseVo();
        OrgDTO orgDTO = new OrgDTO();
        orgDTO.setOrgName(address.getAddressName());
        Address obj = addressMapper.findByPrimaryKey(address.getId());
        orgDTO.setOrgId(obj.getOrgId());
        orgDTO.setValidDateEnd(Const.PLATFORM_VALIDATE_DATE_END);
        String orgId = UUIDUtil.getUUID();
        orgDTO.setOrgEmail(orgId+"@qq.com");

//        Attachment attachment = new Attachment();
//        List<Attachment> fileList = address.getFileList();
//        attachment=fileList.get(0);
//        orgDTO.setLogo(attachment.getFileId());

        OrgDTO dto = thirdPartyInterfaceService.updateOrg(orgDTO);


        addressService.villageModify(address);
        return baseVo;
    }

    @Override
    public BaseVo uploadFiles(MultipartFile file) {
        Attachment attachment = new Attachment();
        attachment.setBusinessId(ModuleFileType.RESIDENT_IMAGE);
        AttachmentThirdVO attachmentThirdVO = attachmentService.addAttachmentThird(attachment,file);
        Attachment att = attachmentService.findByAttachmentId(attachmentThirdVO.getAttachmentId());
        BaseVo baseVo = new BaseVo();
        baseVo.setData(att);
        return baseVo;
    }
}
