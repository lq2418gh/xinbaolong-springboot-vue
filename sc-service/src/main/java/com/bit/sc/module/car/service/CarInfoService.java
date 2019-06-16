package com.bit.sc.module.car.service;

import com.bit.base.vo.BaseVo;
import com.bit.sc.module.car.pojo.CarInfo;
import com.bit.sc.module.car.vo.CarInfoReflectVO;
import com.bit.sc.module.car.vo.CarInfoVO;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * CarInfo的Service
 * @author zhangjie
 * @date 2018-11-22
 */
@Service
public interface CarInfoService {

    /**
     * 车辆信息分页查询列表
     * @param carInfoVO
     * @return
     */
    BaseVo findCarInfoResidentListPage(CarInfoVO carInfoVO);

    /**
     * 根据条件查询CarInfo
     * @param carInfo
     * @return
     */
    List<CarInfo> findByConditionPage(CarInfo carInfo);

    /**
     * 通过主键查询单个CarInfo
     * @param id
     * @return
     */
    CarInfoReflectVO findById(Long id);
    /**
     * 保存CarInfo
     * @param carInfo
     */
    void add(CarInfo carInfo);
    /**
     * 更新CarInfo
     * @param carInfo
     */
    void update(CarInfo carInfo);

}
