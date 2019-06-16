package com.bit.sc.module.house.controller;

import com.bit.base.vo.BaseVo;
import com.bit.sc.module.house.pojo.RegisteredResident;
import com.bit.sc.module.house.service.RegisteredResidentService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * RegisteredResident的相关请求
 * @author chenduo
 */
@RestController
@RequestMapping(value = "/registeredResident")
public class RegisteredResidentController {
	private static final Logger logger = LoggerFactory.getLogger(RegisteredResidentController.class);
	@Autowired
	private RegisteredResidentService registeredResidentService;
	


    /**
     * 根据主键ID查询RegisteredResident
     *
     * @param id
     * @return
     * @author chenduo
     */
    @GetMapping("/query/{id}")
    public BaseVo query(@PathVariable(value = "id") Long id) {

        RegisteredResident registeredResident = registeredResidentService.findById(id);
		BaseVo baseVo = new BaseVo();
		baseVo.setData(registeredResident);
		return baseVo;
    }



    
    /**
     * 新增RegisteredResident
     *
     * @param registeredResident RegisteredResident实体
     * @return
     * @author chenduo
     */
    @PostMapping("/add")
    public BaseVo add(@Valid @RequestBody RegisteredResident registeredResident) {

        return registeredResidentService.add(registeredResident);
    }





    /**
     * 修改RegisteredResident
     *
     * @param registeredResident RegisteredResident实体
     * @return
     * @author chenduo
     */
    @PostMapping("/modify")
    public BaseVo modify(@Valid @RequestBody RegisteredResident registeredResident) {
		registeredResidentService.update(registeredResident);
		BaseVo baseVo = new BaseVo();
        return baseVo;
    }
    
    /**
     * 根据主键ID删除RegisteredResident
     *
     * @param id
     * @return
     * @author chenduo
     */
    @GetMapping("/delete/{id}")
    public BaseVo delete(@PathVariable(value = "id") Long id) {
        registeredResidentService.delete(id);
		BaseVo baseVo = new BaseVo();
        return baseVo;
    }




    /**
     *
     * 功能描述:根据houseid查询结果
     *
     * @param:
     * @return:
     * @author: chenduo
     * @date: 2018/12/3 11:55
     */

    @GetMapping("/queryAllByHouseId/{id}")
    public BaseVo queryAllByHouseId(@PathVariable(value = "id") Long id){

        List<RegisteredResident> registeredResidentList=registeredResidentService.findByHouseId(id);
        BaseVo baseVo=new BaseVo();
        baseVo.setData(registeredResidentList);
        return baseVo;
    }
}
