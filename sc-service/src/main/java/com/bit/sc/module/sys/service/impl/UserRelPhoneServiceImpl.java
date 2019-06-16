package com.bit.sc.module.sys.service.impl;

import java.util.List;

import com.bit.sc.module.sys.service.UserRelPhoneService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.bit.base.vo.BaseVo;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.bit.sc.module.sys.pojo.UserRelPhone;
import com.bit.sc.module.sys.vo.UserRelPhoneVO;
import com.bit.sc.module.sys.dao.UserRelPhoneMapper;

/**
 * UserRelPhone的Service实现类
 * @author codeGenerator
 *
 */
@Service("userRelPhoneService")
public class UserRelPhoneServiceImpl implements UserRelPhoneService {
	
	private static final Logger logger = LoggerFactory.getLogger(UserRelPhoneServiceImpl.class);
	
	@Autowired
	private UserRelPhoneMapper userRelPhoneMapper;
	
	/**
	 * 根据条件查询UserRelPhone
	 * @param userRelPhoneVO
	 * @return
	 */
	@Override
	public BaseVo findByConditionPage(UserRelPhoneVO userRelPhoneVO){
		PageHelper.startPage(userRelPhoneVO.getPageNum(), userRelPhoneVO.getPageSize());
		List<UserRelPhone> list = userRelPhoneMapper.findByConditionPage(userRelPhoneVO);
		PageInfo<UserRelPhone> pageInfo = new PageInfo<UserRelPhone>(list);
		userRelPhoneVO.setData(pageInfo);
		return userRelPhoneVO;
	}
	/**
	 * 查询所有UserRelPhone
	 * @param sorter 排序字符串
	 * @return
	 */
	@Override
	public List<UserRelPhone> findAll(String sorter){
		return userRelPhoneMapper.findAll(sorter);
	}
	/**
	 * 通过主键查询单个UserRelPhone
	 * @param id
	 * @return
	 */
	@Override
	public UserRelPhone findById(Long id){
		return userRelPhoneMapper.findById(id);
	}
	
	/**
	 * 批量保存UserRelPhone
	 * @param userRelPhones
	 */
	@Override
	public void batchAdd(List<UserRelPhone> userRelPhones){
		userRelPhoneMapper.batchAdd(userRelPhones);
	}
	/**
	 * 保存UserRelPhone
	 * @param userRelPhone
	 */
	@Override
	public void add(UserRelPhone userRelPhone){
		userRelPhoneMapper.add(userRelPhone);
	}
	/**
	 * 批量更新UserRelPhone
	 * @param userRelPhones
	 */
	@Override
	public void batchUpdate(List<UserRelPhone> userRelPhones){
		userRelPhoneMapper.batchUpdate(userRelPhones);
	}
	/**
	 * 更新UserRelPhone
	 * @param userRelPhone
	 */
	@Override
	public void update(UserRelPhone userRelPhone){
		userRelPhoneMapper.update(userRelPhone);
	}
	/**
	 * 删除UserRelPhone
	 * @param ids
	 */
	@Override
	public void batchDelete(List<Long> ids){
		userRelPhoneMapper.batchDelete(ids);
	}
	/**
	 * 删除UserRelPhone
	 * @param id
	 */
	@Override
	public void delete(Long id){
		userRelPhoneMapper.delete(id);
	}
}
