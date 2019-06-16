package com.bit.sc.module.sys.controller;

import java.util.List;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.bit.sc.module.sys.pojo.Menu;
import com.bit.sc.module.sys.vo.MenuVO;
import com.bit.sc.module.sys.service.MenuService;
import com.bit.base.vo.BaseVo;

/**
 * Menu的相关请求
 * @author liqi
 */
@RestController
@RequestMapping(value = "/menu")
public class MenuController {
	private static final Logger logger = LoggerFactory.getLogger(MenuController.class);
	@Autowired
	private MenuService menuService;
	

    /**
     * 分页查询Menu列表
     */
    @PostMapping("/listPage")
    public BaseVo listPage(@RequestBody MenuVO menuVO) {
    	//分页对象，前台传递的包含查询的参数
        return menuService.findByConditionPage(menuVO);
    }

    /**
     * 根据主键ID查询Menu
     * @param id
     * @return
     */
    @GetMapping("/query/{id}")
    public BaseVo query(@PathVariable(value = "id") Long id) {
        Menu menu = menuService.findById(id);
		BaseVo baseVo = new BaseVo();
		baseVo.setData(menu);
		return baseVo;
    }
    
    /**
     * 新增Menu
     * @param menu Menu实体
     * @return
     */
    @PostMapping("/add")
    public BaseVo add(@Valid @RequestBody Menu menu) {
        menuService.add(menu);
        BaseVo baseVo = new BaseVo();
        return baseVo;
    }
    /**
     * 修改Menu
     * @param menu Menu实体
     * @return
     */
    @PostMapping("/modify")
    public BaseVo modify(@Valid @RequestBody Menu menu) {
		menuService.update(menu);
		BaseVo baseVo = new BaseVo();
        return baseVo;
    }
    
    /**
     * 根据主键ID删除Menu
     * @param id
     * @return
     */
    @GetMapping("/delete/{id}")
    public BaseVo delete(@PathVariable(value = "id") Long id) {
        menuService.delete(id);
		BaseVo baseVo = new BaseVo();
        return baseVo;
    }

    /**
     * 根据MenuID集合批量删除Menu
     * @param ids MenuID集合
     * @return BaseVo
     *
     */
    @PostMapping("/delBatchByIds")
    public BaseVo delBatchByIds(@RequestBody List<Long> ids) {
        menuService.batchDelete(ids);
		BaseVo baseVo = new BaseVo();
        return baseVo;
   }

    /**
     * @param applicationCode web  端和app
     * @return
     */
    @GetMapping("/menu/{applicationCode}/{type}")
    public BaseVo menuList(@PathVariable(value = "applicationCode") String applicationCode,@PathVariable(value = "type" ,required = false) Integer type) {
        Integer newType = null;
        if (type==2){
            newType=null;
        }else {
            newType=type;
        }
        List<Menu> rs = menuService.findMenuByCondition(null,applicationCode,newType);
        BaseVo baseVo = new BaseVo();
        baseVo.setData(rs);
        return baseVo;
    }

    /**
     * 查询所有menu  返回树
     * @param applicationCode  web 和app 端
     * @return
     */
    @GetMapping("/findMenuTree/{applicationCode}")
    public BaseVo findMenuTree(@PathVariable(value = "applicationCode") String applicationCode){
        List<Menu> rs = menuService.findMenuTree(applicationCode);
        BaseVo baseVo = new BaseVo();
        baseVo.setData(rs);
        return baseVo;
    }
     /**
     * 根据menuCode 查询统计
     * @param menuCode
     * @return
     */
    @GetMapping("/findCountByMenuCode/{menuCode}")
    public BaseVo findCountByMenuCode(@PathVariable(value = "menuCode") String menuCode) {
        int menuCodeCount = menuService.findCountByMenuCode(menuCode);
        BaseVo baseVo = new BaseVo();
        baseVo.setData(menuCodeCount);
        return baseVo;
    }


}
