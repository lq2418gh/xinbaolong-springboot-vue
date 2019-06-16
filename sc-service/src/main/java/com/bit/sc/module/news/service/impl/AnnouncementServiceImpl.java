package com.bit.sc.module.news.service.impl;

import com.bit.base.bean.UserAddress;
import com.bit.base.exception.BusinessException;
import com.bit.base.service.BaseService;
import com.bit.base.vo.BaseVo;
import com.bit.base.vo.UserInfo;
import com.bit.sc.common.Const;
import com.bit.sc.common.ModuleFileType;
import com.bit.sc.module.attachment.pojo.Attachment;
import com.bit.sc.module.attachment.service.AttachmentService;
import com.bit.sc.module.news.dao.AddressRelAnnouncementMapper;
import com.bit.sc.module.news.dao.AnnouncementMapper;
import com.bit.sc.module.news.pojo.AddressRelAnnouncement;
import com.bit.sc.module.news.pojo.Announcement;
import com.bit.sc.module.news.service.AnnouncementService;
import com.bit.sc.module.news.vo.AddressRelAnnouncementVO;
import com.bit.sc.module.news.vo.AnnouncementAddressVO;
import com.bit.sc.module.news.vo.AnnouncementVO;
import com.bit.sc.module.sys.dao.AddressMapper;
import com.bit.sc.module.sys.pojo.Address;
import com.bit.sc.utils.DateUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Announcement的Service实现类
 *
 * @author: chenduo
 */
@Service("announcementService")
public class AnnouncementServiceImpl extends BaseService implements AnnouncementService {

    private static final Logger logger = LoggerFactory.getLogger(AnnouncementServiceImpl.class);

    @Autowired
    private AnnouncementMapper announcementMapper;
    @Autowired
    private AddressRelAnnouncementMapper addressRelAnnouncementMapper;
    @Autowired
    private AddressMapper addressMapper;
    @Autowired
    private AttachmentService attachmentService;

    /**
     * 根据条件查询Announcement
     * @author: chenduo
     * @param announcementVO
     * @return
     */
    @Override
    public BaseVo findByConditionPage(AnnouncementVO announcementVO) {
        BaseVo baseVo = new BaseVo();

        UserInfo userInfo = getCurrentUserInfo();
        UserAddress userAddress = userInfo.getCurrentUserAddresses();
        String addressCode = "";
        //如果是admin userAddress会为null 不设置addressId过滤
        if (userAddress!=null){
            addressCode = userAddress.getAddressCode();
            Address address = addressMapper.findByAddressCode(addressCode);
            announcementVO.setAddressId(address.getId());
        }

        //设置只查询已发布的 未删除的
        announcementVO.setIsDelete(Const.NOT_DELETE);
        announcementVO.setIsPublish(Const.PUBLISH);
        announcementVO.setIsBanner(Const.NOT_BANNER);
        PageHelper.startPage(announcementVO.getPageNum(), announcementVO.getPageSize());

        List<Announcement> list = announcementMapper.findByConditionPage(announcementVO);
        PageInfo<Announcement> pageInfo = new PageInfo<Announcement>(list);
        baseVo.setData(pageInfo);
        return baseVo;
    }

    /**
     * 查询所有Announcement
     * @author: chenduo
     * @param announcementVO 排序字符串
     * @return
     */
    @Override
    public List<Announcement> findAll(AnnouncementVO announcementVO) {
        return announcementMapper.findAll(announcementVO);
    }

    /**
     * 通过主键查询单个Announcement
     * @author: chenduo
     * @param id
     * @return
     */
    @Override
    public Announcement findById(Long id) {

        return announcementMapper.findById(id);
    }

    /**
     * 前台详情接口（查看时阅读量加1）
     * @author: chenduo
     * @param id
     * @return
     */
    @Override
    @Transactional
    public Announcement readNum(Long id) {

        Announcement announcement = announcementMapper.findById(id);
        Integer amount = announcement.getAmount();
        amount += 1;
        announcement.setAmount(amount);
        announcementMapper.update(announcement);
        return announcement;
    }





