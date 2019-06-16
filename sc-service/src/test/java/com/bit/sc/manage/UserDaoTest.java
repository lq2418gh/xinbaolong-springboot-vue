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
import com.bit.sc.module.user.pojo.User;
import com.bit.sc.module.user.vo.UserVO;
import com.bit.sc.module.user.dao.UserMapper;
import com.bit.sc.module.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
/**
 * User的Service实现类
 * @author codeGenerator
 *
 */
@RunWith(SpringJUnit4ClassRunner.class) // SpringJUnit支持，由此引入Spring-Test框架支持！
@SpringBootTest(classes = ScServerApplication.class)
@WebAppConfiguration
public class UserDaoTest{

	private static final Logger logger = LoggerFactory.getLogger(UserDaoTest.class);

	@Autowired
	private UserMapper userMapper;

	/**
	 * 根据条件查询User
	 * @param page
	 * @return
	 */
	@Test
	public void findByConditionPage(){
		UserVO userVO = new UserVO() ;
		PageHelper.startPage(userVO.getPageNum(), userVO.getPageSize());
		List<User> list = userMapper.findByConditionPage(userVO);
		PageInfo<User> pageInfo = new PageInfo<User>(list);
		userVO.setData(pageInfo);
	}
	/**
	 * 查询所有User
	 * @param sorter 排序字符串
	 * @return
	 */
	@Test
	public void findAll(){
		userMapper.findAll(null);
	}
	/**
	 * 通过主键查询单个User
	 * @param id
	 * @return
	 */
	@Test
	public void findById(){ userMapper.findById(1L);

	}

	/**
	 * 批量保存User
	 * @param users
	 */
	@Test
	public void batchAdd(){
		userMapper.batchAdd(null);
	}
	/**
	 * 保存User
	 * @param user
	 */
	@Test
	public void add(){

		userMapper.add(null);
	}
	/**
	 * 批量更新User
	 * @param users
	 */
	@Test
	public void batchUpdate(){
		userMapper.batchUpdate(null);
	}
	/**
	 * 更新User
	 * @param user
	 */
	@Test
	public void update(){
		userMapper.update(null);
	}
	/**
	 * 删除User
	 * @param ids
	 */
	@Test
	public void batchDelete(){
		userMapper.batchDelete(null);
	}
	/**
	 * 删除User
	 * @param id
	 */
	@Test
	public void delete(){
		userMapper.delete(1L);
	}
}
