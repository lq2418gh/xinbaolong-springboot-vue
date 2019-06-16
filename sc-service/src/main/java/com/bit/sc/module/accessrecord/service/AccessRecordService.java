package com.bit.sc.module.accessrecord.service;

import com.bit.base.vo.BaseVo;
import com.bit.sc.module.accessrecord.vo.AccessRecordVo;

/**
 * 访问日志的Service
 * @author mifei
 */
public interface AccessRecordService {

	BaseVo findPage(AccessRecordVo accessRecordVo);
}
