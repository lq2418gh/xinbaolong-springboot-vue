package com.bit.sc.module.sys.service;

import com.bit.base.vo.BaseVo;
import com.bit.sc.module.sys.pojo.ResidentRelAddress;
import com.bit.sc.module.sys.vo.ResidentRelAddressVO;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * ResidentRelAddress的Service
 * @author liqi
 */
@Service
public interface ResidentRelAddressService {
	/**
	 * 根据条件查询ResidentRelAddress
	 * @param residentRelAddressVO
	 * @return
	 */
	BaseVo findByConditionPage(ResidentRelAddressVO residentRelAddressVO);
	/**
	 * 查询所有ResidentRelAddress
	 * @param sorter 排序字符串
	 * @return
	 */
	List<ResidentRelAddress> findAll(String sorter);
	/**
	 * 通过主键查询单个ResidentRelAddress
	 * @param id
	 * @return
	 */
	ResidentRelAddress findById(Long id);

	/**
	 * 批量保存ResidentRelAddress
	 * @param residentRelAddresss
	 */
	void batchAdd(List<ResidentRelAddress> residentRelAddresss);
	/**
	 * 保存ResidentRelAddress
	 * @param residentRelAddress
	 */
	void add(ResidentRelAddress residentRelAddress);
	/**
	 * 批量更新ResidentRelAddress
	 * @param residentRelAddresss
	 */
	void batchUpdate(List<ResidentRelAddress> residentRelAddresss);
	/**
	 * 更新ResidentRelAddress
	 * @param residentRelAddress
	 */
	void update(ResidentRelAddress residentRelAddress);
	/**
	 * 删除ResidentRelAddress
	 * @param id
	 */
	void delete(Long id);
	/**
	 * 批量删除ResidentRelAddress
	 * @param ids
	 */
	void batchDelete(List<Long> ids);
}
