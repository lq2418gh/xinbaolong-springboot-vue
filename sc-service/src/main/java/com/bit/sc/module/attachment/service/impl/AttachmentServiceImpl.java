package com.bit.sc.module.attachment.service.impl;

import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.ArrayList;

import com.bit.base.exception.BusinessException;
import com.bit.base.vo.UserInfo;
import com.bit.sc.common.fastdfs.FastDFSFile;
import com.bit.sc.module.attachment.vo.AttachmentThirdVO;
import com.bit.sc.module.restTemplate.dto.FileDTO;
import com.bit.sc.module.restTemplate.service.ThirdPartyInterfaceService;
import com.bit.sc.utils.FastDFSFileUtil;
import com.bit.sc.utils.FileUtil;
import com.bit.utils.StringUtil;
import org.apache.commons.io.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.bit.base.vo.BaseVo;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.bit.sc.module.attachment.pojo.Attachment;
import com.bit.sc.module.attachment.vo.AttachmentVO;
import com.bit.sc.module.attachment.dao.AttachmentMapper;
import com.bit.sc.module.attachment.service.AttachmentService;
import com.bit.base.service.BaseService;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

/**
 * Attachment的Service实现类
 * @author liuyancheng
 *
 */
@Service("attachmentService")
public class AttachmentServiceImpl extends BaseService implements AttachmentService{
	
	private static final Logger logger = LoggerFactory.getLogger(AttachmentServiceImpl.class);
	
	@Autowired
	private AttachmentMapper attachmentMapper;
	@Autowired
	private ThirdPartyInterfaceService thirdPartyInterfaceService;
	
	/**
	 * 通过主键查询单个Attachment
	 * @param attachmentId
	 * @return
	 */
	@Override
	public Attachment findByAttachmentId(Long attachmentId){
		return attachmentMapper.findByAttachmentId(attachmentId);
	}

	/**
	 * 新增附件服务，返回主键ID
	 * @param attachment
	 * @param multipartFile
	 */
	@Override
	@Transactional
	public Long addAttachment(Attachment attachment,MultipartFile multipartFile){
		//如果对象为空，则新建一个对象，防止空指针
		if (attachment == null){
			attachment = new Attachment();
		}
		String fileName = null;
		String extensionName = null;
		String fileNameNoEx = null;
		Integer i = null;
		if(multipartFile != null && !multipartFile.isEmpty()){
			// 获取上传的文件的名称
			fileName = multipartFile.getOriginalFilename();
			//获取后缀
			extensionName = FileUtil.getExtensionName(fileName);
			//判断上传的文件类型，0图片，1文件
			i = FileUtil.equalsExtensionName(extensionName);
			//获取不带后缀的文件名
			fileNameNoEx = FileUtil.getFileNameNoEx(fileName);
			//文件路径
			try {
				String path = FastDFSFileUtil.saveFile(multipartFile);
				attachment.setAttachmentPath(path);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		//附件名称
		if(fileNameNoEx != null){
			attachment.setAttachmentName(fileNameNoEx);
		}
		//附件类型(0-图片   1-文件)
		if (i != null){
			attachment.setAttachmentType(i);
		}
		//文件扩展名
		if (extensionName != null){
			attachment.setAttachmentSuffix(extensionName);
		}

		//创建人id
		Long id = getCurrentUserInfo().getId();
		if (id != null){
			attachment.setCreateUserId(id);
		}
		//创建人名称
		String username = getCurrentUserInfo().getUserName();
		if (!"".equals(username)){
			attachment.setCreateUsername(username);
		}

		//创建时间
		attachment.setCreateTime(new Date());

        //默认启用状态
        attachment.setAttachmentStatus(1);

		attachmentMapper.add(attachment);

		//返回的主键ID
		Long resultId = attachment.getAttachmentId();
		return resultId;
	}

	@Override
	public List<Attachment> findAllByParams(Attachment attachment) {
		//根据创建时间倒序排序
		List<Attachment> list = attachmentMapper.findAllByParams(attachment);
		return list;
	}

	@Override
	public AttachmentThirdVO addAttachmentThird(Attachment attachment, MultipartFile multipartFile) {
		//如果对象为空，则新建一个对象，防止空指针
		if (attachment == null){
			attachment = new Attachment();
		}
		//调用第三方上传文件接口，返回fileId
		try {
			FileDTO fileDTO = thirdPartyInterfaceService.uploadFile(multipartFile);
			if (fileDTO != null){
				attachment.setFileId(fileDTO.getFileId());

				String fileName = null;
				String extensionName = null;
				String fileNameNoEx = null;
				Integer i = null;
				if(multipartFile != null && !multipartFile.isEmpty()){
					// 获取上传的文件的名称
					fileName = multipartFile.getOriginalFilename();
					//获取后缀
					extensionName = FileUtil.getExtensionName(fileName);
					//判断上传的文件类型，0图片，1文件
					i = FileUtil.equalsExtensionName(extensionName);
					//获取不带后缀的文件名
					fileNameNoEx = FileUtil.getFileNameNoEx(fileName);
					//文件路径
					try {
						String path = FastDFSFileUtil.saveFile(multipartFile);
						attachment.setAttachmentPath(path);
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
				//附件名称
				if(fileNameNoEx != null){
					attachment.setAttachmentName(fileNameNoEx);
				}
				//附件类型(0-图片   1-文件)
				if (i != null){
					attachment.setAttachmentType(i);
				}
				//文件扩展名
				if (extensionName != null){
					attachment.setAttachmentSuffix(extensionName);
				}

				//创建人id
				UserInfo userInfo = getCurrentUserInfo();
				Long id = userInfo.getId();
				if (id != null){
					attachment.setCreateUserId(id);
				}
				//创建人名称
				String username = getCurrentUserInfo().getUserName();
				if (!"".equals(username)){
					attachment.setCreateUsername(username);
				}

				//创建时间
				attachment.setCreateTime(new Date());

				//默认启用状态
				attachment.setAttachmentStatus(1);

				attachmentMapper.add(attachment);

				//返回的主键ID
				Long resultId = attachment.getAttachmentId();

				AttachmentThirdVO attachmentThirdVO = new AttachmentThirdVO();
				attachmentThirdVO.setFileId(fileDTO.getFileId());
				attachmentThirdVO.setAttachmentId(resultId);

				return attachmentThirdVO;
			}
		} catch (Exception e){
			logger.error(e.getMessage(),e);
			throw new BusinessException(e.getMessage());
		}
		return null;
	}

	@Override
	public void updateAttachment(Attachment attachment) {
		attachmentMapper.update(attachment);
	}

	@Override
	public void deleteAttachment(Long id) {
		Attachment attachment = attachmentMapper.findByAttachmentId(id);
		try {
			FastDFSFileUtil.deleteFile(attachment.getAttachmentPath());
			attachmentMapper.delete(id);
		}catch (Exception e){
			e.printStackTrace();
		}
	}
}
