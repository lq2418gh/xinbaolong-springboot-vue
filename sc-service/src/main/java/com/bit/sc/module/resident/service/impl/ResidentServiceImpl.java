package com.bit.sc.module.resident.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.bit.base.bean.UserAddress;
import com.bit.base.exception.BusinessException;
import com.bit.base.service.BaseService;
import com.bit.base.vo.BaseVo;
import com.bit.base.vo.UserInfo;
import com.bit.sc.common.Const;
import com.bit.sc.common.ModuleFileType;
import com.bit.sc.module.attachment.pojo.Attachment;
import com.bit.sc.module.attachment.service.AttachmentService;
import com.bit.sc.module.equipment.dao.EquipmentMapper;
import com.bit.sc.module.equipment.pojo.Equipment;
import com.bit.sc.module.equipment.vo.EquipmentVO;
import com.bit.sc.module.group.dao.GroupMapper;
import com.bit.sc.module.group.dao.GroupRelMapper;
import com.bit.sc.module.group.pojo.Group;
import com.bit.sc.module.group.pojo.GroupRel;
import com.bit.sc.module.group.vo.GroupRelVO;
import com.bit.sc.module.group.vo.GroupVO;
import com.bit.sc.module.manface.dao.ManfacewhitelistMapper;
import com.bit.sc.module.manface.pojo.Manfacewhitelist;
import com.bit.sc.module.manface.service.ManfacewhitelistService;
import com.bit.sc.module.manface.vo.ManfacewhitelistVO;
import com.bit.sc.module.resident.dao.ResidentDoorCardMapper;
import com.bit.sc.module.resident.dao.ResidentMapper;
import com.bit.sc.module.resident.dao.ResidentMobileMapper;
import com.bit.sc.module.resident.pojo.Resident;
import com.bit.sc.module.resident.pojo.ResidentDoorCard;
import com.bit.sc.module.resident.pojo.ResidentMobile;
import com.bit.sc.module.resident.service.ResidentService;
import com.bit.sc.module.resident.vo.*;

import com.bit.sc.module.sys.dao.AddressMapper;
import com.bit.sc.module.sys.dao.ResidentRelAddressMapper;
import com.bit.sc.module.sys.dao.UserRelPhoneMapper;
import com.bit.sc.module.sys.pojo.Address;
import com.bit.sc.module.sys.pojo.ResidentRelAddress;
import com.bit.sc.module.sys.pojo.UserRelPhone;
import com.bit.sc.module.sys.vo.AddressVO;
import com.bit.sc.module.sys.vo.ResidentRelAddressActionVO;
import com.bit.sc.module.sys.vo.ResidentRelAddressVO;
import com.bit.sc.module.sys.vo.UserRelPhoneVO;
import com.bit.sc.module.user.dao.UserMapper;
import com.bit.sc.utils.DateUtil;
import com.bit.sc.utils.JSMSUtil;
import com.bit.utils.CacheUtil;
import com.bit.utils.MD5Util;
import com.bit.utils.StringUtil;
import com.bit.utils.UUIDUtil;
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
import java.util.*;

/**
 * 居民的Service实现类
 *
 * @author chenduo
 */
@Service("residentService")
public class ResidentServiceImpl extends BaseService implements ResidentService {

    private static final Logger logger = LoggerFactory.getLogger(ResidentServiceImpl.class);

    @Autowired
    private UserRelPhoneMapper userRelPhoneMapper;
    @Autowired
    private ResidentMapper residentMapper;
    @Autowired
    private CacheUtil cacheUtil;
    @Autowired
    private ResidentMobileMapper residentMobileMapper;
    @Autowired
    private AddressMapper addressMapper;
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private AttachmentService attachmentService;
    @Autowired
    private ResidentRelAddressMapper residentRelAddressMapper;

    @Autowired
    private ResidentDoorCardMapper residentDoorCardMapper;
    @Autowired
    private GroupRelMapper groupRelMapper;
    @Autowired
    private GroupMapper groupMapper;

