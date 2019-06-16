package com.bit.sc.module.sys.controller;

import com.bit.base.vo.BaseVo;
import com.bit.sc.module.sys.pojo.Dict;
import com.bit.sc.module.sys.service.DictService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Dict的相关请求
 * @author chenduo
 */
@RestController
@RequestMapping(value = "/dict")
public class DictController {
	private static final Logger logger = LoggerFactory.getLogger(DictController.class);
	@Autowired
	private DictService dictService;



    /**
     *
     * 功能描述: 根据传过来的module值 查询这一类的数据 不分页
     *
     * @param:
     * @return:
     * @author: chenduo
     * @date: 2018/11/14 11:37
     */

    @GetMapping("/getModule/{module}")
    public BaseVo getModule(@PathVariable(value = "module") String module){
        BaseVo baseVo = new BaseVo();
        List<Dict> dictList = dictService.getModule(module);
        baseVo.setData(dictList);
        return baseVo;
    }








}
