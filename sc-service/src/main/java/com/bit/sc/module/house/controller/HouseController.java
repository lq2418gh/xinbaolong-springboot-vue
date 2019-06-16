package com.bit.sc.module.house.controller;

import com.bit.base.vo.BaseVo;
import com.bit.sc.module.house.service.HouseService;
import com.bit.sc.module.house.vo.HouseCompanyOwnerVO;
import com.bit.sc.module.house.vo.HouseVO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * 一标三实的相关请求
 * @author chenduo
 */
@RestController
@RequestMapping(value = "/house")
public class HouseController {
	private static final Logger logger = LoggerFactory.getLogger(HouseController.class);
	@Autowired
	private HouseService houseService;


	

    /**
     * 分页查询House列表
     * @author chenduo
     *
     */
    @PostMapping("/listAllByPage")
    public BaseVo listAllByPage(@RequestBody HouseVO houseVO) {
    	//分页对象，前台传递的包含查询的参数

        return houseService.findByConditionPage(houseVO);
    }

    /**
     * 根据主键ID查询House
     *
     * @param id
     * @return
     * @author chenduo
     */
    @GetMapping("/query/{id}")
    public BaseVo query(@PathVariable(value = "id") Long id) {

        HouseCompanyOwnerVO house = houseService.findById(id);

		BaseVo baseVo = new BaseVo();
		baseVo.setData(house);
		return baseVo;
    }



    


    @PostMapping("/add")
    public BaseVo add(@RequestBody HouseVO houseVO) {

        return houseService.add(houseVO);
    }






    /**
     * 修改House
     *
     * @param houseVO House实体
     * @return
     * @author chenduo
     */
    @PostMapping("/modify")
    public BaseVo modify(@RequestBody HouseVO houseVO) {

		houseService.update(houseVO);
		BaseVo baseVo = new BaseVo();
        return baseVo;
    }
    
    /**
     * 根据主键ID删除House
     *
     * @param id
     * @return
     * @author chenduo
     */
    @GetMapping("/delete/{id}")
    public BaseVo delete(@PathVariable(value = "id") Long id) {
        houseService.delete(id);
		BaseVo baseVo = new BaseVo();
        return baseVo;
    }




    /**
     *
     * 功能描述:一标三实页面初始化加载已填报的房屋地址
     *
     * @param:
     * @return:
     * @author: chenduo
     * @date: 2018/11/27 10:33
     */

    @PostMapping("/getAddress")
    public BaseVo getAddress(@RequestBody HouseVO houseVO){
        return houseService.getAddress(houseVO);
    }
    /**
     *
     * 功能描述:同步一标三实
     *
     * @param:
     * @return:
     * @author: chenduo
     * @date: 2018/11/27 13:13
     */

    @GetMapping("/synchronize/{id}")
    public BaseVo synchronize(@PathVariable(value = "id") Long id){
        BaseVo baseVo = houseService.synchronize(id);
        return baseVo;
    }


}