    /**
     * 得到短信验证码
     *
     * @param residentVO
     * @return
     */
    @Override
    public BaseVo getSmsCode(ResidentVO residentVO) {
        UserRelPhoneVO userRelPhoneVO = new UserRelPhoneVO();
        userRelPhoneVO.setPhone(residentVO.getPhone());
        userRelPhoneVO.setStatus(Const.RESIDENT_STATUS_BINDED);
        List<UserRelPhone> list = userRelPhoneMapper.findByConditionPage(userRelPhoneVO);
        if (list == null || list.size() == 0) {
            throw new BusinessException("用户名密码错误");
        }
        String isExist = (String) cacheUtil.get(Const.REDIS_KEY_SMSCAPTCHA_RATE + residentVO.getPhone());
        if (isExist != null) {
            throw new BusinessException("获取短信验证码速度太快了,请稍后再试");
        }
        //60秒获取一次验证码
        cacheUtil.set(Const.REDIS_KEY_SMSCAPTCHA_RATE + residentVO.getPhone(), "1", 60);
        String code = (int) ((Math.random() * 9 + 1) * 1000) + "";
        cacheUtil.set(Const.REDIS_KEY_SMSCAPTCHA + residentVO.getPhone(), code, 300);//算上发短信到手机的时间
        residentVO.setSmsCaptcha(code);
        return residentVO;
    }

    @Override
    public BaseVo login(ResidentVO residentVO) {
        String smscapticha = (String) cacheUtil.get(Const.REDIS_KEY_SMSCAPTCHA + residentVO.getPhone());
        if (residentVO.getSmsCaptcha() == null
                || smscapticha == null
                || !residentVO.getSmsCaptcha().equals(smscapticha)) {
            throw new BusinessException("短信验证码错误");
        }
        UserRelPhoneVO userRelPhoneVO = new UserRelPhoneVO();
        userRelPhoneVO.setPhone(residentVO.getPhone());
        userRelPhoneVO.setStatus(Const.RESIDENT_STATUS_BINDED);
        List<UserRelPhone> list = userRelPhoneMapper.findByConditionPage(userRelPhoneVO);
        if (list != null && list.size() > 0) {
            cacheUtil.del(Const.REDIS_KEY_SMSCAPTCHA + residentVO.getPhone());
            return residentVO;
        } else {
            throw new BusinessException("用户名密码错误");
        }
    }

    /**
     * app居民端用户注册业务
     *
     * @param residentMobileVO
     * @return baseVo
     * @author zhangjie
     * @date 2018-11-13
     */
    @Override
    @Transactional
    public BaseVo appRegister(ResidentMobileVO residentMobileVO) {

        //根据身份证号+手机号查询手机号激活状态
        ResidentMobileVO vo = new ResidentMobileVO();
        vo.setPhone(residentMobileVO.getPhone());
        vo.setCardId(residentMobileVO.getCardId());
        vo.setPassword(residentMobileVO.getPassword());
        vo.setRealName(residentMobileVO.getRealName());
        vo.setSmsCaptcha(residentMobileVO.getSmsCaptcha());
        List<ResidentMobileVO> rm = residentMobileMapper.findStatusByCardId(residentMobileVO);
        if (Const.ONLY_ONE == rm.size()) {//判断是否有脏数据
            if (Const.RESIDENT_STATUS_BINDED.equals(rm.get(0).getStatus())) {
                //已绑定
                throw new BusinessException("已激活");
            } else if (Const.RESIDENT_STATUS_BINDED_NOT.equals(rm.get(0).getStatus())) {//未激活状态
                if (vo.getRealName().equals(rm.get(0).getRealName())) {//核对真实姓名

                    //校验短信验证码
                    //从redis中获取msg_id
                    String msg_id = (String) cacheUtil.get(Const.REDIS_KEY_SMSCAPTCHA + String.valueOf(residentMobileVO.getPhone()));
                    //获取前台输入的验证码
                    String code = residentMobileVO.getSmsCaptcha();
                    //调用极光验证
                    Boolean valid = JSMSUtil.testSendValidSMSCode(msg_id, code);
                    if (false == valid) {
                        throw new BusinessException("短信验证失败！");
                    } else {
                        ResidentMobile residentMobile = new ResidentMobile();
                        Resident resident = new Resident();

                        //更新密码
                        String pw1 = MD5Util.compute(vo.getPassword());
                        resident.setPassword(pw1);
                        resident.setId(rm.get(0).getResidentId());
                        residentMapper.update(resident);

                        //更新激活状态
                        residentMobile.setStatus(1);
                        residentMobile.setId(rm.get(0).getId());

                        //保存极光推送用的registration_id
                        residentMobile.setRegistrationId(residentMobileVO.getRegistrationId());

                        residentMobileMapper.update(residentMobile);
                        residentMobileMapper.findByConditionPage(vo);

                    }
                }
            } else {
                throw new BusinessException("不可用！");
            }
        }
        return new BaseVo();
    }

