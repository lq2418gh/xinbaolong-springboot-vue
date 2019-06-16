package com.bit.sc.module.car.dao;

import com.bit.sc.module.car.pojo.CarWihteList;
import com.bit.sc.module.car.vo.CarWihteListVO;
import com.bit.sc.module.equipment.pojo.Equipment;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * CarWihteList管理的Dao
 * @author zhangjie
 * @date 2018-11-22
 */
public interface CarWihteListMapper {

    /**
     * 根据条件查询CarWihteList
     * @param carWihteListVO
     * @return
     */
    public List<CarWihteListVO> findByConditionPage(CarWihteListVO carWihteListVO);

    /**
     * 更新CarWihteList
     * @param carWihteList
     */
    public void update(CarWihteList carWihteList);

    /**
     * 批量更新CarWihteList
     * @param carWihteList
     */
    public void batchUpdate(CarWihteList carWihteList);

    /**
     * 根据addressCode查询设备
     */
    public Equipment findEquipmentByAddressCode(@Param("addressCode") String addressCode);

    /**
     * 授权
     */
    public void authorize(CarWihteList carWihteList);

    /**
     * 批量授权
     */
    public void batchAuthorize(@Param("carWihteLists") List<CarWihteList> list);

    /**
     * 取消授权
     */
    void cancelAuthorize(Long carId,List<String> addressCodes);

    /**
     * 根据车辆id和设备id查询车辆白名单
     */
    CarWihteList queryCarWhiteList(@Param("carId") Long carId, @Param("equipmentId") Long equipmentId);

    /**
     * 根据carId删除记录
     */
    public void deleteByCarId(@Param("carIds") String carIds);

    /**
     * 根据carId批量删除记录
     */
    public void batchDeleteByCarId(@Param("carIds") List<Long> carIds);

    /**
     * 返显授权
     */
    public List<String> findAddressIdByCarId(@Param("carId") Long carId);

}
