package com.bit.sc.module.sys.controller;

import com.bit.base.vo.BaseVo;
import com.bit.sc.module.sys.pojo.InterfacePermission;
import com.bit.sc.module.sys.pojo.Role;
import com.bit.sc.module.sys.service.InterfacePermissionService;
import com.bit.sc.module.sys.service.RoleService;
import com.bit.sc.module.sys.vo.InterfacePermissionVO;
import com.bit.sc.module.sys.vo.RoleVO;
import com.bit.sc.module.user.service.UserService;
import com.bit.utils.CacheUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static com.bit.sc.utils.ImportExcel.importExcel;

/**
 * InterfacePermission的相关请求
 * @author zhanghaodong
 */
@RestController
@RequestMapping(value = "/interfacePermission")
public class InterfacePermissionController {
    private static final Logger logger = LoggerFactory.getLogger(InterfacePermissionController.class);
    @Autowired
    private InterfacePermissionService interfacePermissionService;
    @Autowired
    private UserService userService;
    @Autowired
    private RoleService roleService;
    @Autowired
    private CacheUtil cacheUtil;

    /**
     * 分页查询InterfacePermission列表
     */
    @PostMapping("/listPage")
    public BaseVo listPage(@RequestBody InterfacePermissionVO interfacePermissionVO) {
        //分页对象，前台传递的包含查询的参数

        return interfacePermissionService.findByConditionPage(interfacePermissionVO);
    }

    /**
     * 根据主键ID查询InterfacePermission
     *
     * @param id
     * @return
     */
    @GetMapping("/query/{id}")
    public BaseVo query(@PathVariable(value = "id") Long id) {

        InterfacePermission interfacePermission = interfacePermissionService.findById(id);
        BaseVo baseVo = new BaseVo();
        baseVo.setData(interfacePermission);
        return baseVo;
    }

    /**
     * 新增InterfacePermission
     *
     * @param interfacePermission InterfacePermission实体
     * @return
     */
    @PostMapping("/add")
    public BaseVo add(@Valid @RequestBody InterfacePermission interfacePermission) {
        interfacePermissionService.add(interfacePermission);
        BaseVo baseVo = new BaseVo();
        return baseVo;
    }

    /**
     * 修改InterfacePermission
     *
     * @param interfacePermission InterfacePermission实体
     * @return
     */
    @PostMapping("/modify")
    public BaseVo modify(@Valid @RequestBody InterfacePermission interfacePermission) {
        interfacePermissionService.update(interfacePermission);
        BaseVo baseVo = new BaseVo();
        return baseVo;
    }

    /**
     * 根据主键ID删除InterfacePermission
     *
     * @param id
     * @return
     */
    @GetMapping("/delete/{id}")
    public BaseVo delete(@PathVariable(value = "id") Long id) {
        interfacePermissionService.delete(id);
        BaseVo baseVo = new BaseVo();
        return baseVo;
    }

    /**
     * 根据InterfacePermissionID集合批量删除InterfacePermission
     *
     * @param ids InterfacePermissionID集合
     * @return BaseVo
     */
    @PostMapping("/delBatchByIds")
    public BaseVo delBatchByIds(@RequestBody List<Long> ids) {
        interfacePermissionService.batchDelete(ids);
        BaseVo baseVo = new BaseVo();
        return baseVo;
    }



    @GetMapping("/findPermissionById/{id}")
    public BaseVo findPermissionById(@PathVariable(value = "id") Long id) {

       List <InterfacePermission>interfacePermission = interfacePermissionService.findPermissionById(id);
        BaseVo baseVo = new BaseVo();
        baseVo.setData(interfacePermission);
        return baseVo;
    }

    /**
     * 查询所有
     * @return
     */
    @PostMapping("/findAll")
    public  BaseVo findAll(){
        List<InterfacePermission> all = interfacePermissionService.findAll(null);
        BaseVo baseVo = new BaseVo();
        baseVo.setData(all);
        return baseVo;
    }

    /**
     * 导入execl---接口
     * @param multipartFile
     * @throws Exception
     */
    @PostMapping("/upload")
    public BaseVo upload(@RequestParam(value = "file") MultipartFile multipartFile) throws Exception {
        String[] keys={"url"};
        //map  是自己上面定义的key    value是在excel 里面的值  对应的
        //只导入sheet  1页
        List<Map<String, Object>> maps = importExcel(multipartFile.getInputStream(), keys);
        List<InterfacePermission> list = new ArrayList<InterfacePermission>();
        for (Map<String, Object> map : maps) {
        	InterfacePermission interfacePermission = new InterfacePermission();
			interfacePermission.setUrl((String)map.get("url"));
			list.add(interfacePermission);
		}
        interfacePermissionService.batchAdd(list);
        System.out.println("导入成功");
        BaseVo baseVo = new BaseVo();
        baseVo.setData(list);
        return baseVo;
    }
}