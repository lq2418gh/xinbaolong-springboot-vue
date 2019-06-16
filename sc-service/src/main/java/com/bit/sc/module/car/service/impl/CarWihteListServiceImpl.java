package com.bit.sc.module.car.service.impl;

import com.bit.base.bean.UserAddress;
import com.bit.base.exception.BusinessException;
import com.bit.base.service.BaseService;
import com.bit.base.vo.BaseVo;
import com.bit.sc.common.Const;
import com.bit.sc.module.car.dao.CarWihteListMapper;
import com.bit.sc.module.car.pojo.CarWihteList;
import com.bit.sc.module.car.service.CarWihteListService;
import  com.bit.sc.module.car.vo.CarWihteListVO;
import com.bit.sc.module.equipment.pojo.Equipment;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

/**
 * CarWihteList的Service实现类
 * @author zhangjie
 * @date 2018-11-22
 */
@Service("carWihteListService")
public class CarWihteListServiceImpl extends BaseService implements CarWihteListService {

    public static final Logger logger = LoggerFactory.getLogger(CarWihteListServiceImpl.class);

    @Autowired
    private CarWihteListMapper carWihteListMapper;

    /**
     * 根据条件查询CarWihteList
     * @param carWihteListVO
     * @return
     */
    @Override
    public BaseVo findByConditionPage(CarWihteListVO carWihteListVO) {
        BaseVo baseVo = new BaseVo();
        PageHelper.startPage(carWihteListVO.getPageNum(),carWihteListVO.getPageSize());
        //判断当前登陆的用户是否绑定地址
        List<UserAddress> ud = getCurrentUserInfo().getUserAddresses();
        if (ud.size()>0){
            //绑定地址则按地址code过滤
            UserAddress userAddress = getCurrentUserInfo().getCurrentUserAddresses();
            CarWihteListVO vo = new CarWihteListVO();
            vo.setCommunityCode(userAddress.getAddressCode());
            vo.setSynchroStatus(carWihteListVO.getSynchroStatus());
            vo.setAddressCode(carWihteListVO.getAddressCode());
            vo.setRealName(carWihteListVO.getRealName());
            vo.setCarNumber(carWihteListVO.getCarNumber());
            List<CarWihteListVO> list = carWihteListMapper.findByConditionPage(vo);
            PageInfo<CarWihteListVO> pageInfo = new PageInfo<>(list);
            baseVo.setData(pageInfo);
            return baseVo;
        }else {
            List<CarWihteListVO> list = carWihteListMapper.findByConditionPage(carWihteListVO);
            PageInfo<CarWihteListVO> pageInfo = new PageInfo<>(list);
            baseVo.setData(pageInfo);
            return baseVo;
        }
    }

    /**
     * 更新CarWihteList(同步状态：0待同步 1已同步 2停用)
     * @param carWihteList
     */
    @Override
    @Transactional
    public void update(CarWihteList carWihteList) {

        carWihteList.setSynchroTime(new Date());
        carWihteListMapper.update(carWihteList);
    }

    /**
     * 授权
     */
    @Override
    @Transactional
    public void authorize(CarWihteListVO carWihteListVO) {
       //解析地址和设备关系
        String addressCode = carWihteListVO.getAddressCode();
        Equipment equipment = carWihteListMapper.findEquipmentByAddressCode(addressCode);
        if (Const.EQUIPMENT_TYPE==equipment.getEquipmentType()){
            //设备类型为车闸
            Long carId = carWihteListVO.getCarId();
            Long equipmentId = equipment.getId();
            CarWihteList cw = carWihteListMapper.queryCarWhiteList(carId,equipmentId);
            //判断车牌白名单是否已存在
            if (cw!=null){
                //白名单已存在，则判断状态
                if (cw.getSynchroStatus().equals(Const.CARWHITELIST_SYNCHRO_STATUS_STOP)){
                    //停用状态，则更新状态为正常
                    cw.setSynchroStatus(Const.CARWHITELIST_SYNCHRO_STATUS_SUCCESS);
                    carWihteListMapper.update(cw);
                }
            }else {
                //白名单不存在，则授权
                CarWihteList c = new CarWihteList();
                c.setEquipmentId(equipmentId);
                c.setCarId(carId);
                c.setSynchroStatus(Const.CARWHITELIST_SYNCHRO_STATUS_WAIT);
                carWihteListMapper.authorize(c);
            }
        }else {
            throw new BusinessException("非车辆道闸不能授权!");
        }
    }

    /**
     * 根据addressCode查询设备
     */
    @Override
    public Equipment batchQuery(String addressCode){
        Equipment ids = carWihteListMapper.findEquipmentByAddressCode(addressCode);
        return ids;
    }

    /**
     * 批量授权
     */
    @Override
    @Transactional
    public void batchAuthorize(CarWihteListVO carWihteListVO){

        Equipment equipment = carWihteListMapper.findEquipmentByAddressCode(carWihteListVO.getAddressCode());
        List<Long> carIds = carWihteListVO.getCarIds();
        Long equipmentId = equipment.getId();
        List<CarWihteList> list = new ArrayList<>();
        for (Long carId : carIds){
            CarWihteList c = new CarWihteList();
            c.setSynchroStatus(Const.CARWHITELIST_SYNCHRO_STATUS_WAIT);
            c.setCarId(carId);
            c.setEquipmentId(equipmentId);
            list.add(c);
        }
        carWihteListMapper.batchDeleteByCarId(carIds);
        carWihteListMapper.batchAuthorize(list);
    }

    /**
     * 取消授权
     */
    @Override
    @Transactional
    public void cancelAuthorize(CarWihteListVO carWihteListVO) {

        Equipment equipment = carWihteListMapper.findEquipmentByAddressCode(carWihteListVO.getAddressCode());
        Long carId = carWihteListVO.getCarId();
        Long equipmentId = equipment.getId();
        CarWihteList cw = carWihteListMapper.queryCarWhiteList(carId,equipmentId);
        //判断白名单是否存在
        if (cw !=null){
            //存在，白名单状态更新为停用
            CarWihteList c = new CarWihteList();
            c.setId(cw.getId());
            c.setSynchroStatus(Const.CARWHITELIST_SYNCHRO_STATUS_STOP);
            carWihteListMapper.update(c);
        }
    }

    /**
     * 批量取消授权
     * @param carWihteListVO
     */
    @Override
    @Transactional
    public void batchCancelAuthorize(CarWihteListVO carWihteListVO) {
        carWihteListMapper.batchDeleteByCarId(carWihteListVO.getCarIds());
    }

    /**
     * 返显授权
     */
    @Override
    public List<String> findAddressIdByCarId(Long carId){
        List<String> addressCode = carWihteListMapper.findAddressIdByCarId(carId);
        return addressCode;
    }

}
