package com.bit.sc.module.car.service.impl;

import com.bit.base.bean.UserAddress;
import com.bit.base.exception.BusinessException;
import com.bit.base.service.BaseService;
import com.bit.base.vo.BaseVo;
import com.bit.sc.common.Const;
import com.bit.sc.module.attachment.dao.AttachmentMapper;
import com.bit.sc.module.attachment.pojo.Attachment;
import com.bit.sc.module.car.dao.CarInfoMapper;
import com.bit.sc.module.car.pojo.CarInfo;
import com.bit.sc.module.car.pojo.CarInfoResident;
import com.bit.sc.module.car.service.CarInfoService;
import com.bit.sc.module.car.vo.CarInfoReflectVO;
import com.bit.sc.module.car.vo.CarInfoVO;
import com.bit.sc.utils.CheckUtil;
import com.bit.utils.UUIDUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

/**
 * CarInfo的Service
 * @author zhangjie
 * @date 2018-11-22
 */
@Service("carInfoService")
public class CarInfoServiceImpl extends BaseService implements CarInfoService {

    public static final Logger logger = LoggerFactory.getLogger(CarInfoServiceImpl.class);

    @Autowired
    private CarInfoMapper carInfoMapper;
    @Autowired
    private AttachmentMapper attachmentMapper;

    /**
     * 车辆信息分页查询列表
     * @param carInfoVO
     * @return
     */
    @Override
    public BaseVo findCarInfoResidentListPage(CarInfoVO carInfoVO) {
        PageHelper.startPage(carInfoVO.getPageNum(),carInfoVO.getPageSize());
        BaseVo baseVo = new BaseVo();
        //判断当前登陆的用户是否绑定地址
        List<UserAddress> ud = getCurrentUserInfo().getUserAddresses();
        if (ud.size()>0){
            //绑定地址则按地址code过滤
            UserAddress userAddress = getCurrentUserInfo().getCurrentUserAddresses();
            CarInfoVO vo = new CarInfoVO();
            vo.setCommunityCode(userAddress.getAddressCode());
            vo.setCarNumber(carInfoVO.getCarNumber());
            vo.setHjdz(carInfoVO.getHjdz());
            vo.setRealName(carInfoVO.getRealName());
            vo.setCardId(carInfoVO.getCardId());
            List<CarInfoResident> list = carInfoMapper.findCarInfoResidentListPage(vo);
            PageInfo<CarInfoResident> pageInfo = new PageInfo<CarInfoResident>(list);
            baseVo.setData(pageInfo);
            return baseVo;
        }else {
            //根据创建时间排序
            carInfoVO.setOrderBy("create_time desc");
            List<CarInfoResident> list = carInfoMapper.findCarInfoResidentListPage(carInfoVO);
            PageInfo<CarInfoResident> pageInfo = new PageInfo<CarInfoResident>(list);
            baseVo.setData(pageInfo);
            return baseVo;
        }
    }

    /**
     * 根据条件查询CarInfo
     * @param carInfo
     * @return
     */
    @Override
    public List<CarInfo> findByConditionPage(CarInfo carInfo) {
        return carInfoMapper.findByConditionPage(carInfo);
    }

    /**
     * 通过主键查询单个CarInfo
     * @param id
     * @return
     */
    @Override
    public CarInfoReflectVO findById(Long id) {
        CarInfo carInfo = carInfoMapper.findById(id);
        Attachment attachment = attachmentMapper.findByAttachmentId(carInfo.getImageId());
        CarInfoReflectVO carInfoReflectVO = new CarInfoReflectVO();
        carInfoReflectVO.setCarInfo(carInfo);
        carInfoReflectVO.setAttachmentPath(attachment.getAttachmentPath());
        return carInfoReflectVO;
    }

    /**
     * 保存CarInfo
     * @param carInfo
     */
    @Override
    @Transactional
    public void add(CarInfo carInfo) {

        CheckUtil.notEmpty(carInfo.getCarBrand().trim(),"品牌不能为空");
        CheckUtil.notNull(carInfo.getCarType(),"车辆类型不能为空");
        CheckUtil.notEmpty(carInfo.getCarColour().trim(),"颜色不能为空");
        CheckUtil.notNull(carInfo.getCarPassengers(),"载客数不能为空");
        CheckUtil.notNull(carInfo.getCarFlapper(),"号牌类型不能为空");
        CheckUtil.notNull(carInfo.getCarPower(),"动力类型不能为空");
        CheckUtil.notEmpty(carInfo.getCarNumber().trim(),"车牌号不能为空");
        CheckUtil.notEmpty(carInfo.getCarEngine().trim(),"发动机型号不能为空");
        CheckUtil.notEmpty(carInfo.getCarBigframe().trim(),"大架号不能为空");

        //车牌号唯一校验
        CarInfo car = new CarInfo();
        car.setCarNumber(carInfo.getCarNumber());
//        car.setCarEngine(carInfo.getCarEngine());
//        car.setCarBigframe(carInfo.getCarBigframe());
        List<CarInfo> list = carInfoMapper.findByConditionPage(car);
        if(Const.ONLY_ONE == list.size()){
            throw new BusinessException("车牌号已存在！");
        }else {
            carInfo.setCreateTime(new Date());
            String carId = UUIDUtil.getUUID();
            carInfo.setCarId(carId);
            carInfo.setCreateUserId(getCurrentUserInfo().getId());
            carInfoMapper.add(carInfo);
        }
    }

    /**
     * 更新CarInfo
     * @param carInfo
     */
    @Override
    @Transactional
    public void update(CarInfo carInfo) {

        CheckUtil.notEmpty(carInfo.getCarNumber().trim(),"车牌号不能为空");
        CheckUtil.notNull(carInfo.getCarBrand(),"品牌不能为空");
        CheckUtil.notNull(carInfo.getCarType(),"车辆类型不能为空");
        CheckUtil.notNull(carInfo.getCarColour(),"颜色不能为空");
        CheckUtil.notNull(carInfo.getCarPassengers(),"载客数不能为空");
        CheckUtil.notNull(carInfo.getCarFlapper(),"号牌类型不能为空");
        CheckUtil.notNull(carInfo.getCarPower(),"动力类型不能为空");
        CheckUtil.notEmpty(carInfo.getCarNumber().trim(),"车牌号不能为空");
        CheckUtil.notNull(carInfo.getCarType(),"车辆类型不能为空");

        carInfo.setUpdateTime(new Date());
        carInfo.setUpdateUserId(getCurrentUserInfo().getId());
        carInfoMapper.update(carInfo);
    }

}
