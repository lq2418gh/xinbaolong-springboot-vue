package com.bit.sc.module.group.controller;

import javax.validation.Valid;

import com.bit.sc.module.group.service.PlatformService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.bit.sc.module.group.pojo.Group;
import com.bit.sc.module.group.vo.GroupVO;
import com.bit.sc.module.group.service.GroupService;
import com.bit.base.vo.BaseVo;

/**
 * Group的相关请求
 * @author generator
 */
@RestController
@RequestMapping(value = "/group")
public class GroupController {
	private static final Logger logger = LoggerFactory.getLogger(GroupController.class);
	@Autowired
	private GroupService groupService;
	@Autowired
    private PlatformService platformService;
	

    /**
     * 分页查询Group列表
     *
     */
    @PostMapping("/listPage")
    public BaseVo listPage(@RequestBody GroupVO groupVO) {
    	//分页对象，前台传递的包含查询的参数

        return groupService.findAllPage(groupVO);
    }

    /**
     * 根据主键ID查询Group
     *
     * @param id
     * @return
     */
    @GetMapping("/query/{id}")
    public BaseVo query(@PathVariable(value = "id") Long id) {

        Group group = groupService.findById(id);
		BaseVo baseVo = new BaseVo();
		baseVo.setData(group);
		return baseVo;
    }
    
    /**
     * 新增Group
     *
     * @param group Group实体
     * @return
     */
    @PostMapping("/add")
    public BaseVo add(@Valid @RequestBody Group group) {
        platformService.addGroup(group);
		BaseVo baseVo = new BaseVo();
        return baseVo;
    }
    /**
     * 修改Group
     *
     * @param group GroupVO实体
     * @return
     */
    @PostMapping("/modify")
    public BaseVo modify(@Valid @RequestBody Group group) {
        platformService.updateGroup(group);
		BaseVo baseVo = new BaseVo();
        return baseVo;
    }
    
    /**
     * 停用组
     *
     * @param group
     * @return
     */
    @PostMapping("/stop")
    public BaseVo stop(@RequestBody Group group) {
        platformService.deleteGroup(group);
		BaseVo baseVo = new BaseVo();
        return baseVo;
    }

    /**
     * 启用组
     *
     * @param group
     * @return
     */
    @PostMapping("/start")
    public BaseVo start(@RequestBody Group group) {
        platformService.startGroup(group);
        BaseVo baseVo = new BaseVo();
        return baseVo;
    }

}
