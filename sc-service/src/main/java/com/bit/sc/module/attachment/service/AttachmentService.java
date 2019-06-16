package com.bit.sc.module.attachment.service;

import java.util.List;

import com.bit.sc.module.attachment.vo.AttachmentThirdVO;
import org.springframework.stereotype.Service;
import com.bit.sc.module.attachment.pojo.Attachment;
import com.bit.sc.module.attachment.vo.AttachmentVO;
import com.bit.base.vo.BaseVo;
import org.springframework.web.multipart.MultipartFile;

/**
 * Attachment的Service
 * @author liuyancheng
 */
@Service
public interface AttachmentService {
	/**
	 * 通过主键查询单个Attachment
	 * @param attachmentId
	 * @return
	 */
	Attachment findByAttachmentId(Long attachmentId);
	/**
	 * 保存附件服务方法，返回主键ID
	 * @param attachment
	 */
	Long addAttachment(Attachment attachment,MultipartFile multipartFile);

	/**
	 * 根据入参查询对应的数据
	 * @param attachment
	 * @return
	 */
	List<Attachment> findAllByParams(Attachment attachment);

	/**
	 * 调用第三方保存附件服务方法，返回主键ID和fileId
	 * @param attachment
	 * @param multipartFile
	 * @return
	 */
	AttachmentThirdVO addAttachmentThird(Attachment attachment,MultipartFile multipartFile);
	/**
	 *
	 * 功能描述:更新附件表
	 *
	 * @param:
	 * @return:
	 * @author: chenduo
	 * @date: 2018/12/29 11:04
	 */

	void updateAttachment(Attachment attachment);
	/**
	 *
	 * 功能描述:删除附件表
	 *
	 * @param:
	 * @return:
	 * @author: chenduo
	 * @date: 2018/12/29 11:11
	 */

	void deleteAttachment(Long id);
}
