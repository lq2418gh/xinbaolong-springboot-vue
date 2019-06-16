package com.bit.sc.module.equipment.service.impl;

import java.util.*;

import com.bit.base.exception.BusinessException;
import com.bit.sc.module.equipment.dao.EquipmentModelNumberMapper;
import com.bit.sc.module.equipment.dao.EquipmentStatusMapper;
import com.bit.sc.module.equipment.dao.EquipmentTypeMapper;
import com.bit.sc.module.equipment.pojo.EquipmentModelNumber;
import com.bit.sc.module.equipment.pojo.EquipmentStatus;
import com.bit.sc.module.equipment.pojo.EquipmentType;
import com.bit.sc.module.equipment.vo.EquipmentNewVO;
import com.bit.sc.module.group.dao.GroupRelMapper;
import com.bit.sc.module.group.pojo.GroupRel;
import com.bit.sc.module.sys.dao.AddressMapper;
import com.bit.sc.module.sys.pojo.Address;
import com.bit.utils.UUIDUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.bit.base.vo.BaseVo;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.bit.sc.module.equipment.pojo.Equipment;
import com.bit.sc.module.equipment.vo.EquipmentVO;
import com.bit.sc.module.equipment.dao.EquipmentMapper;
import com.bit.sc.module.equipment.service.EquipmentService;
import com.bit.base.service.BaseService;
import org.springframework.transaction.annotation.Transactional;

/**
 * Equipment的Service实现类
 * @author liuyancheng
 *
 */
@Service("equipmentService")
public class EquipmentServiceImpl extends BaseService implements EquipmentService{
	
	private static final Logger logger = LoggerFactory.getLogger(EquipmentServiceImpl.class);
	
	@Autowired
	private EquipmentMapper equipmentMapper;
	@Autowired
	private AddressMapper addressMapper;
	@Autowired
	private EquipmentModelNumberMapper equipmentModelNumberMapper;
	@Autowired
	private EquipmentStatusMapper equipmentStatusMapper;
	@Autowired
	private EquipmentTypeMapper equipmentTypeMapper;
	@Autowired
	private GroupRelMapper groupRelMapper;
	
	/**
	 * 根据条件查询Equipment
	 * @return
	 */
	@Override
	public BaseVo findByConditionPage(EquipmentVO equipmentVO){
		PageHelper.startPage(equipmentVO.getPageNum(), equipmentVO.getPageSize());
		//根据创建时间排序
		equipmentVO.setOrderBy("create_time desc");
		List<Equipment> list = equipmentMapper.findByConditionPage(equipmentVO);
		PageInfo<Equipment> pageInfo = new PageInfo<Equipment>(list);
		BaseVo baseVo = new BaseVo();
		baseVo.setData(pageInfo);
		return baseVo;
	}
	/**
	 * 查询所有Equipment
	 * @param sorter 排序字符串
	 * @return
	 */
	@Override
	public List<Equipment> findAll(String sorter){
		return equipmentMapper.findAll(sorter);
	}
	/**
	 * 通过主键查询单个Equipment
	 * @param id
	 * @return
	 */
	@Override
	public Equipment findById(Long id){
		return equipmentMapper.findById(id);
	}
	/**
	 * 保存Equipment
	 * @param equipment
	 */
	@Override
	@Transactional
	public void add(Equipment equipment){
		//判断设备码是否唯一
		if (equipment.getEquipmentCode() != null && !"".equals(equipment.getEquipmentCode())){
			Equipment byEquipmentCode = equipmentMapper.findByEquipmentCode(equipment.getEquipmentCode());
			if (byEquipmentCode == null){
				//创建人id
				Long id = getCurrentUserInfo().getId();
				if (id != null){
					equipment.setCreateUserId(id);
				}
				//默认未启用状态
				equipment.setEquipmentStatus(0);
				//通过设备型号查询型号id
				EquipmentModelNumber equipmentNumber = equipmentModelNumberMapper.findById(equipment.getEquipmentModelNumberId());
				if (equipmentNumber != null){
					equipment.setEquipmentModelNumberId(equipmentNumber.getId());
					equipment.setEquipmentType(equipmentNumber.getEquipmentType());
				}
				//默认离线状态
				equipment.setEquipmentOnlineStatus(0);
				//创建时间
				equipment.setCreateTime(new Date());
				equipmentMapper.add(equipment);

				//返回的主键ID
				Long resultId = equipment.getId();
				//向equipmentStatus插入一条数据
				Equipment byId = equipmentMapper.findById(resultId);
				if (byId != null){
					EquipmentStatus equipmentStatus = new EquipmentStatus();
					equipmentStatus.setEquipmentId(byId.getEquipmentId());
					//默认为4
					equipmentStatus.setEquipmentOnlineStatus(4);
					equipmentStatus.setCreateTime(new Date());
					equipmentStatus.setAddressCode(byId.getAddressCode());
					equipmentStatusMapper.add(equipmentStatus);
				}
			}else {
				throw new BusinessException("设备码重复");
			}
		}
	}
	/**
	 * 更新Equipment
	 * @param equipment
	 */
	@Override
	@Transactional
	public void update(Equipment equipment){
		EquipmentModelNumber equipmentNumber = equipmentModelNumberMapper.findById(equipment.getEquipmentModelNumberId());
		if (equipmentNumber != null){
			equipment.setEquipmentModelNumberId(equipmentNumber.getId());
			equipment.setEquipmentType(equipmentNumber.getEquipmentType());
			//通过设备型号type查询设备类型表中的值
			EquipmentType equipmentType = equipmentTypeMapper.findById(Long.valueOf(equipmentNumber.getEquipmentType()));
			equipment.setEquipmentType(equipmentType.getTypeNum());
		}
		equipmentMapper.update(equipment);
	}
	/**
	 * 删除Equipment
	 * @param ids
	 */
	@Override
	@Transactional
	public void batchDelete(List<Long> ids){
		equipmentMapper.batchDelete(ids);
	}
	/**
	 * 删除Equipment
	 * @param id
	 */
	@Override
	@Transactional
	public void delete(Long id){
		equipmentMapper.delete(id);
	}

