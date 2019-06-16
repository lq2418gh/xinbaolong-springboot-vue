package com.bit.sc.module.car.controller;

import com.bit.base.vo.BaseVo;
import com.bit.sc.module.car.pojo.CarWihteList;
import com.bit.sc.module.car.service.CarWihteListService;
import com.bit.sc.module.car.vo.CarWihteListVO;
import com.sun.xml.internal.rngom.parse.host.Base;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * CarWihteList相关请求
 * @author zhangjie
 * @date 2018-11-22
 */
@RestController
@RequestMapping(value = "/CarWihteList")
public class CarWihteListController {
    private static final Logger Logger = LoggerFactory.getLogger(CarWihteListController.class);
    
    @Autowired
    private CarWihteListService carWihteListService;

    /**
     * 分页查询车辆白名单CarWihteList列表
     * @param carWihteListVO
     * @return baseVo
     */
    @PostMapping("/carWihteListListPage")
    public BaseVo listPage(@RequestBody CarWihteListVO carWihteListVO){
        return carWihteListService.findByConditionPage(carWihteListVO);
    }

    /**
     * 修改CarWihteList(同步状态：0待同步 1已同步 2停用)
     * @param carWihteList
     */
    @PostMapping("/modify")
    public BaseVo modify(@RequestBody CarWihteList carWihteList){
        BaseVo baseVo = new BaseVo();
        carWihteListService.update(carWihteList);
        return baseVo;
    }

    @PostMapping("/query")
    public BaseVo batchQuery(@RequestBody String addressCode){
        BaseVo baseVo = new BaseVo();
        carWihteListService.batchQuery(addressCode);
        return baseVo;
    }

    /**
     * 授权
     * @param carWihteListVO
     */
    @PostMapping("/authorize")
    public BaseVo authorize(@RequestBody CarWihteListVO carWihteListVO){
        BaseVo baseVo = new BaseVo();
        carWihteListService.authorize(carWihteListVO);
        return baseVo;
    }

    /**
     * 批量授权
     * @param carWihteListVO
     */
    @PostMapping("/batchAuthorize")
    public BaseVo batchAuthorize(@RequestBody CarWihteListVO carWihteListVO){
        BaseVo baseVo = new BaseVo();
        carWihteListService.batchAuthorize(carWihteListVO);
        return baseVo;
    }

    /**
     * 取消授权
     */
    @PostMapping("/cancelAuthorize")
    public BaseVo cancelAuthorize(@RequestBody CarWihteListVO carWihteListVO){
        BaseVo baseVo = new BaseVo();
        carWihteListService.cancelAuthorize(carWihteListVO);
        return baseVo;
    }

    /**
     * 批量取消授权
     */
    @PostMapping("/batchCancelAuthorize")
    public BaseVo batchCancelAuthorize(@RequestBody CarWihteListVO carWihteListVO){
        BaseVo baseVo = new BaseVo();
        carWihteListService.batchCancelAuthorize(carWihteListVO);
        return baseVo;
    }

    /**
     * 返显授权
     */
    @GetMapping("/showAuthorize/{id}")
    public BaseVo showAuthorize(@PathVariable("id") Long id){
        BaseVo baseVo = new BaseVo();
        baseVo.setData(carWihteListService.findAddressIdByCarId(id));
        return baseVo;
    }

}
