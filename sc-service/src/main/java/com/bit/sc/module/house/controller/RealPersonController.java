package com.bit.sc.module.house.controller;

import com.bit.base.vo.BaseVo;
import com.bit.sc.module.house.pojo.RealPerson;
import com.bit.sc.module.house.service.RealPersonService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.util.List;

/**
 * RealPerson的相关请求
 * @author chenduo
 */
@RestController
@RequestMapping(value = "/realPerson")
public class RealPersonController {
	private static final Logger logger = LoggerFactory.getLogger(RealPersonController.class);
	@Autowired
	private RealPersonService realPersonService;
	


    
    /**
     * 新增RealPerson
     *
     * @param realPerson RealPerson实体
     * @return
     * @author chenduo
     */
    @PostMapping("/add")
    public BaseVo add(@Valid @RequestBody RealPerson realPerson) {


        return realPersonService.add(realPerson);
    }






    /**
     * 修改RealPerson
     *
     * @param realPerson RealPerson实体
     * @return
     * @author chenduo
     */
    @PostMapping("/modify")
    public BaseVo modify(@Valid @RequestBody RealPerson realPerson) {
		realPersonService.update(realPerson);
		BaseVo baseVo = new BaseVo();
        return baseVo;
    }
    
    /**
     * 根据主键ID删除RealPerson
     *
     * @param id
     * @return
     * @author chenduo
     */
    @GetMapping("/delete/{id}")
    public BaseVo delete(@PathVariable(value = "id") Long id) {
        realPersonService.delete(id);
		BaseVo baseVo = new BaseVo();
        return baseVo;
    }



    /**
     *
     * 功能描述:上传照片
     *
     * @param:
     * @return:
     * @author: chenduo
     * @date: 2018/11/27 13:39
     */

    @PostMapping("/uploadpicture")
    public BaseVo uploadPicture(@RequestParam("file") MultipartFile file){

       return realPersonService.uploadpicture(file);
    }




    /**
     *
     * 功能描述:根据id查询记录 用于反显
     *
     * @param:
     * @return:
     * @author: chenduo
     * @date: 2018/11/28 15:38
     */

    @GetMapping("/queryData/{id}")
    public BaseVo queryData(@PathVariable(value = "id") Long id){
        return realPersonService.queryData(id);
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

        List<RealPerson> registeredResidentList=realPersonService.findByHouseId(id);
        BaseVo baseVo=new BaseVo();
        baseVo.setData(registeredResidentList);
        return baseVo;
    }

}
