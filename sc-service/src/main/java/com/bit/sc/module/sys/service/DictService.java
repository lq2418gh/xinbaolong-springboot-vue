package com.bit.sc.module.sys.service;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;
import com.bit.sc.module.sys.pojo.Dict;
import com.bit.sc.module.sys.vo.DictVO;
import com.bit.base.vo.BaseVo;
/**
 * Dict的Service
 * @author codeGenerator
 */
@Service
public interface DictService {


	/**
	 *
	 * 功能描述: 根据模块查询所有的该模块信息
	 *
	 * @param:
	 * @return:
	 * @author: chenduo
	 * @date: 2018/11/14 11:37
	 */
	List<Dict> getModule(String module);




}