	/**
	 * 分页查询设备列表
	 * @param equipmentVO
	 * @return
	 */
	@Override
	public BaseVo findAllPage(EquipmentVO equipmentVO){
		//1.根据地址code查询地址父集或者子集
		Address address = new Address();
		List<String> list = new ArrayList();
		if (!"".equals(equipmentVO.getAddressCode()) && equipmentVO.getAddressCode() != null){
			address.setAddressCode(equipmentVO.getAddressCode());
			List<Address> addressList = addressMapper.findAllByAddressCode(address);
			for (Address address1 : addressList) {
				list.add(address1.getAddressCode());
			}
		}
		//2.处理地址code，，号分割
		Map<String,Object> map = new HashMap<>();
		map.put("addressCodeList",list);
		//3.传给mapper
		PageHelper.startPage(equipmentVO.getPageNum(), equipmentVO.getPageSize());
		equipmentVO.setOrderBy("create_time desc");
		//判断设备码是否为空，不为空则加%，模糊查询
		if (equipmentVO.getEquipmentCode() != null && !"".equals(equipmentVO.getEquipmentCode())){
			String code = equipmentVO.getEquipmentCode() + "%";
			equipmentVO.setEquipmentCode(code);
		}
		//判断备注是否为空，不为空则加%，模糊查询
		if (equipmentVO.getRemarks() != null && !"".equals(equipmentVO.getRemarks())){
			String remarks = equipmentVO.getRemarks() + "%";
			equipmentVO.setRemarks(remarks);
		}
		map.put("vo",equipmentVO);
		List<EquipmentNewVO> pageList = equipmentMapper.findAllPage(map);
		for (EquipmentNewVO equipmentNewVO : pageList) {
			String addressDetail = equipmentNewVO.getAddressDetail();
			String s = addressDetail.replaceAll(",", "");
			String s1 = s.replaceAll("\\+", "");
			equipmentNewVO.setAddressDetail(s1);

			GroupRel groupRel = new GroupRel();
			groupRel.setRelId(equipmentNewVO.getId());
			groupRel.setType(1);
			List<GroupRel> relList = groupRelMapper.findByParam(groupRel);
			equipmentNewVO.setGroupRelList(relList);

			EquipmentType equipmentType = equipmentTypeMapper.findById(Long.valueOf(equipmentNewVO.getEquipmentType()));
			equipmentNewVO.setEquipmentTypeName(equipmentType.getEquipmentType());

		}
		PageInfo<EquipmentNewVO> pageInfo = new PageInfo<EquipmentNewVO>(pageList);
		BaseVo baseVo = new BaseVo();
		baseVo.setData(pageInfo);
		return baseVo;
	}

	@Override
	public Equipment findByDeviceId(String deviceId) {
		return equipmentMapper.findByDeviceId(deviceId);
	}
}
