package com.bit.sc.module.house.service.impl;

import java.util.List;
import java.util.ArrayList;

import com.bit.sc.module.house.dao.RealCompanyMapper;
import com.bit.sc.module.house.pojo.RealCompany;
import com.bit.sc.module.house.service.RealCompanyService;
import com.bit.sc.module.house.vo.RealCompanyDictVO;
import com.bit.sc.module.house.vo.RealCompanyVO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.bit.base.vo.BaseVo;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.bit.base.service.BaseService;
import org.springframework.transaction.annotation.Transactional;

/**
 * RealCompany的Service实现类
 * @author chenduo
 *
 */
@Service("realCompanyService")
public class RealCompanyServiceImpl extends BaseService implements RealCompanyService {
	
	private static final Logger logger = LoggerFactory.getLogger(RealCompanyServiceImpl.class);
	
	@Autowired
	private RealCompanyMapper realCompanyMapper;
	
	/**
	 * 根据条件查询RealCompany
	 * @param realCompanyVO
	 * @return
	 */
	@Override
	public BaseVo findByConditionPage(RealCompanyVO realCompanyVO){
		PageHelper.startPage(realCompanyVO.getPageNum(), realCompanyVO.getPageSize());
		List<RealCompany> list = realCompanyMapper.findByConditionPage(realCompanyVO);
		PageInfo<RealCompany> pageInfo = new PageInfo<RealCompany>(list);
		BaseVo baseVo = new BaseVo();
		baseVo.setData(pageInfo);
		return baseVo;
	}
	/**
	 * 查询所有RealCompany
	 * @param sorter 排序字符串
	 * @return
	 */
	@Override
	public List<RealCompany> findAll(String sorter){
		return realCompanyMapper.findAll(sorter);
	}
	/**
	 * 通过主键查询单个RealCompany
	 * @param id
	 * @return
	 */
	@Override
	public RealCompany findById(Long id){
		return realCompanyMapper.findById(id);
	}
	
	/**
	 * 批量保存RealCompany
	 * @param realCompanys
	 */
	@Override
	@Transactional
	public void batchAdd(List<RealCompany> realCompanys){
		realCompanyMapper.batchAdd(realCompanys);
	}
	/**
	 * 保存RealCompany
	 * @param realCompany
	 */
	@Override
	@Transactional
	public void add(RealCompany realCompany){
		realCompanyMapper.add(realCompany);
	}
	/**
	 * 批量更新RealCompany
	 * @param realCompanys
	 */
	@Override
	@Transactional
	public void batchUpdate(List<RealCompany> realCompanys){
		realCompanyMapper.batchUpdate(realCompanys);
	}
	/**
	 * 更新RealCompany
	 * @param realCompany
	 */
	@Override
	@Transactional
	public void update(RealCompany realCompany){
		realCompanyMapper.update(realCompany);
	}
	/**
	 * 删除RealCompany
	 * @param ids
	 */
	@Override
	@Transactional
	public void batchDelete(List<Long> ids){
		realCompanyMapper.batchDelete(ids);
	}



	/**
	 * 删除RealCompany
	 * @param id
	 */
	@Override
	@Transactional
	public void delete(Long id){
		realCompanyMapper.delete(id);
	}

	/**
	 *
	 * 功能描述:联合字典表分页查询
	 *
	 * @param:
	 * @return:
	 * @author: chenduo
	 * @date: 2018/11/23 15:12
	 */
	@Override
	public BaseVo querylistAllDictByPage(RealCompanyVO realCompanyVO) {
        PageHelper.startPage(realCompanyVO.getPageNum(), realCompanyVO.getPageSize());
        List<RealCompanyDictVO> list = realCompanyMapper.querylistAllDictByPage(realCompanyVO);
        PageInfo<RealCompanyDictVO> pageInfo = new PageInfo<RealCompanyDictVO>(list);
        BaseVo baseVo = new BaseVo();
        baseVo.setData(pageInfo);
        return baseVo;
	}
}