    /**
     * app居民端用户登录业务
     *
     * @param residentMobileVO
     * @return residentMobileVO
     * @author zhangjie
     * @date 2018-11-13
     */
    @Override
    public BaseVo appLogin(ResidentMobileVO residentMobileVO) {
        BaseVo baseVo = new BaseVo();
        ResidentMobileVO rm = new ResidentMobileVO();
        rm.setPhone(residentMobileVO.getPhone());
        //判断手机号是否存在
        List<ResidentMobileVO> residentMobiles = residentMobileMapper.findStatusByCardId(rm);
        if (Const.ONLY_ONE == residentMobiles.size()) {
            if (Const.RESIDENT_STATUS_BINDED.equals(residentMobiles.get(0).getStatus())) {//是否激活
                //校验密码是否相同
                String pwl = MD5Util.compute(residentMobileVO.getPassword());
                if (!pwl.equals(residentMobiles.get(0).getPassword())) {
                    throw new BusinessException("密码不正确！");
                } else {
                    String token = Const.TOKEN_TITLE_TEST + UUIDUtil.getUUID();
//                        String token = Const.TOKEN_TITLE + UUIDUtil.getUUID();
                    UserInfo userInfo = new UserInfo();
                    userInfo.setId(residentMobiles.get(0).getResidentId());
                    userInfo.setUserName(residentMobiles.get(0).getRealName());
                    userInfo.setToken(token);

                    //根据手机号查询地址
                    List<Long> id = new ArrayList<>();
                    List<Address> addresses = residentMobileMapper.findAddressByPhone(rm);
                    for (Address add : addresses) {
                        String aa=add.getAddressCode().substring(1,add.getAddressCode().length()-1);
                        String[] address = aa.split("/");
                        id.add(Long.valueOf(address[1]));
                    }
                    //根据地址id批量查询
                    List<Address> result = userMapper.findAddress(id);

                    List<UserAddress> userAddresses =new ArrayList<UserAddress>();
                    for (Address addr : result) {
                        UserAddress ua = new UserAddress();
                        ua.setId(addr.getId());
                        ua.setAddressCode(addr.getAddressCode());
                        ua.setAddressName(addr.getAddressName());
                        userAddresses.add(ua);
                    }
                    userInfo.setUserAddresses(userAddresses);
                    userInfo.setCurrentUserAddresses(userAddresses.get(0));

                    String json = JSON.toJSONString(userInfo,SerializerFeature.DisableCircularReferenceDetect);
                    cacheUtil.set(token, json, 3600);
                    Map<String, Object> rss = new HashMap();
                    rss.put("token", token);
                    Map<String, Object> userData = new HashMap();
                    userData.put("userName", residentMobiles.get(0).getRealName());
                    userData.put("userId", residentMobiles.get(0).getResidentId());
                    userData.put("address", result);
                    rss.put("userInfo", userData);
                    baseVo.setData(rss);
                }
            } else {//未激活
                throw new BusinessException("未激活！");
            }
        } else {
            throw new BusinessException("无此用户！");
        }
        return baseVo;
    }

    /**
     * app居民端用户注销登录业务
     *
     * @param
     * @return baseVo
     * @author zhangjie
     * @date 2018-11-14
     */
    @Override
    public BaseVo logout(ResidentMobileVO residentMobileVO) {
        UserInfo userInfo = getCurrentUserInfo();
        cacheUtil.del(userInfo.getToken());
        BaseVo baseVo = new BaseVo();
        return baseVo;
    }

