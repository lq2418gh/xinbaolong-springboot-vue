package com.bit.sc.module.sys.service.impl;

import com.bit.sc.ScServerApplication;
import com.bit.sc.module.sys.dao.InterfacePermissionMapper;
import com.bit.sc.module.sys.dao.RoleMapper;
import com.bit.sc.module.sys.pojo.InterfacePermission;
import com.bit.sc.module.sys.vo.InterfacePermissionVO;
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

/**
 * InterfacePermission的Service实现类
 * @author codeGenerator
 *
 */
@RunWith(SpringJUnit4ClassRunner.class) // SpringJUnit支持，由此引入Spring-Test框架支持！
@SpringBootTest(classes = ScServerApplication.class)
@WebAppConfiguration
public class InterfacePermissionDaoTest {
	
	private static final Logger logger = LoggerFactory.getLogger(InterfacePermissionDaoTest.class);
	
	@Autowired
	private InterfacePermissionMapper interfacePermissionMapper;


	/**
	 * 根据条件查询InterfacePermission
	 * @param page
	 * @return
	 */
	@Test
	public void findByConditionPage(){
		InterfacePermissionVO interfacePermissionVO = new InterfacePermissionVO() ;
		PageHelper.startPage(interfacePermissionVO.getPageNum(), interfacePermissionVO.getPageSize());
		List<InterfacePermission> list = interfacePermissionMapper.findByConditionPage(interfacePermissionVO);
		PageInfo<InterfacePermission> pageInfo = new PageInfo<InterfacePermission>(list);
		interfacePermissionVO.setData(pageInfo);

	}
	/**
	 * 查询所有InterfacePermission
	 * @param sorter 排序字符串
	 * @return
	 */
	@Test
	public void findAll(){
        List<InterfacePermission> all = interfacePermissionMapper.findAll(null);
        System.out.println(all);
    }
	/**
	 * 通过主键查询单个InterfacePermission
	 * @param id
	 * @return
	 */
	@Test
	public void findById(){

        interfacePermissionMapper.findById(1L);

    }

	/**
	 * 批量保存InterfacePermission
	 * @param interfacePermissions
	 */
	@Test
	public void batchAdd(){
		interfacePermissionMapper.batchAdd(null);
	}
	/**
	 * 保存InterfacePermission
	 * @param interfacePermission
	 */
	@Test
	public void add(){
		InterfacePermission interfacePermission = new InterfacePermission() ;
		interfacePermissionMapper.add(interfacePermission);
	}
	/**
	 * 批量更新InterfacePermission
	 * @param interfacePermissions
	 */
	@Test
	public void batchUpdate(){
		interfacePermissionMapper.batchUpdate(null);
	}
	/**
	 * 更新InterfacePermission
	 * @param interfacePermission
	 */
@Test
	public void update(){
		interfacePermissionMapper.update(null);
	}
	/**
	 * 删除InterfacePermission
	 * @param ids
	 */

	/**
	 * 删除InterfacePermission
	 * @param id
	 */
	@Test
	public void delete(){
		interfacePermissionMapper.delete(2L);
	}

	@Test
	public void findPermissionById(){
		List<InterfacePermission> permissionList = interfacePermissionMapper.findPermissionById(5L);
		System.out.println(permissionList);

    }

}
