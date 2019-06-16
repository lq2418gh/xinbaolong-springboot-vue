package com.bit.sc.module.manface.service.impl;

import com.bit.base.bean.UserAddress;
import com.bit.base.service.BaseService;
import com.bit.base.vo.BaseVo;
import com.bit.base.vo.UserInfo;
import com.bit.sc.common.Const;
import com.bit.sc.module.manface.dao.ManfacewhitelistMapper;
import com.bit.sc.module.manface.pojo.Manfacewhitelist;
import com.bit.sc.module.manface.service.ManfacewhitelistService;
import com.bit.sc.module.manface.vo.ManfaceResidentEquipmentVO;
import com.bit.sc.module.manface.vo.ManfacewhitelistVO;
import com.bit.sc.utils.DateUtil;
import com.bit.utils.StringUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * Manfacewhitelist的Service实现类
 * @author chenduo
 *
 */
@Service("manfacewhitelistService")
public class ManfacewhitelistServiceImpl extends BaseService implements ManfacewhitelistService {
	
	private static final Logger logger = LoggerFactory.getLogger(ManfacewhitelistServiceImpl.class);
	
	@Autowired
	private ManfacewhitelistMapper manfacewhitelistMapper;
	

	/**
	 * 查询所有Manfacewhitelist
	 * @param sorter 排序字符串
	 * @return
	 */
	@Override
	public List<Manfacewhitelist> findAll(String sorter){
		return manfacewhitelistMapper.findAll(sorter);
	}
	/**
	 * 通过主键查询单个Manfacewhitelist
	 * @param id
	 * @return
	 */
	@Override
	public Manfacewhitelist findById(Long id){
		return manfacewhitelistMapper.findById(id);
	}
	

	/**
	 * 保存Manfacewhitelist
	 * @param manfacewhitelist
	 */
	@Override
	public void add(Manfacewhitelist manfacewhitelist){


        manfacewhitelist.setSynchroStatus(Const.MANFACE_WHITELIST_SYNCHRO_STATUS_NOT_SYNCHRO);
        manfacewhitelist.setSynchroCount(0);
		manfacewhitelistMapper.add(manfacewhitelist);
	}

	/**
	 * 更新Manfacewhitelist
	 * @param manfacewhitelist
	 */
	@Override
	public void update(Manfacewhitelist manfacewhitelist){

		manfacewhitelistMapper.update(manfacewhitelist);
	}




    /**
	 * 删除Manfacewhitelist
	 * @param id
	 */
	@Override
	public void delete(Long id){

		manfacewhitelistMapper.delete(id);
	}

    /**
     *
     * 功能描述: 分页查询Manfacewhitelist列表 页面显示使用
     *
     * @param: manfacewhitelistVO
     * @return:
     * @author: chenduo
     * @date: 2018/11/21 16:01
     */
    @Override
    public BaseVo queryAllByPage(ManfacewhitelistVO manfacewhitelistVO) {
		PageHelper.startPage(manfacewhitelistVO.getPageNum(), manfacewhitelistVO.getPageSize());
		if (manfacewhitelistVO.getAddressDetail()!=null){
            manfacewhitelistVO.setAddressDetail(manfacewhitelistVO.getAddressDetail()+"%");
        }
        //获取当前管理员你的小区code
        UserInfo userInfo = getCurrentUserInfo();
        UserAddress userAddress = userInfo.getCurrentUserAddresses();
        if (userAddress!=null){
            String addressCode = userAddress.getAddressCode();
            if (StringUtil.isNotEmpty(addressCode)){
                manfacewhitelistVO.setAddressCode(addressCode+"%");
            }
        }


        List<ManfaceResidentEquipmentVO> list = manfacewhitelistMapper.queryAllByPage(manfacewhitelistVO);
        PageInfo<ManfaceResidentEquipmentVO> pageInfo = new PageInfo<ManfaceResidentEquipmentVO>(list);
        BaseVo baseVo = new BaseVo();
        baseVo.setData(pageInfo);
        return baseVo;
    }

    @Override
    public BaseVo synchronize(Long id) {
        BaseVo baseVo = new BaseVo();
        Manfacewhitelist manfacewhitelist = manfacewhitelistMapper.findById(id);
        //TODO 与云平台互动同步数据

        //同步次数加1
        manfacewhitelist.setSynchroCount(manfacewhitelist.getSynchroCount()+1);
        //设置同步时间
        SimpleDateFormat sdf = new SimpleDateFormat(Const.DATE_FORMATE);
        String time = sdf.format(new Date());
        Date date = DateUtil.parse(time);
        manfacewhitelist.setSynchroTime(date);
        manfacewhitelistMapper.update(manfacewhitelist);
        return baseVo;
    }


}
