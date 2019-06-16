package com.bit.sc.module.sys.service.impl;

import java.util.List;
import java.util.ArrayList;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.bit.base.vo.BaseVo;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.bit.sc.module.sys.pojo.Apply;
import com.bit.sc.module.sys.vo.ApplyVO;
import com.bit.sc.module.sys.dao.ApplyMapper;
import com.bit.sc.module.sys.service.ApplyService;
/**
 * Apply的Service实现类
 * @author codeGenerator
 *
 */
@Service("applyService")
public class ApplyServiceImpl implements ApplyService{
	
	private static final Logger logger = LoggerFactory.getLogger(ApplyServiceImpl.class);
	
	@Autowired
	private ApplyMapper applyMapper;
	
	/**
	 * 根据条件查询Apply
	 * @param applyVO
	 * @return
	 */
	@Override
	public BaseVo findByConditionPage(ApplyVO applyVO){
		PageHelper.startPage(applyVO.getPageNum(), applyVO.getPageSize());
		List<Apply> list = applyMapper.findByConditionPage(applyVO);
		PageInfo<Apply> pageInfo = new PageInfo<Apply>(list);
		applyVO.setData(pageInfo);
		return applyVO;
	}
	/**
	 * 查询所有Apply
	 * @param sorter 排序字符串
	 * @return
	 */
	@Override
	public List<Apply> findAll(String sorter){
		return applyMapper.findAll(sorter);
	}
	/**
	 * 通过主键查询单个Apply
	 * @param id
	 * @return
	 */
	@Override
	public Apply findById(Long id){
		return applyMapper.findById(id);
	}
	
	/**
	 * 批量保存Apply
	 * @param applys
	 */
	@Override
	public void batchAdd(List<Apply> applys){
		applyMapper.batchAdd(applys);
	}
	/**
	 * 保存Apply
	 * @param apply
	 */
	@Override
	public void add(Apply apply){
		applyMapper.add(apply);
	}
	/**
	 * 批量更新Apply
	 * @param applys
	 */
	@Override
	public void batchUpdate(List<Apply> applys){
		applyMapper.batchUpdate(applys);
	}
	/**
	 * 更新Apply
	 * @param apply
	 */
	@Override
	public void update(Apply apply){
		applyMapper.update(apply);
	}
	/**
	 * 删除Apply
	 * @param ids
	 */
	@Override
	public void batchDelete(List<Long> ids){
		applyMapper.batchDelete(ids);
	}

	/**
	 * 根据参数查询搜游
	 * @param applyVO
	 * @return
	 */
	@Override
	public List<Apply> findByParm(ApplyVO applyVO) {
		return applyMapper.findByParm(applyVO);
	}

	@Override
	public List<Apply> getNoneResident() {
		return applyMapper.getNoneResident();
	}

	/**
	 * 删除Apply
	 * @param id
	 */
	@Override
	public void delete(Long id){
		applyMapper.delete(id);
	}
}
