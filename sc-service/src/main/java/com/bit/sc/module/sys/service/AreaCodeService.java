package com.bit.sc.module.sys.service;

import com.bit.base.vo.BaseVo;
import com.bit.sc.module.sys.pojo.AreaCode;
import com.bit.sc.module.sys.vo.AreaCodeVO;

import java.util.List;
/**
 * AreaCode的Service
 * @author codeGenerator
 */
public interface AreaCodeService {
	/**
	 * 根据条件查询AreaCode
	 * @param areaCodeVO
	 * @return
	 */
	BaseVo findByConditionPage(AreaCodeVO areaCodeVO);
	/**
	 * 查询所有AreaCode
	 * @param sorter 排序字符串
	 * @return
	 */
	List<AreaCode> findAll(String sorter);
	/**
	 * 通过主键查询单个AreaCode
	 * @param arCode
	 * @return
	 */
	AreaCode findByArCode(String arCode);
	/**
	 * 保存AreaCode
	 * @param areaCode
	 */
	void add(AreaCode areaCode);
	/**
	 * 更新AreaCode
	 * @param areaCode
	 */
	void update(AreaCode areaCode);
	/**
	 * 删除AreaCode
	 * @param code
	 */
	void delete(String code);
	/**
	 * 批量删除AreaCode
	 * @param codes
	 */
	void batchDelete(List<Long> codes);

	/**
	 * 根据父code查询行政list
	 * @param parentCode
	 * @return
	 */
	List<AreaCode> findByParentCode(String parentCode);

	/**
	 * 查询杨柳青 和杨柳青下的list
	 * @return
	 */
	AreaCode findByCode(String Code);

	/**
	 * 查询行政代码和小区的树 根据sign   ROOM ：到房门101   DOOR：到楼层/栋 RESIDENT ：到单元
	 * @return
	 */
	AreaCode findAreaAndAddressTree(String sign);
}
