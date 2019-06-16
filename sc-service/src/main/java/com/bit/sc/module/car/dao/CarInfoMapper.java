package com.bit.sc.module.car.dao;

import com.bit.sc.module.car.pojo.CarInfo;
import com.bit.sc.module.car.pojo.CarInfoResident;
import com.bit.sc.module.car.vo.CarInfoVO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * CarInfo管理的Dao
 * @author zhangjie
 * @date 2018-11-22
 */
public interface CarInfoMapper {

    /**
     * 车辆信息分页查询列表
     * @param carInfoVO
     * @return
     */
    public List<CarInfoResident> findCarInfoResidentListPage(CarInfoVO carInfoVO);

    /**
     * 根据条件查询CarInfo
     * @param carInfo
     * @return
     */
    public List<CarInfo> findByConditionPage(CarInfo carInfo);

    /**
     * 通过主键查询单个CarInfo
     * @param id
     * @return
     */
    public CarInfo findById(@Param(value = "id") Long id);
    /**
     * 保存CarInfo
     * @param carInfo
     */
    public void add(CarInfo carInfo);
    /**
     * 更新CarInfo
     * @param carInfo
     */
    public void update(CarInfo carInfo);

}
