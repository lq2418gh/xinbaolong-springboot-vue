package com.bit.sc.module.sys.controller;

import java.util.List;

import javax.validation.Valid;

import com.bit.common.ResultCode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.bit.sc.module.sys.pojo.Apply;
import com.bit.sc.module.sys.vo.ApplyVO;
import com.bit.sc.module.sys.service.ApplyService;
import com.bit.base.vo.BaseVo;

/**
 * Apply的相关请求
 *
 * @author generator
 */
@RestController
@RequestMapping(value = "/apply")
public class ApplyController {
    private static final Logger logger = LoggerFactory.getLogger(ApplyController.class);
    @Autowired
    private ApplyService applyService;


    /**
     * 分页查询Apply列表
     */
    @PostMapping("/listPage")
    public BaseVo listPage(@RequestBody ApplyVO applyVO) {
        //分页对象，前台传递的包含查询的参数
        return applyService.findByConditionPage(applyVO);
    }

    /**
     * 根据参数查询
     */
    @PostMapping("/findByParm")
    public BaseVo<List<Apply>> findByParm(@RequestBody ApplyVO applyVO) {
        //分页对象，前台传递的包含查询的参数
        BaseVo<List<Apply>> rs = new BaseVo();
        try {
            rs.setData(applyService.findByParm(applyVO));
        } catch (Exception e) {
            rs.setCode(ResultCode.WRONG.getCode());
            rs.setMsg(e.getMessage());
        }
        return rs;
    }

    /**
     * 查询所有
     *
     * @return
     */
    @GetMapping("/listAll")
    public BaseVo<List<Apply>> listAll() {
        BaseVo<List<Apply>> rs = new BaseVo();
        try {
            rs.setData(applyService.findAll(null));
        } catch (Exception e) {
            rs.setCode(ResultCode.WRONG.getCode());
            rs.setMsg(e.getMessage());
        }
        return rs;
    }

    /**
     * 根据主键ID查询Apply
     *
     * @param id
     * @return
     */
    @GetMapping("/query/{id}")
    public BaseVo query(@PathVariable(value = "id") Long id) {

        Apply apply = applyService.findById(id);
        BaseVo baseVo = new BaseVo();
        baseVo.setData(apply);
        return baseVo;
    }

    /**
     * 新增Apply
     *
     * @param apply Apply实体
     * @return
     */
   /* @PostMapping("/add")
    public BaseVo add(@Valid @RequestBody ApplyVO apply) {
        applyService.add(apply);
		BaseVo baseVo = new BaseVo();
        return baseVo;
    }*/

    /**
     * 修改Apply
     *
     * @param apply Apply实体
     * @return
     */
    @PostMapping("/modify")
    public BaseVo modify(@Valid @RequestBody Apply apply) {
        applyService.update(apply);
        BaseVo baseVo = new BaseVo();
        return baseVo;
    }

    /**
     * 根据主键ID删除Apply
     *
     * @param id
     * @return
     */
    @GetMapping("/delete/{id}")
    public BaseVo delete(@PathVariable(value = "id") Long id) {
        applyService.delete(id);
        BaseVo baseVo = new BaseVo();
        return baseVo;
    }

    /**
     * 根据ApplyID集合批量删除Apply
     *
     * @param ids ApplyID集合
     * @return BaseVo
     */
    @PostMapping("/delBatchByIds")
    public BaseVo delBatchByIds(@RequestBody List<Long> ids) {
        applyService.batchDelete(ids);
        BaseVo baseVo = new BaseVo();
        return baseVo;
    }

    @PostMapping("/getNoneResident")
    public BaseVo getNoneResident(){
        BaseVo baseVo = new BaseVo();
        List<Apply> applyList = applyService.getNoneResident();
        baseVo.setData(applyList);
        return baseVo;
    }


}
