package com.bit.sc.module.equipment.service.impl;

import java.util.Date;
import java.util.List;
import java.util.ArrayList;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.bit.base.vo.BaseVo;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.bit.sc.module.equipment.pojo.EquipmentModelNumber;
import com.bit.sc.module.equipment.vo.EquipmentModelNumberVO;
import com.bit.sc.module.equipment.dao.EquipmentModelNumberMapper;
import com.bit.sc.module.equipment.service.EquipmentModelNumberService;
import com.bit.base.service.BaseService;
import org.springframework.transaction.annotation.Transactional;

/**
 * EquipmentModelNumber的Service实现类
 * @author liuyancheng
 *
 */
@Service("equipmentModelNumberService")
public class EquipmentModelNumberServiceImpl extends BaseService implements EquipmentModelNumberService{
	
	private static final Logger logger = LoggerFactory.getLogger(EquipmentModelNumberServiceImpl.class);
	
	@Autowired
	private EquipmentModelNumberMapper equipmentModelNumberMapper;
	
	/**
	 * 根据条件查询EquipmentModelNumber
	 * @return
	 */
	@Override
	public BaseVo findByConditionPage(EquipmentModelNumberVO equipmentModelNumberVO){
		PageHelper.startPage(equipmentModelNumberVO.getPageNum(), equipmentModelNumberVO.getPageSize());
		equipmentModelNumberVO.setOrderBy("create_time desc");
		//判断设备型号是否为空，不为空则加%，模糊查询
		if (equipmentModelNumberVO.getEquipmentModelNumber() != null && !"".equals(equipmentModelNumberVO.getEquipmentModelNumber())){
			String equipmentModelNumber = equipmentModelNumberVO.getEquipmentModelNumber() + "%";
			equipmentModelNumberVO.setEquipmentModelNumber(equipmentModelNumber);
		}
		//判断备注是否为空，不为空则加%，模糊查询
		if (equipmentModelNumberVO.getRemarks() != null && !"".equals(equipmentModelNumberVO.getRemarks())){
			String remarks = equipmentModelNumberVO.getRemarks() + "%";
			equipmentModelNumberVO.setRemarks(remarks);
		}
		List<EquipmentModelNumber> list = equipmentModelNumberMapper.findByConditionPage(equipmentModelNumberVO);
		PageInfo<EquipmentModelNumber> pageInfo = new PageInfo<EquipmentModelNumber>(list);
		BaseVo baseVo = new BaseVo();
		baseVo.setData(pageInfo);
		return baseVo;
	}
	/**
	 * 查询所有EquipmentModelNumber
	 * @param sorter 排序字符串
	 * @return
	 */
	@Override
	public List<EquipmentModelNumber> findAll(String sorter){
		return equipmentModelNumberMapper.findAll(sorter);
	}
	/**
	 * 通过主键查询单个EquipmentModelNumber
	 * @param id
	 * @return
	 */
	@Override
	public EquipmentModelNumber findById(Long id){
		return equipmentModelNumberMapper.findById(id);
	}
	
	/**
	 * 保存EquipmentModelNumber
	 * @param equipmentModelNumber
	 */
	@Override
	@Transactional
	public void add(EquipmentModelNumber equipmentModelNumber){
		//创建时间
		equipmentModelNumber.setCreateTime(new Date());
		//创建人id
		Long id = getCurrentUserInfo().getId();
		if (id != null){
			equipmentModelNumber.setCreateUserId(id);
		}
		//创建人名字
		String userName = getCurrentUserInfo().getUserName();
		if(!"".equals(userName) && userName != null){
			equipmentModelNumber.setCreateUserName(userName);
		}
		//设备状态默认未启用
		equipmentModelNumber.setEquipmentStatus(0);
		equipmentModelNumberMapper.add(equipmentModelNumber);
	}
	/**
	 * 更新EquipmentModelNumber
	 * @param equipmentModelNumber
	 */
	@Override
	@Transactional
	public void update(EquipmentModelNumber equipmentModelNumber){
		equipmentModelNumberMapper.update(equipmentModelNumber);
	}
	/**
	 * 删除EquipmentModelNumber
	 * @param ids
	 */
	@Override
	@Transactional
	public void batchDelete(List<Long> ids){
		equipmentModelNumberMapper.batchDelete(ids);
	}

	@Override
	public BaseVo findByType(EquipmentModelNumber equipmentModelNumber) {
		List<EquipmentModelNumber> byType = equipmentModelNumberMapper.findByType(equipmentModelNumber);
		BaseVo baseVo = new BaseVo();
		baseVo.setData(byType);
		return baseVo;
	}

	@Override
	public BaseVo findAllByStatus() {
		List<EquipmentModelNumber> allByStatus = equipmentModelNumberMapper.findAllByStatus();
		BaseVo baseVo = new BaseVo();
		baseVo.setData(allByStatus);
		return baseVo;
	}

	/**
	 * 删除EquipmentModelNumber
	 * @param id
	 */
	@Override
	@Transactional
	public void delete(Long id){
		equipmentModelNumberMapper.delete(id);
	}
}
