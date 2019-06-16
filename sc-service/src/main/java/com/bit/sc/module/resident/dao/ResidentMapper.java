package com.bit.sc.module.resident.dao;

import java.util.List;
import java.util.Map;

import com.bit.sc.module.resident.pojo.Resident;
import com.bit.sc.module.resident.vo.ResidentDictVO;
import com.bit.sc.module.resident.vo.ResidentMobileAddressAttachmentVO;
import com.bit.sc.module.resident.vo.ResidentVO;
import com.bit.sc.module.sys.pojo.Address;
import com.bit.sc.module.sys.pojo.ResidentRelAddress;
import org.apache.ibatis.annotations.Param;

/**
 * Resident管理的Dao
 * @author 
 *
 */
public interface ResidentMapper {
	/**
	 * 根据条件查询Resident
	 * @param residentVO
	 * @return
	 */
	 List<Resident> findByConditionPage(ResidentVO residentVO);
	/**
	 * 查询所有Resident
	 * @return
	 */
	 List<Resident> findAll(@Param(value = "sorter") String sorter);
	/**
	 * 通过主键查询单个Resident
	 * @param id	 	 
	 * @return
	 */
	 Resident findById(@Param(value = "id") Long id);
	/**
	 * 保存Resident
	 * @param resident
	 */
	 Long add(Resident resident);
	/**
	 * 更新Resident
	 * @param resident
	 */
	 void update(Resident resident);
	/**
	 * 删除Resident
	 * @param ids
	 */
	 void batchDelete(List<Long> ids);
	/**
	 * 删除Resident
	 * @param id
	 */
	 void delete(@Param(value = "id") Long id);

	/**
	 * 进行身份证校验
	 * @param gmsfzhm
	 */
	Integer checkPersonalId(@Param(value = "gmsfzhm") String gmsfzhm);

	/**
	 * 根据身份证查询住户信息
	 * @param gmsfzhm
	 * @return
	 */
	Resident findBygmsfzhm(@Param(value = "gmsfzhm") String gmsfzhm);

	/**
	 * 根据身份证和id删除信息
	 * @param gmsfzhm
	 * @param id
	 */
	void deleteByIdAndgmsfzhm(@Param(value = "gmsfzhm") String gmsfzhm,@Param(value = "id") Long id);
    /**
     * 联合居民地址表 地址表 居民手机表 附件表
     *
     * @param
     * @return
     * @author chenduo
     */
	List<ResidentMobileAddressAttachmentVO> queryResidentInfoByPage(ResidentVO residentVO);
    /**
     *
     * 功能描述:联合居民表和居民地址关系表
     *
     * @param:
     * @return:
     * @author: chenduo
     * @date: 2018/11/28 9:58
     */

	List<String> findResidentInfo(ResidentVO residentVO);
	/**
	 *
	 * 功能描述: 根据id查询居民信息
	 *
	 * @param:
	 * @return:
	 * @author: chenduo
	 * @date: 2018/12/20 8:45
	 */

    ResidentDictVO queryResidentInfoById(@Param(value = "id") Long id);
    /**
     *
     * 功能描述: 根据地址id查询地址
     *
     * @param:
     * @return:
     * @author: chenduo
     * @date: 2018/12/20 8:46
     */


	List<ResidentRelAddress> queryResidentAddressInfoById(@Param(value = "list") List<String> list);

	/**
	 *
	 * 功能描述: 根据居民id查询地址
	 *
	 * @param:
	 * @return:
	 * @author: chenduo
	 * @date: 2018/12/20 8:47
	 */

	List<Address> findAddressById(@Param(value = "id") Long id);
	/**
	 *
	 * 功能描述: 根据居民id查询地址code
	 *
	 * @param:
	 * @return:
	 * @author: chenduo
	 * @date: 2018/12/20 8:49
	 */

	List<String> findAddressCodeById(@Param(value = "id") Long id);
	/**
	 *
	 * 功能描述:根据身份证号和小区code查重数据
	 *
	 * @param:
	 * @return:
	 * @author: chenduo
	 * @date: 2018/12/20 8:49
	 */

	Integer checkResidentInfo(ResidentVO residentVO);


}
