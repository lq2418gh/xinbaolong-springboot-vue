package com.bit.sc.module.mobile.controller;

import java.io.IOException;
import java.util.List;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import com.bit.sc.module.mobile.pojo.MobileVersion;
import com.bit.sc.module.mobile.service.MobileVersionService;
import com.bit.sc.module.mobile.vo.MobileVersionVO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import com.bit.base.exception.BusinessException;
import com.bit.base.vo.BaseVo;

/**
 * MobileVersion的相关请求
 * @author generator
 */
@RestController
@RequestMapping(value = "/mobile")
public class MobileVersionController {
	private static final Logger logger = LoggerFactory.getLogger(MobileVersionController.class);
	@Autowired
	private MobileVersionService mobileVersionService;
	

    /**
     * 分页查询MobileVersion列表
     *
     */
    @PostMapping("/listPage")
    public BaseVo listPage(@RequestBody MobileVersionVO mobileVersionVO) {
    	//分页对象，前台传递的包含查询的参数

        return mobileVersionService.findByConditionPage(mobileVersionVO);
    }

    /**
     * 根据主键ID查询MobileVersion
     *
     * @param id
     * @return
     */
    @GetMapping("/query/{id}")
    public BaseVo query(@PathVariable(value = "id") String id) {

        MobileVersion mobileVersion = mobileVersionService.findById(id);
		BaseVo baseVo = new BaseVo();
		baseVo.setData(mobileVersion);
		return baseVo;
    }
    
    /**
     * 新增MobileVersion
     *
     * @param mobileVersion MobileVersion实体
     * @return
     */
    @PostMapping("/add")
    public BaseVo add(@Valid @RequestBody MobileVersion mobileVersion) {
        mobileVersionService.add(mobileVersion);
		BaseVo baseVo = new BaseVo();
        return baseVo;
    }
    /**
     * 修改MobileVersion
     *
     * @param mobileVersion MobileVersion实体
     * @return
     */
    @PostMapping("/modify")
    public BaseVo modify(@Valid @RequestBody MobileVersion mobileVersion) {
		mobileVersionService.update(mobileVersion);
		BaseVo baseVo = new BaseVo();
        return baseVo;
    }
    
    /**
     * 根据主键ID删除MobileVersion
     *
     * @param id
     * @return
     */
    @GetMapping("/delete/{id}")
    public BaseVo delete(@PathVariable(value = "id") String id) {
        mobileVersionService.delete(id);
		BaseVo baseVo = new BaseVo();
        return baseVo;
    }

    /**
     * 根据MobileVersionID集合批量删除MobileVersion
     *
     * @param ids MobileVersionID集合
     * @return BaseVo
     *
     */
    @PostMapping("/delBatchByIds")
    public BaseVo delBatchByIds(@RequestBody List<Long> ids) {
        mobileVersionService.batchDelete(ids);
		BaseVo baseVo = new BaseVo();
        return baseVo;
   }
}