    /**
     * 保存Announcement
     * @author: chenduo
     * @param announcementVO
     */
    @Override
    @Transactional
    public void add(AnnouncementVO announcementVO) {
        Announcement announcement = new Announcement();
        BeanUtils.copyProperties(announcementVO,announcement);
        SimpleDateFormat sdf = new SimpleDateFormat(Const.DATE_FORMATE);
        String time = sdf.format(new Date());
        Date date = DateUtil.parse(time);
        announcement.setCreateTime(date);
        announcement.setUpdateTime(date);
        //获取当前用户id
        Long id = getCurrentUserInfo().getId();
        //获取当前用户名
        String createUserName = getCurrentUserInfo().getUserName();
        announcement.setCreateUserName(createUserName);
        announcement.setCreateUserId(id);
        //默认设置新闻不是横幅 如有需求可去掉
        announcement.setIsBanner(Const.NOT_BANNER);
        //默认设置新闻可用 如有需求可去掉
        announcement.setIsDelete(Const.NOT_DELETE);
        //默认设置新闻已发布
        announcement.setIsPublish(Const.PUBLISH);
        announcement.setAmount(Const.INITIAL_AMOUNT);
        announcementMapper.add(announcement);

        Long announcementId = announcement.getId();

        List<Long> ids = announcementVO.getAddressIds();
        List<AddressRelAnnouncement> addressRelAnnouncementList = new ArrayList<>();
        for (int i =0;i<ids.size();i++){
            AddressRelAnnouncement addressRelAnnouncement = new AddressRelAnnouncement();
            addressRelAnnouncement.setAddressId(ids.get(i));
            addressRelAnnouncement.setAnnouncementId(announcementId);
            addressRelAnnouncementList.add(addressRelAnnouncement);
        }

        addressRelAnnouncementMapper.batchAdd(addressRelAnnouncementList);


    }



    /**
     * 更新Announcement
     * @author: chenduo
     * @param announcementAddressVO
     */
    @Override
    @Transactional
    public void update(AnnouncementAddressVO announcementAddressVO) {
        Announcement announcement = announcementAddressVO.getAnnouncement();


        Long id = getCurrentUserInfo().getId();
        announcement.setUpdateUserId(id);
        announcement.setUpdateUserName(getCurrentUserInfo().getUserName());

        SimpleDateFormat sdf = new SimpleDateFormat(Const.DATE_FORMATE);
        String time = sdf.format(new Date());
        Date date = DateUtil.parse(time);
        announcement.setUpdateTime(date);


        List<AddressRelAnnouncement> addressRelAnnouncementList = announcementAddressVO.getAddressRelAnnouncementList();
        for (AddressRelAnnouncement addressRelAnnouncement : addressRelAnnouncementList){
            if (addressRelAnnouncement.getActionType().equals(Const.ACTION_TYPE_UPDATE)){
                AddressRelAnnouncement adr = new AddressRelAnnouncement();
                adr.setAnnouncementId(announcement.getId());
                adr.setAddressId(addressRelAnnouncement.getAddressId());
                addressRelAnnouncementMapper.update(adr);
            }
            if (addressRelAnnouncement.getActionType().equals(Const.ACTION_TYPE_DELETE)){
                addressRelAnnouncementMapper.delete(addressRelAnnouncement.getId());
            }
            if (addressRelAnnouncement.getActionType().equals(Const.ACTION_TYPE_INSERT)){
                AddressRelAnnouncement adr = new AddressRelAnnouncement();
                adr.setAnnouncementId(announcement.getId());
                adr.setAddressId(addressRelAnnouncement.getAddressId());
                addressRelAnnouncementMapper.add(adr);
            }
        }


        announcementMapper.update(announcement);
    }





    /**
     * 删除Announcement
     * @author: chenduo
     * @param id
     */
    @Override
    @Transactional
    public void delete(Long id) {

        announcementMapper.deleteLogicById(id);
    }


