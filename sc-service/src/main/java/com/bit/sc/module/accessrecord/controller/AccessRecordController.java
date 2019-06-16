package com.bit.sc.module.accessrecord.controller;

import com.bit.base.vo.BaseVo;
import com.bit.sc.module.accessrecord.service.AccessRecordService;
import com.bit.sc.module.accessrecord.vo.AccessRecordVo;
import com.bit.sc.module.attachment.pojo.Attachment;
import com.bit.sc.module.attachment.vo.AttachmentVO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;

/**
 * 访问日志的相关请求
 * @author mifei
 */
@RestController
@RequestMapping(value = "/accessRecord")
public class AccessRecordController {

	private static final Logger logger = LoggerFactory.getLogger(AccessRecordController.class);

	@Autowired
	private AccessRecordService accessRecordService;


	/**
	 * 分页查询日志
	 * @param type
	 * @param accessRecordVo
	 * @return
	 */
	@PostMapping("/findPage/{type}")
	public BaseVo findPage(@PathVariable(value = "type") int type, @RequestBody AccessRecordVo accessRecordVo) {
		accessRecordVo.setTableName("access_record:"+type);
		return accessRecordService.findPage(accessRecordVo);
	}
}
