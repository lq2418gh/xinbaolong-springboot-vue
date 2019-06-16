package com.bit.sc.module.sys.service.impl;

import com.bit.sc.module.sys.dao.DictMapper;
import com.bit.sc.module.sys.pojo.Dict;
import com.bit.sc.module.sys.service.DictService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Dict的Service实现类
 * @author chenduo
 *
 */
@Service("dictService")
public class DictServiceImpl implements DictService{
	
	private static final Logger logger = LoggerFactory.getLogger(DictServiceImpl.class);
	
	@Autowired
	private DictMapper dictMapper;



	/**
	 *
	 * 功能描述: 根据传过来的module值 查询这一类的数据 不分页
	 *
	 * @param:
	 * @return:
	 * @author: chenduo
	 * @date: 2018/11/14 11:37
	 */
	@Override
	public List<Dict> getModule(String module) {
		List<Dict> dictList = dictMapper.listModule(module);
		return dictList;
	}

}