    /**
     * app居民端忘记密码业务
     *
     * @param residentMobileVO
     * @return baseVo
     * @author zhangjie
     * @date 2018-11-14
     */
    @Override
    @Transactional
    public BaseVo forgetPassword(ResidentMobileVO residentMobileVO) {

        //从redis中获取msg_id
        String msg_id = (String) cacheUtil.get(Const.REDIS_KEY_SMSCAPTCHA + String.valueOf(residentMobileVO.getPhone()));
        //获取前台输入的验证码
        String code = residentMobileVO.getSmsCaptcha();
        //调用极光验证
        Boolean valid = JSMSUtil.testSendValidSMSCode(msg_id, code);
        if (false == valid) {
            throw new BusinessException("短信验证失败！");
        } else {
            List<ResidentMobile> rm = residentMobileMapper.findByConditionPage(residentMobileVO);
            if (Const.ONLY_ONE == rm.size()) {
                if (Const.RESIDENT_STATUS_BINDED.equals(rm.get(0).getStatus())) {
                    String pw1 = MD5Util.compute(residentMobileVO.getPassword());
                    Resident resident = new Resident();
                    resident.setPassword(pw1);
                    resident.setId(rm.get(0).getResidentId());
                    residentMapper.update(resident);
                } else {
                    throw new BusinessException("账号未激活！");
                }
            } else {
                throw new BusinessException("用户不止一个，无法修改密码！");
            }
            return new BaseVo();
        }
    }

    /**
     * app居民端发送验证码业务
     *
     * @param residentMobileVO
     * @return baseVo
     * @author zhangjie
     * @date 2018-11-14
     */
    @Override
    public BaseVo sendVaildCode(ResidentMobileVO residentMobileVO) {



        ResidentMobileVO vo = new ResidentMobileVO();
        vo.setPhone(residentMobileVO.getPhone());
        String phone = String.valueOf(vo.getPhone());
//        String msg_id = JSMSUtil.sendSMSCode(phone);
        cacheUtil.set(Const.REDIS_KEY_SMSCAPTCHA + phone, "1111", 6000 * 10 * 5);
        return new BaseVo();
    }

    /**
     * 查询所有居民
     *
     * @param sorter
     * @return List<Resident>
     * @author zhangjie
     * @date 2018-11-24
     */
    @Override
    public List<Resident> findAll(String sorter) {
        return residentMapper.findAll(sorter);
    }


    /**
     * 保存Resident
     * @param residentVO
     */
    @Override
    @Transactional
    public void add(ResidentVO residentVO){
        Resident resident = new Resident();
        BeanUtils.copyProperties(residentVO,resident);
        //校验居民表里是否存在身份证一样 小区code一样的

        //获取当前登录人员的信息
        UserInfo userInfo = getCurrentUserInfo();
        UserAddress userAddress = userInfo.getCurrentUserAddresses();
        String addressCode = "";
        if (userAddress!=null){
            addressCode = userAddress.getAddressCode();
        }
        ResidentVO param = new ResidentVO();
        param.setCommunityCode(addressCode);
        param.setCardId(residentVO.getCardId());
        int result = residentMapper.checkResidentInfo(param);
        if (result>0){
            throw new BusinessException("不能添加重复数据");
        }


        SimpleDateFormat sdf = new SimpleDateFormat(Const.DATE_FORMATE);
        String time = sdf.format(new Date());
        Date date = DateUtil.parse(time);
        resident.setCreateTime(date);
        resident.setStatus(Const.RESIDENT_STATUS_UNAUTHORIZE);


        resident.setCommunityCode(addressCode);

        residentMapper.add(resident);
        //得到插入居民表中的id
        Long residentId = resident.getId();

        //插入居民手机表
//        ResidentMobile residentMobile =new ResidentMobile();
//        residentMobile.setPhone(Long.parseLong(residentVO.getMobile()));
//        residentMobile.setResidentId(residentId);
//        residentMobile.setStatus(Const.RESIDENT_STATUS_BINDED_NOT);
//        residentMobileMapper.add(residentMobile);

        List<ResidentRelAddress> residentRelAddresses = new ArrayList<>();

        List<String> codes = residentVO.getAddressCodes();

        for (int i = 0; i< codes.size();i++){
            ResidentRelAddress residentRelAddress = new ResidentRelAddress();
            residentRelAddress.setResidentId(residentId);
            residentRelAddress.setAddressCode(codes.get(i));
            residentRelAddresses.add(residentRelAddress);
        }
        //批量插入居民地址关系表
        residentRelAddressMapper.batchAdd(residentRelAddresses);

        if (residentVO.getResidentDoorCardList().size()>0){
            //插入IC卡表
            for (int i = 0;i<residentVO.getResidentDoorCardList().size();i++){
                ResidentDoorCard residentDoorCard = residentVO.getResidentDoorCardList().get(i);
                String cardNum = residentDoorCard.getCardNum();
                ResidentDoorCard temp = new ResidentDoorCard();
                temp.setCardNum(cardNum);
                if (residentDoorCardMapper.findByParam(temp).size()<=0 && residentDoorCardMapper.findByParam(temp)!=null){
                    residentDoorCard.setCreateTime(date);
                    residentDoorCard.setCreateUserId(userInfo.getId());
                    residentDoorCard.setCreateUserName(userInfo.getUserName());
                    residentDoorCard.setResidentId(residentId);
                    residentDoorCardMapper.add(residentDoorCard);
                }else {
                    throw new BusinessException("门卡重复");
                }

            }
        }
        //todo 根据居民的id 更新附件表dataid
        Attachment temp = new Attachment();
        temp.setFileId(residentVO.getFileId());
        List<Attachment> attachments = attachmentService.findAllByParams(temp);
        Attachment attachment = new Attachment();
        attachment=attachments.get(0);
        attachment.setDataId(residentId);
        attachmentService.updateAttachment(attachment);



    }

