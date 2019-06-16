package com.bit.sc.module.house.service.impl;

import com.bit.base.bean.UserAddress;
import com.bit.base.exception.BusinessException;
import com.bit.base.service.BaseService;
import com.bit.base.vo.BaseVo;
import com.bit.base.vo.UserInfo;
import com.bit.sc.common.Const;
import com.bit.sc.module.house.dao.*;
import com.bit.sc.module.house.pojo.*;
import com.bit.sc.module.house.service.HouseService;
import com.bit.sc.module.house.vo.HouseAddressVO;
import com.bit.sc.module.house.vo.HouseCompanyOwnerVO;
import com.bit.sc.module.house.vo.HouseNewVO;
import com.bit.sc.module.house.vo.HouseVO;
import com.bit.sc.module.sys.dao.AddressMapper;
import com.bit.sc.module.sys.pojo.Address;
import com.bit.sc.utils.DateUtil;
import com.bit.utils.StringUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 一标三实的相关请求
 * @author chenduo
 *
 */
@Service("houseService")
public class HouseServiceImpl extends BaseService implements HouseService {
	
	private static final Logger logger = LoggerFactory.getLogger(HouseServiceImpl.class);
	
	@Autowired
	private HouseMapper houseMapper;
	@Autowired
    private RealCompanyMapper realCompanyMapper;

	@Autowired
    private AddressMapper addressMapper;
	@Autowired
    private OwnerCustodianRentMapper ownerCustodianRentMapper;
	@Autowired
    private RegisteredResidentMapper registeredResidentMapper;
	@Autowired
    private RealPersonMapper realPersonMapper;

	/**
	 * 根据条件查询House
	 * @param houseVO
	 * @return
	 */
	@Override
	public BaseVo findByConditionPage(HouseVO houseVO){
		PageHelper.startPage(houseVO.getPageNum(), houseVO.getPageSize());
		List<House> list = houseMapper.findByConditionPage(houseVO);
		PageInfo<House> pageInfo = new PageInfo<House>(list);
		BaseVo baseVo = new BaseVo();
		baseVo.setData(pageInfo);
		return baseVo;
	}

	/**
	 * 通过主键查询单个House
	 * @param id
	 * @return
	 */
	@Override
	public HouseCompanyOwnerVO findById(Long id){
        HouseCompanyOwnerVO houseCompanyOwnerVO = new HouseCompanyOwnerVO();
	    House house = houseMapper.findById(id);
        //如果是公司财产
        RealCompany realCompany=null;
        if (house.getIsCompanyOwned().equals(Const.YIBIAOSANSHI_COMPANY_OWNED)){
            Long companyInfo = house.getCompanyInfo();
            if (companyInfo!=null){
                realCompany = realCompanyMapper.findById(companyInfo);
            }

        }
        OwnerCustodianRent owner=null;
        OwnerCustodianRent cus=null;
        OwnerCustodianRent rent=null;
        //如果是租的房
        if (house.getIsRent().equals(Const.YIBIAOSANSHI_RENT)){
            //得到户主的信息
            if (house.getHouseOwnerInfo()!=null){
                owner = ownerCustodianRentMapper.findById(house.getHouseOwnerInfo());
            }
            if (house.getCustodianInfo()!=null){
                cus = ownerCustodianRentMapper.findById(house.getCustodianInfo());
            }
            if (house.getRentPersonInfo()!=null){
                rent = ownerCustodianRentMapper.findById(house.getRentPersonInfo());
            }


        }

        Long dz = house.getDz();
        Address address = addressMapper.findByPrimaryKey(dz);
        String addressCode = address.getAddressCode();


        HouseNewVO houseNewVO = new HouseNewVO();
        houseNewVO.setAddressCode(addressCode);
        BeanUtils.copyProperties(house,houseNewVO);
        houseCompanyOwnerVO.setHouseNewVO(houseNewVO);
        if (realCompany!=null){
            houseCompanyOwnerVO.setRealCompany(realCompany);
        }
        List<OwnerCustodianRent> list = new ArrayList<>();
        if (owner!=null){
            list.add(owner);
        }
        if (cus!=null){
            list.add(cus);
        }
        if (rent!=null){
            list.add(rent);
        }

        houseCompanyOwnerVO.setOwnerCustodianRentList(list);

        List<RegisteredResident> registeredResidentList = registeredResidentMapper.findByHouseId(id);
        houseCompanyOwnerVO.setRegisteredResidentList(registeredResidentList);

        List<RealPerson> realPersonList = realPersonMapper.findByHouseId(id);
        houseCompanyOwnerVO.setRealPersonList(realPersonList);


		return houseCompanyOwnerVO;
	}
	



