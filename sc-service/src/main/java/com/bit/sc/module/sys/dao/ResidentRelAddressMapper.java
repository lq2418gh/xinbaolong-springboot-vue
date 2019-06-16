package com.bit.sc.module.sys.dao;

import java.util.List;

import com.bit.sc.module.sys.pojo.ResidentRelAddress;
import com.bit.sc.module.sys.vo.ResidentRelAddressVO;
import org.apache.ibatis.annotations.Param;

/**
 * ResidentRelAddress管理的Dao
 * @author liqi
 *
 */
public interface ResidentRelAddressMapper {
	/**
	 * 根据条件查询ResidentRelAddress
	 * @param residentRelAddressVO
	 * @return
	 */
	public List<ResidentRelAddress> findByConditionPage(ResidentRelAddressVO residentRelAddressVO);
	/**
	 * 查询所有ResidentRelAddress
	 * @return
	 */
	public List<ResidentRelAddress> findAll(@Param(value = "sorter") String sorter);
	/**
	 * 通过主键查询单个ResidentRelAddress
	 * @param id	 	 
	 * @return
	 */
	public ResidentRelAddress findById(@Param(value = "id") Long id);
	/**
	 * 批量保存ResidentRelAddress
	 * @param residentRelAddresss
	 */
	public void batchAdd(@Param(value = "residentRelAddresss") List<ResidentRelAddress> residentRelAddresss);
	/**
	 * 保存ResidentRelAddress
	 * @param residentRelAddress
	 */
	public void add(ResidentRelAddress residentRelAddress);
	/**
	 * 批量更新ResidentRelAddress
	 * @param residentRelAddresss
	 */
	public void batchUpdate(List<ResidentRelAddress> residentRelAddresss);
	/**
	 * 更新ResidentRelAddress
	 * @param residentRelAddress
	 */
	public void update(ResidentRelAddress residentRelAddress);
	/**
	 * 删除ResidentRelAddress
	 * @param
	 */
	public void batchDelete(@Param(value = "ids")List<Long> ids);
	/**
	 * 删除ResidentRelAddress
	 * @param id
	 */
	public void delete(@Param(value = "id") Long id);

	List<ResidentRelAddress> getDataByColumns(@Param(value = "residentId") Long residentId);

	/**
	 * 根据addresCode 删除
	 * @param addressCode
	 */
	void deleteByLikeAddressCode(String addressCode);

	ResidentRelAddress findByRelAddress(ResidentRelAddress residentRelAddress);
}
