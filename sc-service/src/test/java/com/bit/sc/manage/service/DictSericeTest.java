package com.bit.sc.manage.service;

import com.bit.sc.ScServerApplication;
import com.bit.sc.module.sys.dao.DictMapper;
import com.bit.sc.module.sys.pojo.Dict;
import com.bit.sc.module.sys.service.DictService;
import com.bit.sc.module.sys.vo.DictVO;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import java.util.List;
import java.util.Map;

/**
 * Dict的Service实现类
 * @author codeGenerator
 *
 */
@RunWith(SpringJUnit4ClassRunner.class) // SpringJUnit支持，由此引入Spring-Test框架支持！
@SpringBootTest(classes = ScServerApplication.class)
@WebAppConfiguration
public class DictSericeTest {
	
	private static final Logger logger = LoggerFactory.getLogger(DictSericeTest.class);
	
	@Autowired
	private DictService dictService;
	@Autowired
	private DictMapper dictMapper;

	@Test
	public void test(){
		DictVO dictVO = new DictVO();
		dictMapper.findByConditionPage(dictVO);
	}
//	@Test
//	public void getDict(){
//		dictService.getDict("all/all/sex");
//	}
//	@Test
//	public void delete(){
//		dictService.delete(1L);
//	}
//	@Test
//	public void add(){
//		Dict dict = new Dict();
//		dict.setModule("/all/all/sex");
//		dict.setDictCode("01");
//		dict.setValue("");
//		dictService.add(dict);
//	}
}