    @Override
    @Transactional
    public BaseVo add(HouseVO houseVO) {

        Address address = addressMapper.findByAddressCode(houseVO.getAddressCode());
        houseVO.setDz(address.getId());
	    //todo 操作之前 先查询数据库是否这个地址已经存在
        HouseVO obj = new HouseVO();
        obj.setDz(address.getId());
        List<House> houseList = new ArrayList<>();
        houseList = houseMapper.findByConditionPage(obj);
        if (houseList.size()>0){
            throw new BusinessException("此地址已经录入 不要重复操作");
        }




        House house = new House();
        BeanUtils.copyProperties(houseVO,house);
        if (houseVO.getIsCompanyOwned().equals(Const.YIBIAOSANSHI_COMPANY_OWNED)) {
            RealCompany realCompany = new RealCompany();
            BeanUtils.copyProperties(houseVO, realCompany);
            realCompanyMapper.add(realCompany);
            house.setCompanyInfo(realCompany.getId());
        }
        if (houseVO.getIsRent().equals(Const.YIBIAOSANSHI_RENT)) {
            //插入房东信息
            OwnerCustodianRent owner = new OwnerCustodianRent();
            owner.setCardId(houseVO.getOwnerCardId());
            owner.setPhone(houseVO.getOwnerPhone());
            owner.setRealName(houseVO.getOwnerName());
            owner.setType(Const.OWNER_TYPE);
            ownerCustodianRentMapper.add(owner);
            house.setHouseOwnerInfo(owner.getId());

            if (!(houseVO.getCusName() == null || houseVO.getCusCardId() == null || houseVO.getCusPhone() == null)) {
                //插入代管人信息
                OwnerCustodianRent custodian = new OwnerCustodianRent();
                custodian.setCardId(houseVO.getCusCardId());
                custodian.setPhone(houseVO.getCusPhone());
                custodian.setRealName(houseVO.getCusName());
                custodian.setType(Const.CUSTODIAN_TYPE);
                ownerCustodianRentMapper.add(owner);
                house.setCustodianInfo(custodian.getId());
            }


            //插入租赁人信息
            OwnerCustodianRent rent = new OwnerCustodianRent();
            rent.setCardId(houseVO.getRentCardId());
            rent.setPhone(houseVO.getRentPhone());
            rent.setRealName(houseVO.getRentName());
            rent.setType(Const.RENT_TYPE);
            ownerCustodianRentMapper.add(rent);
            house.setRentPersonInfo(rent.getId());
        }
        SimpleDateFormat sdf = new SimpleDateFormat(Const.DATE_FORMATE);
        String time = sdf.format(new Date());
        Date date = DateUtil.parse(time);
        house.setCreateTime(date);
        houseMapper.add(house);
        Long houseid = house.getId();
        BaseVo baseVo = new BaseVo();
        baseVo.setData(houseid);
        return baseVo;
    }



