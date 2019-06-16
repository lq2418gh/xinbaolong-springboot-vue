package com.bit.sc.module.sys.service.impl;

import com.bit.base.service.BaseService;
import com.bit.base.vo.BaseVo;
import com.bit.sc.common.Const;
import com.bit.sc.module.sys.dao.AreaCodeMapper;
import com.bit.sc.module.sys.pojo.Address;
import com.bit.sc.module.sys.pojo.AreaCode;
import com.bit.sc.module.sys.service.AddressService;
import com.bit.sc.module.sys.service.AreaCodeService;
import com.bit.sc.module.sys.vo.AreaCodeVO;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * AreaCode的Service实现类
 * @author codeGenerator
 *
 */
@Service("areaCodeService")
public class AreaCodeServiceImpl extends BaseService implements AreaCodeService {

	private static final Logger logger = LoggerFactory.getLogger(AreaCodeServiceImpl.class);

	@Autowired
	private AreaCodeMapper areaCodeMapper;
	@Autowired
	private AddressService addressService;

	/**
	 * 根据条件查询AreaCode
	 * @param areaCodeVO
	 * @return
	 */
	@Override
	public BaseVo findByConditionPage(AreaCodeVO areaCodeVO){
		PageHelper.startPage(areaCodeVO.getPageNum(), areaCodeVO.getPageSize());
		List<AreaCode> list = areaCodeMapper.findByConditionPage(areaCodeVO);
		PageInfo<AreaCode> pageInfo = new PageInfo<AreaCode>(list);
		BaseVo baseVo = new BaseVo();
		baseVo.setData(pageInfo);
		return baseVo;
	}
	/**
	 * 查询所有AreaCode
	 * @param sorter 排序字符串
	 * @return
	 */
	@Override
	public List<AreaCode> findAll(String sorter){
		return areaCodeMapper.findAll(sorter);
	}
	/**
	 * 通过主键查询单个AreaCode
	 * @param arCode
	 * @return
	 */
	@Override
	public AreaCode findByArCode(String arCode){
		return areaCodeMapper.findByArCode(arCode);
	}

	/**
	 * 保存AreaCode
	 * @param areaCode
	 */
	@Override
	public void add(AreaCode areaCode){
		areaCodeMapper.add(areaCode);
	}
	/**
	 * 更新AreaCode
	 * @param areaCode
	 */
	@Override
	public void update(AreaCode areaCode){
		areaCodeMapper.update(areaCode);
	}
	/**
	 * 删除AreaCode
	 * @param codes
	 */
	@Override
	public void batchDelete(List<Long> codes){
		areaCodeMapper.batchDelete(codes);
	}

	/**
	 * 根据父code 查询list
	 * @param parentCode
	 * @return
	 */
	@Override
	public List<AreaCode> findByParentCode(String parentCode) {
		List<AreaCode> areaCodeList=areaCodeMapper.findByParentCode(parentCode);
		return areaCodeList;
	}
	/**
	 * 查询杨柳青 和杨柳青下的list--居委会
	 * @return
	 */
	@Override
	public AreaCode findByCode(String code) {
		AreaCode byArCode = areaCodeMapper.findByArCode(code);
		List<AreaCode> byParentCode = areaCodeMapper.findByParentCode(code);
		byArCode.setChildrenList(byParentCode);
		return byArCode;
	}

	/**
	 * 查询行政代码和小区的树 根据sign   ROOM ：到房门101   DOOR：到楼层/栋  RESIDENT: 到单元
	 * @return
	 */
	@Override
	public AreaCode findAreaAndAddressTree(String sign) {
		String code =Const.AREA_CODE;//杨柳青的code
		AreaCode byArCode = areaCodeMapper.findByArCode(code);
		List<AreaCode> byParentCode = areaCodeMapper.findByParentCode(code);
		List<Address> addressTree = null;
		if(sign.equals("DOOR")){
			 addressTree = addressService.findToDoorList();
		}
		if (sign.equals("ROOM")){
			 addressTree = addressService.findAddressTree();
		}
		if (sign.equals("RESIDENT")){
			addressTree = addressService.findToResident();
		}
		if (byParentCode!=null||byParentCode.size()>0) {
			if (addressTree!=null || addressTree.size()>0) {
				byArCode.setChildrenList(byParentCode);
				for (AreaCode areaCode : byParentCode) {
					List<Address> addresslist = new ArrayList<Address>();
					for (Address address : addressTree) {
						if (address.getAreaCode().equals(areaCode.getArCode())) {
							addresslist.add(address);
						}
					}
					areaCode.setChildrenList(addresslist);
				}
				byArCode.setChildrenList(byParentCode);
			}
		}

		return byArCode;
	}

	/**
	 * 删除AreaCode
	 * @param code
	 */
	@Override
	public void delete(String code){
		areaCodeMapper.delete(code);
	}
}