    /**
     * 更新Resident
     * @param residentVO
     */
    @Override
    @Transactional
    public void update(ResidentVO residentVO){
        Resident resident = new Resident();
        BeanUtils.copyProperties(residentVO,resident);
        //todo 添加校验新旧手机号是否存在
        Resident old = residentMapper.findById(residentVO.getId());
        if (!old.getMobile().equals(residentVO.getMobile())){
            ResidentVO oldvo = new ResidentVO();
            oldvo.setMobile(residentVO.getMobile());
            List<Resident> residents = residentMapper.findByConditionPage(oldvo);
            if (residents.size()>0){
                throw new BusinessException("手机号已存在");
            }
        }

//        ResidentMobile old = residentMobileMapper.findByResidentId(residentVO.getId());
        //如果手机号不等于旧的手机号 就更新手机号
//        boolean flag = oldresident.getMobile().equals(residentVO.getMobile());
//        if (!flag){
//            //更新居民手机表
//            ResidentMobile residentMobile =residentMobileMapper.findByResidentId(residentVO.getId());
//            int result = residentMobileMapper.checkPhone(residentVO.getMobile());
//            if (result>0){
//                throw new BusinessException("手机号已存在");
//            }
//            residentMobile.setPhone(Long.parseLong(residentVO.getMobile()));
//            residentMobile.setStatus(Const.RESIDENT_STATUS_BINDED_NOT);
//            residentMobileMapper.update(residentMobile);
//            //更新手机号后将居民表的状态 设置为未授权
//            resident.setStatus(Const.RESIDENT_STATUS_UNAUTHORIZE);
//        }


        if (residentVO.getResidentDoorCardList()!=null&&residentVO.getResidentDoorCardList().size()>0){
            //获取当前登录人员的信息
            UserInfo userInfo = getCurrentUserInfo();

            SimpleDateFormat sdf = new SimpleDateFormat(Const.DATE_FORMATE);
            String time = sdf.format(new Date());
            Date date = DateUtil.parse(time);
            //插入IC卡表
            for (int i = 0;i<residentVO.getResidentDoorCardList().size();i++){
                ResidentDoorCard residentDoorCard = residentVO.getResidentDoorCardList().get(i);
                String cardNum = residentDoorCard.getCardNum();
                ResidentDoorCard temp = new ResidentDoorCard();
                temp.setCardNum(cardNum);
                if (residentDoorCardMapper.findByParam(temp).size()<=0 && residentDoorCardMapper.findByParam(temp)!=null){
                    residentDoorCard.setCreateTime(date);
                    residentDoorCard.setCreateUserId(userInfo.getId());
                    residentDoorCard.setCreateUserName(userInfo.getUserName());
                    residentDoorCard.setResidentId(residentVO.getId());
                    residentDoorCardMapper.add(residentDoorCard);
                }else {
                    throw new BusinessException("门卡重复");
                }

            }
        }





        List<ResidentRelAddressActionVO> addressCodes = residentVO.getResidentRelAddressActionVOList();
        for (ResidentRelAddressActionVO residentRelAddressActionVO : addressCodes){
            if (residentRelAddressActionVO.getActionType().equals(Const.ACTION_TYPE_UPDATE)){
                ResidentRelAddress rra = new ResidentRelAddress();
                rra.setId(residentRelAddressActionVO.getRelId());
                rra.setAddressCode(residentRelAddressActionVO.getAddressCode());
                rra.setResidentId(residentVO.getId());
                residentRelAddressMapper.update(rra);
            }
            if (residentRelAddressActionVO.getActionType().equals(Const.ACTION_TYPE_DELETE)){
                residentRelAddressMapper.delete(residentRelAddressActionVO.getRelId());
            }
            if (residentRelAddressActionVO.getActionType().equals(Const.ACTION_TYPE_INSERT)){
                ResidentRelAddress rra = new ResidentRelAddress();
                rra.setAddressCode(residentRelAddressActionVO.getAddressCode());
                rra.setResidentId(residentVO.getId());
                residentRelAddressMapper.add(rra);
            }
        }

        //更新居民表
        residentMapper.update(resident);


        //todo 删除附件
        //先查出来这个人的所有图片
        Attachment temp = new Attachment();
        temp.setDataId(residentVO.getId());
        List<Attachment> attachments = attachmentService.findAllByParams(temp);
        //删除这个人的图片
        for (Attachment attachment : attachments){
            Long id = attachment.getAttachmentId();
            attachmentService.deleteAttachment(id);
        }

        //根据fileid查询附件表记录
        String fileId = residentVO.getFileId();
        Attachment obj = new Attachment();
        obj.setFileId(fileId);
        List<Attachment> objlist = attachmentService.findAllByParams(obj);
        //更新附件记录的dataid
        Attachment att = objlist.get(0);
        att.setDataId(residentVO.getId());
        attachmentService.updateAttachment(att);

    }
    /**
     * 删除Resident
     * @param id
     */
    @Override
    @Transactional
    public void delete(Long id){
        //todo 删除组
        GroupRel groupRel = new GroupRel();
        groupRel.setRelId(id);
        groupRel.setType(Const.GROUP_REL_TYPE_MAN);
        List<GroupRel> groupRelList = groupRelMapper.findByParam(groupRel);
        if (groupRelList.size()>0){
            List<Long> groupRelIds = new ArrayList<>();
            for (GroupRel gr : groupRelList){
                groupRelIds.add(gr.getId());
            }

            groupRelMapper.batchDelete(groupRelIds);
        }

        //删除门卡
        ResidentDoorCard temp = new ResidentDoorCard();
        temp.setResidentId(id);
        List<ResidentDoorCard> residentDoorCardList = residentDoorCardMapper.findByParam(temp);
        for (ResidentDoorCard residentDoorCard : residentDoorCardList) {
            residentDoorCardMapper.delete(residentDoorCard.getId());
        }
        //todo 删除地址
        ResidentRelAddressVO residentRelAddressVO = new ResidentRelAddressVO();
        residentRelAddressVO.setResidentId(id);
        List<ResidentRelAddress> residentRelAddressList = residentRelAddressMapper.findByConditionPage(residentRelAddressVO);
        List<Long> relIds= new ArrayList<>();
        for(ResidentRelAddress residentRelAddress : residentRelAddressList){
            relIds.add(residentRelAddress.getId());
        }
        residentRelAddressMapper.batchDelete(relIds);

        // 删除手机
//        ResidentMobile residentMobile = residentMobileMapper.findByResidentId(id);
//        residentMobileMapper.delete(residentMobile.getId());

        //删除附件表
        Attachment obj = new Attachment();
        obj.setDataId(id);
        List<Attachment> attachments = attachmentService.findAllByParams(obj);
        //删除这个人的图片
        for (Attachment attachment : attachments){
            Long attachmentId = attachment.getAttachmentId();
            attachmentService.deleteAttachment(attachmentId);
        }



        residentMapper.delete(id);
    }
    /**
     *
     * 功能描述:上传照片
     *
     * @param:
     * @return:
     * @author: chenduo
     * @date: 2018/11/27 13:39
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
     * 联合居民地址表 地址表 居民手机表 附件表
     *
     * @param
     * @return
     * @author chenduo
     */
    @Override
    public BaseVo queryResidentInfoByPage(ResidentVO residentVO) {
        PageHelper.startPage(residentVO.getPageNum(),residentVO.getPageSize());
        if (StringUtil.isNotEmpty(residentVO.getHjdz())){
            String str = residentVO.getHjdz();
            str=str+"%";
            residentVO.setHjdz(str);
        }
        UserInfo userInfo = getCurrentUserInfo();
        UserAddress userAddress = userInfo.getCurrentUserAddresses();
        if (userAddress!=null){
            String addressCode = userAddress.getAddressCode();
            if (StringUtil.isNotEmpty(addressCode)){
                String str = userInfo.getCurrentUserAddresses().getAddressCode();
                str=str+"%";
                residentVO.setCommunityCode(str);
            }
        }

        if(residentVO.getAddressName()!=null){
            residentVO.setAddressName("%"+residentVO.getAddressName()+"%");
        }


        List<ResidentMobileAddressAttachmentVO> list = residentMapper.queryResidentInfoByPage(residentVO);


        PageInfo<ResidentMobileAddressAttachmentVO> pageInfo = new PageInfo<ResidentMobileAddressAttachmentVO>(list);
        BaseVo baseVo = new BaseVo();
        baseVo.setData(pageInfo);
        return baseVo;
    }
    /**
     *
     * 功能描述:授权人脸白名单
     *
     * @param:
     * @return:
     * @author: chenduo
     * @date: 2018/11/28 9:42
     */
    @Override
    @Transactional
    public BaseVo authorize(ResidentVO residentVO) {



        List<String> groupCodes = residentVO.getGroupCode();
        List<GroupRel> groupRelList = new ArrayList<>();
        for (int i =0;i<groupCodes.size();i++){
            GroupRel groupRel = new GroupRel();
            String code = groupCodes.get(i);
            GroupVO groupvo = new GroupVO();
            groupvo.setGroupCode(code);
            List<Group> groupList = groupMapper.findAllByParam(groupvo);
            groupRel.setGroupId(groupList.get(0).getId());
            groupRel.setRelId(residentVO.getId());
            groupRel.setType(Const.GROUP_REL_TYPE_MAN);
            groupRelList.add(groupRel);
        }
        groupRelMapper.batchAdd(groupRelList);


        BaseVo baseVo = new BaseVo();
        return baseVo;
    }
    /**
     *
     * 功能描述:编辑的时候反显
     *
     * @param:
     * @return:
     * @author: chenduo
     * @date: 2018/11/29 9:04
     */
    @Override
    public BaseVo reflect(Long id) {
        ResidentDictVO residentDictVO = residentMapper.queryResidentInfoById(id);
        List<String> list = residentMapper.findAddressCodeById(id);


        List<ResidentRelAddress> residentRelAddressList = residentMapper.queryResidentAddressInfoById(list);
        List<Long> ids = new ArrayList<>();
        List<ResidentRelAddress> rels =new ArrayList<>();
        for (int i=0;i<residentRelAddressList.size();i++){
            ResidentRelAddress temp = new ResidentRelAddress();
            temp.setAddressCode(residentRelAddressList.get(i).getAddressCode());
            temp.setResidentId(id);
            rels.add(residentRelAddressMapper.findByRelAddress(temp));
        }
        for (ResidentRelAddress residentRelAddress : rels){
            ids.add(residentRelAddress.getId());
        }

        ResidentDoorCardVO residentDoorCardVO = new ResidentDoorCardVO();
        residentDoorCardVO.setResidentId(id);
        List<ResidentDoorCard> residentDoorCardList = residentDoorCardMapper.findByConditionPage(residentDoorCardVO);

        ResidentReflectVO residentReflectVO = new ResidentReflectVO();
        residentReflectVO.setResidentDictVO(residentDictVO);
        residentReflectVO.setResidentRelAddressList(residentRelAddressList);
        residentReflectVO.setResidentDoorCardList(residentDoorCardList);
        residentReflectVO.setRelIds(ids);


        BaseVo baseVo = new BaseVo();
        baseVo.setData(residentReflectVO);
        return baseVo;
    }