	/**
	 * 更新House
	 * @param houseVO
	 */
	@Override
	@Transactional
	public void update(HouseVO houseVO){


        //更新一标三实
        House oldHouse = houseMapper.findById(houseVO.getId());
        House house = new House();
        BeanUtils.copyProperties(houseVO,house);
        //如果参数的 iscompanyowned 等于1 且 旧记录的iscompanyowned等于0  删除公司记录
        if (houseVO.getIsCompanyOwned().equals(Const.YIBIAOSANSHI_COMPANY_OWNED_NOT) && oldHouse.getIsCompanyOwned().equals(Const.YIBIAOSANSHI_COMPANY_OWNED)){
            if (oldHouse.getCompanyInfo()!=null){
                realCompanyMapper.delete(oldHouse.getCompanyInfo());
            }

        }


        if (houseVO.getIsCompanyOwned().equals(Const.YIBIAOSANSHI_COMPANY_OWNED)){

            RealCompany realCompany = new RealCompany();
            BeanUtils.copyProperties(houseVO,realCompany);

            //如果为空 说明之前并不是公司 需要做新增 如果不为空 说明之前就是公司 需要做更新
            if (oldHouse.getCompanyInfo()==null){
                realCompanyMapper.add(realCompany);
                house.setCompanyInfo(realCompany.getId());
            }else {
                realCompany.setId(houseVO.getCompanyInfo());
                realCompanyMapper.update(realCompany);
            }
        }

        //如果参数的 isRent 等于1 且 旧记录的 isRent 等于0  删除房主 代管 租赁信息
        if (houseVO.getIsRent().equals(Const.YIBIAOSANSHI_RENT_NOT) && oldHouse.getIsRent().equals(Const.YIBIAOSANSHI_RENT)){
            if (oldHouse.getHouseOwnerInfo()!=null){
                ownerCustodianRentMapper.delete(oldHouse.getHouseOwnerInfo());
            }
            if (oldHouse.getCustodianInfo()!=null){
                ownerCustodianRentMapper.delete(oldHouse.getCustodianInfo());
            }
            if (oldHouse.getRentPersonInfo()!=null){
                ownerCustodianRentMapper.delete(oldHouse.getRentPersonInfo());
            }

        }


        if (houseVO.getIsRent().equals(Const.YIBIAOSANSHI_RENT)){
            OwnerCustodianRent owner = new OwnerCustodianRent();
            owner.setCardId(houseVO.getOwnerCardId());
            owner.setPhone(houseVO.getOwnerPhone());
            owner.setRealName(houseVO.getOwnerName());
            owner.setType(Const.OWNER_TYPE);

            //如果为空 说明房东为空 需要做新增 如果不为空 说明有房东 需要做更新
            if (oldHouse.getHouseOwnerInfo()==null){
                ownerCustodianRentMapper.add(owner);
                house.setHouseOwnerInfo(owner.getId());
            }else {
                owner.setId(houseVO.getHouseOwnerInfo());
                ownerCustodianRentMapper.update(owner);
            }

			if (!(StringUtil.isEmpty(houseVO.getCusName()) || StringUtil.isEmpty(houseVO.getCusName()) ||StringUtil.isEmpty(houseVO.getCusPhone()) )){
				OwnerCustodianRent custodian = new OwnerCustodianRent();
				custodian.setCardId(houseVO.getCusCardId());
				custodian.setPhone(houseVO.getCusPhone());
				custodian.setRealName(houseVO.getCusName());
				custodian.setType(Const.CUSTODIAN_TYPE);
                if (oldHouse.getCustodianInfo()==null){
                    ownerCustodianRentMapper.add(custodian);
                    house.setCustodianInfo(custodian.getId());
                }else {
                    custodian.setId(houseVO.getCustodianInfo());
                    ownerCustodianRentMapper.update(custodian);
                }
			}


            OwnerCustodianRent rent = new OwnerCustodianRent();
            rent.setCardId(houseVO.getRentCardId());
            rent.setPhone(houseVO.getRentPhone());
            rent.setRealName(houseVO.getRentName());
            rent.setType(Const.RENT_TYPE);

            if (oldHouse.getRentPersonInfo()==null){
                ownerCustodianRentMapper.add(rent);
                house.setRentPersonInfo(rent.getId());
            }else {
                rent.setId(houseVO.getRentPersonInfo());
                ownerCustodianRentMapper.update(rent);
            }
        }
        String code =houseVO.getAddressCode();
        Address param = new Address();
        param.setAddressCode(code);
        List<Address> address = addressMapper.findAddressListByParam(param);
        if (address==null){
            throw new BusinessException("地址参数错误");
        }
        house.setDz(address.get(0).getId());




		houseMapper.update(house);
	}




