package com.bit.sc.module.sys.controller;

import com.bit.base.vo.BaseVo;
import com.bit.sc.common.Const;
import com.bit.sc.module.sys.pojo.Address;
import com.bit.sc.module.sys.pojo.AreaCode;
import com.bit.sc.module.sys.service.AddressService;
import com.bit.sc.module.sys.service.AreaCodeService;
import com.bit.sc.module.sys.vo.AreaCodeVO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * AreaCode的相关请求
 * @author generator
 */
@RestController
@RequestMapping(value = "/areaCode")
public class AreaCodeController {
    private static final Logger logger = LoggerFactory.getLogger(AreaCodeController.class);
    @Autowired
    private AreaCodeService areaCodeService;

    @Autowired
    private AddressService addressService;
    /**
     * 分页查询AreaCode列表
     */
    @PostMapping("/listPage")
    public BaseVo listPage(@RequestBody AreaCodeVO areaCodeVO) {
        //分页对象，前台传递的包含查询的参数
        return areaCodeService.findByConditionPage(areaCodeVO);
    }

    /**
     * 根据主键ID查询AreaCode
     * @param arCode
     * @return
     */
    @GetMapping("/query/{arCode}")
    public BaseVo findByArCode(@PathVariable(value = "arCode") String arCode) {
        AreaCode areaCode = areaCodeService.findByArCode(arCode);
        BaseVo baseVo = new BaseVo();
        baseVo.setData(areaCode);
        return baseVo;
    }

    /**
     * 单节点查询区域
     * @param parentCode
     * @return
     */
    @GetMapping("/findByParentCode/{parentCode}")
    public BaseVo findByParentCode(@PathVariable(value = "parentCode") String parentCode) {
        List<AreaCode> areaCodeList=areaCodeService.findByParentCode(parentCode);
        BaseVo baseVo = new BaseVo();
        baseVo.setData(areaCodeList);
        return baseVo;
    }

    /**
     * 单节查询区域 和区域下的小区树结构
     * @param parentCode
     * @return
     */
    @GetMapping("/findTree/{parentCode}")
    public BaseVo findTree(@PathVariable(value = "parentCode") String parentCode) {
        BaseVo baseVo = new BaseVo();
        List<AreaCode> areaCodeList=areaCodeService.findByParentCode(parentCode);
        if (areaCodeList==null){
            List<Address> addressList=addressService.findByAreaCode(parentCode);
            baseVo.setData(addressList);
        }else{
            baseVo.setData(areaCodeList);
        }
        return baseVo;
    }

    /**
     * 查询 区域行政代码
     * @return
     */
    @PostMapping("/findAreaTree")
    public  BaseVo findAreaTree(){
        BaseVo baseVo = new BaseVo();
        AreaCode byCode = areaCodeService.findByCode(Const.AREA_CODE);
        baseVo.setData(byCode);
        return baseVo;
    }

    /**
     * 查询行政代码和小区的树 根据sign   ROOM ：到房门101   DOOR：到楼层/栋
      * @return
     */
    @PostMapping("/findAreaAndAddressTree/{sign}")
    public  BaseVo findAreaAndAddressTree(@PathVariable(value = "sign") String sign){
        BaseVo baseVo = new BaseVo();
        AreaCode byCode = areaCodeService.findAreaAndAddressTree(sign);
        baseVo.setData(byCode);
        return baseVo;
    }

}
