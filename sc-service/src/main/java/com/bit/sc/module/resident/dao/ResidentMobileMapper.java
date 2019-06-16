package com.bit.sc.module.resident.dao;

import java.util.List;

import com.bit.sc.module.resident.pojo.ResidentMobile;
import com.bit.sc.module.resident.pojo.ResidentRelRole;
import com.bit.sc.module.resident.vo.ResidentMobileVO;
import com.bit.sc.module.sys.pojo.Address;
import com.bit.sc.module.sys.pojo.Role;
import org.apache.ibatis.annotations.Param;

/**
 * ResidentMobile管理的Dao
 * @author 
 *
 */
public interface ResidentMobileMapper {
	/**
	 * 根据条件查询ResidentMobile
	 * @param residentMobileVO
	 * @return
	 */
	public List<ResidentMobile> findByConditionPage(ResidentMobileVO residentMobileVO);
	/**
	 * 查询所有ResidentMobile
	 * @return
	 */
	public List<ResidentMobile> findAll(@Param(value = "sorter") String sorter);
	/**
	 * 通过主键查询单个ResidentMobile
	 * @param id	 	 
	 * @return
	 */
	public ResidentMobile findById(@Param(value = "id") Long id);
	/**
	 * 批量保存ResidentMobile
	 * @param residentMobiles
	 */
	public void batchAdd(List<ResidentMobile> residentMobiles);
	/**
	 * 保存ResidentMobile
	 * @param residentMobile
	 */
	public void add(ResidentMobile residentMobile);
	/**
	 * 批量更新ResidentMobile
	 * @param residentMobiles
	 */
	public void batchUpdate(List<ResidentMobile> residentMobiles);
	/**
	 * 更新ResidentMobile
	 * @param residentMobile
	 */
	public void update(ResidentMobile residentMobile);
	/**
	 * 删除ResidentMobile
	 * @param ids
	 */
	public void batchDelete(List<Long> ids);
	/**
	 * 删除ResidentMobile
	 * @param id
	 */
	public void delete(@Param(value = "id") Long id);

	/**
	 * 根据居民id更新数据
	 * @param residentMobile
	 */
	void updateInfoByResidentId(ResidentMobile residentMobile);

	/**
	 * 根据id和电话删除信息
	 * @param residentId
	 * @param phone
	 */
	void deleteByIdAndMobile(@Param(value = "residentId") Long residentId,@Param(value = "phone") String phone);

	/**
	 * 根据居民id查询居民
	 * @param residentId
	 * @return
	 */

	ResidentMobile findByResidentId(@Param(value = "residentId") Long residentId);
	/**
	 *根据参数查询
	 * @param
	 */
	public List<ResidentMobileVO> findByParam(ResidentMobileVO residentMobileVO);

	/**
	 *根据应用的Code查询角色id
	 * @param residentMobileVO
	 * @author zhangjie
	 * @date 2018-11-14
	 */
	public List<Role> findByAppCode(ResidentMobileVO residentMobileVO);

	/**
	 * 根据用户身份证查询手机号激活状态
	 * @param residentMobileVO
	 * @return
	 * @author zhangjie
	 * @date 2018-11-14
	 */
	public List<ResidentMobileVO> findStatusByCardId(ResidentMobileVO residentMobileVO);

	/**
	 * 根据手机号查询角色id
	 * @param residentMobileVO
	 * @return
	 * @author zhangjie
	 * @date 2018-11-15
	 */
	public List<ResidentRelRole> findRoleIdByPhone(ResidentMobileVO residentMobileVO);

	/**
	 * 通过手机号查询addressCode
	 * @param residentMobileVO
	 * @return
	 * @author zhangjie
	 * @date 2018-11-16
	 */
	public List<Address> findAddressByPhone(ResidentMobileVO residentMobileVO);
	/**
	 *
	 * 功能描述:验证手机号是否重复
	 *
	 * @param:
	 * @return:
	 * @author: chenduo
	 * @date: 2018/12/6 8:54
	 */

	Integer checkPhone(@Param(value = "phone") String phone);

}
