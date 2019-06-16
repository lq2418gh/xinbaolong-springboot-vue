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
import com.bit.sc.module.equipment.pojo.EquipmentStatus;
import com.bit.sc.module.equipment.vo.EquipmentStatusVO;
import com.bit.sc.module.equipment.dao.EquipmentStatusMapper;
import com.bit.sc.module.equipment.service.EquipmentStatusService;
import com.bit.base.service.BaseService;
import org.springframework.transaction.annotation.Transactional;

/**
 * EquipmentStatus的Service实现类
 * @author liuyancheng
 *
 */
@Service("equipmentStatusService")
public class EquipmentStatusServiceImpl extends BaseService implements EquipmentStatusService{
	
	private static final Logger logger = LoggerFactory.getLogger(EquipmentStatusServiceImpl.class);
	
	@Autowired
	private EquipmentStatusMapper equipmentStatusMapper;
	
	/**
	 * 根据条件查询EquipmentStatus
	 * @return
	 */
	@Override
	public BaseVo findByConditionPage(EquipmentStatusVO equipmentStatusVO){
		PageHelper.startPage(equipmentStatusVO.getPageNum(), equipmentStatusVO.getPageSize());
		//按更新时间倒序排序
		equipmentStatusVO.setOrderBy("update_time desc");
		List<EquipmentStatus> list = equipmentStatusMapper.findByConditionPage(equipmentStatusVO);
		PageInfo<EquipmentStatus> pageInfo = new PageInfo<EquipmentStatus>(list);
		BaseVo baseVo = new BaseVo();
		baseVo.setData(pageInfo);
		return baseVo;
	}
	/**
	 * 查询所有EquipmentStatus
	 * @param sorter 排序字符串
	 * @return
	 */
	@Override
	public List<EquipmentStatus> findAll(String sorter){
		return equipmentStatusMapper.findAll(sorter);
	}
	/**
	 * 通过主键查询单个EquipmentStatus
	 * @param id
	 * @return
	 */
	@Override
	public EquipmentStatus findById(Long id){
		return equipmentStatusMapper.findById(id);
	}
	
	/**
	 * 批量保存EquipmentStatus
	 * @param equipmentStatuss
	 */
	@Override
	public void batchAdd(List<EquipmentStatus> equipmentStatuss){
		equipmentStatusMapper.batchAdd(equipmentStatuss);
	}
	/**
	 * 保存EquipmentStatus
	 * @param equipmentStatus
	 */
	@Override
	@Transactional
	public void add(EquipmentStatus equipmentStatus){
		equipmentStatus.setUpdateTime(new Date());
		equipmentStatusMapper.add(equipmentStatus);
	}
	/**
	 * 批量更新EquipmentStatus
	 * @param equipmentStatuss
	 */
	@Override
	public void batchUpdate(List<EquipmentStatus> equipmentStatuss){
		equipmentStatusMapper.batchUpdate(equipmentStatuss);
	}
	/**
	 * 更新EquipmentStatus
	 * @param equipmentStatus
	 */
	@Override
	@Transactional
	public void update(EquipmentStatus equipmentStatus){
		equipmentStatusMapper.update(equipmentStatus);
	}
	/**
	 * 删除EquipmentStatus
	 * @param ids
	 */
	@Override
	@Transactional
	public void batchDelete(List<Long> ids){
		equipmentStatusMapper.batchDelete(ids);
	}
	/**
	 * 删除EquipmentStatus
	 * @param id
	 */
	@Override
	@Transactional
	public void delete(Long id){
		equipmentStatusMapper.delete(id);
	}
}
