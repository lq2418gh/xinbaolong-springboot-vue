package com.bit.sc.module.attachment.controller;

import com.bit.base.vo.BaseVo;
import com.bit.sc.module.attachment.service.AttachmentService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * Attachment的相关请求
 * @author liuyancheng
 */
@RestController
@RequestMapping(value = "/attachment")
public class AttachmentController {
	private static final Logger logger = LoggerFactory.getLogger(AttachmentController.class);

	@Autowired
	private AttachmentService attachmentService;

	/**
	 * 删除
	 * @param attachmentId
	 * @return
	 */
	@GetMapping("/delete/{id}")
	public BaseVo delete(@PathVariable(value = "id") Long attachmentId){
		attachmentService.deleteAttachment(attachmentId);
		BaseVo baseVo = new BaseVo();
		return baseVo;
	}
}
