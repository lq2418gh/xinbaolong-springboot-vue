package com.bit.sc.module.sys.controller;

import com.bit.base.vo.BaseVo;
import com.bit.sc.module.sys.pojo.Function;
import com.bit.sc.module.sys.service.FunctionService;
import com.bit.sc.module.sys.vo.FunctionVO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * function 功能管理的相关请求
 * @author liqi
 */
@RestController
@RequestMapping(value = "/function")
public class FunctionController {
    private static final Logger logger = LoggerFactory.getLogger(ApplyController.class);

    @Autowired
    private FunctionService functionService;

    /**
     * 分页查询Function列表
     *
     */
    @PostMapping("/listPage")
    public BaseVo listPage(@RequestBody FunctionVO functionVO) {
        //分页对象，前台传递的包含查询的参数
        return functionService.findByConditionPage(functionVO);
    }

    /**
     * 新增Function
     *
     * @param function Function实体
     * @return
     */
    @PostMapping("/add")
    public BaseVo add(@Valid @RequestBody Function function) {
        functionService.add(function);
        BaseVo baseVo = new BaseVo();
        return baseVo;
    }

    /**
     * 根据主键ID删除Function
     *
     * @param id
     * @return
     */
    @GetMapping("/delete/{id}")
    public BaseVo delete(@PathVariable(value = "id") Long id) {
        functionService.delete(id);
        BaseVo baseVo = new BaseVo();
        return baseVo;
    }

    /**
     * 修改Function
     *
     * @param function Function实体
     * @return
     */
    @PostMapping("/modify")
    public BaseVo modify(@Valid @RequestBody Function function) {
        functionService.update(function);
        BaseVo baseVo = new BaseVo();
        return baseVo;
    }


    /**
     * 根据主键ID查询function
     *
     * @param id
     * @return
     */
    @GetMapping("/query/{id}")
    public BaseVo query(@PathVariable(value = "id") Long id) {
        Function function = functionService.findById(id);
        BaseVo baseVo = new BaseVo();
        baseVo.setData(function);
        return baseVo;
    }


    /**
     * functionCode 查询统计
     * @param functionCode
     * @return
     */
    @GetMapping("/findCountByCode/{functionCode}")
    public BaseVo findCountByFunctionCode(@PathVariable(value = "functionCode") String functionCode) {
        int functionCodeFunctionCode = functionService.findCountByFunctionCode(functionCode);
        BaseVo baseVo = new BaseVo();
        baseVo.setData(functionCodeFunctionCode);
        return baseVo;
    }
}
