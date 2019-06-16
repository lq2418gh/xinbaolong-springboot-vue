package com.bit.sc.module.sys.service.impl;

import java.util.List;

import com.bit.sc.module.sys.dao.ResidentRelAddressMapper;
import com.bit.sc.module.sys.pojo.ResidentRelAddress;
import com.bit.sc.module.sys.service.ResidentRelAddressService;
import com.bit.sc.module.sys.vo.ResidentRelAddressVO;
import com.bit.sc.utils.CheckUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.bit.base.vo.BaseVo;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.bit.base.service.BaseService;
import org.springframework.transaction.annotation.Transactional;

/**
 * ResidentRelAddress的Service实现类
 * @author liqi
 *
 */
@Service("residentRelAddressService")
public class ResidentRelAddressServiceImpl extends BaseService implements ResidentRelAddressService {
	

	@Autowired
	private ResidentRelAddressMapper residentRelAddressMapper;
	
	/**
	 * 根据条件查询residentRelAddress
	 * @param
	 * @return
	 */
	@Override
	public BaseVo findByConditionPage(ResidentRelAddressVO residentRelAddressVO){
		PageHelper.startPage(residentRelAddressVO.getPageNum(), residentRelAddressVO.getPageSize());
		List<ResidentRelAddress> list = residentRelAddressMapper.findByConditionPage(residentRelAddressVO);
		PageInfo<ResidentRelAddress> pageInfo = new PageInfo<ResidentRelAddress>(list);
		residentRelAddressVO.setData(pageInfo);
		return residentRelAddressVO;
	}
	/**
	 * 查询所有ResidentRelAddress
	 * @param sorter 排序字符串
	 * @return
	 */
	@Override
	public List<ResidentRelAddress> findAll(String sorter){
		return residentRelAddressMapper.findAll(sorter);
	}
	/**
	 * 通过主键查询单个ResidentRelAddress
	 * @param id
	 * @return
	 */
	@Override
	public ResidentRelAddress findById(Long id){
		return residentRelAddressMapper.findById(id);
	}
	
	/**
	 * 批量保存ResidentRelAddress
	 * @param residentRelAddresss
	 */
	@Override
	@Transactional
	public void batchAdd(List<ResidentRelAddress> residentRelAddresss){
		residentRelAddressMapper.batchAdd(residentRelAddresss);
	}
	/**
	 * 保存ResidentRelAddress
	 * @param residentRelAddress
	 */
	@Override
	@Transactional
	public void add(ResidentRelAddress residentRelAddress){
		CheckUtil.notNull(residentRelAddress.getId(),"id不能为空");
		CheckUtil.notNull(residentRelAddress.getAddressId(),"地址id不能为空");
		CheckUtil.notNull(residentRelAddress.getResidentId(),"居民id不能为空");
		residentRelAddressMapper.add(residentRelAddress);
	}
	/**
	 * 批量更新ResidentRelAddress
	 * @param residentRelAddresss
	 */
	@Override
	@Transactional
	public void batchUpdate(List<ResidentRelAddress> residentRelAddresss){
		residentRelAddressMapper.batchUpdate(residentRelAddresss);
	}
	/**
	 * 更新ResidentRelAddress
	 * @param residentRelAddress
	 */
	@Override
	@Transactional
	public void update(ResidentRelAddress residentRelAddress){
		residentRelAddressMapper.update(residentRelAddress);
	}
	/**
	 * 删除ResidentRelAddress
	 * @param ids
	 */
	@Override
	@Transactional
	public void batchDelete(List<Long> ids){
		residentRelAddressMapper.batchDelete(ids);
	}
	/**
	 * 删除ResidentRelAddress
	 * @param id
	 */
	@Override
	@Transactional
	public void delete(Long id){
		residentRelAddressMapper.delete(id);
	}
}