    @Override
    @Transactional
    public BaseVo batchImport() {
        return null;
    }

    @Override
    @Transactional
    public BaseVo batchAuthorize(ResidentBatchAuthorizeVO residentBatchAuthorizeVO) {
        List<GroupRel> groupRelList = new ArrayList<>();
        List<Long> ids = residentBatchAuthorizeVO.getIds();
        String groupCode = residentBatchAuthorizeVO.getGroupCode();

        for (int i = 0;i<ids.size();i++){

            GroupRel groupRel = new GroupRel();
            GroupVO groupVO = new GroupVO();
            groupVO.setGroupCode(groupCode);
            List<Group> groupList = groupMapper.findAllByParam(groupVO);
            groupRel.setGroupId(groupList.get(0).getId());

            groupRel.setRelId(ids.get(i));
            groupRel.setType(Const.GROUP_REL_TYPE_MAN);
            groupRelList.add(groupRel);

        }
        groupRelMapper.batchAdd(groupRelList);

        return new BaseVo();
    }

    @Override
    @Transactional
    public BaseVo revoke(ResidentVO residentVO) {
        BaseVo baseVo = new BaseVo();
        Long relId = residentVO.getId();


        List<String> groupCode = residentVO.getGroupCode();
        List<GroupRel> groupRelList = new ArrayList<>();
        for (int i =0;i<groupCode.size();i++){
            GroupRel groupRel = new GroupRel();
            groupRel.setRelId(relId);

            GroupVO groupvo = new GroupVO();
            groupvo.setGroupCode(groupCode.get(i));
            List<Group> groupList = groupMapper.findAllByParam(groupvo);
            groupRel.setGroupId(groupList.get(0).getId());

            groupRel.setType(Const.GROUP_REL_TYPE_MAN);
            groupRelList.add(groupRelMapper.findByParam(groupRel).get(0));
        }

        List<Long> list = new ArrayList<>();
        for (GroupRel groupRel : groupRelList){
            list.add(groupRel.getId());
        }
        groupRelMapper.batchDelete(list);
        return baseVo;
    }

