package com.bit.sc.module.group.controller;

import com.bit.base.vo.BaseVo;
import com.bit.sc.module.group.pojo.GroupRel;
import com.bit.sc.module.group.service.GroupRelService;
import com.bit.sc.module.group.vo.GroupRelVO;
import com.bit.sc.module.group.vo.GroupResEquVo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * GroupRel的相关请求
 * @author liqi
 */
@RestController
@RequestMapping(value = "/groupRel")
public class GroupRelController {
    private static final Logger logger = LoggerFactory.getLogger(GroupRelController.class);
    @Autowired
    private GroupRelService groupRelService;


    /**
     * 1分页查询GroupRel列表 sql  处理
     */
    @PostMapping("/listPage")
    public BaseVo listPage(@RequestBody GroupRelVO groupRelVO) {
        return groupRelService.findByConditionPage(groupRelVO);
    }
    /**
     * 2分页查询GroupRel列表  这里的是java处理type
     */
    @PostMapping("/findListPage")
    public BaseVo findListPage(@RequestBody GroupRelVO groupRelVO) {
        return groupRelService.findListPage(groupRelVO);
    }
    /**
     * 3分页查询GroupRel列表  这里的是java处理type
     */
    @PostMapping("/findPageByParam")
    public BaseVo findPageByParam(@RequestBody GroupResEquVo groupResEquVo) {
        return groupRelService.findPageByParam(groupResEquVo);
    }
    /**
     * 根据主键ID查询GroupRel
     * @param id
     */
    @GetMapping("/query/{id}")
    public BaseVo query(@PathVariable(value = "id") Long id) {
        GroupRel groupRel = groupRelService.findById(id);
        BaseVo baseVo = new BaseVo();
        baseVo.setData(groupRel);
        return baseVo;
    }
    /**
     * 新增GroupRel
     * @param groupRel GroupRel实体
     */
    @PostMapping("/add")
    public BaseVo add(@Valid @RequestBody GroupRel groupRel) {
        groupRelService.add(groupRel);
        BaseVo baseVo = new BaseVo();
        return baseVo;
    }
    /**
     * 批量新增GroupRel
     * @param groupRels GroupRel实体
     */
    @PostMapping("/batchAdd")
    public BaseVo batchAdd(@RequestBody List<GroupRel> groupRels) {
        groupRelService.batchAdd(groupRels);
        BaseVo baseVo = new BaseVo();
        return baseVo;
    }
    /**
     * 修改GroupRel
     * @param groupRel GroupRel实体
     */
    @PostMapping("/modify")
    public BaseVo modify(@Valid @RequestBody GroupRel groupRel) {
        groupRelService.update(groupRel);
        BaseVo baseVo = new BaseVo();
        return baseVo;
    }
    /**
     * 根据主键ID删除GroupRel
     * @param id
     */
    @GetMapping("/delete/{id}")
    public BaseVo delete(@PathVariable(value = "id") Long id) {
        groupRelService.delete(id);
        BaseVo baseVo = new BaseVo();
        return baseVo;
    }
    /**
     * 根据主键ID删除GroupRel
     * @param ids
     */
    @PostMapping("/batchDelete")
    public BaseVo batchDelete(@RequestBody List<Long> ids) {
        groupRelService.batchDelete(ids);
        BaseVo baseVo = new BaseVo();
        return baseVo;
    }
    /**
     * 根据groupID查询GroupRel
     * @param groupId
     */
    @GetMapping("/findGroupRelList/{groupId}")
    public BaseVo findGroupRelList(@PathVariable(value = "groupId") Long groupId) {
        List<GroupRel> list=groupRelService.findGroupRelList(groupId);
        BaseVo baseVo = new BaseVo();
        baseVo.setData(list);
        return baseVo;
    }
    /**
     * 根据 relid  type 查询记录  list
     */
    @PostMapping("findByParam")
    public BaseVo findByParam(@RequestBody GroupRel groupRel){
        List<GroupRel> list=groupRelService.findByParam(groupRel);
        BaseVo baseVo = new BaseVo();
        baseVo.setData(list);
        return baseVo;
    }
    /**
     * 批量删除  根据groupid 和relid  和type
     */
    @PostMapping("/delByParam")
    public BaseVo delByParam(@RequestBody List<GroupRel> groupRel){
        groupRelService.delByParam(groupRel);
        BaseVo baseVo = new BaseVo();
        return baseVo;
    }


}