	/**
	 * 删除House
	 * @param id
	 */
	@Override
	@Transactional
	public void delete(Long id){
	    House house = houseMapper.findById(id);
	    if (house.getIsCompanyOwned().equals(Const.YIBIAOSANSHI_COMPANY_OWNED)){
	        Long companyId = house.getCompanyInfo();
            realCompanyMapper.delete(companyId);
        }
        if (house.getHouseUsage().equals(Const.HOUSE_USAGE_FOR_RENT)||house.getHouseUsage().equals(Const.HOUSE_USAGE_FOR_RENT_OR_OTHER)){
	        Long ownerid = house.getHouseOwnerInfo();
	        Long cusId = house.getCustodianInfo();
	        Long rentId = house.getRentPersonInfo();
	        List<Long> ids = new ArrayList<>();
	        ids.add(ownerid);
	        ids.add(cusId);
	        ids.add(rentId);
            realCompanyMapper.batchDelete(ids);
        }
		houseMapper.delete(id);
	}




	/**
	 *
	 * 功能描述:一标三实页面初始化加载已填报的房屋地址
	 *
	 * @param:
	 * @return:
	 * @author: chenduo
	 * @date: 2018/11/27 10:33
	 */
	@Override
	public BaseVo getAddress(HouseVO houseVO) {
		PageHelper.startPage(houseVO.getPageNum(), houseVO.getPageSize());
        if (houseVO.getAddressDetail()!=null){
            String str = houseVO.getAddressDetail();
            str = str+"%";
            houseVO.setAddressDetail(str);
        }
        UserInfo userInfo = getCurrentUserInfo();
        UserAddress userAddress = userInfo.getCurrentUserAddresses();
        if (userAddress!=null){
            String addressCode = userAddress.getAddressCode();
            if (StringUtil.isNotEmpty(addressCode)){
                addressCode = addressCode+'%';
                houseVO.setAddressCode(addressCode);
            }
        }

		List<HouseAddressVO> list = houseMapper.getAddress(houseVO);
        for (HouseAddressVO houseAddressVO : list) {
            houseAddressVO.setAddressDetail(houseAddressVO.getAddressDetail().replace(",",""));
        }
        PageInfo<HouseAddressVO> pageInfo = new PageInfo<HouseAddressVO>(list);
		BaseVo baseVo = new BaseVo();
		baseVo.setData(pageInfo);
		return baseVo;
	}

    /**
     *
     * 功能描述:同步一标三实
     *
     * @param:
     * @return:
     * @author: chenduo
     * @date: 2018/11/27 13:13
     */
    @Override
    @Transactional
    public BaseVo synchronize(Long id) {
	    BaseVo baseVo = new BaseVo();
        House house = houseMapper.findById(id);
        //TODO 与云平台互动同步数据

        //同步次数加1
        if (house.getSynchroCount()==null){
            house.setSynchroCount(0);
        }
        house.setSynchroCount(house.getSynchroCount()+1);
        //设置同步时间
        SimpleDateFormat sdf = new SimpleDateFormat(Const.DATE_FORMATE);
        String time = sdf.format(new Date());
        Date date = DateUtil.parse(time);
        house.setSynchroTime(date);
        houseMapper.update(house);
        return baseVo;
    }
}