    @Override
    @Transactional
    public BaseVo batchRevoke(ResidentBatchAuthorizeVO residentBatchAuthorizeVO) {
        BaseVo baseVo = new BaseVo();
        List<GroupRel> groupRelList = new ArrayList<>();
        List<Long> ids = residentBatchAuthorizeVO.getIds();
        String groupCode = residentBatchAuthorizeVO.getGroupCode();

        GroupRel groupRel = new GroupRel();
        for (int i=0;i<ids.size();i++){
            groupRel.setRelId(ids.get(i));
            groupRel.setType(Const.GROUP_REL_TYPE_MAN);
            GroupVO groupVO = new GroupVO();
            groupVO.setGroupCode(groupCode);
            List<Group> groupList = groupMapper.findAllByParam(groupVO);
            groupRel.setGroupId(groupList.get(0).getId());
            groupRelList.add(groupRel);
        }

        groupRelMapper.delByParam(groupRelList);




        return baseVo;
    }
    /**
     *
     * 功能描述: 授权反显
     *
     * @param:
     * @return:
     * @author: chenduo
     * @date: 2018/12/14 14:14
     */
    @Override
    public BaseVo authorizationReflect(Long id) {
        GroupRel temp = new GroupRel();
        temp.setRelId(id);
        temp.setType(Const.GROUP_REL_TYPE_MAN);
        List<GroupRel> groupRelList = groupRelMapper.findByParam(temp);
        BaseVo baseVo = new BaseVo();
        baseVo.setData(groupRelList);
        return baseVo;
    }

    private  boolean equalList(List list1, List list2) {
        if (list1.size() != list2.size()){
            return false;
        }

        if (list2.containsAll(list1)){
            return true;
        }

        return false;
    }


}
