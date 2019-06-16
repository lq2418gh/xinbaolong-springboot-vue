package com.bit.sc.module.car.service;

import com.bit.base.vo.BaseVo;
import com.bit.sc.module.car.pojo.CarWihteList;
import com.bit.sc.module.car.vo.CarWihteListVO;
import com.bit.sc.module.equipment.pojo.Equipment;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * CarWihteList的Service
 * @author zhangjie
 * @date 2018-11-22
 */
@Service
public interface CarWihteListService {

    /**
     * 根据条件查询CarWihteList
     * @param carWihteListVO
     * @return
     */
    public BaseVo findByConditionPage(CarWihteListVO carWihteListVO);

    /**
     * 更新CarWihteList(同步状态：0待同步 1已同步 2停用)
     * @param carWihteList
     */
    public void update(CarWihteList carWihteList);

    /**
     * 授权
     */
    public void authorize(CarWihteListVO carWihteListVO);
    /**
     * 批量授权
     */
    public void batchAuthorize(CarWihteListVO carWihteListVO);

    /**
     * 根据addressCode查询设备
     */
    public Equipment batchQuery(String addressCodes);

    /**
     * 取消授权
     */
    void cancelAuthorize(CarWihteListVO carWihteListVO);

    /**
     * 批量取消授权
     */
    void batchCancelAuthorize(CarWihteListVO carWihteListVO);

    /**
     * 返显授权
     */
    List<String> findAddressIdByCarId(Long carId);


}