    /**
     *
     * 功能描述: 查询横幅
     *
     * @param: AnnouncementVO announcementVO
     * @return: basevo
     * @author: chenduo
     * @date: 2018/11/16 10:22
     */
    @Override
    public BaseVo queryBanner(AnnouncementVO announcementVO) {
        BaseVo baseVo = new BaseVo();
        announcementVO.setIsDelete(Const.NOT_DELETE);
        announcementVO.setIsPublish(Const.PUBLISH);
        announcementVO.setIsBanner(Const.BANNER);
        PageHelper.startPage(announcementVO.getPageNum(), announcementVO.getPageSize());
        List<Announcement> list = announcementMapper.queryBanner(announcementVO);
        PageInfo<Announcement> pageInfo = new PageInfo<Announcement>(list);
        baseVo.setData(pageInfo);
        return baseVo;
    }
    /**
     * 根据条件查询Announcement web端用
     * @param announcementVO
     * @return
     */
    @Override
    public BaseVo queryListPage(AnnouncementVO announcementVO) {
        BaseVo baseVo = new BaseVo();

        if (announcementVO.getTitle()!=null){
            String str = "%"+announcementVO.getTitle()+"%";
            announcementVO.setTitle(str);
        }

        UserInfo userInfo = getCurrentUserInfo();
        UserAddress userAddress = userInfo.getCurrentUserAddresses();
        String addressCode = "";
        if (userAddress!=null){
            addressCode = userAddress.getAddressCode();
        }
        if (!addressCode.equals("")){
            Address address = addressMapper.findByAddressCode(addressCode);
            announcementVO.setAddressId(address.getId());
        }

        announcementVO.setIsBanner(Const.NOT_BANNER);
        PageHelper.startPage(announcementVO.getPageNum(), announcementVO.getPageSize());
        List<Announcement> list = announcementMapper.queryListPage(announcementVO);
        PageInfo<Announcement> pageInfo = new PageInfo<Announcement>(list);
        baseVo.setData(pageInfo);
        return baseVo;
    }
    /**
     * 根据条件查询Announcement 反显使用
     * @param id
     * @return
     */
    @Override
    public BaseVo reflect(Long id) {
        BaseVo baseVo = new BaseVo();
        Announcement announcement = announcementMapper.findById(id);

        AddressRelAnnouncementVO temp = new AddressRelAnnouncementVO();
        temp.setAnnouncementId(id);
        List<AddressRelAnnouncement> addressRelAnnouncementList = addressRelAnnouncementMapper.findByConditionPage(temp);

        AnnouncementAddressVO announcementAddressVO =new AnnouncementAddressVO();
        announcementAddressVO.setAnnouncement(announcement);
        announcementAddressVO.setAddressRelAnnouncementList(addressRelAnnouncementList);

        baseVo.setData(announcementAddressVO);
        return baseVo;
    }
    /**
     * 置顶
     * @param id
     * @return
     */
    @Override
    public BaseVo top(Long id) {
        Announcement announcement = announcementMapper.findById(id);
        SimpleDateFormat sdf = new SimpleDateFormat(Const.DATE_FORMATE);
        String time = sdf.format(new Date());
        Date date = DateUtil.parse(time);
        announcement.setUpdateTime(date);
        announcementMapper.update(announcement);
        return new BaseVo();
    }
    /**
     * 上传照片
     * @param file
     * @return
     */
    @Override
    public BaseVo uploadpicture(MultipartFile file) {
        Attachment attachment = new Attachment();
        attachment.setBusinessId(ModuleFileType.RESIDENT_IMAGE);
        Long addAttachment  = attachmentService.addAttachment(attachment,file);
        attachmentService.findByAttachmentId(addAttachment);
        BaseVo baseVo = new BaseVo();
        baseVo.setData(attachment);


        return baseVo;
    }
    /**
     *
     * 功能描述:修改发布状态
     *
     * @param:
     * @return:
     * @author: chenduo
     * @date: 2018/12/24 11:24
     */
    @Override
    public BaseVo changePublish(Announcement announcement) {

        announcementMapper.update(announcement);
        return new BaseVo();
    }
}
