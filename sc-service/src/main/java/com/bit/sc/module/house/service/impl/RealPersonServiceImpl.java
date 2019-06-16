package com.bit.sc.module.house.service.impl;

import com.bit.base.exception.BusinessException;
import com.bit.base.service.BaseService;
import com.bit.base.vo.BaseVo;
import com.bit.sc.common.Const;
import com.bit.sc.common.ModuleFileType;
import com.bit.sc.module.attachment.dao.AttachmentMapper;
import com.bit.sc.module.attachment.pojo.Attachment;
import com.bit.sc.module.attachment.service.AttachmentService;
import com.bit.sc.module.house.dao.RealPersonMapper;
import com.bit.sc.module.house.pojo.RealPerson;
import com.bit.sc.module.house.service.RealPersonService;
import com.bit.sc.module.house.vo.RealPersonDictVO;
import com.bit.sc.module.manface.dao.ManfacewhitelistMapper;
import com.bit.sc.module.manface.pojo.Manfacewhitelist;
import com.bit.sc.module.resident.dao.ResidentMapper;
import com.bit.sc.module.resident.pojo.Resident;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * RealPerson的Service实现类
 * @author chenduo
 *
 */
@Service("realPersonService")
public class RealPersonServiceImpl extends BaseService implements RealPersonService {
	
	private static final Logger logger = LoggerFactory.getLogger(RealPersonServiceImpl.class);
	
	@Autowired
	private RealPersonMapper realPersonMapper;
	@Autowired
    private AttachmentService attachmentService;
	@Autowired
    private AttachmentMapper attachmentMapper;
	@Autowired
    private ResidentMapper residentMapper;

	@Autowired
    private ManfacewhitelistMapper manfacewhitelistMapper;
	

	/**
	 * 保存RealPerson
	 * @param realPerson
	 */
	@Override
    @Transactional
	public BaseVo add(RealPerson realPerson){
		realPersonMapper.add(realPerson);
		RealPerson rp = realPersonMapper.findById(realPerson.getId());
		BaseVo baseVo = new BaseVo();
		baseVo.setData(rp);

        return baseVo;
	}

	/**
	 * 更新RealPerson
	 * @param realPerson
	 */
	@Override
    @Transactional
	public void update(RealPerson realPerson){


        //更新实有人口信息
		realPersonMapper.update(realPerson);
	}



	/**
	 * 删除RealPerson
	 * @param id
	 */
	@Override
    @Transactional
	public void delete(Long id){
	    RealPerson realPerson = realPersonMapper.findById(id);
	    Resident resident = residentMapper.findBygmsfzhm(realPerson.getCardId());

		//todo 删除实有人员后对应的白名单全部置为停用
        Manfacewhitelist manfacewhitelist = new Manfacewhitelist();
        manfacewhitelist.setSynchroStatus(Const.MANFACE_WHITELIST_SYNCHRO_STATUS_NOT_USE);
        if (manfacewhitelistMapper.findById(resident.getId())==null){
            throw new BusinessException("此人尚未添加白名单");
        }
        manfacewhitelistMapper.updateStatusByResidentId(resident.getId());

        //todo 并将白名单从硬件设备上删除

        realPersonMapper.delete(id);

	}

	/**
	 *
	 * 功能描述:上传照片
	 *
	 * @param:
	 * @return:
	 * @author: chenduo
	 * @date: 2018/11/27 13:39
	 */
	@Override
	public BaseVo uploadpicture(MultipartFile file) {
		Attachment attachment = new Attachment();
		attachment.setBusinessId(ModuleFileType.RESIDENT_IMAGE);
		Long addAttachment  = attachmentService.addAttachment(attachment,file);
		attachmentService.findByAttachmentId(addAttachment);
		BaseVo baseVo = new BaseVo();
		baseVo.setData(attachment);


		return baseVo;
	}


    /**
     *
     * 功能描述:根据id查询记录 用于反显
     *
     * @param:
     * @return:
     * @author: chenduo
     * @date: 2018/11/28 15:38
     */
    @Override
    public BaseVo queryData(Long id) {
        RealPerson realPerson = realPersonMapper.findById(id);
        Long imgid= realPerson.getPicture();
        Attachment attachment = attachmentMapper.findByAttachmentId(imgid);
        String path = attachment.getAttachmentPath();
        //批量查询附件
        String certificatePicture = realPerson.getCertificatePicture();
        String[] str = certificatePicture.split(",");
        Long[] ids =new Long[str.length];
        for (int i =0 ;i<str.length;i++){
            ids[i]=Long.parseLong(str[i]);
        }
        List<Attachment> attachments = attachmentMapper.batchSelect(ids);



        RealPersonDictVO realPersonDictVO =new RealPersonDictVO();
        BeanUtils.copyProperties(realPerson,realPersonDictVO);
        realPersonDictVO.setPath(path);
        realPersonDictVO.setAttachmentList(attachments);
        BaseVo baseVo = new BaseVo();
        baseVo.setData(realPersonDictVO);
        return baseVo;
    }
	/**
	 *
	 * 功能描述:根据houseid查询结果
	 *
	 * @param:
	 * @return:
	 * @author: chenduo
	 * @date: 2018/12/3 11:55
	 */
    @Override
    public List<RealPerson> findByHouseId(Long id) {
        return realPersonMapper.findByHouseId(id);
    }

}
