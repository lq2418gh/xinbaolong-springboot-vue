package com.bit.sc.module.resident.service.impl;

import java.util.Date;
import java.util.List;
import java.util.ArrayList;

import com.bit.base.exception.BusinessException;
import com.bit.sc.module.resident.dao.ResidentMapper;
import com.bit.sc.module.resident.pojo.Resident;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.bit.base.vo.BaseVo;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.bit.sc.module.resident.pojo.ResidentDoorCard;
import com.bit.sc.module.resident.vo.ResidentDoorCardVO;
import com.bit.sc.module.resident.dao.ResidentDoorCardMapper;
import com.bit.sc.module.resident.service.ResidentDoorCardService;
import com.bit.base.service.BaseService;
import org.springframework.transaction.annotation.Transactional;

/**
 * ResidentDoorCard的Service实现类
 * @author liuyancheng
 *
 */
@Service("residentDoorCardService")
public class ResidentDoorCardServiceImpl extends BaseService implements ResidentDoorCardService{
	
	private static final Logger logger = LoggerFactory.getLogger(ResidentDoorCardServiceImpl.class);
	
	@Autowired
	private ResidentDoorCardMapper residentDoorCardMapper;
	@Autowired
	private ResidentMapper residentMapper;
	
	/**
	 * 根据条件查询ResidentDoorCard
	 * @return
	 */
	@Override
	public BaseVo findByConditionPage(ResidentDoorCardVO residentDoorCardVO){
		PageHelper.startPage(residentDoorCardVO.getPageNum(), residentDoorCardVO.getPageSize());
		List<ResidentDoorCard> list = residentDoorCardMapper.findByConditionPage(residentDoorCardVO);
		PageInfo<ResidentDoorCard> pageInfo = new PageInfo<ResidentDoorCard>(list);
		BaseVo baseVo = new BaseVo();
		baseVo.setData(pageInfo);
		return baseVo;
	}
	/**
	 * 查询所有ResidentDoorCard
	 * @param sorter 排序字符串
	 * @return
	 */
	@Override
	public List<ResidentDoorCard> findAll(String sorter){
		return residentDoorCardMapper.findAll(sorter);
	}
	/**
	 * 通过主键查询单个ResidentDoorCard
	 * @param id
	 * @return
	 */
	@Override
	public ResidentDoorCard findById(Long id){
		return residentDoorCardMapper.findById(id);
	}
	
	/**
	 * 批量保存ResidentDoorCard
	 * @param residentDoorCards
	 */
	@Override
	public void batchAdd(List<ResidentDoorCard> residentDoorCards){
		residentDoorCardMapper.batchAdd(residentDoorCards);
	}
	/**
	 * 保存ResidentDoorCard
	 * @param residentDoorCard
	 */
	@Override
	@Transactional
	public void add(ResidentDoorCard residentDoorCard){
		//判断门卡卡号在表里是否唯一
		ResidentDoorCard cardNum = residentDoorCardMapper.findByCardNum(residentDoorCard);
		if (cardNum == null){
			//创建时间
			residentDoorCard.setCreateTime(new Date());
			//创建人id
			Long id = getCurrentUserInfo().getId();
			if (id != null){
				residentDoorCard.setCreateUserId(id);
			}
			//创建人名称
			String userName = getCurrentUserInfo().getUserName();
			if (userName != null && !"".equals(userName)){
				residentDoorCard.setCreateUserName(userName);
			}
			residentDoorCardMapper.add(residentDoorCard);
		}else {
			throw new BusinessException("门卡卡号重复，请重新添加");
		}
	}
	/**
	 * 批量更新ResidentDoorCard
	 * @param residentDoorCards
	 */
	@Override
	public void batchUpdate(List<ResidentDoorCard> residentDoorCards){
		residentDoorCardMapper.batchUpdate(residentDoorCards);
	}
	/**
	 * 更新ResidentDoorCard
	 * @param residentDoorCard
	 */
	@Override
	@Transactional
	public void update(ResidentDoorCard residentDoorCard){
		residentDoorCardMapper.update(residentDoorCard);
	}
	/**
	 * 删除ResidentDoorCard
	 * @param ids
	 */
	@Override
	@Transactional
	public void batchDelete(List<Long> ids){
		residentDoorCardMapper.batchDelete(ids);
	}

	/**
	 * 根据居民id查询门卡列表
	 * @param residentDoorCardVO
	 * @return
	 */
	@Override
	public BaseVo findByResidentId(ResidentDoorCardVO residentDoorCardVO) {
		List<ResidentDoorCard> list = residentDoorCardMapper.findByConditionPage(residentDoorCardVO);
		BaseVo baseVo = new BaseVo();
		baseVo.setData(list);
		return baseVo;
	}

	@Override
	public BaseVo checkCard(ResidentDoorCard residentDoorCard) {
		BaseVo baseVo = new BaseVo();
		List<ResidentDoorCard> residentDoorCardList = residentDoorCardMapper.findByParam(residentDoorCard);
		if (residentDoorCardList.size()>0){
			baseVo.setData("false");
		}else {
			baseVo.setData("true");
		}
		return baseVo;
	}

	/**
	 * 删除ResidentDoorCard
	 * @param id
	 */
	@Override
	@Transactional
	public void delete(Long id){
		//需要先取消授权
		//1. 先根据主键id查询居民id
		ResidentDoorCard residentDoorCard = residentDoorCardMapper.findById(id);
		if (residentDoorCard != null){
			//2. 设置居民状态为未授权状态
			Long residentId = residentDoorCard.getResidentId();
			Resident resident = new Resident();
			resident.setId(residentId);
			resident.setStatus(0);
			residentMapper.update(resident);
			//3. 门卡与硬件解除绑定
			//4. 删除门卡
			residentDoorCardMapper.delete(id);
		}
	}
}
