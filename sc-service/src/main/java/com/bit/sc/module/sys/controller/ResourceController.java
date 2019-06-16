package com.bit.sc.module.sys.controller;

import com.bit.base.vo.BaseVo;
import com.bit.sc.module.sys.pojo.Resource;
import com.bit.sc.module.sys.service.ResourceService;
import com.bit.sc.module.sys.vo.ResourceVO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * Resource的相关请求
 * @author liqi
 */
@RestController
@RequestMapping(value = "/resource")
public class ResourceController {
	private static final Logger logger = LoggerFactory.getLogger(ResourceController.class);
	@Autowired
	private ResourceService resourceService;
	

    /**
     * 分页查询Resource列表
     */
    @PostMapping("/listPage")
    public BaseVo listPage(@RequestBody ResourceVO resourceVO) {
        return resourceService.findByConditionPage(resourceVO);
    }

    /**
     * 根据主键ID查询Resource
     * @param id
     * @return
     */
    @GetMapping("/query/{id}")
    public BaseVo query(@PathVariable(value = "id") Long id) {
        Resource resource = resourceService.findById(id);
		BaseVo baseVo = new BaseVo();
		baseVo.setData(resource);
		return baseVo;
    }
    
    /**
     * 新增Resource
     * @param resource Resource实体
     * @return
     */
    @PostMapping("/add")
    public BaseVo add(@Valid @RequestBody Resource resource) {
        resourceService.add(resource);
		BaseVo baseVo = new BaseVo();
        return baseVo;
    }
    /**
     * 修改Resource
     *
     * @param resource Resource实体
     * @return
     */
    @PostMapping("/modify")
    public BaseVo modify(@Valid @RequestBody Resource resource) {
		resourceService.update(resource);
		BaseVo baseVo = new BaseVo();
        return baseVo;
    }
    /**
     * 根据主键ID删除Resource
     * @param id
     * @return
     */
    @GetMapping("/delete/{id}")
    public BaseVo delete(@PathVariable(value = "id") Long id) {
        resourceService.delete(id);
		BaseVo baseVo = new BaseVo();
        return baseVo;
    }
    /**
     * 查询资源树 0查询菜单  1查询按钮  2查询所有
     * @param applicationCode web  端和app
     * @param type
     * @return
     */
    @GetMapping("/resourceListByParam/{applicationCode}/{type}")
    public BaseVo resourceListByParam(@PathVariable(value = "applicationCode") String applicationCode,@PathVariable(value = "type")Integer type ) {
        List<Resource> rs = resourceService.resourceListByParam(applicationCode,type);
        BaseVo baseVo = new BaseVo();
        baseVo.setData(rs);
        return baseVo;
    }

    /**
     * 查询resource--web--所有--树
     * @param applicationCode
     * @return
     */
    @GetMapping("/findByApplocation/{applicationCode}")
    public BaseVo findByApplocation(@PathVariable(value = "applicationCode") String applicationCode){
        List<Resource> list=resourceService.findByApplication(applicationCode);
        BaseVo baseVo = new BaseVo();
        baseVo.setData(list);
        return baseVo;
    }
    /**
     * 统计  根据资源编码  不能重复
     * @param resourceCode
     * @return
     */
    @GetMapping("/findCountByResourceCode/{resourceCode}")
    public BaseVo findCountByResourceCode(@PathVariable(value = "resourceCode") String resourceCode) {
        int count = resourceService.findCountByResourceCode(resourceCode);
        BaseVo baseVo = new BaseVo();
        baseVo.setData(count);
        return baseVo;
    }


}
