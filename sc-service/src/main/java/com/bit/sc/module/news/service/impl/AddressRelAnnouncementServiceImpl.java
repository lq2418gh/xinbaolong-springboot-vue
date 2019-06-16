package com.bit.sc.module.news.service.impl;

import java.util.List;
import java.util.ArrayList;

import com.bit.sc.module.news.dao.AddressRelAnnouncementMapper;
import com.bit.sc.module.news.pojo.AddressRelAnnouncement;
import com.bit.sc.module.news.service.AddressRelAnnouncementService;
import com.bit.sc.module.news.vo.AddressAnnouncementVO;
import com.bit.sc.module.news.vo.AddressRelAnnouncementVO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.bit.base.vo.BaseVo;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.bit.base.service.BaseService;
import org.springframework.transaction.annotation.Transactional;

/**
 * AddressRelAnnouncement的Service实现类
 * @author chenduo
 *
 */
@Service("addressRelAnnouncementService")
public class AddressRelAnnouncementServiceImpl extends BaseService implements AddressRelAnnouncementService {
	
	private static final Logger logger = LoggerFactory.getLogger(AddressRelAnnouncementServiceImpl.class);
	
	@Autowired
	private AddressRelAnnouncementMapper addressRelAnnouncementMapper;
	
	/**
	 * 根据条件查询AddressRelAnnouncement
	 * @param addressRelAnnouncementVO
	 * @return
	 */
	@Override
	public BaseVo findByConditionPage(AddressRelAnnouncementVO addressRelAnnouncementVO){
		PageHelper.startPage(addressRelAnnouncementVO.getPageNum(), addressRelAnnouncementVO.getPageSize());
		List<AddressRelAnnouncement> list = addressRelAnnouncementMapper.findByConditionPage(addressRelAnnouncementVO);
		PageInfo<AddressRelAnnouncement> pageInfo = new PageInfo<AddressRelAnnouncement>(list);
		BaseVo baseVo = new BaseVo();
		baseVo.setData(pageInfo);
		return baseVo;
	}
	/**
	 * 查询所有AddressRelAnnouncement
	 * @param sorter 排序字符串
	 * @return
	 */
	@Override
	public List<AddressRelAnnouncement> findAll(String sorter){
		return addressRelAnnouncementMapper.findAll(sorter);
	}
	/**
	 * 通过主键查询单个AddressRelAnnouncement
	 * @param id
	 * @return
	 */
	@Override
	public AddressRelAnnouncement findById(Long id){
		return addressRelAnnouncementMapper.findById(id);
	}
	
	/**
	 * 批量保存AddressRelAnnouncement
	 * @param addressRelAnnouncements
	 */
	@Override
	@Transactional
	public void batchAdd(List<AddressRelAnnouncement> addressRelAnnouncements){
		addressRelAnnouncementMapper.batchAdd(addressRelAnnouncements);
	}
	/**
	 * 保存AddressRelAnnouncement
	 * @param addressRelAnnouncement
	 */
	@Override
	@Transactional
	public void add(AddressRelAnnouncement addressRelAnnouncement){
		addressRelAnnouncementMapper.add(addressRelAnnouncement);
	}
	/**
	 * 批量更新AddressRelAnnouncement
	 * @param addressRelAnnouncements
	 */
	@Override
	@Transactional
	public void batchUpdate(List<AddressRelAnnouncement> addressRelAnnouncements){
//		addressRelAnnouncementMapper.batchUpdate(addressRelAnnouncements);
	}
	/**
	 * 更新AddressRelAnnouncement
	 * @param addressRelAnnouncement
	 */
	@Override
	@Transactional
	public void update(AddressRelAnnouncement addressRelAnnouncement){
		addressRelAnnouncementMapper.update(addressRelAnnouncement);
	}
	/**
	 * 删除AddressRelAnnouncement
	 * @param ids
	 */
	@Override
	@Transactional
	public void batchDelete(List<Long> ids){
		addressRelAnnouncementMapper.batchDelete(ids);
	}


	/**
	 * 删除AddressRelAnnouncement
	 * @param id
	 */
	@Override
	@Transactional
	public void delete(Long id){
		addressRelAnnouncementMapper.delete(id);
	}

	/**
	 *
	 * 功能描述:页面上使用 查询分页查询地址 新闻 中间表关系
	 *
	 * @param:
	 * @return:
	 * @author: chenduo
	 * @date: 2018/11/20 15:17
	 */
	@Override
	public BaseVo queryAddressNewsByConditionPage(AddressRelAnnouncementVO addressRelAnnouncementVO) {
		PageHelper.startPage(addressRelAnnouncementVO.getPageNum(), addressRelAnnouncementVO.getPageSize());
		List<AddressAnnouncementVO> list = addressRelAnnouncementMapper.queryAddressNewsByConditionPage(addressRelAnnouncementVO);
		PageInfo<AddressAnnouncementVO> pageInfo = new PageInfo<AddressAnnouncementVO>(list);
		BaseVo baseVo = new BaseVo();
		baseVo.setData(pageInfo);
		return baseVo;
	}
    /**
     *
     * 功能描述:修改关联关系
     *
     * @param:
     * @return:
     * @author: chenduo
     * @date: 2018/11/20 16:24
     */
    @Override
    @Transactional
    public void editRelation(AddressRelAnnouncementVO addressRelAnnouncementVO) {
        //先批量删除 根据新闻id
        addressRelAnnouncementMapper.deleteRelation(addressRelAnnouncementVO);
        List<AddressRelAnnouncement> list = new ArrayList<>();
        List<Long> ids = addressRelAnnouncementVO.getAddressids();
        for (int i = 0 ;i<ids.size();i++){
            AddressRelAnnouncement addressRelAnnouncement = new AddressRelAnnouncement();
            addressRelAnnouncement.setAddressId(ids.get(i));
            addressRelAnnouncement.setAnnouncementId(addressRelAnnouncementVO.getAnnouncementId());
            list.add(addressRelAnnouncement);
        }

        //新增 新闻和小区id
        addressRelAnnouncementMapper.batchAdd(list);
    }

    /**
     *
     * 功能描述:删除新闻和小区的关联关系
     *
     * @param:
     * @return:
     * @author: chenduo
     * @date: 2018/11/20 16:48
     */

    @Override
    @Transactional
    public void deleteRelation(AddressRelAnnouncementVO addressRelAnnouncementVO) {
        addressRelAnnouncementMapper.deleteRelation(addressRelAnnouncementVO);
    }

}
