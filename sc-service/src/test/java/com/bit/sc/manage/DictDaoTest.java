package com.bit.sc.manage;

import java.util.List;
import java.util.ArrayList;

import com.bit.sc.ScServerApplication;
import org.junit.runner.RunWith;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.bit.base.vo.BaseVo;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.bit.sc.module.sys.pojo.Dict;
import com.bit.sc.module.sys.vo.DictVO;
import com.bit.sc.module.sys.dao.DictMapper;
import com.bit.sc.module.sys.service.DictService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
/**
 * Dict的Service实现类
 * @author codeGenerator
 *
 */
@RunWith(SpringJUnit4ClassRunner.class) // SpringJUnit支持，由此引入Spring-Test框架支持！
@SpringBootTest(classes = ScServerApplication.class)
@WebAppConfiguration
public class DictDaoTest{
	
	private static final Logger logger = LoggerFactory.getLogger(DictDaoTest.class);
	
	@Autowired
	private DictMapper dictMapper;
	
	/**
	 * 根据条件查询Dict
	 * @param page
	 * @return
	 */
	@Test
	public void findByConditionPage(){
		DictVO dictVO = new DictVO() ;
		PageHelper.startPage(dictVO.getPageNum(), dictVO.getPageSize());
		List<Dict> list = dictMapper.findByConditionPage(dictVO);
		PageInfo<Dict> pageInfo = new PageInfo<Dict>(list);
		dictVO.setData(pageInfo);
	}
	/**
	 * 查询所有Dict
	 * @param sorter 排序字符串
	 * @return
	 */
	@Test
	public void findAll(){
		dictMapper.findAll(null);
	}
	/**
	 * 通过主键查询单个Dict
	 * @param id
	 * @return
	 */
	@Test
	public void findById(){
		 dictMapper.findById(1L);
	}

	/**
	 * 批量保存Dict
	 * @param dicts
	 */
	@Test
	public void batchAdd(){
		dictMapper.batchAdd(null);
	}
	/**
	 * 保存Dict
	 * @param dict
	 */
	@Test
	public void add(){
		Dict dict = new Dict();
		dict.setDictCode("03");
		dict.setRank(1);
		dict.setValue("男");
		dict.setModule("aaa/dfsdf/");
		dictMapper.add(dict);
	}
	/**
	 * 批量更新Dict
	 * @param dicts
	 */
	@Test
	public void batchUpdate(){
		dictMapper.batchUpdate(null);
	}
	/**
	 * 更新Dict
	 * @param dict
	 */
@Test
	public void update(){
		dictMapper.update(null);
	}
	/**
	 * 删除Dict
	 * @param ids
	 */
	@Test
	public void batchDelete(){
		dictMapper.batchDelete(null);
	}
	/**
	 * 删除Dict
	 * @param id
	 */
	@Test
	public void delete(){
		dictMapper.delete(1L);
	}
}
