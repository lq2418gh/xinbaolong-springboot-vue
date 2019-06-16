package com.bit.sc.module.car.controller;

import com.bit.base.vo.BaseVo;
import com.bit.sc.common.ModuleFileType;
import com.bit.sc.module.attachment.pojo.Attachment;
import com.bit.sc.module.attachment.service.AttachmentService;
import com.bit.sc.module.car.pojo.CarInfo;
import com.bit.sc.module.car.service.CarInfoService;
import com.bit.sc.module.car.vo.CarInfoReflectVO;
import com.bit.sc.module.car.vo.CarInfoVO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

/**
 * CarInfo相关请求
 * @author zhangjie
 * @date 2018-11-21
 */
@RestController
@RequestMapping(value = "/carInfo")
public class CarInfoController {
    private static final Logger Logger = LoggerFactory.getLogger(CarInfoController.class);

    @Autowired
    private CarInfoService carInfoService;
    @Autowired
    private AttachmentService attachmentService;

    /**
     * 车辆信息分页查询列表
     * @param carInfoVO
     * @return
     */
    @PostMapping("/listPage")
    public BaseVo findCarInfoResidentListPage(@RequestBody CarInfoVO carInfoVO){
       return carInfoService.findCarInfoResidentListPage(carInfoVO);

    }

    /**
     * 通过主键查询单个CarInfo
     * @param id
     * @return baseVo
     */
    @GetMapping("/query/{id}")
    public BaseVo query(@PathVariable(value = "id") Long id){
        BaseVo baseVo = new BaseVo();
        CarInfoReflectVO carInfoReflectVO = carInfoService.findById(id);
        baseVo.setData(carInfoReflectVO);
        return baseVo;
    }

    /**
     * 添加CarInfo
     * @param carInfo
     * @return
     */
    @PostMapping("/add")
    public BaseVo add(@RequestBody CarInfo carInfo){
        BaseVo baseVo = new BaseVo();
        carInfoService.add(carInfo);
        return baseVo;
    }

    /**
     * 修改CarInfo
     * @param carInfo
     */
    @PostMapping("/modify")
    public BaseVo modify(@RequestBody CarInfo carInfo){
        BaseVo baseVo = new BaseVo();
        carInfoService.update(carInfo);
        return baseVo;
    }

    /**
     * 上传图片
     */
    @PostMapping("/uploadFiles")
    public BaseVo uploadFiles(@RequestBody MultipartFile file){
        BaseVo baseVo = new BaseVo();
        Attachment attachment = new Attachment();
        attachment.setBusinessId(ModuleFileType.CAR_IMAGE);
        Long attachmentId = attachmentService.addAttachment(attachment,file);
        baseVo.setData(attachmentService.findByAttachmentId(attachmentId));
        return baseVo;
    }
}
